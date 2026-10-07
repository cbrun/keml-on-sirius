import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import { readdirSync } from 'node:fs';
import { resolve } from 'node:path';

// Reuse the matching release's global styles/fonts rather than copying Sirius's CSS.
const assets = resolve('target/upstream/static/assets');
const siriusCss = readdirSync(assets).find(name => name.endsWith('.css'));
if (!siriusCss) throw new Error('Build the Maven reactor first to extract Sirius Web styles.');

export default defineConfig({
  plugins: [react()],
  resolve: { alias: { '@sirius-base-style': resolve(assets, siriusCss) } },
  publicDir: 'target/upstream/static',
  build: { outDir: 'target/frontend', emptyOutDir: true },
  server: { proxy: { '/api': { target: 'http://localhost:8080', ws: true } } },
});
