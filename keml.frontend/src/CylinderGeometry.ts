/** One geometry for the drawing, label bounds and edge anchors (coordinates local to the node). */
export function cylinderGeometry(width: number, height: number) {
  const inset = 0.5;
  const radiusY = Math.min(18, (height - 1) / 6);
  const radiusX = (width - 1) / 2;
  const top = inset + radiusY;
  const bottom = height - inset - radiusY;
  return {
    radiusX, radiusY, top, bottom, centerX: width / 2,
    path: `M${inset} ${top} A${radiusX} ${radiusY} 0 0 1 ${width - inset} ${top}`
      + ` V${bottom} A${radiusX} ${radiusY} 0 0 1 ${inset} ${bottom} Z`,
    label: { x: 12, y: top + radiusY + 8, width: width - 24, height: height - 3 * radiusY - 17 },
  };
}

/** Project a rectangular handle onto the cylinder's silhouette, including its curved caps. */
export function cylinderAnchor(width: number, height: number, side: string, point: { x: number; y: number }) {
  const { radiusX, radiusY, top, bottom, centerX } = cylinderGeometry(width, height);
  if (side === 'top' || side === 'bottom') {
    const x = Math.max(0.5, Math.min(width - 0.5, point.x));
    const curve = radiusY * Math.sqrt(Math.max(0, 1 - ((x - centerX) / radiusX) ** 2));
    return { x, y: side === 'top' ? top - curve : bottom + curve };
  }
  const y = Math.max(0.5, Math.min(height - 0.5, point.y));
  const distance = y < top ? (y - top) / radiusY : y > bottom ? (y - bottom) / radiusY : 0;
  const curve = radiusX * Math.sqrt(Math.max(0, 1 - distance ** 2));
  return { x: centerX + (side === 'left' ? -curve : curve), y };
}
