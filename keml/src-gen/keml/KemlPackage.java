/**
 */
package keml;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see keml.KemlFactory
 * @model kind="package"
 * @generated
 */
public interface KemlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "keml";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.unikoblenz.de/keml";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "keml";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	KemlPackage eINSTANCE = keml.impl.KemlPackageImpl.init();

	/**
	 * The meta object id for the '{@link keml.impl.ConversationImpl <em>Conversation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see keml.impl.ConversationImpl
	 * @see keml.impl.KemlPackageImpl#getConversation()
	 * @generated
	 */
	int CONVERSATION = 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERSATION__TITLE = 0;

	/**
	 * The feature id for the '<em><b>Author</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERSATION__AUTHOR = 1;

	/**
	 * The feature id for the '<em><b>Conversation Partners</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERSATION__CONVERSATION_PARTNERS = 2;

	/**
	 * The number of structural features of the '<em>Conversation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERSATION_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Conversation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERSATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link keml.impl.LifeLineImpl <em>Life Line</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see keml.impl.LifeLineImpl
	 * @see keml.impl.KemlPackageImpl#getLifeLine()
	 * @generated
	 */
	int LIFE_LINE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIFE_LINE__NAME = 0;

	/**
	 * The feature id for the '<em><b>XPosition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIFE_LINE__XPOSITION = 1;

	/**
	 * The number of structural features of the '<em>Life Line</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIFE_LINE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Life Line</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIFE_LINE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link keml.impl.ConversationPartnerImpl <em>Conversation Partner</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see keml.impl.ConversationPartnerImpl
	 * @see keml.impl.KemlPackageImpl#getConversationPartner()
	 * @generated
	 */
	int CONVERSATION_PARTNER = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERSATION_PARTNER__NAME = LIFE_LINE__NAME;

	/**
	 * The feature id for the '<em><b>XPosition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERSATION_PARTNER__XPOSITION = LIFE_LINE__XPOSITION;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERSATION_PARTNER__COLOR = LIFE_LINE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Conversation Partner</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERSATION_PARTNER_FEATURE_COUNT = LIFE_LINE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Conversation Partner</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONVERSATION_PARTNER_OPERATION_COUNT = LIFE_LINE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link keml.impl.AuthorImpl <em>Author</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see keml.impl.AuthorImpl
	 * @see keml.impl.KemlPackageImpl#getAuthor()
	 * @generated
	 */
	int AUTHOR = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHOR__NAME = LIFE_LINE__NAME;

	/**
	 * The feature id for the '<em><b>XPosition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHOR__XPOSITION = LIFE_LINE__XPOSITION;

	/**
	 * The feature id for the '<em><b>Messages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHOR__MESSAGES = LIFE_LINE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Preknowledge</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHOR__PREKNOWLEDGE = LIFE_LINE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Author</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHOR_FEATURE_COUNT = LIFE_LINE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Author</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHOR_OPERATION_COUNT = LIFE_LINE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link keml.impl.MessageImpl <em>Message</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see keml.impl.MessageImpl
	 * @see keml.impl.KemlPackageImpl#getMessage()
	 * @generated
	 */
	int MESSAGE = 6;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__CONTENT = 0;

	/**
	 * The feature id for the '<em><b>Timing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__TIMING = 1;

	/**
	 * The feature id for the '<em><b>Counter Part</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__COUNTER_PART = 2;

	/**
	 * The feature id for the '<em><b>Original Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__ORIGINAL_CONTENT = 3;

	/**
	 * The number of structural features of the '<em>Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link keml.impl.SendMessageImpl <em>Send Message</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see keml.impl.SendMessageImpl
	 * @see keml.impl.KemlPackageImpl#getSendMessage()
	 * @generated
	 */
	int SEND_MESSAGE = 4;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_MESSAGE__CONTENT = MESSAGE__CONTENT;

	/**
	 * The feature id for the '<em><b>Timing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_MESSAGE__TIMING = MESSAGE__TIMING;

	/**
	 * The feature id for the '<em><b>Counter Part</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_MESSAGE__COUNTER_PART = MESSAGE__COUNTER_PART;

	/**
	 * The feature id for the '<em><b>Original Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_MESSAGE__ORIGINAL_CONTENT = MESSAGE__ORIGINAL_CONTENT;

	/**
	 * The feature id for the '<em><b>Uses</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_MESSAGE__USES = MESSAGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Send Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_MESSAGE_FEATURE_COUNT = MESSAGE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Send Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_MESSAGE_OPERATION_COUNT = MESSAGE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link keml.impl.ReceiveMessageImpl <em>Receive Message</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see keml.impl.ReceiveMessageImpl
	 * @see keml.impl.KemlPackageImpl#getReceiveMessage()
	 * @generated
	 */
	int RECEIVE_MESSAGE = 5;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_MESSAGE__CONTENT = MESSAGE__CONTENT;

	/**
	 * The feature id for the '<em><b>Timing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_MESSAGE__TIMING = MESSAGE__TIMING;

	/**
	 * The feature id for the '<em><b>Counter Part</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_MESSAGE__COUNTER_PART = MESSAGE__COUNTER_PART;

	/**
	 * The feature id for the '<em><b>Original Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_MESSAGE__ORIGINAL_CONTENT = MESSAGE__ORIGINAL_CONTENT;

	/**
	 * The feature id for the '<em><b>Is Interrupted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_MESSAGE__IS_INTERRUPTED = MESSAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Generates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_MESSAGE__GENERATES = MESSAGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Repeats</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_MESSAGE__REPEATS = MESSAGE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Receive Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_MESSAGE_FEATURE_COUNT = MESSAGE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Receive Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_MESSAGE_OPERATION_COUNT = MESSAGE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link keml.impl.InformationImpl <em>Information</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see keml.impl.InformationImpl
	 * @see keml.impl.KemlPackageImpl#getInformation()
	 * @generated
	 */
	int INFORMATION = 9;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION__MESSAGE = 0;

	/**
	 * The feature id for the '<em><b>Is Instruction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION__IS_INSTRUCTION = 1;

	/**
	 * The feature id for the '<em><b>Repeated By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION__REPEATED_BY = 2;

	/**
	 * The feature id for the '<em><b>Targeted By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION__TARGETED_BY = 3;

	/**
	 * The feature id for the '<em><b>Causes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION__CAUSES = 4;

	/**
	 * The feature id for the '<em><b>Is Used On</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION__IS_USED_ON = 5;

	/**
	 * The feature id for the '<em><b>Felt Trust Immediately</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION__FELT_TRUST_IMMEDIATELY = 6;

	/**
	 * The feature id for the '<em><b>Felt Trust Afterwards</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION__FELT_TRUST_AFTERWARDS = 7;

	/**
	 * The feature id for the '<em><b>Initial Trust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION__INITIAL_TRUST = 8;

	/**
	 * The feature id for the '<em><b>Current Trust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION__CURRENT_TRUST = 9;

	/**
	 * The number of structural features of the '<em>Information</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_FEATURE_COUNT = 10;

	/**
	 * The number of operations of the '<em>Information</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link keml.impl.NewInformationImpl <em>New Information</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see keml.impl.NewInformationImpl
	 * @see keml.impl.KemlPackageImpl#getNewInformation()
	 * @generated
	 */
	int NEW_INFORMATION = 7;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_INFORMATION__MESSAGE = INFORMATION__MESSAGE;

	/**
	 * The feature id for the '<em><b>Is Instruction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_INFORMATION__IS_INSTRUCTION = INFORMATION__IS_INSTRUCTION;

	/**
	 * The feature id for the '<em><b>Repeated By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_INFORMATION__REPEATED_BY = INFORMATION__REPEATED_BY;

	/**
	 * The feature id for the '<em><b>Targeted By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_INFORMATION__TARGETED_BY = INFORMATION__TARGETED_BY;

	/**
	 * The feature id for the '<em><b>Causes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_INFORMATION__CAUSES = INFORMATION__CAUSES;

	/**
	 * The feature id for the '<em><b>Is Used On</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_INFORMATION__IS_USED_ON = INFORMATION__IS_USED_ON;

	/**
	 * The feature id for the '<em><b>Felt Trust Immediately</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_INFORMATION__FELT_TRUST_IMMEDIATELY = INFORMATION__FELT_TRUST_IMMEDIATELY;

	/**
	 * The feature id for the '<em><b>Felt Trust Afterwards</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_INFORMATION__FELT_TRUST_AFTERWARDS = INFORMATION__FELT_TRUST_AFTERWARDS;

	/**
	 * The feature id for the '<em><b>Initial Trust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_INFORMATION__INITIAL_TRUST = INFORMATION__INITIAL_TRUST;

	/**
	 * The feature id for the '<em><b>Current Trust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_INFORMATION__CURRENT_TRUST = INFORMATION__CURRENT_TRUST;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_INFORMATION__SOURCE = INFORMATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>New Information</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_INFORMATION_FEATURE_COUNT = INFORMATION_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Source Conversation Partner</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_INFORMATION___GET_SOURCE_CONVERSATION_PARTNER = INFORMATION_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Timing</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_INFORMATION___GET_TIMING = INFORMATION_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>New Information</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_INFORMATION_OPERATION_COUNT = INFORMATION_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link keml.impl.PreKnowledgeImpl <em>Pre Knowledge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see keml.impl.PreKnowledgeImpl
	 * @see keml.impl.KemlPackageImpl#getPreKnowledge()
	 * @generated
	 */
	int PRE_KNOWLEDGE = 8;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRE_KNOWLEDGE__MESSAGE = INFORMATION__MESSAGE;

	/**
	 * The feature id for the '<em><b>Is Instruction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRE_KNOWLEDGE__IS_INSTRUCTION = INFORMATION__IS_INSTRUCTION;

	/**
	 * The feature id for the '<em><b>Repeated By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRE_KNOWLEDGE__REPEATED_BY = INFORMATION__REPEATED_BY;

	/**
	 * The feature id for the '<em><b>Targeted By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRE_KNOWLEDGE__TARGETED_BY = INFORMATION__TARGETED_BY;

	/**
	 * The feature id for the '<em><b>Causes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRE_KNOWLEDGE__CAUSES = INFORMATION__CAUSES;

	/**
	 * The feature id for the '<em><b>Is Used On</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRE_KNOWLEDGE__IS_USED_ON = INFORMATION__IS_USED_ON;

	/**
	 * The feature id for the '<em><b>Felt Trust Immediately</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRE_KNOWLEDGE__FELT_TRUST_IMMEDIATELY = INFORMATION__FELT_TRUST_IMMEDIATELY;

	/**
	 * The feature id for the '<em><b>Felt Trust Afterwards</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRE_KNOWLEDGE__FELT_TRUST_AFTERWARDS = INFORMATION__FELT_TRUST_AFTERWARDS;

	/**
	 * The feature id for the '<em><b>Initial Trust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRE_KNOWLEDGE__INITIAL_TRUST = INFORMATION__INITIAL_TRUST;

	/**
	 * The feature id for the '<em><b>Current Trust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRE_KNOWLEDGE__CURRENT_TRUST = INFORMATION__CURRENT_TRUST;

	/**
	 * The number of structural features of the '<em>Pre Knowledge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRE_KNOWLEDGE_FEATURE_COUNT = INFORMATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Pre Knowledge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRE_KNOWLEDGE_OPERATION_COUNT = INFORMATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link keml.impl.InformationLinkImpl <em>Information Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see keml.impl.InformationLinkImpl
	 * @see keml.impl.KemlPackageImpl#getInformationLink()
	 * @generated
	 */
	int INFORMATION_LINK = 10;

	/**
	 * The feature id for the '<em><b>Link Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_LINK__LINK_TEXT = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_LINK__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_LINK__TARGET = 2;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_LINK__SOURCE = 3;

	/**
	 * The number of structural features of the '<em>Information Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_LINK_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Information Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_LINK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link keml.InformationLinkType <em>Information Link Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see keml.InformationLinkType
	 * @see keml.impl.KemlPackageImpl#getInformationLinkType()
	 * @generated
	 */
	int INFORMATION_LINK_TYPE = 11;

	/**
	 * Returns the meta object for class '{@link keml.Conversation <em>Conversation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conversation</em>'.
	 * @see keml.Conversation
	 * @generated
	 */
	EClass getConversation();

	/**
	 * Returns the meta object for the attribute '{@link keml.Conversation#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see keml.Conversation#getTitle()
	 * @see #getConversation()
	 * @generated
	 */
	EAttribute getConversation_Title();

	/**
	 * Returns the meta object for the containment reference '{@link keml.Conversation#getAuthor <em>Author</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Author</em>'.
	 * @see keml.Conversation#getAuthor()
	 * @see #getConversation()
	 * @generated
	 */
	EReference getConversation_Author();

	/**
	 * Returns the meta object for the containment reference list '{@link keml.Conversation#getConversationPartners <em>Conversation Partners</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Conversation Partners</em>'.
	 * @see keml.Conversation#getConversationPartners()
	 * @see #getConversation()
	 * @generated
	 */
	EReference getConversation_ConversationPartners();

	/**
	 * Returns the meta object for class '{@link keml.ConversationPartner <em>Conversation Partner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conversation Partner</em>'.
	 * @see keml.ConversationPartner
	 * @generated
	 */
	EClass getConversationPartner();

	/**
	 * Returns the meta object for the attribute '{@link keml.ConversationPartner#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see keml.ConversationPartner#getColor()
	 * @see #getConversationPartner()
	 * @generated
	 */
	EAttribute getConversationPartner_Color();

	/**
	 * Returns the meta object for class '{@link keml.LifeLine <em>Life Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Life Line</em>'.
	 * @see keml.LifeLine
	 * @generated
	 */
	EClass getLifeLine();

	/**
	 * Returns the meta object for the attribute '{@link keml.LifeLine#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see keml.LifeLine#getName()
	 * @see #getLifeLine()
	 * @generated
	 */
	EAttribute getLifeLine_Name();

	/**
	 * Returns the meta object for the attribute '{@link keml.LifeLine#getXPosition <em>XPosition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>XPosition</em>'.
	 * @see keml.LifeLine#getXPosition()
	 * @see #getLifeLine()
	 * @generated
	 */
	EAttribute getLifeLine_XPosition();

	/**
	 * Returns the meta object for class '{@link keml.Author <em>Author</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Author</em>'.
	 * @see keml.Author
	 * @generated
	 */
	EClass getAuthor();

	/**
	 * Returns the meta object for the containment reference list '{@link keml.Author#getMessages <em>Messages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Messages</em>'.
	 * @see keml.Author#getMessages()
	 * @see #getAuthor()
	 * @generated
	 */
	EReference getAuthor_Messages();

	/**
	 * Returns the meta object for the containment reference list '{@link keml.Author#getPreknowledge <em>Preknowledge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Preknowledge</em>'.
	 * @see keml.Author#getPreknowledge()
	 * @see #getAuthor()
	 * @generated
	 */
	EReference getAuthor_Preknowledge();

	/**
	 * Returns the meta object for class '{@link keml.SendMessage <em>Send Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Send Message</em>'.
	 * @see keml.SendMessage
	 * @generated
	 */
	EClass getSendMessage();

	/**
	 * Returns the meta object for the reference list '{@link keml.SendMessage#getUses <em>Uses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Uses</em>'.
	 * @see keml.SendMessage#getUses()
	 * @see #getSendMessage()
	 * @generated
	 */
	EReference getSendMessage_Uses();

	/**
	 * Returns the meta object for class '{@link keml.ReceiveMessage <em>Receive Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Receive Message</em>'.
	 * @see keml.ReceiveMessage
	 * @generated
	 */
	EClass getReceiveMessage();

	/**
	 * Returns the meta object for the attribute '{@link keml.ReceiveMessage#isIsInterrupted <em>Is Interrupted</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Interrupted</em>'.
	 * @see keml.ReceiveMessage#isIsInterrupted()
	 * @see #getReceiveMessage()
	 * @generated
	 */
	EAttribute getReceiveMessage_IsInterrupted();

	/**
	 * Returns the meta object for the containment reference list '{@link keml.ReceiveMessage#getGenerates <em>Generates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Generates</em>'.
	 * @see keml.ReceiveMessage#getGenerates()
	 * @see #getReceiveMessage()
	 * @generated
	 */
	EReference getReceiveMessage_Generates();

	/**
	 * Returns the meta object for the reference list '{@link keml.ReceiveMessage#getRepeats <em>Repeats</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Repeats</em>'.
	 * @see keml.ReceiveMessage#getRepeats()
	 * @see #getReceiveMessage()
	 * @generated
	 */
	EReference getReceiveMessage_Repeats();

	/**
	 * Returns the meta object for class '{@link keml.Message <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message</em>'.
	 * @see keml.Message
	 * @generated
	 */
	EClass getMessage();

	/**
	 * Returns the meta object for the attribute '{@link keml.Message#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see keml.Message#getContent()
	 * @see #getMessage()
	 * @generated
	 */
	EAttribute getMessage_Content();

	/**
	 * Returns the meta object for the attribute '{@link keml.Message#getTiming <em>Timing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timing</em>'.
	 * @see keml.Message#getTiming()
	 * @see #getMessage()
	 * @generated
	 */
	EAttribute getMessage_Timing();

	/**
	 * Returns the meta object for the reference '{@link keml.Message#getCounterPart <em>Counter Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Counter Part</em>'.
	 * @see keml.Message#getCounterPart()
	 * @see #getMessage()
	 * @generated
	 */
	EReference getMessage_CounterPart();

	/**
	 * Returns the meta object for the attribute '{@link keml.Message#getOriginalContent <em>Original Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Original Content</em>'.
	 * @see keml.Message#getOriginalContent()
	 * @see #getMessage()
	 * @generated
	 */
	EAttribute getMessage_OriginalContent();

	/**
	 * Returns the meta object for class '{@link keml.NewInformation <em>New Information</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>New Information</em>'.
	 * @see keml.NewInformation
	 * @generated
	 */
	EClass getNewInformation();

	/**
	 * Returns the meta object for the container reference '{@link keml.NewInformation#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see keml.NewInformation#getSource()
	 * @see #getNewInformation()
	 * @generated
	 */
	EReference getNewInformation_Source();

	/**
	 * Returns the meta object for the '{@link keml.NewInformation#getSourceConversationPartner() <em>Get Source Conversation Partner</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Source Conversation Partner</em>' operation.
	 * @see keml.NewInformation#getSourceConversationPartner()
	 * @generated
	 */
	EOperation getNewInformation__GetSourceConversationPartner();

	/**
	 * Returns the meta object for the '{@link keml.NewInformation#getTiming() <em>Get Timing</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Timing</em>' operation.
	 * @see keml.NewInformation#getTiming()
	 * @generated
	 */
	EOperation getNewInformation__GetTiming();

	/**
	 * Returns the meta object for class '{@link keml.PreKnowledge <em>Pre Knowledge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pre Knowledge</em>'.
	 * @see keml.PreKnowledge
	 * @generated
	 */
	EClass getPreKnowledge();

	/**
	 * Returns the meta object for class '{@link keml.Information <em>Information</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Information</em>'.
	 * @see keml.Information
	 * @generated
	 */
	EClass getInformation();

	/**
	 * Returns the meta object for the attribute '{@link keml.Information#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see keml.Information#getMessage()
	 * @see #getInformation()
	 * @generated
	 */
	EAttribute getInformation_Message();

	/**
	 * Returns the meta object for the attribute '{@link keml.Information#isIsInstruction <em>Is Instruction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Instruction</em>'.
	 * @see keml.Information#isIsInstruction()
	 * @see #getInformation()
	 * @generated
	 */
	EAttribute getInformation_IsInstruction();

	/**
	 * Returns the meta object for the reference list '{@link keml.Information#getRepeatedBy <em>Repeated By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Repeated By</em>'.
	 * @see keml.Information#getRepeatedBy()
	 * @see #getInformation()
	 * @generated
	 */
	EReference getInformation_RepeatedBy();

	/**
	 * Returns the meta object for the reference list '{@link keml.Information#getTargetedBy <em>Targeted By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Targeted By</em>'.
	 * @see keml.Information#getTargetedBy()
	 * @see #getInformation()
	 * @generated
	 */
	EReference getInformation_TargetedBy();

	/**
	 * Returns the meta object for the containment reference list '{@link keml.Information#getCauses <em>Causes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Causes</em>'.
	 * @see keml.Information#getCauses()
	 * @see #getInformation()
	 * @generated
	 */
	EReference getInformation_Causes();

	/**
	 * Returns the meta object for the reference list '{@link keml.Information#getIsUsedOn <em>Is Used On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Is Used On</em>'.
	 * @see keml.Information#getIsUsedOn()
	 * @see #getInformation()
	 * @generated
	 */
	EReference getInformation_IsUsedOn();

	/**
	 * Returns the meta object for the attribute '{@link keml.Information#getFeltTrustImmediately <em>Felt Trust Immediately</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Felt Trust Immediately</em>'.
	 * @see keml.Information#getFeltTrustImmediately()
	 * @see #getInformation()
	 * @generated
	 */
	EAttribute getInformation_FeltTrustImmediately();

	/**
	 * Returns the meta object for the attribute '{@link keml.Information#getFeltTrustAfterwards <em>Felt Trust Afterwards</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Felt Trust Afterwards</em>'.
	 * @see keml.Information#getFeltTrustAfterwards()
	 * @see #getInformation()
	 * @generated
	 */
	EAttribute getInformation_FeltTrustAfterwards();

	/**
	 * Returns the meta object for the attribute '{@link keml.Information#getInitialTrust <em>Initial Trust</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initial Trust</em>'.
	 * @see keml.Information#getInitialTrust()
	 * @see #getInformation()
	 * @generated
	 */
	EAttribute getInformation_InitialTrust();

	/**
	 * Returns the meta object for the attribute '{@link keml.Information#getCurrentTrust <em>Current Trust</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Current Trust</em>'.
	 * @see keml.Information#getCurrentTrust()
	 * @see #getInformation()
	 * @generated
	 */
	EAttribute getInformation_CurrentTrust();

	/**
	 * Returns the meta object for class '{@link keml.InformationLink <em>Information Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Information Link</em>'.
	 * @see keml.InformationLink
	 * @generated
	 */
	EClass getInformationLink();

	/**
	 * Returns the meta object for the attribute '{@link keml.InformationLink#getLinkText <em>Link Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Link Text</em>'.
	 * @see keml.InformationLink#getLinkText()
	 * @see #getInformationLink()
	 * @generated
	 */
	EAttribute getInformationLink_LinkText();

	/**
	 * Returns the meta object for the attribute '{@link keml.InformationLink#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see keml.InformationLink#getType()
	 * @see #getInformationLink()
	 * @generated
	 */
	EAttribute getInformationLink_Type();

	/**
	 * Returns the meta object for the reference '{@link keml.InformationLink#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see keml.InformationLink#getTarget()
	 * @see #getInformationLink()
	 * @generated
	 */
	EReference getInformationLink_Target();

	/**
	 * Returns the meta object for the container reference '{@link keml.InformationLink#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see keml.InformationLink#getSource()
	 * @see #getInformationLink()
	 * @generated
	 */
	EReference getInformationLink_Source();

	/**
	 * Returns the meta object for enum '{@link keml.InformationLinkType <em>Information Link Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Information Link Type</em>'.
	 * @see keml.InformationLinkType
	 * @generated
	 */
	EEnum getInformationLinkType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	KemlFactory getKemlFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link keml.impl.ConversationImpl <em>Conversation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see keml.impl.ConversationImpl
		 * @see keml.impl.KemlPackageImpl#getConversation()
		 * @generated
		 */
		EClass CONVERSATION = eINSTANCE.getConversation();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONVERSATION__TITLE = eINSTANCE.getConversation_Title();

		/**
		 * The meta object literal for the '<em><b>Author</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONVERSATION__AUTHOR = eINSTANCE.getConversation_Author();

		/**
		 * The meta object literal for the '<em><b>Conversation Partners</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONVERSATION__CONVERSATION_PARTNERS = eINSTANCE.getConversation_ConversationPartners();

		/**
		 * The meta object literal for the '{@link keml.impl.ConversationPartnerImpl <em>Conversation Partner</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see keml.impl.ConversationPartnerImpl
		 * @see keml.impl.KemlPackageImpl#getConversationPartner()
		 * @generated
		 */
		EClass CONVERSATION_PARTNER = eINSTANCE.getConversationPartner();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONVERSATION_PARTNER__COLOR = eINSTANCE.getConversationPartner_Color();

		/**
		 * The meta object literal for the '{@link keml.impl.LifeLineImpl <em>Life Line</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see keml.impl.LifeLineImpl
		 * @see keml.impl.KemlPackageImpl#getLifeLine()
		 * @generated
		 */
		EClass LIFE_LINE = eINSTANCE.getLifeLine();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIFE_LINE__NAME = eINSTANCE.getLifeLine_Name();

		/**
		 * The meta object literal for the '<em><b>XPosition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIFE_LINE__XPOSITION = eINSTANCE.getLifeLine_XPosition();

		/**
		 * The meta object literal for the '{@link keml.impl.AuthorImpl <em>Author</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see keml.impl.AuthorImpl
		 * @see keml.impl.KemlPackageImpl#getAuthor()
		 * @generated
		 */
		EClass AUTHOR = eINSTANCE.getAuthor();

		/**
		 * The meta object literal for the '<em><b>Messages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUTHOR__MESSAGES = eINSTANCE.getAuthor_Messages();

		/**
		 * The meta object literal for the '<em><b>Preknowledge</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUTHOR__PREKNOWLEDGE = eINSTANCE.getAuthor_Preknowledge();

		/**
		 * The meta object literal for the '{@link keml.impl.SendMessageImpl <em>Send Message</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see keml.impl.SendMessageImpl
		 * @see keml.impl.KemlPackageImpl#getSendMessage()
		 * @generated
		 */
		EClass SEND_MESSAGE = eINSTANCE.getSendMessage();

		/**
		 * The meta object literal for the '<em><b>Uses</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEND_MESSAGE__USES = eINSTANCE.getSendMessage_Uses();

		/**
		 * The meta object literal for the '{@link keml.impl.ReceiveMessageImpl <em>Receive Message</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see keml.impl.ReceiveMessageImpl
		 * @see keml.impl.KemlPackageImpl#getReceiveMessage()
		 * @generated
		 */
		EClass RECEIVE_MESSAGE = eINSTANCE.getReceiveMessage();

		/**
		 * The meta object literal for the '<em><b>Is Interrupted</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECEIVE_MESSAGE__IS_INTERRUPTED = eINSTANCE.getReceiveMessage_IsInterrupted();

		/**
		 * The meta object literal for the '<em><b>Generates</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RECEIVE_MESSAGE__GENERATES = eINSTANCE.getReceiveMessage_Generates();

		/**
		 * The meta object literal for the '<em><b>Repeats</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RECEIVE_MESSAGE__REPEATS = eINSTANCE.getReceiveMessage_Repeats();

		/**
		 * The meta object literal for the '{@link keml.impl.MessageImpl <em>Message</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see keml.impl.MessageImpl
		 * @see keml.impl.KemlPackageImpl#getMessage()
		 * @generated
		 */
		EClass MESSAGE = eINSTANCE.getMessage();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE__CONTENT = eINSTANCE.getMessage_Content();

		/**
		 * The meta object literal for the '<em><b>Timing</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE__TIMING = eINSTANCE.getMessage_Timing();

		/**
		 * The meta object literal for the '<em><b>Counter Part</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE__COUNTER_PART = eINSTANCE.getMessage_CounterPart();

		/**
		 * The meta object literal for the '<em><b>Original Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE__ORIGINAL_CONTENT = eINSTANCE.getMessage_OriginalContent();

		/**
		 * The meta object literal for the '{@link keml.impl.NewInformationImpl <em>New Information</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see keml.impl.NewInformationImpl
		 * @see keml.impl.KemlPackageImpl#getNewInformation()
		 * @generated
		 */
		EClass NEW_INFORMATION = eINSTANCE.getNewInformation();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NEW_INFORMATION__SOURCE = eINSTANCE.getNewInformation_Source();

		/**
		 * The meta object literal for the '<em><b>Get Source Conversation Partner</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation NEW_INFORMATION___GET_SOURCE_CONVERSATION_PARTNER = eINSTANCE
				.getNewInformation__GetSourceConversationPartner();

		/**
		 * The meta object literal for the '<em><b>Get Timing</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation NEW_INFORMATION___GET_TIMING = eINSTANCE.getNewInformation__GetTiming();

		/**
		 * The meta object literal for the '{@link keml.impl.PreKnowledgeImpl <em>Pre Knowledge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see keml.impl.PreKnowledgeImpl
		 * @see keml.impl.KemlPackageImpl#getPreKnowledge()
		 * @generated
		 */
		EClass PRE_KNOWLEDGE = eINSTANCE.getPreKnowledge();

		/**
		 * The meta object literal for the '{@link keml.impl.InformationImpl <em>Information</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see keml.impl.InformationImpl
		 * @see keml.impl.KemlPackageImpl#getInformation()
		 * @generated
		 */
		EClass INFORMATION = eINSTANCE.getInformation();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFORMATION__MESSAGE = eINSTANCE.getInformation_Message();

		/**
		 * The meta object literal for the '<em><b>Is Instruction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFORMATION__IS_INSTRUCTION = eINSTANCE.getInformation_IsInstruction();

		/**
		 * The meta object literal for the '<em><b>Repeated By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFORMATION__REPEATED_BY = eINSTANCE.getInformation_RepeatedBy();

		/**
		 * The meta object literal for the '<em><b>Targeted By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFORMATION__TARGETED_BY = eINSTANCE.getInformation_TargetedBy();

		/**
		 * The meta object literal for the '<em><b>Causes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFORMATION__CAUSES = eINSTANCE.getInformation_Causes();

		/**
		 * The meta object literal for the '<em><b>Is Used On</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFORMATION__IS_USED_ON = eINSTANCE.getInformation_IsUsedOn();

		/**
		 * The meta object literal for the '<em><b>Felt Trust Immediately</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFORMATION__FELT_TRUST_IMMEDIATELY = eINSTANCE.getInformation_FeltTrustImmediately();

		/**
		 * The meta object literal for the '<em><b>Felt Trust Afterwards</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFORMATION__FELT_TRUST_AFTERWARDS = eINSTANCE.getInformation_FeltTrustAfterwards();

		/**
		 * The meta object literal for the '<em><b>Initial Trust</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFORMATION__INITIAL_TRUST = eINSTANCE.getInformation_InitialTrust();

		/**
		 * The meta object literal for the '<em><b>Current Trust</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFORMATION__CURRENT_TRUST = eINSTANCE.getInformation_CurrentTrust();

		/**
		 * The meta object literal for the '{@link keml.impl.InformationLinkImpl <em>Information Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see keml.impl.InformationLinkImpl
		 * @see keml.impl.KemlPackageImpl#getInformationLink()
		 * @generated
		 */
		EClass INFORMATION_LINK = eINSTANCE.getInformationLink();

		/**
		 * The meta object literal for the '<em><b>Link Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFORMATION_LINK__LINK_TEXT = eINSTANCE.getInformationLink_LinkText();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFORMATION_LINK__TYPE = eINSTANCE.getInformationLink_Type();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFORMATION_LINK__TARGET = eINSTANCE.getInformationLink_Target();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFORMATION_LINK__SOURCE = eINSTANCE.getInformationLink_Source();

		/**
		 * The meta object literal for the '{@link keml.InformationLinkType <em>Information Link Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see keml.InformationLinkType
		 * @see keml.impl.KemlPackageImpl#getInformationLinkType()
		 * @generated
		 */
		EEnum INFORMATION_LINK_TYPE = eINSTANCE.getInformationLinkType();

	}

} //KemlPackage
