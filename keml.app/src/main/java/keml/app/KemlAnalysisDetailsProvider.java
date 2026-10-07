package keml.app;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.components.collaborative.forms.services.api.IPropertiesDescriptionRegistry;
import org.eclipse.sirius.components.collaborative.forms.services.api.IPropertiesDescriptionRegistryConfigurer;
import org.eclipse.sirius.components.core.api.IEditingContext;
import org.eclipse.sirius.components.core.api.IIdentityService;
import org.eclipse.sirius.components.emf.forms.api.IEMFFormDescriptionProvider;
import org.eclipse.sirius.components.forms.LinkStyle;
import org.eclipse.sirius.components.forms.description.GroupDescription;
import org.eclipse.sirius.components.forms.description.LinkDescription;
import org.eclipse.sirius.components.forms.description.PageDescription;
import org.eclipse.sirius.components.representations.VariableManager;
import org.springframework.stereotype.Service;

import keml.Conversation;

/** Adds an Analysis group while reusing the standard editable EMF properties. */
@Service
public class KemlAnalysisDetailsProvider implements IPropertiesDescriptionRegistryConfigurer {
    private final IEMFFormDescriptionProvider defaultForms;
    private final IIdentityService identity;

    public KemlAnalysisDetailsProvider(IEMFFormDescriptionProvider defaultForms, IIdentityService identity) {
        this.defaultForms = defaultForms;
        this.identity = identity;
    }

    @Override
    public void addPropertiesDescriptions(IPropertiesDescriptionRegistry registry) {
        var link = LinkDescription.newLinkDescription("keml-analysis-download")
                .idProvider(vm -> "keml-analysis-download-" + objectId(vm))
                .targetObjectIdProvider(this::objectId)
                .labelProvider(vm -> "Download analysis reports (ZIP)")
                .urlProvider(this::downloadUrl)
                .styleProvider(vm -> LinkStyle.newLinkStyle().fontSize(14).underline(true).color("#1565c0").build())
                .diagnosticsProvider(vm -> List.of()).kindProvider(value -> "").messageProvider(value -> "")
                .helpTextProvider(vm -> "Download two CSV files and nine Excel workbooks for the current conversation. The model is not changed.")
                .build();
        var analysis = GroupDescription.newGroupDescription("keml-analysis-group")
                .idProvider(vm -> "keml-analysis-group-" + objectId(vm)).labelProvider(vm -> "Analysis")
                .semanticElementsProvider(vm -> List.of(vm.getVariables().get(VariableManager.SELF)))
                .controlDescriptions(List.of(link)).build();
        var standard = defaultForms.getFormDescription().getPageDescriptions().getFirst();
        var groups = new ArrayList<>(standard.getGroupDescriptions());
        groups.add(analysis);
        registry.add(PageDescription.newPageDescription("keml-conversation-details")
                .idProvider(standard.getIdProvider()).labelProvider(standard.getLabelProvider())
                .semanticElementsProvider(standard.getSemanticElementsProvider())
                .groupDescriptions(groups)
                .canCreatePredicate(vm -> vm.get(VariableManager.SELF, Conversation.class).isPresent()).build());
    }

    private String objectId(VariableManager vm) {
        return vm.get(VariableManager.SELF, Object.class).map(identity::getId).orElse("");
    }

    private String downloadUrl(VariableManager vm) {
        var context = vm.get(IEditingContext.EDITING_CONTEXT, IEditingContext.class).orElseThrow();
        return "/api/editingcontexts/" + encode(context.getId()) + "/keml/conversations/" + encode(objectId(vm)) + "/reports";
    }

    private static String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8).replace("+", "%20");
    }
}
