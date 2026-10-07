/**
 */
package keml;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Receive Message</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link keml.ReceiveMessage#isIsInterrupted <em>Is Interrupted</em>}</li>
 *   <li>{@link keml.ReceiveMessage#getGenerates <em>Generates</em>}</li>
 *   <li>{@link keml.ReceiveMessage#getRepeats <em>Repeats</em>}</li>
 * </ul>
 *
 * @see keml.KemlPackage#getReceiveMessage()
 * @model
 * @generated
 */
public interface ReceiveMessage extends Message {
	/**
	 * Returns the value of the '<em><b>Is Interrupted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Interrupted</em>' attribute.
	 * @see #setIsInterrupted(boolean)
	 * @see keml.KemlPackage#getReceiveMessage_IsInterrupted()
	 * @model
	 * @generated
	 */
	boolean isIsInterrupted();

	/**
	 * Sets the value of the '{@link keml.ReceiveMessage#isIsInterrupted <em>Is Interrupted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Interrupted</em>' attribute.
	 * @see #isIsInterrupted()
	 * @generated
	 */
	void setIsInterrupted(boolean value);

	/**
	 * Returns the value of the '<em><b>Generates</b></em>' containment reference list.
	 * The list contents are of type {@link keml.NewInformation}.
	 * It is bidirectional and its opposite is '{@link keml.NewInformation#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generates</em>' containment reference list.
	 * @see keml.KemlPackage#getReceiveMessage_Generates()
	 * @see keml.NewInformation#getSource
	 * @model opposite="source" containment="true"
	 * @generated
	 */
	EList<NewInformation> getGenerates();

	/**
	 * Returns the value of the '<em><b>Repeats</b></em>' reference list.
	 * The list contents are of type {@link keml.Information}.
	 * It is bidirectional and its opposite is '{@link keml.Information#getRepeatedBy <em>Repeated By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repeats</em>' reference list.
	 * @see keml.KemlPackage#getReceiveMessage_Repeats()
	 * @see keml.Information#getRepeatedBy
	 * @model opposite="repeatedBy"
	 * @generated
	 */
	EList<Information> getRepeats();

} // ReceiveMessage
