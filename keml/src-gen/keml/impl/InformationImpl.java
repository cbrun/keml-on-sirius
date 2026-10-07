/**
 */
package keml.impl;

import java.util.Collection;

import keml.Information;
import keml.InformationLink;
import keml.KemlPackage;
import keml.ReceiveMessage;
import keml.SendMessage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Information</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link keml.impl.InformationImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link keml.impl.InformationImpl#isIsInstruction <em>Is Instruction</em>}</li>
 *   <li>{@link keml.impl.InformationImpl#getRepeatedBy <em>Repeated By</em>}</li>
 *   <li>{@link keml.impl.InformationImpl#getTargetedBy <em>Targeted By</em>}</li>
 *   <li>{@link keml.impl.InformationImpl#getCauses <em>Causes</em>}</li>
 *   <li>{@link keml.impl.InformationImpl#getIsUsedOn <em>Is Used On</em>}</li>
 *   <li>{@link keml.impl.InformationImpl#getFeltTrustImmediately <em>Felt Trust Immediately</em>}</li>
 *   <li>{@link keml.impl.InformationImpl#getFeltTrustAfterwards <em>Felt Trust Afterwards</em>}</li>
 *   <li>{@link keml.impl.InformationImpl#getInitialTrust <em>Initial Trust</em>}</li>
 *   <li>{@link keml.impl.InformationImpl#getCurrentTrust <em>Current Trust</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class InformationImpl extends MinimalEObjectImpl.Container implements Information {
	/**
	 * The default value of the '{@link #getMessage() <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessage()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessage() <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessage()
	 * @generated
	 * @ordered
	 */
	protected String message = MESSAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsInstruction() <em>Is Instruction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInstruction()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_INSTRUCTION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsInstruction() <em>Is Instruction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInstruction()
	 * @generated
	 * @ordered
	 */
	protected boolean isInstruction = IS_INSTRUCTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRepeatedBy() <em>Repeated By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepeatedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<ReceiveMessage> repeatedBy;

	/**
	 * The cached value of the '{@link #getTargetedBy() <em>Targeted By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<InformationLink> targetedBy;

	/**
	 * The cached value of the '{@link #getCauses() <em>Causes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCauses()
	 * @generated
	 * @ordered
	 */
	protected EList<InformationLink> causes;

	/**
	 * The cached value of the '{@link #getIsUsedOn() <em>Is Used On</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsUsedOn()
	 * @generated
	 * @ordered
	 */
	protected EList<SendMessage> isUsedOn;

	/**
	 * The default value of the '{@link #getFeltTrustImmediately() <em>Felt Trust Immediately</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeltTrustImmediately()
	 * @generated
	 * @ordered
	 */
	protected static final Float FELT_TRUST_IMMEDIATELY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFeltTrustImmediately() <em>Felt Trust Immediately</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeltTrustImmediately()
	 * @generated
	 * @ordered
	 */
	protected Float feltTrustImmediately = FELT_TRUST_IMMEDIATELY_EDEFAULT;

	/**
	 * The default value of the '{@link #getFeltTrustAfterwards() <em>Felt Trust Afterwards</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeltTrustAfterwards()
	 * @generated
	 * @ordered
	 */
	protected static final Float FELT_TRUST_AFTERWARDS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFeltTrustAfterwards() <em>Felt Trust Afterwards</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeltTrustAfterwards()
	 * @generated
	 * @ordered
	 */
	protected Float feltTrustAfterwards = FELT_TRUST_AFTERWARDS_EDEFAULT;

	/**
	 * The default value of the '{@link #getInitialTrust() <em>Initial Trust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialTrust()
	 * @generated
	 * @ordered
	 */
	protected static final Float INITIAL_TRUST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInitialTrust() <em>Initial Trust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialTrust()
	 * @generated
	 * @ordered
	 */
	protected Float initialTrust = INITIAL_TRUST_EDEFAULT;

	/**
	 * The default value of the '{@link #getCurrentTrust() <em>Current Trust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrentTrust()
	 * @generated
	 * @ordered
	 */
	protected static final Float CURRENT_TRUST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCurrentTrust() <em>Current Trust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrentTrust()
	 * @generated
	 * @ordered
	 */
	protected Float currentTrust = CURRENT_TRUST_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InformationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KemlPackage.Literals.INFORMATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMessage(String newMessage) {
		String oldMessage = message;
		message = newMessage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KemlPackage.INFORMATION__MESSAGE, oldMessage,
					message));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsInstruction() {
		return isInstruction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsInstruction(boolean newIsInstruction) {
		boolean oldIsInstruction = isInstruction;
		isInstruction = newIsInstruction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KemlPackage.INFORMATION__IS_INSTRUCTION,
					oldIsInstruction, isInstruction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ReceiveMessage> getRepeatedBy() {
		if (repeatedBy == null) {
			repeatedBy = new EObjectWithInverseResolvingEList.ManyInverse<ReceiveMessage>(ReceiveMessage.class, this,
					KemlPackage.INFORMATION__REPEATED_BY, KemlPackage.RECEIVE_MESSAGE__REPEATS);
		}
		return repeatedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<InformationLink> getTargetedBy() {
		if (targetedBy == null) {
			targetedBy = new EObjectWithInverseResolvingEList<InformationLink>(InformationLink.class, this,
					KemlPackage.INFORMATION__TARGETED_BY, KemlPackage.INFORMATION_LINK__TARGET);
		}
		return targetedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<InformationLink> getCauses() {
		if (causes == null) {
			causes = new EObjectContainmentWithInverseEList<InformationLink>(InformationLink.class, this,
					KemlPackage.INFORMATION__CAUSES, KemlPackage.INFORMATION_LINK__SOURCE);
		}
		return causes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<SendMessage> getIsUsedOn() {
		if (isUsedOn == null) {
			isUsedOn = new EObjectWithInverseResolvingEList.ManyInverse<SendMessage>(SendMessage.class, this,
					KemlPackage.INFORMATION__IS_USED_ON, KemlPackage.SEND_MESSAGE__USES);
		}
		return isUsedOn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getFeltTrustImmediately() {
		return feltTrustImmediately;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFeltTrustImmediately(Float newFeltTrustImmediately) {
		Float oldFeltTrustImmediately = feltTrustImmediately;
		feltTrustImmediately = newFeltTrustImmediately;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KemlPackage.INFORMATION__FELT_TRUST_IMMEDIATELY,
					oldFeltTrustImmediately, feltTrustImmediately));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getFeltTrustAfterwards() {
		return feltTrustAfterwards;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFeltTrustAfterwards(Float newFeltTrustAfterwards) {
		Float oldFeltTrustAfterwards = feltTrustAfterwards;
		feltTrustAfterwards = newFeltTrustAfterwards;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KemlPackage.INFORMATION__FELT_TRUST_AFTERWARDS,
					oldFeltTrustAfterwards, feltTrustAfterwards));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getInitialTrust() {
		return initialTrust;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInitialTrust(Float newInitialTrust) {
		Float oldInitialTrust = initialTrust;
		initialTrust = newInitialTrust;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KemlPackage.INFORMATION__INITIAL_TRUST,
					oldInitialTrust, initialTrust));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Float getCurrentTrust() {
		return currentTrust;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCurrentTrust(Float newCurrentTrust) {
		Float oldCurrentTrust = currentTrust;
		currentTrust = newCurrentTrust;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KemlPackage.INFORMATION__CURRENT_TRUST,
					oldCurrentTrust, currentTrust));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case KemlPackage.INFORMATION__REPEATED_BY:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getRepeatedBy()).basicAdd(otherEnd, msgs);
		case KemlPackage.INFORMATION__TARGETED_BY:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getTargetedBy()).basicAdd(otherEnd, msgs);
		case KemlPackage.INFORMATION__CAUSES:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getCauses()).basicAdd(otherEnd, msgs);
		case KemlPackage.INFORMATION__IS_USED_ON:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getIsUsedOn()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case KemlPackage.INFORMATION__REPEATED_BY:
			return ((InternalEList<?>) getRepeatedBy()).basicRemove(otherEnd, msgs);
		case KemlPackage.INFORMATION__TARGETED_BY:
			return ((InternalEList<?>) getTargetedBy()).basicRemove(otherEnd, msgs);
		case KemlPackage.INFORMATION__CAUSES:
			return ((InternalEList<?>) getCauses()).basicRemove(otherEnd, msgs);
		case KemlPackage.INFORMATION__IS_USED_ON:
			return ((InternalEList<?>) getIsUsedOn()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case KemlPackage.INFORMATION__MESSAGE:
			return getMessage();
		case KemlPackage.INFORMATION__IS_INSTRUCTION:
			return isIsInstruction();
		case KemlPackage.INFORMATION__REPEATED_BY:
			return getRepeatedBy();
		case KemlPackage.INFORMATION__TARGETED_BY:
			return getTargetedBy();
		case KemlPackage.INFORMATION__CAUSES:
			return getCauses();
		case KemlPackage.INFORMATION__IS_USED_ON:
			return getIsUsedOn();
		case KemlPackage.INFORMATION__FELT_TRUST_IMMEDIATELY:
			return getFeltTrustImmediately();
		case KemlPackage.INFORMATION__FELT_TRUST_AFTERWARDS:
			return getFeltTrustAfterwards();
		case KemlPackage.INFORMATION__INITIAL_TRUST:
			return getInitialTrust();
		case KemlPackage.INFORMATION__CURRENT_TRUST:
			return getCurrentTrust();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case KemlPackage.INFORMATION__MESSAGE:
			setMessage((String) newValue);
			return;
		case KemlPackage.INFORMATION__IS_INSTRUCTION:
			setIsInstruction((Boolean) newValue);
			return;
		case KemlPackage.INFORMATION__REPEATED_BY:
			getRepeatedBy().clear();
			getRepeatedBy().addAll((Collection<? extends ReceiveMessage>) newValue);
			return;
		case KemlPackage.INFORMATION__TARGETED_BY:
			getTargetedBy().clear();
			getTargetedBy().addAll((Collection<? extends InformationLink>) newValue);
			return;
		case KemlPackage.INFORMATION__CAUSES:
			getCauses().clear();
			getCauses().addAll((Collection<? extends InformationLink>) newValue);
			return;
		case KemlPackage.INFORMATION__IS_USED_ON:
			getIsUsedOn().clear();
			getIsUsedOn().addAll((Collection<? extends SendMessage>) newValue);
			return;
		case KemlPackage.INFORMATION__FELT_TRUST_IMMEDIATELY:
			setFeltTrustImmediately((Float) newValue);
			return;
		case KemlPackage.INFORMATION__FELT_TRUST_AFTERWARDS:
			setFeltTrustAfterwards((Float) newValue);
			return;
		case KemlPackage.INFORMATION__INITIAL_TRUST:
			setInitialTrust((Float) newValue);
			return;
		case KemlPackage.INFORMATION__CURRENT_TRUST:
			setCurrentTrust((Float) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case KemlPackage.INFORMATION__MESSAGE:
			setMessage(MESSAGE_EDEFAULT);
			return;
		case KemlPackage.INFORMATION__IS_INSTRUCTION:
			setIsInstruction(IS_INSTRUCTION_EDEFAULT);
			return;
		case KemlPackage.INFORMATION__REPEATED_BY:
			getRepeatedBy().clear();
			return;
		case KemlPackage.INFORMATION__TARGETED_BY:
			getTargetedBy().clear();
			return;
		case KemlPackage.INFORMATION__CAUSES:
			getCauses().clear();
			return;
		case KemlPackage.INFORMATION__IS_USED_ON:
			getIsUsedOn().clear();
			return;
		case KemlPackage.INFORMATION__FELT_TRUST_IMMEDIATELY:
			setFeltTrustImmediately(FELT_TRUST_IMMEDIATELY_EDEFAULT);
			return;
		case KemlPackage.INFORMATION__FELT_TRUST_AFTERWARDS:
			setFeltTrustAfterwards(FELT_TRUST_AFTERWARDS_EDEFAULT);
			return;
		case KemlPackage.INFORMATION__INITIAL_TRUST:
			setInitialTrust(INITIAL_TRUST_EDEFAULT);
			return;
		case KemlPackage.INFORMATION__CURRENT_TRUST:
			setCurrentTrust(CURRENT_TRUST_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case KemlPackage.INFORMATION__MESSAGE:
			return MESSAGE_EDEFAULT == null ? message != null : !MESSAGE_EDEFAULT.equals(message);
		case KemlPackage.INFORMATION__IS_INSTRUCTION:
			return isInstruction != IS_INSTRUCTION_EDEFAULT;
		case KemlPackage.INFORMATION__REPEATED_BY:
			return repeatedBy != null && !repeatedBy.isEmpty();
		case KemlPackage.INFORMATION__TARGETED_BY:
			return targetedBy != null && !targetedBy.isEmpty();
		case KemlPackage.INFORMATION__CAUSES:
			return causes != null && !causes.isEmpty();
		case KemlPackage.INFORMATION__IS_USED_ON:
			return isUsedOn != null && !isUsedOn.isEmpty();
		case KemlPackage.INFORMATION__FELT_TRUST_IMMEDIATELY:
			return FELT_TRUST_IMMEDIATELY_EDEFAULT == null ? feltTrustImmediately != null
					: !FELT_TRUST_IMMEDIATELY_EDEFAULT.equals(feltTrustImmediately);
		case KemlPackage.INFORMATION__FELT_TRUST_AFTERWARDS:
			return FELT_TRUST_AFTERWARDS_EDEFAULT == null ? feltTrustAfterwards != null
					: !FELT_TRUST_AFTERWARDS_EDEFAULT.equals(feltTrustAfterwards);
		case KemlPackage.INFORMATION__INITIAL_TRUST:
			return INITIAL_TRUST_EDEFAULT == null ? initialTrust != null : !INITIAL_TRUST_EDEFAULT.equals(initialTrust);
		case KemlPackage.INFORMATION__CURRENT_TRUST:
			return CURRENT_TRUST_EDEFAULT == null ? currentTrust != null : !CURRENT_TRUST_EDEFAULT.equals(currentTrust);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (message: ");
		result.append(message);
		result.append(", isInstruction: ");
		result.append(isInstruction);
		result.append(", feltTrustImmediately: ");
		result.append(feltTrustImmediately);
		result.append(", feltTrustAfterwards: ");
		result.append(feltTrustAfterwards);
		result.append(", initialTrust: ");
		result.append(initialTrust);
		result.append(", currentTrust: ");
		result.append(currentTrust);
		result.append(')');
		return result.toString();
	}

} //InformationImpl
