package keml.diagram;

import java.util.Optional;

import org.eclipse.sirius.components.collaborative.diagrams.api.ICustomNodeStyleDeserializer;
import org.eclipse.sirius.components.diagrams.ILayoutStrategy;
import org.eclipse.sirius.components.diagrams.INodeStyle;
import org.eclipse.sirius.components.diagrams.LineStyle;
import org.eclipse.sirius.components.interpreter.AQLInterpreter;
import org.eclipse.sirius.components.representations.VariableManager;
import org.eclipse.sirius.components.view.FixedColor;
import org.eclipse.sirius.components.view.diagram.ImageNodeStyleDescription;
import org.eclipse.sirius.components.view.diagram.NodeStyleDescription;
import org.eclipse.sirius.components.view.emf.diagram.INodeStyleProvider;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.node.ObjectNode;

/** Example of contributing a runtime node style without extending the View metamodel. */
@Service
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CylinderNodeStyleProvider implements INodeStyleProvider, ICustomNodeStyleDeserializer {
    public static final String SHAPE = "keml:cylinder";
    public static final String NODE_TYPE = "customnode:keml-cylinder";

    @Override
    public Optional<String> getNodeType(NodeStyleDescription style) {
        // This image description is a marker, not an image URL. Handle it before the image provider.
        return style instanceof ImageNodeStyleDescription image && SHAPE.equals(image.getShape())
                ? Optional.of(NODE_TYPE) : Optional.empty();
    }

    @Override
    public Optional<INodeStyle> createNodeStyle(NodeStyleDescription style, ILayoutStrategy childrenLayoutStrategy,
            AQLInterpreter interpreter, VariableManager variables) {
        return this.getNodeType(style).map(type -> new CylinderNodeStyle("#ffffa0",
                style.getBorderColor() instanceof FixedColor color ? color.getValue() : "#b3b36a",
                style.getBorderSize(), LineStyle.valueOf(style.getBorderLineStyle().getLiteral()), childrenLayoutStrategy));
    }

    @Override
    public boolean canHandle(String type) {
        return NODE_TYPE.equals(type);
    }

    @Override
    public INodeStyle handle(ObjectNode root, JsonParser parser, DeserializationContext context) {
        return context.readTreeAsValue(root, CylinderNodeStyle.class);
    }
}
