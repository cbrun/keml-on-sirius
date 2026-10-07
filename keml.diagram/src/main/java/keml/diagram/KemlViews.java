package keml.diagram;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.components.emf.ResourceMetadataAdapter;
import org.eclipse.sirius.components.emf.services.IDAdapter;
import org.eclipse.sirius.components.emf.services.JSONResourceFactory;
import org.eclipse.sirius.components.view.FixedColor;
import org.eclipse.sirius.components.view.View;
import org.eclipse.sirius.components.view.builder.generated.diagram.DiagramBuilders;
import org.eclipse.sirius.components.view.builder.generated.view.ViewBuilders;
import org.eclipse.sirius.components.view.diagram.ArrowStyle;
import org.eclipse.sirius.components.view.diagram.DeleteTool;
import org.eclipse.sirius.components.view.diagram.DiagramDescription;
import org.eclipse.sirius.components.view.diagram.DiagramLayoutOption;
import org.eclipse.sirius.components.view.diagram.EdgeDescription;
import org.eclipse.sirius.components.view.diagram.EdgeStyle;
import org.eclipse.sirius.components.view.diagram.EdgeTool;
import org.eclipse.sirius.components.view.diagram.EdgeType;
import org.eclipse.sirius.components.view.diagram.InsideLabelDescription;
import org.eclipse.sirius.components.view.diagram.InsideLabelPosition;
import org.eclipse.sirius.components.view.diagram.LabelEditTool;
import org.eclipse.sirius.components.view.diagram.LineStyle;
import org.eclipse.sirius.components.view.diagram.NodeDescription;
import org.eclipse.sirius.components.view.diagram.NodeTool;
import org.eclipse.sirius.components.view.diagram.OutsideLabelPosition;
import org.eclipse.sirius.components.view.diagram.UserResizableDirection;

import keml.InformationLinkType;

/** Builds a native Sirius Web conversation timeline and knowledge graph. */
public class KemlViews {
    public static final String DIAGRAM_NAME = "KemlConversationDiagram";

    private final DiagramBuilders diagrams = new DiagramBuilders();
    private final ViewBuilders views = new ViewBuilders();
    private final List<FixedColor> colors = new ArrayList<>();
    private final FixedColor black = this.color("Ink", "#202124");
    private final FixedColor grey = this.color("LifelineGrey", "#9b9b9b");
    private final FixedColor white = this.color("White", "#ffffff");
    private final FixedColor yellow = this.color("KnowledgeYellow", "#ffffa0");
    private final FixedColor cyan = this.color("InstructionCyan", "#ccffff");
    private final FixedColor green = this.color("SupportGreen", "#168a32");
    private final FixedColor red = this.color("AttackRed", "#db3030");

    public View create() {
        View view = this.views.newView().descriptions(this.conversationDiagram())
                .colorPalettes(this.views.newColorPalette().name("KemlColors")
                        .colors(this.colors.toArray(FixedColor[]::new)).build()).build();
        String name = "KEML conversation view";
        String path = UUID.nameUUIDFromBytes(name.getBytes(StandardCharsets.UTF_8)).toString();
        Resource resource = new JSONResourceFactory().createResourceFromPath(path);
        resource.eAdapters().add(new ResourceMetadataAdapter(name));
        view.eAllContents().forEachRemaining(object -> object.eAdapters().add(new IDAdapter(
                UUID.nameUUIDFromBytes((name + "/" + EcoreUtil.getRelativeURIFragmentPath(view, object)).getBytes(StandardCharsets.UTF_8)))));
        view.eAdapters().add(new IDAdapter(UUID.nameUUIDFromBytes(name.getBytes(StandardCharsets.UTF_8))));
        resource.getContents().add(view);
        Diagnostic diagnostic = Diagnostician.INSTANCE.validate(view);
        if (diagnostic.getSeverity() == Diagnostic.ERROR) {
            throw new IllegalStateException("Invalid KEML view: " + diagnostic);
        }
        return view;
    }

    private FixedColor color(String name, String value) {
        FixedColor color = this.views.newFixedColor().name(name).value(value).build();
        this.colors.add(color);
        return color;
    }

