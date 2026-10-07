package keml.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.sirius.components.collaborative.diagrams.DiagramContext;
import org.eclipse.sirius.components.collaborative.diagrams.api.IDiagramPostProcessor;
import org.eclipse.sirius.components.core.api.IEditingContext;
import org.eclipse.sirius.components.core.api.IIdentityService;
import org.eclipse.sirius.components.core.api.IObjectSearchService;
import org.eclipse.sirius.components.diagrams.Diagram;
import org.eclipse.sirius.components.diagrams.Edge;
import org.eclipse.sirius.components.diagrams.Node;
import org.eclipse.sirius.components.diagrams.layoutdata.DiagramLayoutData;
import org.eclipse.sirius.components.diagrams.layoutdata.EdgeLayoutData;
import org.eclipse.sirius.components.diagrams.layoutdata.HandleLayoutData;
import org.eclipse.sirius.components.diagrams.layoutdata.HandleType;
import org.eclipse.sirius.components.diagrams.layoutdata.NodeLayoutData;
import org.eclipse.sirius.components.diagrams.layoutdata.Position;
import org.eclipse.sirius.components.diagrams.layoutdata.Size;
import org.eclipse.sirius.components.view.View;
import org.eclipse.sirius.components.view.diagram.DiagramDescription;
import org.eclipse.sirius.components.view.emf.diagram.IDiagramIdProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import keml.Conversation;
import keml.LifeLine;
import keml.Message;
import keml.ReceiveMessage;

/** Positions conversation events on a shared chronology using Sirius' native layout data. */
@Service
public class KemlDiagramPostProcessor implements IDiagramPostProcessor {

    private final IObjectSearchService objectSearchService;

    private final IIdentityService identityService;

    private final String diagramDescriptionId;

    private final Map<String, String> descriptionIds = new HashMap<>();

    private final KemlServices services = new KemlServices();

    public KemlDiagramPostProcessor(@Qualifier("kemlView") View view, IDiagramIdProvider diagramIdProvider,
            IObjectSearchService objectSearchService, IIdentityService identityService) {
        this.objectSearchService = objectSearchService;
        this.identityService = identityService;
        DiagramDescription description = view.getDescriptions().stream()
                .filter(DiagramDescription.class::isInstance).map(DiagramDescription.class::cast)
                .filter(candidate -> KemlViews.DIAGRAM_NAME.equals(candidate.getName())).findFirst().orElseThrow();
        this.diagramDescriptionId = diagramIdProvider.getId(description);
        description.getNodeDescriptions().forEach(node -> this.descriptionIds.put(node.getName(), diagramIdProvider.getId(node)));
        description.getEdgeDescriptions().forEach(edge -> this.descriptionIds.put(edge.getName(), diagramIdProvider.getId(edge)));
    }

    @Override
    public boolean canHandle(IEditingContext editingContext, DiagramContext diagramContext) {
        return this.diagramDescriptionId.equals(diagramContext.diagram().getDescriptionId());
    }

    @Override
    public Optional<Diagram> postProcess(IEditingContext editingContext, DiagramContext diagramContext) {
        Diagram diagram = diagramContext.diagram();
        return this.objectSearchService.getObject(editingContext, diagram.getTargetObjectId())
                .filter(Conversation.class::isInstance).map(Conversation.class::cast)
                .flatMap(conversation -> this.layout(conversation, diagram));
    }

