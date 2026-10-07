/**
 */
package keml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conversation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link keml.Conversation#getTitle <em>Title</em>}</li>
 *   <li>{@link keml.Conversation#getAuthor <em>Author</em>}</li>
 *   <li>{@link keml.Conversation#getConversationPartners <em>Conversation Partners</em>}</li>
 * </ul>
 *
 * @see keml.KemlPackage#getConversation()
 * @model
 * @generated
 */
public interface Conversation extends EObject {
	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see keml.KemlPackage#getConversation_Title()
	 * @model
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link keml.Conversation#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Author</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Author</em>' containment reference.
	 * @see #setAuthor(Author)
	 * @see keml.KemlPackage#getConversation_Author()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Author getAuthor();

	/**
	 * Sets the value of the '{@link keml.Conversation#getAuthor <em>Author</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Author</em>' containment reference.
	 * @see #getAuthor()
	 * @generated
	 */
	void setAuthor(Author value);

	/**
	 * Returns the value of the '<em><b>Conversation Partners</b></em>' containment reference list.
	 * The list contents are of type {@link keml.ConversationPartner}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conversation Partners</em>' containment reference list.
	 * @see keml.KemlPackage#getConversation_ConversationPartners()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConversationPartner> getConversationPartners();

} // Conversation