    private DiagramDescription conversationDiagram() {
        NodeDescription participant = this.diagrams.newNodeDescription().name("ParticipantNode").domainType("keml::LifeLine")
                .semanticCandidatesExpression("aql:self.kemlLifeLines()")
                .defaultWidthExpression("80").defaultHeightExpression("100").keepAspectRatio(true)
                .userResizable(UserResizableDirection.NONE)
                .style(this.diagrams.newImageNodeStyleDescription().shape("/images/keml/actor.svg").borderSize(0).borderColor(this.white).build())
                .outsideLabels(this.diagrams.newOutsideLabelDescription().position(OutsideLabelPosition.BOTTOM_CENTER)
                        .labelExpression("aql:self.kemlParticipantLabel()")
                        .style(this.diagrams.newOutsideLabelStyle().fontSize(16).labelColor(this.black).borderColor(this.white).borderSize(0)
                                .showIconExpression("false").maxWidthExpression("220").build())
                        .conditionalStyles(this.diagrams.newConditionalOutsideLabelStyle().condition("aql:self.oclIsKindOf(keml::Author)")
                                .style(this.diagrams.newOutsideLabelStyle().fontSize(16).bold(true).labelColor(this.black).borderColor(this.white).borderSize(0)
                                        .showIconExpression("false").maxWidthExpression("220").build()).build()).build())
                .palette(this.diagrams.newNodePalette().labelEditTool(this.editLabel("name"))
                        .deleteTool(this.diagrams.newDeleteTool().name("DeletePartnerAndMessages")
                                .preconditionExpression("aql:self.oclIsKindOf(keml::ConversationPartner)")
                                .body(this.views.newChangeContext().expression("aql:self.kemlDeletePartner()").build()).build())
                        .nodeTools(this.partnerMessageTool(true), this.partnerMessageTool(false),
                                this.messageTool(true), this.messageTool(false))
                        .build()).build();

        NodeDescription tail = this.diagrams.newNodeDescription().name("LifelineEndNode").domainType("keml::LifeLine")
                .semanticCandidatesExpression("aql:self.kemlLifeLines()")
                .defaultWidthExpression("1").defaultHeightExpression("1").userResizable(UserResizableDirection.NONE)
                .style(this.diagrams.newRectangularNodeStyleDescription().background(this.white).borderColor(this.white).borderSize(0).build())
                .palette(this.diagrams.newNodePalette().deleteTool(this.noDelete()).build()).build();

        NodeDescription authorEvent = this.event("AuthorEventNode");
        NodeDescription partnerEvent = this.event("PartnerEventNode");
        NodeDescription preknowledge = this.knowledge("PreKnowledgeNode", "keml::PreKnowledge",
                "aql:self.kemlInformation()->filter(keml::PreKnowledge)", true);
        NodeDescription information = this.knowledge("NewInformationNode", "keml::NewInformation",
                "aql:self.kemlInformation()->filter(keml::NewInformation)", false);

        for (NodeDescription card : List.of(preknowledge, information)) {
            List<EdgeTool> tools = new ArrayList<>();
            for (InformationLinkType type : InformationLinkType.VALUES) {
                tools.add(this.diagrams.newEdgeTool().name(this.linkLabel(type))
                        .iconURLsExpression(this.icon(type.getName()))
                        .targetElementDescriptions(preknowledge, information)
                        .body(this.views.newChangeContext().expression("aql:semanticEdgeSource.kemlLinkTo(semanticEdgeTarget, '" + type.getName() + "')").build())
                        .build());
            }
            card.getPalette().getEdgeTools().addAll(tools);
            card.getPalette().getNodeTools().add(this.diagrams.newNodeTool().name("Use in sent message")
                    .iconURLsExpression(this.icon("UseInformation"))
                    .preconditionExpression("aql:self.kemlConversation().kemlMessages()->filter(keml::SendMessage)->notEmpty()")
                    .dialogDescription(this.selectionDialog("Choose an Author-sent message", "aql:self.kemlConversation().kemlMessages()->filter(keml::SendMessage)"))
                    .body(this.views.newChangeContext().expression("aql:self.kemlUseInformation(selectedObject)").build()).build());
        }
        for (NodeDescription event : List.of(authorEvent, partnerEvent)) {
            event.getPalette().getEdgeTools().add(this.diagrams.newEdgeTool().name("Repeat knowledge")
                    .iconURLsExpression(this.icon("RepeatInformation"))
                    .preconditionExpression("aql:self.oclIsKindOf(keml::ReceiveMessage)")
                    .targetElementDescriptions(preknowledge, information)
                    .body(this.views.newChangeContext().expression("aql:semanticEdgeSource.kemlRepeatInformation(semanticEdgeTarget)").build()).build());
        }

        List<EdgeDescription> edges = new ArrayList<>();
        edges.add(this.edge("LifelineEdge", "keml::LifeLine", "aql:self.kemlLifeLines()", "aql:self", "aql:self",
                new NodeDescription[] {participant}, new NodeDescription[] {tail}, "", this.grey, LineStyle.DASH, 1, ArrowStyle.NONE)
                .conditionalStyles(this.diagrams.newConditionalEdgeStyle().condition("aql:self.oclIsKindOf(keml::Author)")
                        .color(this.black).borderColor(this.black).lineStyle(LineStyle.DASH).edgeWidth(2)
                        .sourceArrowStyle(ArrowStyle.NONE).targetArrowStyle(ArrowStyle.NONE).edgeType(EdgeType.OBLIQUE)
                        .showIcon(false).borderSize(0).build())
                .palette(this.diagrams.newEdgePalette().deleteTool(this.noDelete()).build()).build());
        edges.add(this.edge("SendMessageEdge", "keml::SendMessage", "aql:self.kemlMessages()->filter(keml::SendMessage)", "aql:self", "aql:self",
                new NodeDescription[] {authorEvent}, new NodeDescription[] {partnerEvent}, "aql:self.kemlMessageLabel()", this.black, LineStyle.SOLID, 1, ArrowStyle.INPUT_ARROW)
                .palette(this.diagrams.newEdgePalette().centerLabelEditTool(this.editLabel("content")).deleteTool(this.delete())
                        .nodeTools(this.tool("Reply", "Reply", "self.kemlReply()")).build()).build());
        edges.add(this.edge("ReceiveMessageEdge", "keml::ReceiveMessage", "aql:self.kemlMessages()->filter(keml::ReceiveMessage)", "aql:self", "aql:self",
                new NodeDescription[] {partnerEvent}, new NodeDescription[] {authorEvent}, "aql:self.kemlMessageLabel()", this.black, LineStyle.SOLID, 1, ArrowStyle.INPUT_ARROW)
                .palette(this.diagrams.newEdgePalette().centerLabelEditTool(this.editLabel("content")).deleteTool(this.delete())
                        .nodeTools(this.tool("Reply", "Reply", "self.kemlReply()"), this.factTool(false), this.factTool(true)).build()).build());
        edges.add(this.edge("GeneratedInformationEdge", "keml::NewInformation", "aql:self.kemlInformation()->filter(keml::NewInformation)", "aql:self.source", "aql:self",
                new NodeDescription[] {authorEvent}, new NodeDescription[] {information}, "", this.black, LineStyle.SOLID, 1, ArrowStyle.INPUT_ARROW)
                .palette(this.diagrams.newEdgePalette().deleteTool(this.noDelete()).build()).build());
        edges.add(this.edge("UsedInformationEdge", "keml::SendMessage", "aql:self.kemlMessages()->filter(keml::SendMessage)", "aql:self.uses", "aql:self",
                new NodeDescription[] {preknowledge, information}, new NodeDescription[] {authorEvent}, "", this.black, LineStyle.SOLID, 1, ArrowStyle.INPUT_ARROW)
                .palette(this.diagrams.newEdgePalette().deleteTool(this.relationDelete("semanticEdgeSource.kemlForgetUse(semanticEdgeTarget)")).build()).build());
        edges.add(this.edge("RepeatedInformationEdge", "keml::ReceiveMessage", "aql:self.kemlMessages()->filter(keml::ReceiveMessage)", "aql:self", "aql:self.repeats",
                new NodeDescription[] {authorEvent}, new NodeDescription[] {preknowledge, information}, "repeats", this.grey, LineStyle.DOT, 1, ArrowStyle.INPUT_ARROW)
                .palette(this.diagrams.newEdgePalette().deleteTool(this.relationDelete("semanticEdgeTarget.kemlForgetRepeat(semanticEdgeSource)")).build()).build());
        for (InformationLinkType type : InformationLinkType.VALUES) {
            boolean attack = type == InformationLinkType.ATTACK || type == InformationLinkType.STRONG_ATTACK;
            boolean supplement = type == InformationLinkType.SUPPLEMENT;
            boolean strong = type == InformationLinkType.STRONG_ATTACK || type == InformationLinkType.STRONG_SUPPORT;
            edges.add(this.edge(type.getName() + "InformationEdge", "keml::InformationLink", "aql:self.kemlLinks()", "aql:self.source", "aql:self.target",
                    new NodeDescription[] {preknowledge, information}, new NodeDescription[] {preknowledge, information},
                    "aql:if self.linkText <> null and self.linkText <> '' then self.linkText else '" + this.linkLabel(type) + "' endif",
                    attack ? this.red : supplement ? this.grey : this.green, supplement ? LineStyle.DOT : LineStyle.DASH, strong ? 3 : 1, ArrowStyle.INPUT_ARROW)
                    .preconditionExpression("aql:self.type = keml::InformationLinkType::" + type.getName())
                    .palette(this.diagrams.newEdgePalette().centerLabelEditTool(this.editLabel("linkText")).deleteTool(this.delete()).build()).build());
        }
        return this.diagrams.newDiagramDescription().name(DIAGRAM_NAME).domainType("keml::Conversation")
                .titleExpression("aql:'KEML · ' + self.title")
                .description("Conversation timeline with knowledge, instructions and argument links")
                .layoutOption(DiagramLayoutOption.NONE).minimapVisible(true)
                .toolbar(this.diagrams.newDiagramToolbar().expandedByDefault(true).build())
                .style(this.diagrams.newDiagramStyleDescription().build())
                .nodeDescriptions(participant, tail, authorEvent, partnerEvent, preknowledge, information)
                .edgeDescriptions(edges.toArray(EdgeDescription[]::new))
                .palette(this.diagrams.newDiagramPalette().nodeTools(
                        this.tool("Participant", "ConversationPartner", "self.kemlCreatePartner()"),
                        this.messageTool(true), this.messageTool(false),
                        this.tool("Pre-knowledge", "PreKnowledge", "self.kemlAddPreKnowledge()")).build())
                .build();
    }

