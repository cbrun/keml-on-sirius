package keml.analysis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import keml.InformationLinkType;
import keml.KemlFactory;

class AnalysisProviderTests {
    @TempDir
    Path directory;

    @Test
    void exportsStatisticsAndTrustScenariosWithoutChangingTheEditedConversation() throws Exception {
        var factory = KemlFactory.eINSTANCE;
        var conversation = factory.createConversation();
        conversation.setAuthor(factory.createAuthor());
        var partner = factory.createConversationPartner();
        partner.setName("LLM");
        conversation.getConversationPartners().add(partner);
        var received = factory.createReceiveMessage();
        received.setCounterPart(partner);
        conversation.getAuthor().getMessages().add(received);
        var knowledge = factory.createNewInformation();
        knowledge.setMessage("Partner's claim");
        knowledge.setInitialTrust(0.3F);
        knowledge.setCurrentTrust(0.4F);
        received.getGenerates().add(knowledge);
        var preknowledge = factory.createPreKnowledge();
        preknowledge.setMessage("Author's prior knowledge");
        preknowledge.setInitialTrust(0.7F);
        preknowledge.setCurrentTrust(0.8F);
        conversation.getAuthor().getPreknowledge().add(preknowledge);
        var attack = factory.createInformationLink();
        attack.setType(InformationLinkType.ATTACK);
        attack.setTarget(knowledge);
        preknowledge.getCauses().add(attack);

        var results = new AnalysisProvider().analyse(conversation);
        assertThat(results.overview().getFirst().participant()).isEqualTo("LLM");
        assertThat(results.overview().getFirst().sent()).isEqualTo(1);
        assertThat(results.overview().getLast().received()).isEqualTo(1);
        assertThat(results.arguments()[2][0]).isEqualTo("1/0");
        assertThat(results.trustScenarios()).hasSize(36);
        var preview = results.trustScenarios().stream().filter(s -> s.weight() == 2 && s.preset().equals("b")).findFirst().orElseThrow();
        assertThat(preview.scores().getLast().computed()).isEqualTo(-0.5F);

        new AnalysisProvider().writeReports(conversation, directory);

        try (var reports = Files.list(directory)) {
            assertThat(reports.toList()).hasSize(11);
        }
        assertThat(Files.readString(directory.resolve("conversation-general.csv"))).contains("ReceiveMsg,1", "Facts,1,1");
        assertThat(Files.readString(directory.resolve("conversation-arguments.csv"))).contains("Attacks/Supports", "Author F,1/0");
        try (var input = Files.newInputStream(directory.resolve("conversation-w2--trust.xlsx"));
                var workbook = new XSSFWorkbook(input)) {
            var sheet = workbook.getSheet("Trust");
            assertThat(sheet.getRow(0).getCell(6).getStringCellValue()).isEqualTo("a");
            assertThat(sheet.getRow(0).getCell(12).getStringCellValue()).isEqualTo("d");
            assertThat(sheet.getRow(3).getCell(9).getNumericCellValue()).isEqualTo(-0.5);
        }
        assertThat(knowledge.getInitialTrust()).isEqualTo(0.3F);
        assertThat(knowledge.getCurrentTrust()).isEqualTo(0.4F);
        assertThat(preknowledge.getInitialTrust()).isEqualTo(0.7F);
        assertThat(preknowledge.getCurrentTrust()).isEqualTo(0.8F);

        var duplicate = factory.createConversationPartner();
        duplicate.setName("LLM");
        conversation.getConversationPartners().add(duplicate);
        assertThatThrownBy(() -> new AnalysisProvider().writeReports(conversation, directory.resolve("duplicate")))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("distinct");
        conversation.getConversationPartners().remove(duplicate);

        var cycle = factory.createInformationLink();
        cycle.setTarget(preknowledge);
        knowledge.getCauses().add(cycle);
        assertThatThrownBy(() -> new AnalysisProvider().analyse(conversation)).hasMessageContaining("argumentation graph");
        assertThatThrownBy(() -> new AnalysisProvider().writeReports(conversation, directory.resolve("cycle")))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("argumentation graph");
        assertThat(knowledge.getCurrentTrust()).isEqualTo(0.4F);
        assertThat(preknowledge.getCurrentTrust()).isEqualTo(0.8F);
    }

    @Test
    void handlesPreknowledgeWithoutReceivedMessagesAndAnEmptyConversation() throws Exception {
        var factory = KemlFactory.eINSTANCE;
        var conversation = factory.createConversation();
        conversation.setAuthor(factory.createAuthor());
        new AnalysisProvider().writeReports(conversation, directory.resolve("empty"));
        var preknowledge = factory.createPreKnowledge();
        preknowledge.setMessage("Prior knowledge");
        conversation.getAuthor().getPreknowledge().add(preknowledge);
        new AnalysisProvider().writeReports(conversation, directory.resolve("prior"));
        try (var input = Files.newInputStream(directory.resolve("prior/conversation-w2--trust.xlsx"));
                var workbook = new XSSFWorkbook(input)) {
            assertThat(workbook.getSheet("Trust").getRow(2).getCell(7).getNumericCellValue()).isEqualTo(1.0);
        }
    }
}