    private Optional<Diagram> layout(Conversation conversation, Diagram diagram) {
        Map<String, NodeLayoutData> nodes = new LinkedHashMap<>(diagram.getLayoutData().nodeLayoutData());
        Map<String, EdgeLayoutData> edges = new LinkedHashMap<>(diagram.getLayoutData().edgeLayoutData());
        Map<String, Double> lifelineCenters = new HashMap<>();
        double rowY = 160;
        double actorStartX = 720;
        if (conversation.getAuthor() != null) {
            double preknowledgeBottom = this.placeCards(diagram, nodes, "PreKnowledgeNode", conversation.getAuthor().getPreknowledge(), 30);
            rowY = Math.max(rowY, preknowledgeBottom + 70);
            for (var information : conversation.getAuthor().getPreknowledge()) {
                Node card = this.find(diagram, "PreKnowledgeNode", information);
                if (card != null && !nodes.get(card.getId()).movedByUser()) {
                    var data = nodes.get(card.getId());
                    actorStartX = Math.max(actorStartX, data.position().x() + data.size().width() + 130);
                }
            }
        }
        List<LifeLine> lifelines = this.services.kemlLifeLines(conversation);
        for (int index = 0; index < lifelines.size(); index++) {
            LifeLine lifeline = lifelines.get(index);
            Node header = this.find(diagram, "ParticipantNode", lifeline);
            if (header != null) {
                this.place(nodes, header, actorStartX + index * 320, 20, 80, 100);
                NodeLayoutData data = nodes.get(header.getId());
                lifelineCenters.put(this.identityService.getId(lifeline), data.position().x() + data.size().width() / 2);
            }
        }

        for (Message message : this.services.kemlMessages(conversation)) {
            double rowBottom = rowY + 100;
            if (message instanceof ReceiveMessage receive) {
                rowBottom = Math.max(rowBottom, this.placeCards(diagram, nodes, "NewInformationNode", receive.getGenerates(), rowY) + 30);
            }
            Double authorX = conversation.getAuthor() == null ? null : lifelineCenters.get(this.identityService.getId(conversation.getAuthor()));
            Double partnerX = message.getCounterPart() == null ? null : lifelineCenters.get(this.identityService.getId(message.getCounterPart()));
            Node authorEvent = this.find(diagram, "AuthorEventNode", message);
            Node partnerEvent = this.find(diagram, "PartnerEventNode", message);
            if (authorEvent != null && authorX != null) {
                this.place(nodes, authorEvent, authorX - 4, rowY + 33, 8, 14);
            }
            if (partnerEvent != null && partnerX != null) {
                this.place(nodes, partnerEvent, partnerX - 4, rowY + 33, 8, 14);
            }
            rowY = rowBottom;
        }
        for (LifeLine lifeline : lifelines) {
            Node tail = this.find(diagram, "LifelineEndNode", lifeline);
            Double center = lifelineCenters.get(this.identityService.getId(lifeline));
            if (tail != null && center != null) {
                this.place(nodes, tail, center - 0.5, rowY + 20, 1, 1);
            }
        }

        Map<String, List<HandleLayoutData>> handles = new HashMap<>();
        for (Edge edge : diagram.getEdges()) {
            NodeLayoutData source = nodes.get(edge.getSourceId());
            NodeLayoutData target = nodes.get(edge.getTargetId());
            if (source == null || target == null) {
                continue;
            }
            boolean lifeline = edge.getDescriptionId().equals(this.descriptionIds.get("LifelineEdge"));
            boolean message = edge.getDescriptionId().equals(this.descriptionIds.get("SendMessageEdge"))
                    || edge.getDescriptionId().equals(this.descriptionIds.get("ReceiveMessageEdge"));
            String sourceSide = lifeline ? "bottom" : source.position().x() <= target.position().x() ? "right" : "left";
            String targetSide = lifeline ? "top" : sourceSide.equals("right") ? "left" : "right";
            handles.computeIfAbsent(source.id(), ignored -> new ArrayList<>()).add(lifeline || message
                    ? this.handle(edge, source, sourceSide, HandleType.source)
                    : this.knowledgeHandle(diagram, edge, source, target, HandleType.source));
            handles.computeIfAbsent(target.id(), ignored -> new ArrayList<>()).add(lifeline || message
                    ? this.handle(edge, target, targetSide, HandleType.target)
                    : this.knowledgeHandle(diagram, edge, target, source, HandleType.target));
            if (lifeline || message) {
                edges.put(edge.getId(), new EdgeLayoutData(edge.getId(), List.of(), List.of(), List.of()));
            }
        }
        handles.forEach((id, nodeHandles) -> {
            NodeLayoutData data = nodes.get(id);
            nodes.put(id, new NodeLayoutData(id, data.position(), data.size(), data.resizedByUser(), data.movedByUser(), nodeHandles, data.minComputedSize()));
        });
        DiagramLayoutData layoutData = new DiagramLayoutData(nodes, edges, diagram.getLayoutData().labelLayoutData(), false);
        if (layoutData.equals(diagram.getLayoutData())) {
            return Optional.empty();
        }
        return Optional.of(Diagram.newDiagram(diagram).layoutData(layoutData).build());
    }

