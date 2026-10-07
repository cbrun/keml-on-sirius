/**
 */
package keml.impl;

import keml.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KemlFactoryImpl extends EFactoryImpl implements KemlFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static KemlFactory init() {
		try {
			KemlFactory theKemlFactory = (KemlFactory) EPackage.Registry.INSTANCE.getEFactory(KemlPackage.eNS_URI);
			if (theKemlFactory != null) {
				return theKemlFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new KemlFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KemlFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case KemlPackage.CONVERSATION:
			return createConversation();
		case KemlPackage.CONVERSATION_PARTNER:
			return createConversationPartner();
		case KemlPackage.AUTHOR:
			return createAuthor();
		case KemlPackage.SEND_MESSAGE:
			return createSendMessage();
		case KemlPackage.RECEIVE_MESSAGE:
			return createReceiveMessage();
		case KemlPackage.NEW_INFORMATION:
			return createNewInformation();
		case KemlPackage.PRE_KNOWLEDGE:
			return createPreKnowledge();
		case KemlPackage.INFORMATION_LINK:
			return createInformationLink();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
		case KemlPackage.INFORMATION_LINK_TYPE:
			return createInformationLinkTypeFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
		case KemlPackage.INFORMATION_LINK_TYPE:
			return convertInformationLinkTypeToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Conversation createConversation() {
		ConversationImpl conversation = new ConversationImpl();
		return conversation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConversationPartner createConversationPartner() {
		ConversationPartnerImpl conversationPartner = new ConversationPartnerImpl();
		return conversationPartner;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Author createAuthor() {
		AuthorImpl author = new AuthorImpl();
		return author;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SendMessage createSendMessage() {
		SendMessageImpl sendMessage = new SendMessageImpl();
		return sendMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ReceiveMessage createReceiveMessage() {
		ReceiveMessageImpl receiveMessage = new ReceiveMessageImpl();
		return receiveMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NewInformation createNewInformation() {
		NewInformationImpl newInformation = new NewInformationImpl();
		return newInformation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PreKnowledge createPreKnowledge() {
		PreKnowledgeImpl preKnowledge = new PreKnowledgeImpl();
		return preKnowledge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InformationLink createInformationLink() {
		InformationLinkImpl informationLink = new InformationLinkImpl();
		return informationLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InformationLinkType createInformationLinkTypeFromString(EDataType eDataType, String initialValue) {
		InformationLinkType result = InformationLinkType.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInformationLinkTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public KemlPackage getKemlPackage() {
		return (KemlPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static KemlPackage getPackage() {
		return KemlPackage.eINSTANCE;
	}

} //KemlFactoryImpl
