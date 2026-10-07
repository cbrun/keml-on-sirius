/**
 */
package keml;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>New Information</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link keml.NewInformation#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @see keml.KemlPackage#getNewInformation()
 * @model
 * @generated
 */
public interface NewInformation extends Information {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link keml.ReceiveMessage#getGenerates <em>Generates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' container reference.
	 * @see #setSource(ReceiveMessage)
	 * @see keml.KemlPackage#getNewInformation_Source()
	 * @see keml.ReceiveMessage#getGenerates
	 * @model opposite="generates" required="true" transient="false"
	 * @generated
	 */
	ReceiveMessage getSource();

	/**
	 * Sets the value of the '{@link keml.NewInformation#getSource <em>Source</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' container reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(ReceiveMessage value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	ConversationPartner getSourceConversationPartner();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	int getTiming();

} // NewInformation
