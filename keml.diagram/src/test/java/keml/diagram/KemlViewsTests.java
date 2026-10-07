package keml.diagram;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.components.interpreter.AQLInterpreter;
import org.eclipse.sirius.components.interpreter.Result;
import org.eclipse.sirius.components.interpreter.Status;
import org.eclipse.sirius.components.view.diagram.DiagramDescription;
import org.eclipse.sirius.components.view.diagram.ImageNodeStyleDescription;
import org.eclipse.sirius.components.view.ChangeContext;
import org.eclipse.sirius.components.view.diagram.NodeTool;
import org.eclipse.sirius.components.view.diagram.SelectionDialogDescription;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import keml.KemlPackage;
import keml.KemlFactory;
import keml.LifeLine;
import keml.Message;
import keml.ReceiveMessage;
import keml.SendMessage;

class KemlViewsTests {
    private final KemlServices services = new KemlServices();
    private final AQLInterpreter interpreter = new AQLInterpreter(List.of(KemlServices.class), List.of(KemlPackage.eINSTANCE));

    @Test
    public void creationToolsAndModelLabelsUsePackagedSvgIcons() {
        var diagram = (DiagramDescription) new KemlViews().create().getDescriptions().getFirst();
        diagram.eAllContents().forEachRemaining(element -> {
            if (element instanceof NodeTool || element instanceof org.eclipse.sirius.components.view.diagram.EdgeTool) {
                String path = (String) element.eGet(element.eClass().getEStructuralFeature("iconURLsExpression"));
                assertThat(path).as("Icon for %s", element.eGet(element.eClass().getEStructuralFeature("name"))).endsWith(".svg");
                assertThat(this.getClass().getResource(path)).as("Packaged palette icon %s", path).isNotNull();
            }
        });
        var adapters = new keml.provider.KemlItemProviderAdapterFactory();
        try {
            for (var classifier : KemlPackage.eINSTANCE.getEClassifiers()) {
                if (classifier instanceof org.eclipse.emf.ecore.EClass type && !type.isAbstract()) {
                    var object = KemlFactory.eINSTANCE.create(type);
                    var labels = (org.eclipse.emf.edit.provider.IItemLabelProvider) adapters.adapt(object, org.eclipse.emf.edit.provider.IItemLabelProvider.class);
                    assertThat(labels.getImage(object)).as("SVG for %s", type.getName()).isInstanceOf(java.net.URL.class);
                    assertThat(labels.getImage(object).toString()).endsWith(".svg");
                }
            }
            var instruction = KemlFactory.eINSTANCE.createNewInformation();
            instruction.setIsInstruction(true);
            var labels = (org.eclipse.emf.edit.provider.IItemLabelProvider) adapters.adapt(instruction, org.eclipse.emf.edit.provider.IItemLabelProvider.class);
            assertThat(labels.getImage(instruction).toString()).endsWith("isInstruction.svg");
            var link = KemlFactory.eINSTANCE.createInformationLink();
            labels = (org.eclipse.emf.edit.provider.IItemLabelProvider) adapters.adapt(link, org.eclipse.emf.edit.provider.IItemLabelProvider.class);
            for (var type : keml.InformationLinkType.VALUES) {
                link.setType(type);
                assertThat(labels.getImage(link).toString()).endsWith(type.getName() + ".svg");
            }
        } finally {
            adapters.dispose();
        }
    }

