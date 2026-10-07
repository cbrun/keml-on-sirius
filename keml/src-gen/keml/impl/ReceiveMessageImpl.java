/**
 */
package keml.impl;

import java.util.Collection;

import keml.Information;
import keml.KemlPackage;
import keml.NewInformation;
import keml.ReceiveMessage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Receive Message</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link keml.impl.ReceiveMessageImpl#isIsInterrupted <em>Is Interrupted</em>}</li>
 *   <li>{@link keml.impl.ReceiveMessageImpl#getGenerates <em>Generates</em>}</li>
 *   <li>{@link keml.impl.ReceiveMessageImpl#getRepeats <em>Repeats</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReceiveMessageImpl extends MessageImpl implements ReceiveMessage {
	/**
	 * The default value of the '{@link #isIsInterrupted() <em>Is Interrupted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInterrupted()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_INTERRUPTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsInterrupted() <em>Is Interrupted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInterrupted()
	 * @generated
	 * @ordered
	 */
	protected boolean isInterrupted = IS_INTERRUPTED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGenerates() <em>Generates</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenerates()
	 * @generated
	 * @ordered
	 */
	protected EList<NewInformation> generates;

	/**
	 * The cached value of the '{@link #getRepeats() <em>Repeats</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepeats()
	 * @generated
	 * @ordered
	 */
	protected EList<Information> repeats;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReceiveMessageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KemlPackage.Literals.RECEIVE_MESSAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsInterrupted() {
		return isInterrupted;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsInterrupted(boolean newIsInterrupted) {
		boolean oldIsInterrupted = isInterrupted;
		isInterrupted = newIsInterrupted;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KemlPackage.RECEIVE_MESSAGE__IS_INTERRUPTED,
					oldIsInterrupted, isInterrupted));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<NewInformation> getGenerates() {
		if (generates == null) {
			generates = new EObjectContainmentWithInverseEList<NewInformation>(NewInformation.class, this,
					KemlPackage.RECEIVE_MESSAGE__GENERATES, KemlPackage.NEW_INFORMATION__SOURCE);
		}
		return generates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Information> getRepeats() {
		if (repeats == null) {
			repeats = new EObjectWithInverseResolvingEList.ManyInverse<Information>(Information.class, this,
					KemlPackage.RECEIVE_MESSAGE__REPEATS, KemlPackage.INFORMATION__REPEATED_BY);
		}
		return repeats;
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
		case KemlPackage.RECEIVE_MESSAGE__GENERATES:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getGenerates()).basicAdd(otherEnd, msgs);
		case KemlPackage.RECEIVE_MESSAGE__REPEATS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getRepeats()).basicAdd(otherEnd, msgs);
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
		case KemlPackage.RECEIVE_MESSAGE__GENERATES:
			return ((InternalEList<?>) getGenerates()).basicRemove(otherEnd, msgs);
		case KemlPackage.RECEIVE_MESSAGE__REPEATS:
			return ((InternalEList<?>) getRepeats()).basicRemove(otherEnd, msgs);
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
		case KemlPackage.RECEIVE_MESSAGE__IS_INTERRUPTED:
			return isIsInterrupted();
		case KemlPackage.RECEIVE_MESSAGE__GENERATES:
			return getGenerates();
		case KemlPackage.RECEIVE_MESSAGE__REPEATS:
			return getRepeats();
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
		case KemlPackage.RECEIVE_MESSAGE__IS_INTERRUPTED:
			setIsInterrupted((Boolean) newValue);
			return;
		case KemlPackage.RECEIVE_MESSAGE__GENERATES:
			getGenerates().clear();
			getGenerates().addAll((Collection<? extends NewInformation>) newValue);
			return;
		case KemlPackage.RECEIVE_MESSAGE__REPEATS:
			getRepeats().clear();
			getRepeats().addAll((Collection<? extends Information>) newValue);
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
		case KemlPackage.RECEIVE_MESSAGE__IS_INTERRUPTED:
			setIsInterrupted(IS_INTERRUPTED_EDEFAULT);
			return;
		case KemlPackage.RECEIVE_MESSAGE__GENERATES:
			getGenerates().clear();
			return;
		case KemlPackage.RECEIVE_MESSAGE__REPEATS:
			getRepeats().clear();
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
		case KemlPackage.RECEIVE_MESSAGE__IS_INTERRUPTED:
			return isInterrupted != IS_INTERRUPTED_EDEFAULT;
		case KemlPackage.RECEIVE_MESSAGE__GENERATES:
			return generates != null && !generates.isEmpty();
		case KemlPackage.RECEIVE_MESSAGE__REPEATS:
			return repeats != null && !repeats.isEmpty();
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
		result.append(" (isInterrupted: ");
		result.append(isInterrupted);
		result.append(')');
		return result.toString();
	}

} //ReceiveMessageImpl
