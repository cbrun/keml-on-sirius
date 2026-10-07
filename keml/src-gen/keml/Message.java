/**
 */
package keml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link keml.Message#getContent <em>Content</em>}</li>
 *   <li>{@link keml.Message#getTiming <em>Timing</em>}</li>
 *   <li>{@link keml.Message#getCounterPart <em>Counter Part</em>}</li>
 *   <li>{@link keml.Message#getOriginalContent <em>Original Content</em>}</li>
 * </ul>
 *
 * @see keml.KemlPackage#getMessage()
 * @model abstract="true"
 * @generated
 */
public interface Message extends EObject {
	/**
	 * Returns the value of the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content</em>' attribute.
	 * @see #setContent(String)
	 * @see keml.KemlPackage#getMessage_Content()
	 * @model
	 * @generated
	 */
	String getContent();

	/**
	 * Sets the value of the '{@link keml.Message#getContent <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content</em>' attribute.
	 * @see #getContent()
	 * @generated
	 */
	void setContent(String value);

	/**
	 * Returns the value of the '<em><b>Timing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timing</em>' attribute.
	 * @see #setTiming(int)
	 * @see keml.KemlPackage#getMessage_Timing()
	 * @model
	 * @generated
	 */
	int getTiming();

	/**
	 * Sets the value of the '{@link keml.Message#getTiming <em>Timing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timing</em>' attribute.
	 * @see #getTiming()
	 * @generated
	 */
	void setTiming(int value);

	/**
	 * Returns the value of the '<em><b>Counter Part</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Counter Part</em>' reference.
	 * @see #setCounterPart(ConversationPartner)
	 * @see keml.KemlPackage#getMessage_CounterPart()
	 * @model required="true"
	 * @generated
	 */
	ConversationPartner getCounterPart();

	/**
	 * Sets the value of the '{@link keml.Message#getCounterPart <em>Counter Part</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Counter Part</em>' reference.
	 * @see #getCounterPart()
	 * @generated
	 */
	void setCounterPart(ConversationPartner value);

	/**
	 * Returns the value of the '<em><b>Original Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Original Content</em>' attribute.
	 * @see #setOriginalContent(String)
	 * @see keml.KemlPackage#getMessage_OriginalContent()
	 * @model
	 * @generated
	 */
	String getOriginalContent();

	/**
	 * Sets the value of the '{@link keml.Message#getOriginalContent <em>Original Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Original Content</em>' attribute.
	 * @see #getOriginalContent()
	 * @generated
	 */
	void setOriginalContent(String value);

} // Message
