/**
 */
package keml.impl;

import keml.Information;
import keml.InformationLink;
import keml.InformationLinkType;
import keml.KemlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Information Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link keml.impl.InformationLinkImpl#getLinkText <em>Link Text</em>}</li>
 *   <li>{@link keml.impl.InformationLinkImpl#getType <em>Type</em>}</li>
 *   <li>{@link keml.impl.InformationLinkImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link keml.impl.InformationLinkImpl#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InformationLinkImpl extends MinimalEObjectImpl.Container implements InformationLink {
	/**
	 * The default value of the '{@link #getLinkText() <em>Link Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkText()
	 * @generated
	 * @ordered
	 */
	protected static final String LINK_TEXT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLinkText() <em>Link Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkText()
	 * @generated
	 * @ordered
	 */
	protected String linkText = LINK_TEXT_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final InformationLinkType TYPE_EDEFAULT = InformationLinkType.SUPPLEMENT;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected InformationLinkType type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Information target;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InformationLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KemlPackage.Literals.INFORMATION_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLinkText() {
		return linkText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLinkText(String newLinkText) {
		String oldLinkText = linkText;
		linkText = newLinkText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KemlPackage.INFORMATION_LINK__LINK_TEXT, oldLinkText,
					linkText));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InformationLinkType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(InformationLinkType newType) {
		InformationLinkType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KemlPackage.INFORMATION_LINK__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Information getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject) target;
			target = (Information) eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KemlPackage.INFORMATION_LINK__TARGET,
							oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Information basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(Information newTarget, NotificationChain msgs) {
		Information oldTarget = target;
		target = newTarget;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					KemlPackage.INFORMATION_LINK__TARGET, oldTarget, newTarget);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTarget(Information newTarget) {
		if (newTarget != target) {
			NotificationChain msgs = null;
			if (target != null)
				msgs = ((InternalEObject) target).eInverseRemove(this, KemlPackage.INFORMATION__TARGETED_BY,
						Information.class, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject) newTarget).eInverseAdd(this, KemlPackage.INFORMATION__TARGETED_BY,
						Information.class, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KemlPackage.INFORMATION_LINK__TARGET, newTarget,
					newTarget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Information getSource() {
		if (eContainerFeatureID() != KemlPackage.INFORMATION_LINK__SOURCE)
			return null;
		return (Information) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSource(Information newSource, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newSource, KemlPackage.INFORMATION_LINK__SOURCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSource(Information newSource) {
		if (newSource != eInternalContainer()
				|| (eContainerFeatureID() != KemlPackage.INFORMATION_LINK__SOURCE && newSource != null)) {
			if (EcoreUtil.isAncestor(this, newSource))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSource != null)
				msgs = ((InternalEObject) newSource).eInverseAdd(this, KemlPackage.INFORMATION__CAUSES,
						Information.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KemlPackage.INFORMATION_LINK__SOURCE, newSource,
					newSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case KemlPackage.INFORMATION_LINK__TARGET:
			if (target != null)
				msgs = ((InternalEObject) target).eInverseRemove(this, KemlPackage.INFORMATION__TARGETED_BY,
						Information.class, msgs);
			return basicSetTarget((Information) otherEnd, msgs);
		case KemlPackage.INFORMATION_LINK__SOURCE:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetSource((Information) otherEnd, msgs);
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
		case KemlPackage.INFORMATION_LINK__TARGET:
			return basicSetTarget(null, msgs);
		case KemlPackage.INFORMATION_LINK__SOURCE:
			return basicSetSource(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case KemlPackage.INFORMATION_LINK__SOURCE:
			return eInternalContainer().eInverseRemove(this, KemlPackage.INFORMATION__CAUSES, Information.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case KemlPackage.INFORMATION_LINK__LINK_TEXT:
			return getLinkText();
		case KemlPackage.INFORMATION_LINK__TYPE:
			return getType();
		case KemlPackage.INFORMATION_LINK__TARGET:
			if (resolve)
				return getTarget();
			return basicGetTarget();
		case KemlPackage.INFORMATION_LINK__SOURCE:
			return getSource();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case KemlPackage.INFORMATION_LINK__LINK_TEXT:
			setLinkText((String) newValue);
			return;
		case KemlPackage.INFORMATION_LINK__TYPE:
			setType((InformationLinkType) newValue);
			return;
		case KemlPackage.INFORMATION_LINK__TARGET:
			setTarget((Information) newValue);
			return;
		case KemlPackage.INFORMATION_LINK__SOURCE:
			setSource((Information) newValue);
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
		case KemlPackage.INFORMATION_LINK__LINK_TEXT:
			setLinkText(LINK_TEXT_EDEFAULT);
			return;
		case KemlPackage.INFORMATION_LINK__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case KemlPackage.INFORMATION_LINK__TARGET:
			setTarget((Information) null);
			return;
		case KemlPackage.INFORMATION_LINK__SOURCE:
			setSource((Information) null);
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
		case KemlPackage.INFORMATION_LINK__LINK_TEXT:
			return LINK_TEXT_EDEFAULT == null ? linkText != null : !LINK_TEXT_EDEFAULT.equals(linkText);
		case KemlPackage.INFORMATION_LINK__TYPE:
			return type != TYPE_EDEFAULT;
		case KemlPackage.INFORMATION_LINK__TARGET:
			return target != null;
		case KemlPackage.INFORMATION_LINK__SOURCE:
			return getSource() != null;
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
		result.append(" (linkText: ");
		result.append(linkText);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //InformationLinkImpl
