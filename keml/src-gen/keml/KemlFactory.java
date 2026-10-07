/**
 */
package keml;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see keml.KemlPackage
 * @generated
 */
public interface KemlFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	KemlFactory eINSTANCE = keml.impl.KemlFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Conversation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Conversation</em>'.
	 * @generated
	 */
	Conversation createConversation();

	/**
	 * Returns a new object of class '<em>Conversation Partner</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Conversation Partner</em>'.
	 * @generated
	 */
	ConversationPartner createConversationPartner();

	/**
	 * Returns a new object of class '<em>Author</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Author</em>'.
	 * @generated
	 */
	Author createAuthor();

	/**
	 * Returns a new object of class '<em>Send Message</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Send Message</em>'.
	 * @generated
	 */
	SendMessage createSendMessage();

	/**
	 * Returns a new object of class '<em>Receive Message</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Receive Message</em>'.
	 * @generated
	 */
	ReceiveMessage createReceiveMessage();

	/**
	 * Returns a new object of class '<em>New Information</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>New Information</em>'.
	 * @generated
	 */
	NewInformation createNewInformation();

	/**
	 * Returns a new object of class '<em>Pre Knowledge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Pre Knowledge</em>'.
	 * @generated
	 */
	PreKnowledge createPreKnowledge();

	/**
	 * Returns a new object of class '<em>Information Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Information Link</em>'.
	 * @generated
	 */
	InformationLink createInformationLink();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	KemlPackage getKemlPackage();

} //KemlFactory
