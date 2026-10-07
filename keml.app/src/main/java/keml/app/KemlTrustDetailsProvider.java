package keml.app;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.components.collaborative.forms.services.api.IPropertiesDescriptionRegistry;
import org.eclipse.sirius.components.collaborative.forms.services.api.IPropertiesDescriptionRegistryConfigurer;
import org.eclipse.sirius.components.core.api.IIdentityService;
import org.eclipse.sirius.components.emf.forms.EMFFormDescriptionProvider;
import org.eclipse.sirius.components.emf.forms.api.IEMFFormDescriptionProvider;
import org.eclipse.sirius.components.emf.forms.api.IPropertiesValidationProvider;
import org.eclipse.sirius.components.emf.forms.api.IWidgetReadOnlyProvider;
import org.eclipse.sirius.components.forms.description.AbstractControlDescription;
import org.eclipse.sirius.components.forms.description.ForDescription;
import org.eclipse.sirius.components.forms.description.GroupDescription;
import org.eclipse.sirius.components.forms.description.IfDescription;
import org.eclipse.sirius.components.forms.description.PageDescription;
import org.eclipse.sirius.components.forms.description.TextfieldDescription;
import org.eclipse.sirius.components.representations.Failure;
import org.eclipse.sirius.components.representations.IStatus;
import org.eclipse.sirius.components.representations.Success;
import org.eclipse.sirius.components.representations.VariableManager;
import org.springframework.stereotype.Service;

import keml.Information;
import keml.KemlPackage;
import keml.PreKnowledge;

/**
 * Specializes two EMF properties while preserving the standard Details form.
 * A native textfield keeps Sirius's edit mutation, persistence and subscriptions;
 * the frontend recognizes its ID and renders a slider alongside the numeric input.
 */
@Service
public class KemlTrustDetailsProvider implements IPropertiesDescriptionRegistryConfigurer {
    private final IEMFFormDescriptionProvider defaultForms;
    private final TextfieldDescription trust;

    public KemlTrustDetailsProvider(IEMFFormDescriptionProvider defaultForms, IIdentityService identity,
            IPropertiesValidationProvider validation, IWidgetReadOnlyProvider readOnly) {
        this.defaultForms = defaultForms;
        this.trust = TextfieldDescription.newTextfieldDescription("keml-trust")
                .targetObjectIdProvider(vm -> identity.getId(vm.getVariables().get(VariableManager.SELF)))
                .idProvider(vm -> "keml-trust:" + identity.getId(vm.getVariables().get(VariableManager.SELF))
                        + ":" + EcoreUtil.getURI(feature(vm)))
                .labelProvider(vm -> feature(vm) == KemlPackage.Literals.INFORMATION__FELT_TRUST_AFTERWARDS
                        ? "Felt trust AFTER conversation" : "Initial trust")
                .valueProvider(vm -> {
                    var value = vm.get(VariableManager.SELF, Information.class).orElseThrow().eGet(feature(vm));
                    return value == null ? "" : value.toString();
                })
                .newValueHandler(KemlTrustDetailsProvider::editTrust)
                .isReadOnlyProvider(readOnly)
                .diagnosticsProvider(validation.getDiagnosticsProvider())
                .kindProvider(validation.getKindProvider()).messageProvider(validation.getMessageProvider())
                .helpTextProvider(vm -> "Trust ranges from -1 to +1; 0 is neutral. Clear the number to leave it unassessed.")
                .build();
    }

    @Override
    public void addPropertiesDescriptions(IPropertiesDescriptionRegistry registry) {
        var standard = defaultForms.getFormDescription().getPageDescriptions().getFirst();
        var groups = standard.getGroupDescriptions().stream().map(group -> GroupDescription.newGroupDescription(group.getId())
                .idProvider(group.getIdProvider()).labelProvider(group.getLabelProvider())
                .semanticElementsProvider(group.getSemanticElementsProvider()).displayModeProvider(group.getDisplayModeProvider())
                .toolbarActionDescriptions(group.getToolbarActionDescriptions()).borderStyleProvider(group.getBorderStyleProvider())
                .controlDescriptions(group.getControlDescriptions().stream().map(this::specialize).toList()).build()).toList();
        registry.add(PageDescription.newPageDescription("keml-information-details")
                .idProvider(standard.getIdProvider()).labelProvider(standard.getLabelProvider())
                .semanticElementsProvider(standard.getSemanticElementsProvider()).groupDescriptions(groups)
                .canCreatePredicate(vm -> vm.get(VariableManager.SELF, Information.class).isPresent()).build());
    }

    private AbstractControlDescription specialize(AbstractControlDescription control) {
        if (!(control instanceof ForDescription loop)) {
            return control;
        }
        // Reuse EMF's feature iteration and all ordinary widgets. Excluding the
        // special attributes from the default branches avoids duplicate fields.
        var controls = new ArrayList<AbstractControlDescription>();
        for (var child : loop.getControlDescriptions()) {
            if (child instanceof IfDescription branch) {
                controls.add(IfDescription.newIfDescription(branch.getId())
                        .targetObjectIdProvider(branch.getTargetObjectIdProvider())
                        .predicate(vm -> !isTrust(vm) && branch.getPredicate().apply(vm))
                        .controlDescriptions(branch.getControlDescriptions()).build());
            } else {
                controls.add(child);
            }
        }
        controls.add(IfDescription.newIfDescription("keml-trust-if")
                .targetObjectIdProvider(loop.getTargetObjectIdProvider()).predicate(KemlTrustDetailsProvider::isTrust)
                .controlDescriptions(List.of(trust)).build());
        return ForDescription.newForDescription(loop.getId()).targetObjectIdProvider(loop.getTargetObjectIdProvider())
                .iterator(loop.getIterator()).iterableProvider(loop.getIterableProvider()).controlDescriptions(controls).build();
    }

    private static EAttribute feature(VariableManager vm) {
        return vm.get(EMFFormDescriptionProvider.ESTRUCTURAL_FEATURE, EAttribute.class).orElseThrow();
    }

    static boolean isTrust(VariableManager vm) {
        var attribute = vm.getVariables().get(EMFFormDescriptionProvider.ESTRUCTURAL_FEATURE);
        return attribute == KemlPackage.Literals.INFORMATION__FELT_TRUST_AFTERWARDS
                || attribute == KemlPackage.Literals.INFORMATION__INITIAL_TRUST
                        && vm.get(VariableManager.SELF, PreKnowledge.class).isPresent();
    }

    static IStatus editTrust(VariableManager vm, String text) {
        var information = vm.get(VariableManager.SELF, Information.class);
        if (information.isEmpty() || !isTrust(vm)) {
            return new Failure("This property is not an editable KEML trust score.");
        }
        Float value = null;
        if (text != null && !text.isBlank()) {
            try {
                // Validate before changing the model, including NaN and infinity.
                double number = Double.parseDouble(text.strip());
                if (!Double.isFinite(number) || number < -1 || number > 1) {
                    return new Failure("Trust must be a finite number between -1 and +1.");
                }
                value = (float) number;
            } catch (NumberFormatException exception) {
                return new Failure("Trust must be a finite number between -1 and +1.");
            }
        }
        information.get().eSet(feature(vm), value);
        return new Success();
    }
}
