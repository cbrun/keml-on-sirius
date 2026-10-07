/**
 */
package keml;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conversation Partner</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Conversation partners are for example the LLM and a web browser, more or less are possible
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link keml.ConversationPartner#getColor <em>Color</em>}</li>
 * </ul>
 *
 * @see keml.KemlPackage#getConversationPartner()
 * @model
 * @generated
 */
public interface ConversationPartner extends LifeLine {
	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see #setColor(int)
	 * @see keml.KemlPackage#getConversationPartner_Color()
	 * @model
	 * @generated
	 */
	int getColor();

	/**
	 * Sets the value of the '{@link keml.ConversationPartner#getColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(int value);

} // ConversationPartner