    @Test
    @DisplayName("Given an example conversation, when the view is evaluated, then its semantic expressions resolve")
    public void givenExampleWhenViewIsEvaluatedThenSemanticExpressionsResolve() {
        var conversation = this.services.kemlExampleConversation();
        assertThat(Diagnostician.INSTANCE.validate(conversation).getSeverity()).isLessThan(Diagnostic.ERROR);
        var diagram = (DiagramDescription) new KemlViews().create().getDescriptions().getFirst();
        assertThat(EcoreUtil.getURI(diagram)).isEqualTo(EcoreUtil.getURI(new KemlViews().create().getDescriptions().getFirst()));
        assertThat(this.services.kemlMessages(conversation)).hasSize(8);
        assertThat(diagram.getDomainType()).isEqualTo("keml::Conversation");
        assertThat(diagram.getToolbar()).isNotNull();
        assertThat(diagram.getToolbar().isExpandedByDefault()).isTrue();
        var template = new KemlProjectTemplateProvider().getProjectTemplates().getFirst();
        assertThat(template.id()).isEqualTo(KemlProjectTemplateProvider.TEMPLATE_ID);
        assertThat(this.getClass().getResource(template.imageURL())).as("Packaged project-template thumbnail").isNotNull();
        this.evaluate(conversation, diagram.getTitleExpression());
        for (var node : diagram.getNodeDescriptions()) {
            if (node.getStyle() instanceof ImageNodeStyleDescription image) {
                assertThat(this.getClass().getResource(image.getShape())).as("Packaged SVG %s", image.getShape()).isNotNull();
                assertThat(new KemlDiagramConfiguration().kemlImagePaths().getPaths()).anyMatch(image.getShape()::startsWith);
            }
            var candidates = this.evaluate(conversation, node.getSemanticCandidatesExpression()).asObjects().orElseThrow();
            assertThat(candidates).isNotEmpty();
            for (var candidate : candidates) {
                if (node.getPreconditionExpression() != null && !node.getPreconditionExpression().isBlank()) {
                    this.evaluate(candidate, node.getPreconditionExpression());
                }
                assertThat(this.evaluate(candidate, node.getDefaultWidthExpression()).asInt()).isPresent();
                assertThat(this.evaluate(candidate, node.getDefaultHeightExpression()).asInt()).isPresent();
                if (node.getInsideLabel() != null) {
                    this.evaluate(candidate, node.getInsideLabel().getLabelExpression());
                }
                node.getOutsideLabels().forEach(label -> this.evaluate(candidate, label.getLabelExpression()));
                node.getConditionalStyles().forEach(style -> this.evaluate(candidate, style.getCondition()));
            }
        }
        for (var edge : diagram.getEdgeDescriptions()) {
            for (var candidate : this.evaluate(conversation, edge.getSemanticCandidatesExpression()).asObjects().orElseThrow()) {
                boolean selected = edge.getPreconditionExpression() == null || edge.getPreconditionExpression().isBlank()
                        || this.evaluate(candidate, edge.getPreconditionExpression()).asBoolean().orElseThrow();
                if (selected) {
                    assertThat(this.evaluate(candidate, edge.getSourceExpression()).asObjects()).isPresent();
                    assertThat(this.evaluate(candidate, edge.getTargetExpression()).asObjects()).isPresent();
                    this.evaluate(candidate, edge.getCenterLabelExpression());
                }
            }
        }
    }

    @Test
    public void distinguishesAuthorAndGivesPreknowledgeLargerDefaultDimensions() {
        var conversation = this.services.kemlExampleConversation();
        var diagram = (DiagramDescription) new KemlViews().create().getDescriptions().getFirst();
        var participant = diagram.getNodeDescriptions().stream().filter(node -> "ParticipantNode".equals(node.getName())).findFirst().orElseThrow();
        var label = participant.getOutsideLabels().getFirst();
        var authorLabel = label.getConditionalStyles().getFirst();
        var lifeline = diagram.getEdgeDescriptions().stream().filter(edge -> "LifelineEdge".equals(edge.getName())).findFirst().orElseThrow();
        var authorLine = lifeline.getConditionalStyles().getFirst();
        assertThat(label.getStyle().isBold()).isFalse();
        assertThat(authorLabel.getStyle().isBold()).isTrue();
        assertThat(lifeline.getStyle().getEdgeWidth()).isEqualTo(1);
        assertThat(authorLine.getEdgeWidth()).isEqualTo(2);
        assertThat(authorLine.getColor().getName()).isEqualTo("Ink");
        for (var actor : this.services.kemlLifeLines(conversation)) {
            boolean author = actor == conversation.getAuthor();
            assertThat(this.evaluate(actor, authorLabel.getCondition()).asBoolean()).contains(author);
            assertThat(this.evaluate(actor, authorLine.getCondition()).asBoolean()).contains(author);
        }
        var preknowledge = this.services.kemlAddPreKnowledge(conversation);
        var knowledge = KemlFactory.eINSTANCE.createNewInformation();
        knowledge.setMessage(preknowledge.getMessage());
        var store = diagram.getNodeDescriptions().stream().filter(node -> "PreKnowledgeNode".equals(node.getName())).findFirst().orElseThrow();
        assertThat(this.evaluate(preknowledge, store.getDefaultWidthExpression()).asInt().orElseThrow()).isEqualTo(390);
        assertThat(this.evaluate(preknowledge, store.getDefaultHeightExpression()).asInt().orElseThrow()).isEqualTo(this.services.kemlKnowledgeHeight(knowledge) * 3 / 2);
    }