    private NodeDescription event(String name) {
        return this.diagrams.newNodeDescription().name(name).domainType("keml::Message")
                .semanticCandidatesExpression("aql:self.kemlMessages()")
                .preconditionExpression("aql:self.counterPart <> null")
                .defaultWidthExpression("8").defaultHeightExpression("14").userResizable(UserResizableDirection.NONE)
                .style(this.diagrams.newRectangularNodeStyleDescription().background(this.grey).borderColor(this.grey).borderSize(0).build())
                .palette(this.diagrams.newNodePalette().labelEditTool(this.editLabel("content")).deleteTool(this.delete())
                        .nodeTools(this.tool("Reply", "Reply", "self.kemlReply()"), this.factTool(false), this.factTool(true)).build()).build();
    }

    private NodeDescription knowledge(String name, String domain, String candidates, boolean store) {
        var builder = this.diagrams.newNodeDescription().name(name).domainType(domain).semanticCandidatesExpression(candidates)
                .defaultWidthExpression(store ? "390" : "260")
                .defaultHeightExpression("aql:self.kemlKnowledgeHeight()")
                .insideLabel(this.knowledgeLabel())
                .palette(this.diagrams.newNodePalette().labelEditTool(this.editLabel("message")).deleteTool(this.delete()).build());
        if (store) {
            builder.style(this.diagrams.newImageNodeStyleDescription().shape("/images/keml/knowledge-store.svg").borderColor(this.grey).borderSize(0).build());
        } else {
            builder.style(this.diagrams.newRectangularNodeStyleDescription().background(this.yellow).borderColor(this.grey).borderSize(1).borderRadius(0).build())
                    .conditionalStyles(this.diagrams.newConditionalNodeStyle().condition("aql:self.isInstruction")
                            .style(this.diagrams.newRectangularNodeStyleDescription().background(this.cyan).borderColor(this.grey).borderSize(1).borderRadius(0).build()).build());
        }
        return builder.build();
    }

