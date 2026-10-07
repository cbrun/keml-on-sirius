package keml.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.eclipse.sirius.components.collaborative.forms.services.api.IPropertiesDescriptionRegistry;
import org.eclipse.sirius.components.core.api.IIdentityService;
import org.eclipse.sirius.components.emf.forms.EMFFormDescriptionProvider;
import org.eclipse.sirius.components.emf.forms.api.IEMFFormDescriptionProvider;
import org.eclipse.sirius.components.emf.forms.api.IPropertiesValidationProvider;
import org.eclipse.sirius.components.forms.description.ForDescription;
import org.eclipse.sirius.components.forms.description.FormDescription;
import org.eclipse.sirius.components.forms.description.GroupDescription;
import org.eclipse.sirius.components.forms.description.IfDescription;
import org.eclipse.sirius.components.forms.description.PageDescription;
import org.eclipse.sirius.components.forms.description.TextfieldDescription;
import org.eclipse.sirius.components.representations.Failure;
import org.eclipse.sirius.components.representations.Success;
import org.eclipse.sirius.components.representations.VariableManager;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import keml.Information;
import keml.KemlFactory;
import keml.KemlPackage;

class KemlTrustDetailsTests {
    @Test
    void preservesPrecisionAndUnsetValuesAndRejectsInvalidEditsWithoutChangingTheModel() {
        for (var information : List.of(KemlFactory.eINSTANCE.createPreKnowledge(), KemlFactory.eINSTANCE.createNewInformation())) {
            var vm = variables(information);
            vm.put(EMFFormDescriptionProvider.ESTRUCTURAL_FEATURE, KemlPackage.Literals.INFORMATION__FELT_TRUST_AFTERWARDS);
            for (var value : List.of("-1", "0", "0.25", "1")) {
                assertThat(KemlTrustDetailsProvider.editTrust(vm, value)).isInstanceOf(Success.class);
                assertThat(information.getFeltTrustAfterwards()).isEqualTo(Float.valueOf(value));
            }
            for (var invalid : List.of("-1.1", "1.1", "1.00000001", "NaN", "Infinity", "-Infinity", "invalid", "1e400")) {
                assertThat(KemlTrustDetailsProvider.editTrust(vm, invalid)).isInstanceOf(Failure.class);
                assertThat(information.getFeltTrustAfterwards()).isEqualTo(1f);
            }
            assertThat(KemlTrustDetailsProvider.editTrust(vm, "")).isInstanceOf(Success.class);
            assertThat(information.getFeltTrustAfterwards()).isNull();
            vm.put(EMFFormDescriptionProvider.ESTRUCTURAL_FEATURE, KemlPackage.Literals.INFORMATION__CURRENT_TRUST);
            assertThat(KemlTrustDetailsProvider.editTrust(vm, "0.5")).isInstanceOf(Failure.class);
            assertThat(information.getCurrentTrust()).isNull();
        }
    }

    @Test
    void replacesOnlyTheRelevantFieldsAndKeepsReadOnlyAndNativeEditSupport() {
        var defaults = mock(IEMFFormDescriptionProvider.class);
        var form = mock(FormDescription.class);
        var original = IfDescription.newIfDescription("ordinary-widget").targetObjectIdProvider(vm -> "object")
                .predicate(vm -> true).controlDescriptions(List.of()).build();
        var loop = ForDescription.newForDescription("features").targetObjectIdProvider(vm -> "object")
                .iterator(EMFFormDescriptionProvider.ESTRUCTURAL_FEATURE).iterableProvider(vm -> List.of())
                .controlDescriptions(List.of(original)).build();
        var group = GroupDescription.newGroupDescription("core").idProvider(vm -> "core").labelProvider(vm -> "Core")
                .semanticElementsProvider(vm -> List.of(vm.getVariables().get(VariableManager.SELF)))
                .controlDescriptions(List.of(loop)).build();
        var page = PageDescription.newPageDescription("default").idProvider(vm -> "page").labelProvider(vm -> "Properties")
                .semanticElementsProvider(group.getSemanticElementsProvider()).groupDescriptions(List.of(group)).canCreatePredicate(vm -> true).build();
        when(defaults.getFormDescription()).thenReturn(form);
        when(form.getPageDescriptions()).thenReturn(List.of(page));
        var registry = mock(IPropertiesDescriptionRegistry.class);
        new KemlTrustDetailsProvider(defaults, mock(IIdentityService.class), new IPropertiesValidationProvider.NoOp(), vm -> true)
                .addPropertiesDescriptions(registry);
        var captured = ArgumentCaptor.forClass(PageDescription.class);
        verify(registry).add(captured.capture());
        var customized = captured.getValue();
        var branches = ((ForDescription) customized.getGroupDescriptions().getFirst().getControlDescriptions().getFirst())
                .getControlDescriptions().stream().map(IfDescription.class::cast).toList();
        var trust = (TextfieldDescription) branches.getLast().getControlDescriptions().getFirst();
        var preKnowledge = KemlFactory.eINSTANCE.createPreKnowledge();
        var vm = variables(preKnowledge);
        assertThat(customized.getCanCreatePredicate().test(vm)).isTrue();
        vm.put(EMFFormDescriptionProvider.ESTRUCTURAL_FEATURE, KemlPackage.Literals.INFORMATION__FELT_TRUST_AFTERWARDS);
        assertThat(branches.getFirst().getPredicate().apply(vm)).isFalse();
        assertThat(branches.getLast().getPredicate().apply(vm)).isTrue();
        assertThat(trust.getIsReadOnlyProvider().apply(vm)).isTrue();
        assertThat(trust.getValueProvider().apply(vm)).isEmpty();
        assertThat(trust.getNewValueHandler().apply(vm, "0.25")).isInstanceOf(Success.class);
        assertThat(trust.getValueProvider().apply(vm)).isEqualTo("0.25");
        assertThat(trust.getIdProvider().apply(vm)).startsWith("keml-trust:").endsWith("#//Information/feltTrustAfterwards");
        vm.put(EMFFormDescriptionProvider.ESTRUCTURAL_FEATURE, KemlPackage.Literals.INFORMATION__INITIAL_TRUST);
        assertThat(branches.getLast().getPredicate().apply(vm)).isTrue();
        assertThat(trust.getNewValueHandler().apply(vm, "-0.5")).isInstanceOf(Success.class);
        assertThat(preKnowledge.getInitialTrust()).isEqualTo(-0.5f);
        vm.put(VariableManager.SELF, KemlFactory.eINSTANCE.createNewInformation());
        assertThat(branches.getFirst().getPredicate().apply(vm)).isTrue();
        assertThat(branches.getLast().getPredicate().apply(vm)).isFalse();
        vm.put(VariableManager.SELF, KemlFactory.eINSTANCE.createConversation());
        assertThat(customized.getCanCreatePredicate().test(vm)).isFalse();
    }

    private VariableManager variables(Information information) {
        var vm = new VariableManager();
        vm.put(VariableManager.SELF, information);
        return vm;
    }
}
