package keml.app;

import java.util.List;
import java.util.ArrayList;

import org.eclipse.sirius.components.collaborative.trees.api.ITreeItemContextMenuEntryProvider;
import org.eclipse.sirius.components.collaborative.trees.dto.ITreeItemContextMenuEntry;
import org.eclipse.sirius.components.collaborative.trees.dto.SingleClickTreeItemContextMenuEntry;
import org.eclipse.sirius.components.collaborative.trees.dto.palette.SingleClickTreeItemTool;
import org.eclipse.sirius.components.collaborative.trees.palette.api.ITreeItemPaletteCustomizer;
import org.eclipse.sirius.components.core.api.IEditingContext;
import org.eclipse.sirius.components.core.api.IObjectSearchService;
import org.eclipse.sirius.components.trees.Tree;
import org.eclipse.sirius.components.trees.TreeItem;
import org.eclipse.sirius.components.trees.description.TreeDescription;
import org.eclipse.sirius.components.palette.dto.Palette;
import org.springframework.stereotype.Service;

import keml.Conversation;

/** The Java contribution declares availability; its frontend contribution opens the dialog. */
@Service
public class KemlAnalysisMenuProvider implements ITreeItemContextMenuEntryProvider, ITreeItemPaletteCustomizer {
    private final IObjectSearchService objects;

    public KemlAnalysisMenuProvider(IObjectSearchService objects) {
        this.objects = objects;
    }

    @Override
    public boolean canHandle(IEditingContext context, TreeDescription description, Tree tree, TreeItem item) {
        return tree.getId().startsWith("explorer://") && objects.getObject(context, item.getId()).filter(Conversation.class::isInstance).isPresent();
    }

    @Override
    public List<ITreeItemContextMenuEntry> getTreeItemContextMenuEntries(IEditingContext context, TreeDescription description, Tree tree, TreeItem item) {
        return List.of(new SingleClickTreeItemContextMenuEntry("keml-analyse-conversation", "Analyse conversation…",
                List.of("/icons/full/obj16/AnalyseConversation.svg"), false, List.of()));
    }

    /** Current Sirius workbenches use a palette; retain the context-menu contribution for legacy menus. */
    @Override
    public Palette customize(IEditingContext context, TreeDescription description, Tree tree, TreeItem item, Palette palette) {
        var entries = new ArrayList<>(palette.paletteEntries());
        entries.addFirst(new SingleClickTreeItemTool("keml-analyse-conversation", "Analyse conversation…",
                List.of("/icons/full/obj16/AnalyseConversation.svg"), false, List.of()));
        return new Palette(palette.id(), palette.quickAccessTools(), entries);
    }
}