    private InsideLabelDescription knowledgeLabel() {
        return this.diagrams.newInsideLabelDescription().labelExpression("aql:self.kemlInformationLabel()")
                .position(InsideLabelPosition.MIDDLE_CENTER)
                .style(this.diagrams.newInsideLabelStyle().labelColor(this.black).fontSize(14).borderColor(this.grey).borderSize(0)
                        .showIconExpression("false").maxWidthExpression("230").withHeader(false).build()).build();
    }

    private org.eclipse.sirius.components.view.builder.generated.diagram.EdgeDescriptionBuilder edge(String name, String domain, String candidates,
            String source, String target, NodeDescription[] sources, NodeDescription[] targets, String label, FixedColor color, LineStyle line, int width, ArrowStyle arrow) {
        EdgeStyle style = this.diagrams.newEdgeStyle().color(color).borderColor(color).lineStyle(line).edgeWidth(width)
                .sourceArrowStyle(ArrowStyle.NONE).targetArrowStyle(arrow).edgeType(EdgeType.OBLIQUE)
                .fontSize(14).maxWidthExpression("280").showIcon(false).borderSize(0).build();
        return this.diagrams.newEdgeDescription().name(name).domainType(domain).semanticCandidatesExpression(candidates)
                .isDomainBasedEdge(true).sourceDescriptions(sources).targetDescriptions(targets)
                .sourceExpression(source).targetExpression(target).centerLabelExpression(label).style(style);
    }

