import { ExtensionRegistry, ExtensionRegistryMergeStrategy } from '@eclipse-sirius/sirius-components-core';
import {
  TreeItemContextMenuOverrideContribution,
  TREE_REPRESENTATION_KIND,
  treeItemContextMenuEntryOverrideExtensionPoint,
} from '@eclipse-sirius/sirius-components-trees';
import { SiriusWebApplication } from '@eclipse-sirius/sirius-web-application';
import { WidgetContribution, widgetContributionExtensionPoint } from '@eclipse-sirius/sirius-components-forms';
import { PaletteToolOverriddenContributionProps, paletteToolOverrideExtensionPoint } from '@eclipse-sirius/sirius-components-palette';
import { createRoot } from 'react-dom/client';
import { AnalyseConversationMenu, AnalyseConversationPaletteTool } from './AnalysisDialog';
import { trustWidgetContribution } from './TrustPropertySection';
import '@xyflow/react/dist/style.css';
import '@sirius-base-style';
import './styles.css';

// Contributions extend the published workbench. Preserve Sirius's existing menu entries.
const registry = new ExtensionRegistry();
registry.putData<WidgetContribution[]>(widgetContributionExtensionPoint, {
  identifier: 'keml-trust-widget', data: [trustWidgetContribution],
});
registry.putData<TreeItemContextMenuOverrideContribution[]>(treeItemContextMenuEntryOverrideExtensionPoint, {
  identifier: 'keml-analysis-menu',
  data: [{ canHandle: (entry) => entry.id === 'keml-analyse-conversation', component: AnalyseConversationMenu }],
});
registry.putData<PaletteToolOverriddenContributionProps[]>(paletteToolOverrideExtensionPoint, {
  identifier: 'keml-analysis-palette',
  data: [{ canHandle: (kind, tool) => kind === TREE_REPRESENTATION_KIND && tool.id === 'keml-analyse-conversation', component: AnalyseConversationPaletteTool }],
});
const mergeStrategy: ExtensionRegistryMergeStrategy = {
  mergeComponentExtensions: (_id, existing, added) => [...existing, ...added],
  mergeDataExtensions: (id, existing, added) => [treeItemContextMenuEntryOverrideExtensionPoint.identifier, paletteToolOverrideExtensionPoint.identifier, widgetContributionExtensionPoint.identifier].includes(id)
    ? { identifier: added.identifier, data: [...existing.data, ...added.data] }
    : added,
};

// The same build works in Docker, Java/Eclipse and Vite's development proxy.
const httpOrigin = window.location.origin;
const wsOrigin = httpOrigin.replace(/^http/, 'ws');
createRoot(document.getElementById('root')!).render(
  <SiriusWebApplication httpOrigin={httpOrigin} wsOrigin={wsOrigin}
    extensionRegistry={registry} extensionRegistryMergeStrategy={mergeStrategy} />
);
