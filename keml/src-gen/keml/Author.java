/**
 */
package keml;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Author</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The human main author of the conversation
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link keml.Author#getMessages <em>Messages</em>}</li>
 *   <li>{@link keml.Author#getPreknowledge <em>Preknowledge</em>}</li>
 * </ul>
 *
 * @see keml.KemlPackage#getAuthor()
 * @model
 * @generated
 */
public interface Author extends LifeLine {
	/**
	 * Returns the value of the '<em><b>Messages</b></em>' containment reference list.
	 * The list contents are of type {@link keml.Message}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Messages</em>' containment reference list.
	 * @see keml.KemlPackage#getAuthor_Messages()
	 * @model containment="true"
	 * @generated
	 */
	EList<Message> getMessages();

	/**
	 * Returns the value of the '<em><b>Preknowledge</b></em>' containment reference list.
	 * The list contents are of type {@link keml.PreKnowledge}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Preknowledge</em>' containment reference list.
	 * @see keml.KemlPackage#getAuthor_Preknowledge()
	 * @model containment="true"
	 * @generated
	 */
	EList<PreKnowledge> getPreknowledge();

} // Author
