/**
 */
package keml.impl;

import keml.ConversationPartner;
import keml.KemlPackage;
import keml.Message;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Message</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link keml.impl.MessageImpl#getContent <em>Content</em>}</li>
 *   <li>{@link keml.impl.MessageImpl#getTiming <em>Timing</em>}</li>
 *   <li>{@link keml.impl.MessageImpl#getCounterPart <em>Counter Part</em>}</li>
 *   <li>{@link keml.impl.MessageImpl#getOriginalContent <em>Original Content</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class MessageImpl extends MinimalEObjectImpl.Container implements Message {
	/**
	 * The default value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected String content = CONTENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTiming() <em>Timing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTiming()
	 * @generated
	 * @ordered
	 */
	protected static final int TIMING_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTiming() <em>Timing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTiming()
	 * @generated
	 * @ordered
	 */
	protected int timing = TIMING_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCounterPart() <em>Counter Part</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCounterPart()
	 * @generated
	 * @ordered
	 */
	protected ConversationPartner counterPart;

	/**
	 * The default value of the '{@link #getOriginalContent() <em>Original Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalContent()
	 * @generated
	 * @ordered
	 */
	protected static final String ORIGINAL_CONTENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOriginalContent() <em>Original Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOriginalContent()
	 * @generated
	 * @ordered
	 */
	protected String originalContent = ORIGINAL_CONTENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MessageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KemlPackage.Literals.MESSAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getContent() {
		return content;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContent(String newContent) {
		String oldContent = content;
		content = newContent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KemlPackage.MESSAGE__CONTENT, oldContent, content));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getTiming() {
		return timing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTiming(int newTiming) {
		int oldTiming = timing;
		timing = newTiming;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KemlPackage.MESSAGE__TIMING, oldTiming, timing));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConversationPartner getCounterPart() {
		if (counterPart != null && counterPart.eIsProxy()) {
			InternalEObject oldCounterPart = (InternalEObject) counterPart;
			counterPart = (ConversationPartner) eResolveProxy(oldCounterPart);
			if (counterPart != oldCounterPart) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KemlPackage.MESSAGE__COUNTER_PART,
							oldCounterPart, counterPart));
			}
		}
		return counterPart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConversationPartner basicGetCounterPart() {
		return counterPart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCounterPart(ConversationPartner newCounterPart) {
		ConversationPartner oldCounterPart = counterPart;
		counterPart = newCounterPart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KemlPackage.MESSAGE__COUNTER_PART, oldCounterPart,
					counterPart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOriginalContent() {
		return originalContent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOriginalContent(String newOriginalContent) {
		String oldOriginalContent = originalContent;
		originalContent = newOriginalContent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KemlPackage.MESSAGE__ORIGINAL_CONTENT,
					oldOriginalContent, originalContent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case KemlPackage.MESSAGE__CONTENT:
			return getContent();
		case KemlPackage.MESSAGE__TIMING:
			return getTiming();
		case KemlPackage.MESSAGE__COUNTER_PART:
			if (resolve)
				return getCounterPart();
			return basicGetCounterPart();
		case KemlPackage.MESSAGE__ORIGINAL_CONTENT:
			return getOriginalContent();
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
		case KemlPackage.MESSAGE__CONTENT:
			setContent((String) newValue);
			return;
		case KemlPackage.MESSAGE__TIMING:
			setTiming((Integer) newValue);
			return;
		case KemlPackage.MESSAGE__COUNTER_PART:
			setCounterPart((ConversationPartner) newValue);
			return;
		case KemlPackage.MESSAGE__ORIGINAL_CONTENT:
			setOriginalContent((String) newValue);
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
		case KemlPackage.MESSAGE__CONTENT:
			setContent(CONTENT_EDEFAULT);
			return;
		case KemlPackage.MESSAGE__TIMING:
			setTiming(TIMING_EDEFAULT);
			return;
		case KemlPackage.MESSAGE__COUNTER_PART:
			setCounterPart((ConversationPartner) null);
			return;
		case KemlPackage.MESSAGE__ORIGINAL_CONTENT:
			setOriginalContent(ORIGINAL_CONTENT_EDEFAULT);
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
		case KemlPackage.MESSAGE__CONTENT:
			return CONTENT_EDEFAULT == null ? content != null : !CONTENT_EDEFAULT.equals(content);
		case KemlPackage.MESSAGE__TIMING:
			return timing != TIMING_EDEFAULT;
		case KemlPackage.MESSAGE__COUNTER_PART:
			return counterPart != null;
		case KemlPackage.MESSAGE__ORIGINAL_CONTENT:
			return ORIGINAL_CONTENT_EDEFAULT == null ? originalContent != null
					: !ORIGINAL_CONTENT_EDEFAULT.equals(originalContent);
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
		result.append(" (content: ");
		result.append(content);
		result.append(", timing: ");
		result.append(timing);
		result.append(", originalContent: ");
		result.append(originalContent);
		result.append(')');
		return result.toString();
	}

} //MessageImpl
