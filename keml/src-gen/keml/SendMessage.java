/**
 */
package keml;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Send Message</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link keml.SendMessage#getUses <em>Uses</em>}</li>
 * </ul>
 *
 * @see keml.KemlPackage#getSendMessage()
 * @model
 * @generated
 */
public interface SendMessage extends Message {
	/**
	 * Returns the value of the '<em><b>Uses</b></em>' reference list.
	 * The list contents are of type {@link keml.Information}.
	 * It is bidirectional and its opposite is '{@link keml.Information#getIsUsedOn <em>Is Used On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uses</em>' reference list.
	 * @see keml.KemlPackage#getSendMessage_Uses()
	 * @see keml.Information#getIsUsedOn
	 * @model opposite="isUsedOn"
	 * @generated
	 */
	EList<Information> getUses();

} // SendMessage
