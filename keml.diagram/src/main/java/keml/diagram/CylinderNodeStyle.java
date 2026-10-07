package keml.diagram;

import org.eclipse.sirius.components.diagrams.ILayoutStrategy;
import org.eclipse.sirius.components.diagrams.INodeStyle;
import org.eclipse.sirius.components.diagrams.LineStyle;

/** Runtime style shared by GraphQL and saved diagrams; the frontend draws the cylinder. */
public record CylinderNodeStyle(String background, String borderColor, int borderSize, LineStyle borderStyle,
        ILayoutStrategy childrenLayoutStrategy) implements INodeStyle {
    @Override
    public ILayoutStrategy getChildrenLayoutStrategy() {
        return this.childrenLayoutStrategy;
    }
}
