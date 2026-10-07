package keml.diagram;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.sirius.components.collaborative.diagrams.DiagramContext;
import org.eclipse.sirius.components.core.URLParser;
import org.eclipse.sirius.components.core.api.IIdentityService;
import org.eclipse.sirius.components.core.services.ComposedIdentityService;
import org.eclipse.sirius.components.core.services.ComposedObjectSearchService;
import org.eclipse.sirius.components.diagrams.ArrowStyle;
import org.eclipse.sirius.components.diagrams.CollapsingState;
import org.eclipse.sirius.components.diagrams.Diagram;
import org.eclipse.sirius.components.diagrams.DiagramStyle;
import org.eclipse.sirius.components.diagrams.Edge;
import org.eclipse.sirius.components.diagrams.EdgeStyle;
import org.eclipse.sirius.components.diagrams.EdgeType;
import org.eclipse.sirius.components.diagrams.FreeFormLayoutStrategy;
import org.eclipse.sirius.components.diagrams.LineStyle;
import org.eclipse.sirius.components.diagrams.Node;
import org.eclipse.sirius.components.diagrams.RectangularNodeStyle;
import org.eclipse.sirius.components.diagrams.ViewModifier;
import org.eclipse.sirius.components.diagrams.components.BorderNodePosition;
import org.eclipse.sirius.components.diagrams.layoutdata.DiagramLayoutData;
import org.eclipse.sirius.components.diagrams.layoutdata.HandleLayoutData;
import org.eclipse.sirius.components.diagrams.layoutdata.HandleType;
import org.eclipse.sirius.components.diagrams.layoutdata.NodeLayoutData;
import org.eclipse.sirius.components.diagrams.layoutdata.Position;
import org.eclipse.sirius.components.diagrams.layoutdata.Size;
import org.eclipse.sirius.components.emf.services.EMFKindService;
import org.eclipse.sirius.components.interpreter.AQLInterpreter;
import org.eclipse.sirius.components.view.View;
import org.eclipse.sirius.components.view.diagram.DiagramDescription;
import org.eclipse.sirius.components.view.emf.diagram.DiagramIdProvider;
import org.eclipse.sirius.web.application.editingcontext.EditingContext;
import org.eclipse.sirius.web.application.object.services.DefaultIdentityService;
import org.eclipse.sirius.web.application.object.services.DefaultObjectSearchService;
import org.junit.jupiter.api.Test;

import keml.KemlFactory;
import keml.KemlPackage;

class KemlDiagramPostProcessorTests {

    private final IIdentityService identities = new ComposedIdentityService(List.of(), new DefaultIdentityService(new EMFKindService(new URLParser())));

    private final View view = new KemlViews().create();

    private final DiagramIdProvider diagramIds = new DiagramIdProvider(this.identities);