    /** Reuse EMF Edit icons so the explorer and creation tools share a visual vocabulary. */
    private String icon(String name) {
        return "/icons/full/obj16/" + name + ".svg";
    }

    private NodeTool tool(String name, String icon, String expression) {
        return this.diagrams.newNodeTool().name(name)
                .iconURLsExpression(this.icon(icon))
                .body(this.views.newChangeContext().expression("aql:" + expression).build()).build();
    }

    private NodeTool partnerMessageTool(boolean send) {
        NodeTool tool = this.tool(send ? "Send to Author" : "Receive from Author",
                send ? "ReceiveMessage" : "SendMessage",
                "self." + (send ? "kemlSendFrom" : "kemlReceiveAt") + "(self.kemlConversation().author)");
        tool.setPreconditionExpression("aql:self.oclIsKindOf(keml::ConversationPartner) and self.kemlMessagePeers()->notEmpty()");
        return tool;
    }

    private NodeTool messageTool(boolean send) {
        // The Author/background must choose a partner; there is no implicit first-partner default.
        return this.diagrams.newNodeTool().name(send ? "Send message" : "Receive message")
                .iconURLsExpression(this.icon(send ? "SendMessage" : "ReceiveMessage"))
                .preconditionExpression("aql:(self.oclIsKindOf(keml::Author) or self.oclIsKindOf(keml::Conversation)) and self.kemlMessagePeers()->notEmpty()")
                .dialogDescription(this.selectionDialog(send ? "Choose the recipient" : "Choose the sender", "aql:self.kemlMessagePeers()"))
                .body(this.views.newChangeContext().expression("aql:self." + (send ? "kemlSendFrom" : "kemlReceiveAt") + "(selectedObject)").build()).build();
    }

    private org.eclipse.sirius.components.view.diagram.SelectionDialogDescription selectionDialog(String title, String candidates) {
        return this.diagrams.newSelectionDialogDescription().multiple(false).optional(false)
                .defaultTitleExpression(title).selectionRequiredWithSelectionConfirmButtonLabelExpression("Create")
                .selectionDialogTreeDescription(this.diagrams.newSelectionDialogTreeDescription()
                        .elementsExpression(candidates).childrenExpression("aql:Sequence{}").isSelectableExpression("true").build()).build();
    }

    private NodeTool factTool(boolean instruction) {
        NodeTool tool = this.tool(instruction ? "Instruction" : "Knowledge", instruction ? "isInstruction" : "isNoInstruction",
                instruction ? "self.kemlAddInstruction()" : "self.kemlAddInformation()");
        tool.setPreconditionExpression("aql:self.oclIsKindOf(keml::ReceiveMessage)");
        return tool;
    }

    private LabelEditTool editLabel(String feature) {
        return this.diagrams.newLabelEditTool().name("Edit" + Character.toUpperCase(feature.charAt(0)) + feature.substring(1))
                .initialDirectEditLabelExpression("aql:if self." + feature + " <> null then self." + feature + " else '' endif")
                .body(this.views.newSetValue().featureName(feature).valueExpression("aql:newLabel").build()).build();
    }

    private DeleteTool delete() {
        return this.diagrams.newDeleteTool().name("DeleteElement")
                .body(this.views.newChangeContext().expression("aql:self.kemlDeleteElement()").build()).build();
    }

    private DeleteTool noDelete() {
        return this.diagrams.newDeleteTool().name("KeepStructuralLink").preconditionExpression("false")
                .body(this.views.newChangeContext().expression("aql:self").build()).build();
    }

    private DeleteTool relationDelete(String expression) {
        return this.diagrams.newDeleteTool().name("RemoveKnowledgeReference")
                .body(this.views.newChangeContext().expression("aql:" + expression).build()).build();
    }

    private String linkLabel(InformationLinkType type) {
        return switch (type) {
            case SUPPLEMENT -> "Supplement";
            case SUPPORT -> "Support";
            case STRONG_SUPPORT -> "Strong support";
            case ATTACK -> "Attack";
            case STRONG_ATTACK -> "Strong attack";
        };
    }
}
