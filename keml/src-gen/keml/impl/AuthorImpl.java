/**
 */
package keml.impl;

import java.util.Collection;

import keml.Author;
import keml.KemlPackage;
import keml.Message;
import keml.PreKnowledge;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Author</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link keml.impl.AuthorImpl#getMessages <em>Messages</em>}</li>
 *   <li>{@link keml.impl.AuthorImpl#getPreknowledge <em>Preknowledge</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AuthorImpl extends LifeLineImpl implements Author {
	/**
	 * The cached value of the '{@link #getMessages() <em>Messages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessages()
	 * @generated
	 * @ordered
	 */
	protected EList<Message> messages;

	/**
	 * The cached value of the '{@link #getPreknowledge() <em>Preknowledge</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreknowledge()
	 * @generated
	 * @ordered
	 */
	protected EList<PreKnowledge> preknowledge;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AuthorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KemlPackage.Literals.AUTHOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Message> getMessages() {
		if (messages == null) {
			messages = new EObjectContainmentEList<Message>(Message.class, this, KemlPackage.AUTHOR__MESSAGES);
		}
		return messages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PreKnowledge> getPreknowledge() {
		if (preknowledge == null) {
			preknowledge = new EObjectContainmentEList<PreKnowledge>(PreKnowledge.class, this,
					KemlPackage.AUTHOR__PREKNOWLEDGE);
		}
		return preknowledge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case KemlPackage.AUTHOR__MESSAGES:
			return ((InternalEList<?>) getMessages()).basicRemove(otherEnd, msgs);
		case KemlPackage.AUTHOR__PREKNOWLEDGE:
			return ((InternalEList<?>) getPreknowledge()).basicRemove(otherEnd, msgs);
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
		case KemlPackage.AUTHOR__MESSAGES:
			return getMessages();
		case KemlPackage.AUTHOR__PREKNOWLEDGE:
			return getPreknowledge();
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
		case KemlPackage.AUTHOR__MESSAGES:
			getMessages().clear();
			getMessages().addAll((Collection<? extends Message>) newValue);
			return;
		case KemlPackage.AUTHOR__PREKNOWLEDGE:
			getPreknowledge().clear();
			getPreknowledge().addAll((Collection<? extends PreKnowledge>) newValue);
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
		case KemlPackage.AUTHOR__MESSAGES:
			getMessages().clear();
			return;
		case KemlPackage.AUTHOR__PREKNOWLEDGE:
			getPreknowledge().clear();
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
		case KemlPackage.AUTHOR__MESSAGES:
			return messages != null && !messages.isEmpty();
		case KemlPackage.AUTHOR__PREKNOWLEDGE:
			return preknowledge != null && !preknowledge.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AuthorImpl
