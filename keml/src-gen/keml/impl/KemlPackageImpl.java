/**
 */
package keml.impl;

import keml.Author;
import keml.Conversation;
import keml.ConversationPartner;
import keml.Information;
import keml.InformationLink;
import keml.InformationLinkType;
import keml.KemlFactory;
import keml.KemlPackage;
import keml.LifeLine;
import keml.Message;
import keml.NewInformation;
import keml.PreKnowledge;
import keml.ReceiveMessage;
import keml.SendMessage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KemlPackageImpl extends EPackageImpl implements KemlPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conversationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conversationPartnerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lifeLineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass authorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sendMessageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass receiveMessageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass messageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass newInformationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass preKnowledgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass informationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass informationLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum informationLinkTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see keml.KemlPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private KemlPackageImpl() {
		super(eNS_URI, KemlFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link KemlPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static KemlPackage init() {
		if (isInited)
			return (KemlPackage) EPackage.Registry.INSTANCE.getEPackage(KemlPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredKemlPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		KemlPackageImpl theKemlPackage = registeredKemlPackage instanceof KemlPackageImpl
				? (KemlPackageImpl) registeredKemlPackage
				: new KemlPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theKemlPackage.createPackageContents();

		// Initialize created meta-data
		theKemlPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theKemlPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(KemlPackage.eNS_URI, theKemlPackage);
		return theKemlPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConversation() {
		return conversationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConversation_Title() {
		return (EAttribute) conversationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConversation_Author() {
		return (EReference) conversationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConversation_ConversationPartners() {
		return (EReference) conversationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConversationPartner() {
		return conversationPartnerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConversationPartner_Color() {
		return (EAttribute) conversationPartnerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLifeLine() {
		return lifeLineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLifeLine_Name() {
		return (EAttribute) lifeLineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLifeLine_XPosition() {
		return (EAttribute) lifeLineEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAuthor() {
		return authorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAuthor_Messages() {
		return (EReference) authorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAuthor_Preknowledge() {
		return (EReference) authorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSendMessage() {
		return sendMessageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSendMessage_Uses() {
		return (EReference) sendMessageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getReceiveMessage() {
		return receiveMessageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReceiveMessage_IsInterrupted() {
		return (EAttribute) receiveMessageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReceiveMessage_Generates() {
		return (EReference) receiveMessageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReceiveMessage_Repeats() {
		return (EReference) receiveMessageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMessage() {
		return messageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMessage_Content() {
		return (EAttribute) messageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMessage_Timing() {
		return (EAttribute) messageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMessage_CounterPart() {
		return (EReference) messageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMessage_OriginalContent() {
		return (EAttribute) messageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNewInformation() {
		return newInformationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getNewInformation_Source() {
		return (EReference) newInformationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getNewInformation__GetSourceConversationPartner() {
		return newInformationEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getNewInformation__GetTiming() {
		return newInformationEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPreKnowledge() {
		return preKnowledgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInformation() {
		return informationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInformation_Message() {
		return (EAttribute) informationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInformation_IsInstruction() {
		return (EAttribute) informationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInformation_RepeatedBy() {
		return (EReference) informationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInformation_TargetedBy() {
		return (EReference) informationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInformation_Causes() {
		return (EReference) informationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInformation_IsUsedOn() {
		return (EReference) informationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInformation_FeltTrustImmediately() {
		return (EAttribute) informationEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInformation_FeltTrustAfterwards() {
		return (EAttribute) informationEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInformation_InitialTrust() {
		return (EAttribute) informationEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInformation_CurrentTrust() {
		return (EAttribute) informationEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInformationLink() {
		return informationLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInformationLink_LinkText() {
		return (EAttribute) informationLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInformationLink_Type() {
		return (EAttribute) informationLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInformationLink_Target() {
		return (EReference) informationLinkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getInformationLink_Source() {
		return (EReference) informationLinkEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getInformationLinkType() {
		return informationLinkTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public KemlFactory getKemlFactory() {
		return (KemlFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		conversationEClass = createEClass(CONVERSATION);
		createEAttribute(conversationEClass, CONVERSATION__TITLE);
		createEReference(conversationEClass, CONVERSATION__AUTHOR);
		createEReference(conversationEClass, CONVERSATION__CONVERSATION_PARTNERS);

		conversationPartnerEClass = createEClass(CONVERSATION_PARTNER);
		createEAttribute(conversationPartnerEClass, CONVERSATION_PARTNER__COLOR);

		lifeLineEClass = createEClass(LIFE_LINE);
		createEAttribute(lifeLineEClass, LIFE_LINE__NAME);
		createEAttribute(lifeLineEClass, LIFE_LINE__XPOSITION);

		authorEClass = createEClass(AUTHOR);
		createEReference(authorEClass, AUTHOR__MESSAGES);
		createEReference(authorEClass, AUTHOR__PREKNOWLEDGE);

		sendMessageEClass = createEClass(SEND_MESSAGE);
		createEReference(sendMessageEClass, SEND_MESSAGE__USES);

		receiveMessageEClass = createEClass(RECEIVE_MESSAGE);
		createEAttribute(receiveMessageEClass, RECEIVE_MESSAGE__IS_INTERRUPTED);
		createEReference(receiveMessageEClass, RECEIVE_MESSAGE__GENERATES);
		createEReference(receiveMessageEClass, RECEIVE_MESSAGE__REPEATS);

		messageEClass = createEClass(MESSAGE);
		createEAttribute(messageEClass, MESSAGE__CONTENT);
		createEAttribute(messageEClass, MESSAGE__TIMING);
		createEReference(messageEClass, MESSAGE__COUNTER_PART);
		createEAttribute(messageEClass, MESSAGE__ORIGINAL_CONTENT);

		newInformationEClass = createEClass(NEW_INFORMATION);
		createEReference(newInformationEClass, NEW_INFORMATION__SOURCE);
		createEOperation(newInformationEClass, NEW_INFORMATION___GET_SOURCE_CONVERSATION_PARTNER);
		createEOperation(newInformationEClass, NEW_INFORMATION___GET_TIMING);

		preKnowledgeEClass = createEClass(PRE_KNOWLEDGE);

		informationEClass = createEClass(INFORMATION);
		createEAttribute(informationEClass, INFORMATION__MESSAGE);
		createEAttribute(informationEClass, INFORMATION__IS_INSTRUCTION);
		createEReference(informationEClass, INFORMATION__REPEATED_BY);
		createEReference(informationEClass, INFORMATION__TARGETED_BY);
		createEReference(informationEClass, INFORMATION__CAUSES);
		createEReference(informationEClass, INFORMATION__IS_USED_ON);
		createEAttribute(informationEClass, INFORMATION__FELT_TRUST_IMMEDIATELY);
		createEAttribute(informationEClass, INFORMATION__FELT_TRUST_AFTERWARDS);
		createEAttribute(informationEClass, INFORMATION__INITIAL_TRUST);
		createEAttribute(informationEClass, INFORMATION__CURRENT_TRUST);

		informationLinkEClass = createEClass(INFORMATION_LINK);
		createEAttribute(informationLinkEClass, INFORMATION_LINK__LINK_TEXT);
		createEAttribute(informationLinkEClass, INFORMATION_LINK__TYPE);
		createEReference(informationLinkEClass, INFORMATION_LINK__TARGET);
		createEReference(informationLinkEClass, INFORMATION_LINK__SOURCE);

		// Create enums
		informationLinkTypeEEnum = createEEnum(INFORMATION_LINK_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		conversationPartnerEClass.getESuperTypes().add(this.getLifeLine());
		authorEClass.getESuperTypes().add(this.getLifeLine());
		sendMessageEClass.getESuperTypes().add(this.getMessage());
		receiveMessageEClass.getESuperTypes().add(this.getMessage());
		newInformationEClass.getESuperTypes().add(this.getInformation());
		preKnowledgeEClass.getESuperTypes().add(this.getInformation());

		// Initialize classes, features, and operations; add parameters
		initEClass(conversationEClass, Conversation.class, "Conversation", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConversation_Title(), ecorePackage.getEString(), "title", null, 0, 1, Conversation.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConversation_Author(), this.getAuthor(), null, "author", null, 1, 1, Conversation.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConversation_ConversationPartners(), this.getConversationPartner(), null,
				"conversationPartners", null, 0, -1, Conversation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conversationPartnerEClass, ConversationPartner.class, "ConversationPartner", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConversationPartner_Color(), ecorePackage.getEInt(), "color", null, 0, 1,
				ConversationPartner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(lifeLineEClass, LifeLine.class, "LifeLine", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLifeLine_Name(), ecorePackage.getEString(), "name", null, 0, 1, LifeLine.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLifeLine_XPosition(), ecorePackage.getEInt(), "xPosition", null, 0, 1, LifeLine.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(authorEClass, Author.class, "Author", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAuthor_Messages(), this.getMessage(), null, "messages", null, 0, -1, Author.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAuthor_Preknowledge(), this.getPreKnowledge(), null, "preknowledge", null, 0, -1,
				Author.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sendMessageEClass, SendMessage.class, "SendMessage", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSendMessage_Uses(), this.getInformation(), this.getInformation_IsUsedOn(), "uses", null, 0,
				-1, SendMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(receiveMessageEClass, ReceiveMessage.class, "ReceiveMessage", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReceiveMessage_IsInterrupted(), ecorePackage.getEBoolean(), "isInterrupted", null, 0, 1,
				ReceiveMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getReceiveMessage_Generates(), this.getNewInformation(), this.getNewInformation_Source(),
				"generates", null, 0, -1, ReceiveMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReceiveMessage_Repeats(), this.getInformation(), this.getInformation_RepeatedBy(), "repeats",
				null, 0, -1, ReceiveMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(messageEClass, Message.class, "Message", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMessage_Content(), ecorePackage.getEString(), "content", null, 0, 1, Message.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMessage_Timing(), ecorePackage.getEInt(), "timing", null, 0, 1, Message.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMessage_CounterPart(), this.getConversationPartner(), null, "counterPart", null, 1, 1,
				Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMessage_OriginalContent(), ecorePackage.getEString(), "originalContent", null, 0, 1,
				Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(newInformationEClass, NewInformation.class, "NewInformation", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNewInformation_Source(), this.getReceiveMessage(), this.getReceiveMessage_Generates(),
				"source", null, 1, 1, NewInformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getNewInformation__GetSourceConversationPartner(), this.getConversationPartner(),
				"getSourceConversationPartner", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getNewInformation__GetTiming(), ecorePackage.getEInt(), "getTiming", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		initEClass(preKnowledgeEClass, PreKnowledge.class, "PreKnowledge", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		initEClass(informationEClass, Information.class, "Information", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInformation_Message(), ecorePackage.getEString(), "message", null, 0, 1, Information.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInformation_IsInstruction(), ecorePackage.getEBoolean(), "isInstruction", null, 0, 1,
				Information.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getInformation_RepeatedBy(), this.getReceiveMessage(), this.getReceiveMessage_Repeats(),
				"repeatedBy", null, 0, -1, Information.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInformation_TargetedBy(), this.getInformationLink(), this.getInformationLink_Target(),
				"targetedBy", null, 0, -1, Information.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInformation_Causes(), this.getInformationLink(), this.getInformationLink_Source(), "causes",
				null, 0, -1, Information.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInformation_IsUsedOn(), this.getSendMessage(), this.getSendMessage_Uses(), "isUsedOn", null,
				0, -1, Information.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInformation_FeltTrustImmediately(), ecorePackage.getEFloatObject(), "feltTrustImmediately",
				null, 0, 1, Information.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInformation_FeltTrustAfterwards(), ecorePackage.getEFloatObject(), "feltTrustAfterwards",
				null, 0, 1, Information.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInformation_InitialTrust(), ecorePackage.getEFloatObject(), "initialTrust", null, 0, 1,
				Information.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getInformation_CurrentTrust(), ecorePackage.getEFloatObject(), "currentTrust", null, 0, 1,
				Information.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(informationLinkEClass, InformationLink.class, "InformationLink", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInformationLink_LinkText(), ecorePackage.getEString(), "linkText", null, 0, 1,
				InformationLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getInformationLink_Type(), this.getInformationLinkType(), "type", null, 0, 1,
				InformationLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getInformationLink_Target(), this.getInformation(), this.getInformation_TargetedBy(), "target",
				null, 1, 1, InformationLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInformationLink_Source(), this.getInformation(), this.getInformation_Causes(), "source", null,
				1, 1, InformationLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(informationLinkTypeEEnum, InformationLinkType.class, "InformationLinkType");
		addEEnumLiteral(informationLinkTypeEEnum, InformationLinkType.SUPPLEMENT);
		addEEnumLiteral(informationLinkTypeEEnum, InformationLinkType.SUPPORT);
		addEEnumLiteral(informationLinkTypeEEnum, InformationLinkType.STRONG_SUPPORT);
		addEEnumLiteral(informationLinkTypeEEnum, InformationLinkType.ATTACK);
		addEEnumLiteral(informationLinkTypeEEnum, InformationLinkType.STRONG_ATTACK);

		// Create resource
		createResource(eNS_URI);
	}

} //KemlPackageImpl