    @Test
    public void alignsChronologyAndKeepsUserPositionsWhileReflowingDefaultEvents() {
        var factory = KemlFactory.eINSTANCE;
        var conversation = factory.createConversation();
        var author = factory.createAuthor();
        conversation.setAuthor(author);
        var partner = factory.createConversationPartner();
        conversation.getConversationPartners().add(partner);
        var receive = factory.createReceiveMessage();
        receive.setTiming(20);
        receive.setCounterPart(partner);
        var send = factory.createSendMessage();
        send.setTiming(10);
        send.setCounterPart(partner);
        author.getMessages().addAll(List.of(receive, send));
        var fact = factory.createNewInformation();
        receive.getGenerates().add(fact);
        var preknowledge = factory.createPreKnowledge();
        var secondPreknowledge = factory.createPreKnowledge();
        author.getPreknowledge().addAll(List.of(preknowledge, secondPreknowledge));

        var resources = new ResourceSetImpl();
        var resource = new ResourceImpl(URI.createURI("test:/conversation.keml"));
        resource.getContents().add(conversation);
        resources.getResources().add(resource);
        var domain = new AdapterFactoryEditingDomain(new ComposedAdapterFactory(), new BasicCommandStack(), resources);
        var editingContext = new EditingContext("test", domain, Map.of(), List.of(this.view));
        var description = (DiagramDescription) this.view.getDescriptions().getFirst();
        var search = new ComposedObjectSearchService(List.of(), new DefaultObjectSearchService());
        var processor = new KemlDiagramPostProcessor(this.view, this.diagramIds, search, this.identities);
        List<Node> nodes = new ArrayList<>();
        nodes.add(this.node("author", "ParticipantNode", author));
        nodes.add(this.node("partner", "ParticipantNode", partner));
        nodes.add(this.node("authorTail", "LifelineEndNode", author));
        nodes.add(this.node("partnerTail", "LifelineEndNode", partner));
        nodes.add(this.node("sendAuthor", "AuthorEventNode", send));
        nodes.add(this.node("sendPartner", "PartnerEventNode", send));
        nodes.add(this.node("receiveAuthor", "AuthorEventNode", receive));
        nodes.add(this.node("receivePartner", "PartnerEventNode", receive));
        nodes.add(this.node("fact", "NewInformationNode", fact));
        nodes.add(this.node("preknowledge", "PreKnowledgeNode", preknowledge));
        nodes.add(this.node("secondPreknowledge", "PreKnowledgeNode", secondPreknowledge));
        List<Edge> edges = List.of(this.edge("authorLine", "LifelineEdge", "author", "authorTail", author),
                this.edge("partnerLine", "LifelineEdge", "partner", "partnerTail", partner),
                this.edge("send", "SendMessageEdge", "sendAuthor", "sendPartner", send),
                this.edge("receive", "ReceiveMessageEdge", "receivePartner", "receiveAuthor", receive),
                this.edge("generated", "GeneratedInformationEdge", "receiveAuthor", "fact", fact),
                this.edge("used", "UsedInformationEdge", "preknowledge", "sendAuthor", send),
                this.edge("repeated", "RepeatedInformationEdge", "receiveAuthor", "secondPreknowledge", receive));
        Diagram diagram = Diagram.newDiagram("diagram").targetObjectId(this.identities.getId(conversation)).descriptionId(this.diagramIds.getId(description))
                .nodes(nodes).edges(edges).style(DiagramStyle.newDiagramStyle().build()).build();
        assertThat(processor.canHandle(editingContext, new DiagramContext(diagram))).isTrue();
        Diagram initialized = processor.postProcess(editingContext, new DiagramContext(diagram)).orElseThrow();
        var positions = initialized.getLayoutData().nodeLayoutData();
        assertThat(positions.get("preknowledge").size()).isEqualTo(new Size(390, 144));
        assertThat(positions.get("secondPreknowledge").position().x()).isGreaterThanOrEqualTo(
                positions.get("preknowledge").position().x() + positions.get("preknowledge").size().width() + 30);
        assertThat(positions.get("author").position().x()).isGreaterThanOrEqualTo(
                positions.get("secondPreknowledge").position().x() + positions.get("secondPreknowledge").size().width() + 130);
        assertThat(positions.get("sendAuthor").position().y()).isGreaterThan(
                positions.get("preknowledge").position().y() + positions.get("preknowledge").size().height());
        assertThat(positions.get("sendAuthor").position().y()).isEqualTo(positions.get("sendPartner").position().y());
        assertThat(positions.get("sendAuthor").position().y()).isLessThan(positions.get("receiveAuthor").position().y());
        assertThat(positions.get("fact").position().x()).isLessThan(positions.get("author").position().x());
        assertThat(positions.get("fact").movedByUser()).isFalse();
        assertThat(positions.get("author").handleLayoutData()).singleElement().satisfies(handle -> assertThat(handle.handlePosition()).isEqualTo("bottom"));
        assertThat(positions.get("sendAuthor").handleLayoutData()).filteredOn(handle -> handle.edgeId().equals("send"))
                .singleElement().satisfies(handle -> assertThat(handle.handlePosition()).isEqualTo("right"));
        assertThat(positions.get("sendPartner").handleLayoutData()).singleElement().satisfies(handle -> assertThat(handle.handlePosition()).isEqualTo("left"));
        assertThat(processor.postProcess(editingContext, new DiagramContext(initialized))).isEmpty();

        Map<String, NodeLayoutData> customNodes = new HashMap<>(positions);
        NodeLayoutData oldFact = customNodes.get("fact");
        customNodes.put("fact", new NodeLayoutData("fact", new Position(90, 600), new Size(300, 130), true, true,
                oldFact.handleLayoutData(), oldFact.minComputedSize()));
        var oldStore = customNodes.get("preknowledge");
        customNodes.put("preknowledge", new NodeLayoutData("preknowledge", new Position(20, 15), new Size(460, 180), true, true,
                List.of(new HandleLayoutData("used", new Position(115, 0), "top", HandleType.source)), oldStore.minComputedSize()));
        var oldRepeated = customNodes.get("secondPreknowledge");
        customNodes.put("secondPreknowledge", new NodeLayoutData(oldRepeated.id(), oldRepeated.position(), oldRepeated.size(), false, false,
                List.of(new HandleLayoutData("repeated", new Position(390, 40), "right", HandleType.target)), oldRepeated.minComputedSize()));
        Diagram customized = Diagram.newDiagram(initialized).layoutData(new DiagramLayoutData(customNodes, initialized.getLayoutData().edgeLayoutData(), Map.of(), false)).build();
        receive.setTiming(0);
        Diagram reordered = processor.postProcess(editingContext, new DiagramContext(customized)).orElseThrow();
        var reorderedNodes = reordered.getLayoutData().nodeLayoutData();
        assertThat(reorderedNodes.get("fact").position()).isEqualTo(new Position(90, 600));
        assertThat(reorderedNodes.get("fact").size()).isEqualTo(new Size(300, 130));
        assertThat(reorderedNodes.get("preknowledge").position()).isEqualTo(new Position(20, 15));
        assertThat(reorderedNodes.get("preknowledge").size()).isEqualTo(new Size(460, 180));
        assertThat(reorderedNodes.get("preknowledge").handleLayoutData()).containsExactly(
                new HandleLayoutData("used", new Position(115, 0), "top", HandleType.source));
        assertThat(reorderedNodes.get("secondPreknowledge").handleLayoutData()).containsExactly(
                new HandleLayoutData("repeated", new Position(390, 40), "right", HandleType.target));
        assertThat(reorderedNodes.get("receiveAuthor").position().y()).isLessThan(reorderedNodes.get("sendAuthor").position().y());
        assertThat(processor.postProcess(editingContext, new DiagramContext(reordered))).isEmpty();
        var widerDefault = Diagram.newDiagram(reordered).nodes(reordered.getNodes().stream()
                .map(node -> node.getId().equals("secondPreknowledge") ? Node.newNode(node).defaultWidth(500).build() : node).toList()).build();
        var wider = processor.postProcess(editingContext, new DiagramContext(widerDefault)).orElseThrow();
        assertThat(wider.getLayoutData().nodeLayoutData().get("secondPreknowledge").handleLayoutData()).containsExactly(
                new HandleLayoutData("repeated", new Position(500, 40), "right", HandleType.target));
        assertThat(processor.postProcess(editingContext, new DiagramContext(wider))).isEmpty();
        editingContext.dispose();
    }

