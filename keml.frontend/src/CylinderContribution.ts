import { DocumentTransform } from '@apollo/client';
import { Kind, parse, visit, InlineFragmentNode } from 'graphql';
import {
  INodeConverter, INodeLayoutHandler, NodeData, computePreviousSize,
} from '@eclipse-sirius/sirius-components-diagrams';
import { cylinderAnchor } from './CylinderGeometry';

export const cylinderNodeType = 'kemlCylinderNode';

// Extend the native subscription instead of maintaining a copy of Sirius's diagram query.
const styleFragment = parse(`fragment cylinder on INodeStyle {
  ... on CylinderNodeStyle {
    background borderColor borderSize borderStyle
    childrenLayoutStrategy { ...childrenLayoutStrategyFragment }
  }
}`).definitions[0];
const inlineFragment = styleFragment.kind === Kind.FRAGMENT_DEFINITION
  ? styleFragment.selectionSet.selections[0] as InlineFragmentNode : undefined;
export const cylinderDocumentTransform = new DocumentTransform((document) => {
  if (!document.definitions.some((definition) => definition.kind === Kind.OPERATION_DEFINITION
    && definition.name?.value === 'diagramEvent')) return document;
  return visit(document, {
    Field(field) {
      const selections = field.selectionSet?.selections;
      if (field.name.value !== 'style' || !selections || !inlineFragment
        || !selections.some((selection) => selection.kind === Kind.INLINE_FRAGMENT && selection.typeCondition?.name.value === 'ImageNodeStyle')
        || selections.some((selection) => selection.kind === Kind.INLINE_FRAGMENT && selection.typeCondition?.name.value === 'CylinderNodeStyle')) return;
      return { ...field, selectionSet: { ...field.selectionSet!, selections: [...selections, inlineFragment] } };
    },
  });
});

export const cylinderConverter: INodeConverter = {
  canHandle: (node) => node.style.__typename === 'CylinderNodeStyle',
  handle(engine, diagram, node, _edges, parent, _border, nodes, description, nodeDescriptions) {
    // Reuse native conversion for labels, saved bounds, handles, visibility and editing permissions.
    const nativeNode = { ...node, style: { ...node.style, __typename: 'RectangularNodeStyle', borderRadius: 0 } };
    engine.convertNodes(diagram, [nativeNode],
      parent, nodes, description, nodeDescriptions);
    const converted = nodes.find((candidate) => candidate.id === node.id);
    if (converted) {
      converted.type = cylinderNodeType;
      (converted.data as NodeData).nodeAppearanceData.gqlStyle = node.style;
    }
  },
};

export const cylinderLayoutHandler: INodeLayoutHandler<NodeData> = {
  canHandle: (node) => node.type === cylinderNodeType,
  handle(_engine, previousDiagram, node, _visible, _children, _added, forced) {
    const previous = computePreviousSize(previousDiagram?.nodes.find((candidate) => candidate.id === node.id), node);
    // Bounded labels must not enlarge the node; resize explicitly to reveal more text.
    node.width = Math.max(160, forced?.width ?? (node.data.resizedByUser ? previous.width : node.data.defaultWidth ?? 390));
    node.height = Math.max(100, forced?.height ?? (node.data.resizedByUser ? previous.height : node.data.defaultHeight ?? 144));
    node.data.minComputedWidth = 160;
    node.data.minComputedHeight = 100;
  },
  calculateCustomNodeEdgeHandlePosition(node, side, handle) {
    return cylinderAnchor(node.width ?? 390, node.height ?? 144, side,
      { x: handle.x + (handle.width ?? 6) / 2, y: handle.y + (handle.height ?? 6) / 2 });
  },
};