    private double placeCards(Diagram diagram, Map<String, NodeLayoutData> nodes, String description,
            List<?> information, double startY) {
        double nextY = startY;
        double nextX = 40;
        double rowHeight = 0;
        for (int index = 0; index < information.size(); index++) {
            Node card = this.find(diagram, description, information.get(index));
            if (card != null) {
                double height = Math.max(80, card.getDefaultHeight() == null ? 80 : card.getDefaultHeight());
                double width = card.getDefaultWidth() == null ? 260 : card.getDefaultWidth();
                this.place(nodes, card, nextX, nextY, width, height);
                nextX += nodes.get(card.getId()).size().width() + 30;
                rowHeight = Math.max(rowHeight, nodes.get(card.getId()).size().height());
            }
            if (index % 2 == 1 || index == information.size() - 1) {
                nextY += rowHeight + 25;
                rowHeight = 0;
                nextX = 40;
            }
        }
        return nextY;
    }

    private Node find(Diagram diagram, String description, Object object) {
        String objectId = this.identityService.getId(object);
        return diagram.getNodes().stream().filter(node -> node.getDescriptionId().equals(this.descriptionIds.get(description)))
                .filter(node -> node.getTargetObjectId().equals(objectId)).findFirst().orElse(null);
    }

    private void place(Map<String, NodeLayoutData> nodes, Node node, double x, double y, double width, double height) {
        NodeLayoutData previous = nodes.get(node.getId());
        Position position = previous != null && previous.movedByUser() ? previous.position() : new Position(x, y);
        Size size = previous != null && previous.resizedByUser() ? previous.size() : new Size(width, height);
        nodes.put(node.getId(), new NodeLayoutData(node.getId(), position, size,
                previous != null && previous.resizedByUser(), previous != null && previous.movedByUser(), List.of(),
                previous == null ? new Size(0, 0) : previous.minComputedSize()));
    }

    private HandleLayoutData handle(Edge edge, NodeLayoutData node, String side, HandleType type) {
        double x = side.equals("left") ? 0 : side.equals("right") ? node.size().width() : node.size().width() / 2;
        double y = side.equals("top") ? 0 : side.equals("bottom") ? node.size().height() : node.size().height() / 2;
        return new HandleLayoutData(edge.getId(), new Position(x, y), side, type);
    }

    private HandleLayoutData knowledgeHandle(Diagram diagram, Edge edge, NodeLayoutData node, NodeLayoutData other, HandleType type) {
        // Knowledge anchors belong to the user. Only chronology edges have imposed side midpoints.
        NodeLayoutData previous = diagram.getLayoutData().nodeLayoutData().get(node.id());
        if (previous != null) {
            var saved = previous.handleLayoutData().stream().filter(handle -> edge.getId().equals(handle.edgeId()) && type == handle.type()).findFirst();
            if (saved.isPresent() && previous.size().width() > 0 && previous.size().height() > 0) {
                var handle = saved.get();
                if (node.size().equals(previous.size())) {
                    return handle;
                }
                return new HandleLayoutData(edge.getId(), new Position(
                        (handle.position().x() / previous.size().width()) * node.size().width(),
                        (handle.position().y() / previous.size().height()) * node.size().height()), handle.handlePosition(), type);
            }
        }
        // Start toward the other endpoint; the custom frontend handler projects onto the cylinder.
        double dx = other.position().x() + other.size().width() / 2 - node.position().x() - node.size().width() / 2;
        double dy = other.position().y() + other.size().height() / 2 - node.position().y() - node.size().height() / 2;
        double horizontal = dx == 0 ? Double.POSITIVE_INFINITY : node.size().width() / 2 / Math.abs(dx);
        double vertical = dy == 0 ? Double.POSITIVE_INFINITY : node.size().height() / 2 / Math.abs(dy);
        if (!Double.isFinite(Math.min(horizontal, vertical))) {
            return this.handle(edge, node, "right", type);
        }
        double ratio = Math.min(horizontal, vertical);
        String side = horizontal <= vertical ? dx < 0 ? "left" : "right" : dy < 0 ? "top" : "bottom";
        return new HandleLayoutData(edge.getId(), new Position(node.size().width() / 2 + dx * ratio,
                node.size().height() / 2 + dy * ratio), side, type);
    }
}
