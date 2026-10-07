/**
 */
package keml.util;

import keml.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see keml.KemlPackage
 * @generated
 */
public class KemlAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static KemlPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KemlAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = KemlPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected KemlSwitch<Adapter> modelSwitch = new KemlSwitch<Adapter>() {
		@Override
		public Adapter caseConversation(Conversation object) {
			return createConversationAdapter();
		}

		@Override
		public Adapter caseConversationPartner(ConversationPartner object) {
			return createConversationPartnerAdapter();
		}

		@Override
		public Adapter caseLifeLine(LifeLine object) {
			return createLifeLineAdapter();
		}

		@Override
		public Adapter caseAuthor(Author object) {
			return createAuthorAdapter();
		}

		@Override
		public Adapter caseSendMessage(SendMessage object) {
			return createSendMessageAdapter();
		}

		@Override
		public Adapter caseReceiveMessage(ReceiveMessage object) {
			return createReceiveMessageAdapter();
		}

		@Override
		public Adapter caseMessage(Message object) {
			return createMessageAdapter();
		}

		@Override
		public Adapter caseNewInformation(NewInformation object) {
			return createNewInformationAdapter();
		}

		@Override
		public Adapter casePreKnowledge(PreKnowledge object) {
			return createPreKnowledgeAdapter();
		}

		@Override
		public Adapter caseInformation(Information object) {
			return createInformationAdapter();
		}

		@Override
		public Adapter caseInformationLink(InformationLink object) {
			return createInformationLinkAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link keml.Conversation <em>Conversation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see keml.Conversation
	 * @generated
	 */
	public Adapter createConversationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link keml.ConversationPartner <em>Conversation Partner</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see keml.ConversationPartner
	 * @generated
	 */
	public Adapter createConversationPartnerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link keml.LifeLine <em>Life Line</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see keml.LifeLine
	 * @generated
	 */
	public Adapter createLifeLineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link keml.Author <em>Author</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see keml.Author
	 * @generated
	 */
	public Adapter createAuthorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link keml.SendMessage <em>Send Message</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see keml.SendMessage
	 * @generated
	 */
	public Adapter createSendMessageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link keml.ReceiveMessage <em>Receive Message</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see keml.ReceiveMessage
	 * @generated
	 */
	public Adapter createReceiveMessageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link keml.Message <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see keml.Message
	 * @generated
	 */
	public Adapter createMessageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link keml.NewInformation <em>New Information</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see keml.NewInformation
	 * @generated
	 */
	public Adapter createNewInformationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link keml.PreKnowledge <em>Pre Knowledge</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see keml.PreKnowledge
	 * @generated
	 */
	public Adapter createPreKnowledgeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link keml.Information <em>Information</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see keml.Information
	 * @generated
	 */
	public Adapter createInformationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link keml.InformationLink <em>Information Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see keml.InformationLink
	 * @generated
	 */
	public Adapter createInformationLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //KemlAdapterFactory