    private Node node(String id, String description, Object object) {
        var diagram = (DiagramDescription) this.view.getDescriptions().getFirst();
        var nodeDescription = diagram.getNodeDescriptions().stream().filter(candidate -> description.equals(candidate.getName())).findFirst().orElseThrow();
        String descriptionId = this.diagramIds.getId(nodeDescription);
        var interpreter = new AQLInterpreter(List.of(KemlServices.class), List.of(KemlPackage.eINSTANCE));
        int width = interpreter.evaluateExpression(Map.of("self", object), nodeDescription.getDefaultWidthExpression()).asInt().orElseThrow();
        int height = interpreter.evaluateExpression(Map.of("self", object), nodeDescription.getDefaultHeightExpression()).asInt().orElseThrow();
        return Node.newNode(id).type("rectangular").targetObjectId(this.identities.getId(object)).targetObjectKind("keml")
                .targetObjectLabel("").descriptionId(descriptionId).initialBorderNodePosition(BorderNodePosition.NONE)
                .modifiers(Set.of()).state(ViewModifier.Normal).collapsingState(CollapsingState.EXPANDED)
                .borderNodes(List.of()).childNodes(List.of()).customizedStyleProperties(Set.of()).decorators(List.of()).defaultWidth(width).defaultHeight(height)
                .style(RectangularNodeStyle.newRectangularNodeStyle().background("white").borderColor("black").borderStyle(LineStyle.Solid)
                        .childrenLayoutStrategy(new FreeFormLayoutStrategy()).build())
                .build();
    }

    private Edge edge(String id, String description, String source, String target, Object object) {
        var diagram = (DiagramDescription) this.view.getDescriptions().getFirst();
        String descriptionId = diagram.getEdgeDescriptions().stream().filter(candidate -> description.equals(candidate.getName()))
                .findFirst().map(this.diagramIds::getId).orElseThrow();
        return Edge.newEdge(id).type("edge").targetObjectId(this.identities.getId(object)).targetObjectKind("keml")
                .targetObjectLabel("").descriptionId(descriptionId).sourceId(source).targetId(target)
                .modifiers(Set.of()).state(ViewModifier.Normal).customizedStyleProperties(Set.of())
                .style(EdgeStyle.newEdgeStyle().color("black").lineStyle(LineStyle.Solid).sourceArrow(ArrowStyle.None)
                        .targetArrow(ArrowStyle.InputArrow).edgeType(EdgeType.Oblique).build()).build();
    }
}