    @Test
    @DisplayName("Given any participant, send and receive tools use that participant's perspective")
    public void givenParticipantWhenMessageToolsExecuteThenDirectionMatchesSelectedParticipant() {
        var conversation = this.services.kemlExampleConversation();
        var diagram = (DiagramDescription) new KemlViews().create().getDescriptions().getFirst();
        var participant = diagram.getNodeDescriptions().stream().filter(node -> "ParticipantNode".equals(node.getName())).findFirst().orElseThrow();
        var browser = conversation.getConversationPartners().get(1);
        for (var actor : this.services.kemlLifeLines(conversation)) {
            LifeLine peer = actor == conversation.getAuthor() ? browser : conversation.getAuthor();
            for (boolean sends : List.of(true, false)) {
                String name = actor == conversation.getAuthor() ? sends ? "Send message" : "Receive message"
                        : sends ? "Send to Author" : "Receive from Author";
                List<NodeTool> tools = participant.getPalette().getNodeTools().stream().filter(tool -> name.equals(tool.getName()))
                        .filter(tool -> this.evaluate(actor, tool.getPreconditionExpression()).asBoolean().orElseThrow()).toList();
                assertThat(tools).hasSize(1);
                var tool = tools.getFirst();
                assertThat(tool.getDialogDescription() != null).isEqualTo(actor == conversation.getAuthor());
                var body = (ChangeContext) tool.getBody().getFirst();
                var result = this.interpreter.evaluateExpression(Map.of("self", actor, "selectedObject", peer), body.getExpression());
                assertThat(result.getStatus()).as(result.toString()).isEqualTo(Status.OK);
                var message = (Message) result.asObjects().orElseThrow().getFirst();
                boolean authorSends = (actor == conversation.getAuthor()) == sends;
                assertThat(message instanceof SendMessage).isEqualTo(authorSends);
                assertThat(message.getCounterPart()).isSameAs(actor == conversation.getAuthor() ? peer : actor);
                assertThat(message.eContainer()).isSameAs(conversation.getAuthor());
                assertThat(message.getTiming()).isEqualTo(conversation.getAuthor().getMessages().size() - 1);
                var reply = this.services.kemlReply(message);
                assertThat(reply instanceof SendMessage).isEqualTo(!authorSends);
                assertThat(reply.getCounterPart()).isSameAs(message.getCounterPart());
                assertThat(reply.getTiming()).isGreaterThan(message.getTiming());
            }
        }
        for (var tool : diagram.getPalette().getNodeTools()) {
            if (tool.getDialogDescription() instanceof SelectionDialogDescription dialog) {
                assertThat(this.evaluate(conversation, dialog.getSelectionDialogTreeDescription().getElementsExpression()).asObjects().orElseThrow())
                        .containsExactlyElementsOf(conversation.getConversationPartners());
                assertThat(dialog.isMultiple()).isFalse();
                assertThat(dialog.isOptional()).isFalse();
            }
        }
        int count = conversation.getAuthor().getMessages().size();
        assertThatThrownBy(() -> this.services.kemlSend(conversation)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> this.services.kemlReceive(conversation.getAuthor())).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> this.services.kemlSendFrom(browser, browser)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> this.services.kemlSendFrom(browser, conversation.getConversationPartners().getFirst())).isInstanceOf(IllegalArgumentException.class);
        var foreign = this.services.kemlExampleConversation().getConversationPartners().getFirst();
        assertThatThrownBy(() -> this.services.kemlSendFrom(browser, foreign)).isInstanceOf(IllegalArgumentException.class);
        assertThat(conversation.getAuthor().getMessages()).hasSize(count);
        assertThat(this.services.kemlMessagePeers(KemlFactory.eINSTANCE.createConversation())).isEmpty();
        assertThat(Diagnostician.INSTANCE.validate(conversation).getSeverity()).isLessThan(Diagnostic.ERROR);
    }

    @Test
    @DisplayName("Given decorated long labels, when direct edit starts, then the full original value is edited")
    public void givenDecoratedLongLabelsWhenDirectEditStartsThenOriginalValueIsEdited() {
        var conversation = this.services.kemlExampleConversation();
        var diagram = (DiagramDescription) new KemlViews().create().getDescriptions().getFirst();
        var received = (ReceiveMessage) conversation.getAuthor().getMessages().get(1);
        var information = received.getGenerates().getFirst();
        information.setMessage("A long piece of knowledge. ".repeat(20));
        var knowledgeNode = diagram.getNodeDescriptions().stream().filter(node -> "NewInformationNode".equals(node.getName())).findFirst().orElseThrow();
        String initial = knowledgeNode.getPalette().getLabelEditTool().getInitialDirectEditLabelExpression();
        assertThat(this.evaluate(information, initial).asString()).contains(information.getMessage());
        assertThat(this.services.kemlInformationLabel(information)).doesNotContain(information.getMessage());
        var send = diagram.getEdgeDescriptions().stream().filter(edge -> "SendMessageEdge".equals(edge.getName())).findFirst().orElseThrow();
        initial = send.getPalette().getCenterLabelEditTool().getInitialDirectEditLabelExpression();
        var message = conversation.getAuthor().getMessages().getFirst();
        assertThat(this.evaluate(message, initial).asString()).contains(message.getContent());
    }

    @Test
    @DisplayName("Given knowledge references, when they are edited, then messages survive and cross-conversation links are rejected")
    public void givenKnowledgeReferencesWhenEditedThenMessagesSurviveAndCrossConversationLinksAreRejected() {
        var conversation = this.services.kemlExampleConversation();
        var sent = (SendMessage) conversation.getAuthor().getMessages().get(0);
        var received = (ReceiveMessage) conversation.getAuthor().getMessages().get(1);
        var knowledge = conversation.getAuthor().getPreknowledge().getFirst();
        this.services.kemlUseInformation(knowledge, sent);
        assertThat(sent.getUses()).containsExactly(knowledge);
        this.services.kemlForgetUse(knowledge, sent);
        assertThat(sent.getUses()).isEmpty();
        assertThat(conversation.getAuthor().getMessages()).contains(sent);

        this.services.kemlRepeatInformation(received, knowledge);
        assertThat(knowledge.getRepeatedBy()).contains(received);
        this.services.kemlForgetRepeat(knowledge, received);
        assertThat(knowledge.getRepeatedBy()).doesNotContain(received);

        var fact = this.services.kemlAddInformation(received);
        assertThat(fact.getSource()).isSameAs(received);
        var link = this.services.kemlLinkTo(fact, knowledge, "SUPPORT");
        assertThat(link.getSource()).isSameAs(fact);
        assertThat(knowledge.getTargetedBy()).contains(link);
        assertThat(this.services.kemlLinkTo(fact, knowledge, "SUPPORT")).isSameAs(link);
        var foreignKnowledge = this.services.kemlExampleConversation().getAuthor().getPreknowledge().getFirst();
        assertThatThrownBy(() -> this.services.kemlLinkTo(fact, foreignKnowledge, "ATTACK")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> this.services.kemlUseInformation(knowledge, received)).isInstanceOf(IllegalArgumentException.class);

        this.services.kemlDeletePartner(received.getCounterPart());
        var diagnostic = Diagnostician.INSTANCE.validate(conversation);
        assertThat(diagnostic.getSeverity()).as(diagnostic.toString()).isLessThan(Diagnostic.ERROR);
    }

    private Result evaluate(Object self, String expression) {
        Result result = this.interpreter.evaluateExpression(Map.of("self", self), expression);
        assertThat(result.getStatus()).as("%s on %s: %s", expression, self, result).isEqualTo(Status.OK);
        return result;
    }
}
