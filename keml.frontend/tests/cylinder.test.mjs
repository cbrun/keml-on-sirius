import assert from 'node:assert/strict';
import test from 'node:test';
import { cylinderAnchor, cylinderGeometry } from '../src/CylinderGeometry.ts';

test('labels stay in the body and anchors follow the silhouette at every supported size', () => {
  for (const [width, height] of [[390, 144], [160, 100], [800, 400]]) {
    const { label, radiusX, radiusY, centerX, top, bottom } = cylinderGeometry(width, height);
    assert.ok(label.x > 0 && label.x + label.width < width);
    assert.ok(label.y > top + radiusY && label.y + label.height < bottom);
    assert.deepEqual(cylinderAnchor(width, height, 'top', { x: centerX, y: 0 }), { x: centerX, y: 0.5 });
    assert.deepEqual(cylinderAnchor(width, height, 'right', { x: width, y: height / 2 }), { x: width - 0.5, y: height / 2 });
    for (const side of ['top', 'bottom', 'left', 'right']) {
      for (const fraction of [0, 0.1, 0.25, 0.5, 0.75, 0.9, 1]) {
        const point = cylinderAnchor(width, height, side, { x: fraction * width, y: fraction * height });
        assert.ok(Number.isFinite(point.x) && Number.isFinite(point.y));
        assert.ok(point.x >= 0.5 && point.x <= width - 0.5 && point.y >= 0.5 && point.y <= height - 0.5);
        if (point.y < top || point.y > bottom || side === 'top' || side === 'bottom') {
          const cy = point.y <= top ? top : bottom;
          assert.ok(Math.abs(((point.x - centerX) / radiusX) ** 2 + ((point.y - cy) / radiusY) ** 2 - 1) < 1e-9);
        }
        assert.deepEqual(cylinderAnchor(width, height, side, point), point);
      }
    }
  }
});
