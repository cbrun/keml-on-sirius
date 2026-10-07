/**
 */
package keml.impl;

import java.util.Collection;

import keml.Author;
import keml.Conversation;
import keml.ConversationPartner;
import keml.KemlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conversation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link keml.impl.ConversationImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link keml.impl.ConversationImpl#getAuthor <em>Author</em>}</li>
 *   <li>{@link keml.impl.ConversationImpl#getConversationPartners <em>Conversation Partners</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConversationImpl extends MinimalEObjectImpl.Container implements Conversation {
	/**
	 * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String TITLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected String title = TITLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAuthor() <em>Author</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
	protected Author author;

	/**
	 * The cached value of the '{@link #getConversationPartners() <em>Conversation Partners</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConversationPartners()
	 * @generated
	 * @ordered
	 */
	protected EList<ConversationPartner> conversationPartners;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConversationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KemlPackage.Literals.CONVERSATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTitle(String newTitle) {
		String oldTitle = title;
		title = newTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KemlPackage.CONVERSATION__TITLE, oldTitle, title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Author getAuthor() {
		return author;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAuthor(Author newAuthor, NotificationChain msgs) {
		Author oldAuthor = author;
		author = newAuthor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					KemlPackage.CONVERSATION__AUTHOR, oldAuthor, newAuthor);
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
	public void setAuthor(Author newAuthor) {
		if (newAuthor != author) {
			NotificationChain msgs = null;
			if (author != null)
				msgs = ((InternalEObject) author).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - KemlPackage.CONVERSATION__AUTHOR, null, msgs);
			if (newAuthor != null)
				msgs = ((InternalEObject) newAuthor).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - KemlPackage.CONVERSATION__AUTHOR, null, msgs);
			msgs = basicSetAuthor(newAuthor, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KemlPackage.CONVERSATION__AUTHOR, newAuthor,
					newAuthor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ConversationPartner> getConversationPartners() {
		if (conversationPartners == null) {
			conversationPartners = new EObjectContainmentEList<ConversationPartner>(ConversationPartner.class, this,
					KemlPackage.CONVERSATION__CONVERSATION_PARTNERS);
		}
		return conversationPartners;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case KemlPackage.CONVERSATION__AUTHOR:
			return basicSetAuthor(null, msgs);
		case KemlPackage.CONVERSATION__CONVERSATION_PARTNERS:
			return ((InternalEList<?>) getConversationPartners()).basicRemove(otherEnd, msgs);
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
		case KemlPackage.CONVERSATION__TITLE:
			return getTitle();
		case KemlPackage.CONVERSATION__AUTHOR:
			return getAuthor();
		case KemlPackage.CONVERSATION__CONVERSATION_PARTNERS:
			return getConversationPartners();
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
		case KemlPackage.CONVERSATION__TITLE:
			setTitle((String) newValue);
			return;
		case KemlPackage.CONVERSATION__AUTHOR:
			setAuthor((Author) newValue);
			return;
		case KemlPackage.CONVERSATION__CONVERSATION_PARTNERS:
			getConversationPartners().clear();
			getConversationPartners().addAll((Collection<? extends ConversationPartner>) newValue);
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
		case KemlPackage.CONVERSATION__TITLE:
			setTitle(TITLE_EDEFAULT);
			return;
		case KemlPackage.CONVERSATION__AUTHOR:
			setAuthor((Author) null);
			return;
		case KemlPackage.CONVERSATION__CONVERSATION_PARTNERS:
			getConversationPartners().clear();
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
		case KemlPackage.CONVERSATION__TITLE:
			return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
		case KemlPackage.CONVERSATION__AUTHOR:
			return author != null;
		case KemlPackage.CONVERSATION__CONVERSATION_PARTNERS:
			return conversationPartners != null && !conversationPartners.isEmpty();
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
		result.append(" (title: ");
		result.append(title);
		result.append(')');
		return result.toString();
	}

} //ConversationImpl
