package keml.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Optional;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipInputStream;

import org.eclipse.sirius.components.collaborative.api.ChangeDescription;
import org.eclipse.sirius.components.collaborative.api.IEditingContextEventProcessorRegistry;
import org.eclipse.sirius.components.core.api.IEditingContext;
import org.eclipse.sirius.components.core.api.IEditingContextSearchService;
import org.eclipse.sirius.components.core.api.IObjectSearchService;
import org.eclipse.sirius.components.core.api.IPayload;
import org.eclipse.sirius.components.palette.dto.Palette;
import org.eclipse.sirius.components.collaborative.trees.dto.palette.SingleClickTreeItemTool;
import org.eclipse.sirius.components.trees.Tree;
import org.eclipse.sirius.components.trees.TreeItem;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import keml.KemlFactory;
import keml.analysis.AnalysisProvider;
import reactor.core.publisher.Sinks;

class KemlAnalysisTests {
    @Test
    void contributesAnalysisToTheExplorerPaletteWithoutReplacingItsTools() {
        var context = mock(IEditingContext.class);
        var objects = mock(IObjectSearchService.class);
        when(objects.getObject(context, "conversation")).thenReturn(Optional.of(KemlFactory.eINSTANCE.createConversation()));
        when(objects.getObject(context, "participant")).thenReturn(Optional.of(KemlFactory.eINSTANCE.createConversationPartner()));
        var tree = mock(Tree.class);
        when(tree.getId()).thenReturn("explorer://");
        var item = mock(TreeItem.class);
        when(item.getId()).thenReturn("conversation");
        var provider = new KemlAnalysisMenuProvider(objects);
        assertThat(provider.canHandle(context, null, tree, item)).isTrue();
        var existing = new SingleClickTreeItemTool("new-object", "New object", List.of(), false, List.of());
        var palette = provider.customize(context, null, tree, item, new Palette("palette", List.of(existing), List.of(existing)));
        assertThat(palette.quickAccessTools()).containsExactly(existing);
        assertThat(palette.paletteEntries()).hasSize(2).contains(existing);
        assertThat(((SingleClickTreeItemTool) palette.paletteEntries().getFirst()).id()).isEqualTo("keml-analyse-conversation");
        when(item.getId()).thenReturn("participant");
        assertThat(provider.canHandle(context, null, tree, item)).isFalse();
    }

    @Test
    void downloadsTheDisplayedSnapshotAndRejectsOtherConversations() throws Exception {
        var factory = KemlFactory.eINSTANCE;
        var conversation = factory.createConversation();
        conversation.setTitle("Test conversation");
        conversation.setAuthor(factory.createAuthor());
        var partner = factory.createConversationPartner();
        partner.setName("LLM");
        conversation.getConversationPartners().add(partner);
        var received = factory.createReceiveMessage();
        received.setCounterPart(partner);
        conversation.getAuthor().getMessages().add(received);

        var objects = mock(IObjectSearchService.class);
        when(objects.getObject(any(), eq("conversation"))).thenReturn(Optional.of(conversation));
        var handler = new KemlAnalysisSnapshotHandler(objects);
        var processors = mock(IEditingContextEventProcessorRegistry.class);
        when(processors.dispatchEvent(eq("context"), any())).thenAnswer(invocation -> {
            Sinks.One<IPayload> sink = Sinks.one();
            Sinks.Many<ChangeDescription> changes = Sinks.many().multicast().directBestEffort();
            handler.handle(sink, changes, mock(IEditingContext.class), invocation.getArgument(1));
            return sink.asMono();
        });
        var contexts = mock(IEditingContextSearchService.class);
        when(contexts.existsById("context")).thenReturn(true);
        var controller = new KemlAnalysisController(processors, contexts, new AnalysisProvider());
        var response = controller.analyse("context", "conversation").getBody();
        assertThat(response.results().overview().getFirst().sent()).isEqualTo(1);

        // Editing after analysis must not change the reports for the displayed snapshot.
        var next = factory.createReceiveMessage();
        next.setCounterPart(partner);
        conversation.getAuthor().getMessages().add(next);
        var archive = controller.snapshotReports("context", "conversation", response.snapshotId());
        assertThat(archive.getHeaders().getContentType().toString()).isEqualTo("application/zip");
        var files = new HashMap<String, byte[]>();
        try (var zip = new ZipInputStream(new ByteArrayInputStream(archive.getBody()))) {
            for (var entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                files.put(entry.getName(), zip.readAllBytes());
            }
        }
        assertThat(files).hasSize(11);
        assertThat(new String(files.get("conversation-general.csv"), StandardCharsets.UTF_8)).contains("ReceiveMsg,1");
        assertThat(controller.analyse("context", "conversation").getBody().results().overview().getFirst().sent()).isEqualTo(2);
        assertThatThrownBy(() -> controller.snapshotReports("context", "other", response.snapshotId()))
                .isInstanceOfSatisfying(ResponseStatusException.class, error -> assertThat(error.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND));
        assertThatThrownBy(() -> controller.snapshotReports("context", "conversation", UUID.randomUUID()))
                .isInstanceOfSatisfying(ResponseStatusException.class, error -> assertThat(error.getStatusCode()).isEqualTo(HttpStatus.GONE));
        assertThatThrownBy(() -> controller.analyse("missing", "conversation"))
                .isInstanceOfSatisfying(ResponseStatusException.class, error -> assertThat(error.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND));
        assertThatThrownBy(() -> controller.analyse("context", "other"))
                .isInstanceOfSatisfying(ResponseStatusException.class, error -> assertThat(error.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND));
        // The browser needs a structured error with a recovery action, not the SPA's HTML error page.
        var mvc = MockMvcBuilders.standaloneSetup(controller).build();
        mvc.perform(get("/api/editingcontexts/context/keml/conversations/conversation/analysis/" + UUID.randomUUID() + "/reports"))
                .andExpect(status().isGone()).andExpect(jsonPath("$.detail", containsString("Recalculate")));
        mvc.perform(get("/api/editingcontexts/context/keml/conversations/conversation/analysis/invalid/reports"))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.detail").value("Invalid analysis identifier."));
    }
}
