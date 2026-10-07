import { CSSProperties, memo } from 'react';
import { NodeProps } from '@xyflow/react';
import {
  ActionsContainer, ConnectionCreationHandles, ConnectionHandles, ConnectionTargetHandle,
  DecoratorContainer, Label, NodeData, NodeTypeContribution, Resizer, useConnectionLineNodeStyle, useConnectorNodeStyle,
  useDrop, useDropNodeStyle, useRefreshConnectionHandles,
} from '@eclipse-sirius/sirius-components-diagrams';
import { NodeTypeRegistry } from '@eclipse-sirius/sirius-web-application';
import { cylinderConverter, cylinderLayoutHandler, cylinderNodeType } from './CylinderContribution';
import { cylinderGeometry } from './CylinderGeometry';

const CylinderNode = memo(({ id, data: rawData, selected, dragging, width = 390, height = 144,
  positionAbsoluteX, positionAbsoluteY }: NodeProps) => {
  const data = rawData as NodeData;
  const geometry = cylinderGeometry(width, height);
  const { onDrop, onDragOver } = useDrop();
  const { style: connectorStyle } = useConnectorNodeStyle(id, data.nodeDescription.id);
  const { style: dropStyle } = useDropNodeStyle(data.isDropNodeTarget, data.isDragNodeSource, data.isDropNodeCandidate, dragging);
  const { style: connectionStyle } = useConnectionLineNodeStyle(data.connectionLinePositionOnNode);
  useRefreshConnectionHandles(id, data.connectionHandles);
  const fontSize = parseFloat(String(data.insideLabel?.style.fontSize)) || 14;
  const lineHeight = fontSize * 1.4;
  const label = data.insideLabel ? {
    ...data.insideLabel, overflowStrategy: 'WRAP' as const,
    style: { ...data.insideLabel.style, width: '100%', maxWidth: '100%', height: '100%', padding: 0 },
    contentStyle: { ...data.insideLabel.contentStyle, maxWidth: 'none', width: '100%', padding: 0 },
  } : null;
  return <>
    <Resizer data={data} selected={!!selected} />
    <div className="keml-cylinder custom-drag-handle" data-testid={`Cylinder - ${data.targetObjectLabel}`}
      style={{ width: '100%', height: '100%', position: 'relative', opacity: data.faded ? 0.4 : 1,
        outline: selected || data.isHovered ? '1px solid #1976d2' : undefined,
        ...connectorStyle, ...dropStyle, ...connectionStyle }} onDragOver={onDragOver} onDrop={(event) => onDrop(event, id)}>
      <svg data-svg="svg" width={width} height={height} aria-hidden="true" style={{ position: 'absolute', pointerEvents: 'none' }}>
        <path d={geometry.path} fill={String(data.style.background)} stroke={String(data.style.borderColor)} strokeWidth={1} />
        <ellipse cx={geometry.centerX} cy={geometry.top} rx={geometry.radiusX} ry={geometry.radiusY}
          fill={String(data.style.background)} stroke={String(data.style.borderColor)} strokeWidth={1} />
      </svg>
      <DecoratorContainer decorators={data.decorators} />
      <div className="keml-cylinder-label" title={label?.text} style={{ position: 'absolute', left: geometry.label.x,
        top: geometry.label.y, width: geometry.label.width, height: geometry.label.height,
        '--keml-label-lines': Math.max(1, Math.floor(geometry.label.height / lineHeight)),
        '--keml-label-line-height': `${lineHeight}px` } as CSSProperties}>
        {label && <Label diagramElementId={id} label={label} faded={false} width={geometry.label.width} />}
      </div>
      {data.isHovered && <div style={{ position: 'absolute', right: 4, top: geometry.top }}><ActionsContainer diagramElementId={id} /></div>}
      {selected && <ConnectionCreationHandles nodeId={id} nodePosition={{ x: positionAbsoluteX, y: positionAbsoluteY }}
        nodeWidth={width} nodeHeight={height} isDraggedNode={data.isDraggedNode} />}
      <ConnectionTargetHandle nodeId={id} nodeDescription={data.nodeDescription} isHovered={data.isHovered} />
      <ConnectionHandles connectionHandles={data.connectionHandles} />
    </div>
  </>;
});

export const cylinderNodeRegistry: NodeTypeRegistry = {
  nodeConverters: [cylinderConverter], nodeLayoutHandlers: [cylinderLayoutHandler],
  nodeTypeContributions: [<NodeTypeContribution key={cylinderNodeType} type={cylinderNodeType} component={CylinderNode} />],
};
