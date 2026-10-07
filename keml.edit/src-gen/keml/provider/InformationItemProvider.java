/**
 */
package keml.provider;

import java.util.Collection;
import java.util.List;

import keml.Information;
import keml.KemlFactory;
import keml.KemlPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link keml.Information} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class InformationItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InformationItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addMessagePropertyDescriptor(object);
			addIsInstructionPropertyDescriptor(object);
			addRepeatedByPropertyDescriptor(object);
			addTargetedByPropertyDescriptor(object);
			addCausesPropertyDescriptor(object);
			addIsUsedOnPropertyDescriptor(object);
			addFeltTrustImmediatelyPropertyDescriptor(object);
			addFeltTrustAfterwardsPropertyDescriptor(object);
			addInitialTrustPropertyDescriptor(object);
			addCurrentTrustPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Is Used On feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsUsedOnPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Information_isUsedOn_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Information_isUsedOn_feature",
								"_UI_Information_type"),
						KemlPackage.Literals.INFORMATION__IS_USED_ON, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Felt Trust Immediately feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFeltTrustImmediatelyPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Information_feltTrustImmediately_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Information_feltTrustImmediately_feature",
								"_UI_Information_type"),
						KemlPackage.Literals.INFORMATION__FELT_TRUST_IMMEDIATELY, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Felt Trust Afterwards feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFeltTrustAfterwardsPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Information_feltTrustAfterwards_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Information_feltTrustAfterwards_feature",
								"_UI_Information_type"),
						KemlPackage.Literals.INFORMATION__FELT_TRUST_AFTERWARDS, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Message feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMessagePropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Information_message_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Information_message_feature",
								"_UI_Information_type"),
						KemlPackage.Literals.INFORMATION__MESSAGE, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Is Instruction feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIsInstructionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Information_isInstruction_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Information_isInstruction_feature",
								"_UI_Information_type"),
						KemlPackage.Literals.INFORMATION__IS_INSTRUCTION, true, false, false,
						ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Initial Trust feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInitialTrustPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Information_initialTrust_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Information_initialTrust_feature",
								"_UI_Information_type"),
						KemlPackage.Literals.INFORMATION__INITIAL_TRUST, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Current Trust feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCurrentTrustPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Information_currentTrust_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Information_currentTrust_feature",
								"_UI_Information_type"),
						KemlPackage.Literals.INFORMATION__CURRENT_TRUST, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	 * This adds a property descriptor for the Repeated By feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRepeatedByPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Information_repeatedBy_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Information_repeatedBy_feature",
								"_UI_Information_type"),
						KemlPackage.Literals.INFORMATION__REPEATED_BY, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Targeted By feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetedByPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Information_targetedBy_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Information_targetedBy_feature",
								"_UI_Information_type"),
						KemlPackage.Literals.INFORMATION__TARGETED_BY, true, false, true, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Causes feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCausesPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_Information_causes_feature"),
						getString("_UI_PropertyDescriptor_description", "_UI_Information_causes_feature",
								"_UI_Information_type"),
						KemlPackage.Literals.INFORMATION__CAUSES, true, false, true, null, null, null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(KemlPackage.Literals.INFORMATION__CAUSES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean shouldComposeCreationImage() {
		return true;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Information) object).getMessage();
		return label == null || label.length() == 0 ? getString("_UI_Information_type")
				: getString("_UI_Information_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Information.class)) {
		case KemlPackage.INFORMATION__MESSAGE:
		case KemlPackage.INFORMATION__IS_INSTRUCTION:
		case KemlPackage.INFORMATION__FELT_TRUST_IMMEDIATELY:
		case KemlPackage.INFORMATION__FELT_TRUST_AFTERWARDS:
		case KemlPackage.INFORMATION__INITIAL_TRUST:
		case KemlPackage.INFORMATION__CURRENT_TRUST:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case KemlPackage.INFORMATION__CAUSES:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(KemlPackage.Literals.INFORMATION__CAUSES,
				KemlFactory.eINSTANCE.createInformationLink()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return KemlEditPlugin.INSTANCE;
	}

}
