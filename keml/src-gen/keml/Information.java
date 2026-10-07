/**
 */
package keml;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Information</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link keml.Information#getMessage <em>Message</em>}</li>
 *   <li>{@link keml.Information#isIsInstruction <em>Is Instruction</em>}</li>
 *   <li>{@link keml.Information#getRepeatedBy <em>Repeated By</em>}</li>
 *   <li>{@link keml.Information#getTargetedBy <em>Targeted By</em>}</li>
 *   <li>{@link keml.Information#getCauses <em>Causes</em>}</li>
 *   <li>{@link keml.Information#getIsUsedOn <em>Is Used On</em>}</li>
 *   <li>{@link keml.Information#getFeltTrustImmediately <em>Felt Trust Immediately</em>}</li>
 *   <li>{@link keml.Information#getFeltTrustAfterwards <em>Felt Trust Afterwards</em>}</li>
 *   <li>{@link keml.Information#getInitialTrust <em>Initial Trust</em>}</li>
 *   <li>{@link keml.Information#getCurrentTrust <em>Current Trust</em>}</li>
 * </ul>
 *
 * @see keml.KemlPackage#getInformation()
 * @model abstract="true"
 * @generated
 */
public interface Information extends EObject {
	/**
	 * Returns the value of the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message</em>' attribute.
	 * @see #setMessage(String)
	 * @see keml.KemlPackage#getInformation_Message()
	 * @model
	 * @generated
	 */
	String getMessage();

	/**
	 * Sets the value of the '{@link keml.Information#getMessage <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message</em>' attribute.
	 * @see #getMessage()
	 * @generated
	 */
	void setMessage(String value);

	/**
	 * Returns the value of the '<em><b>Is Instruction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Instruction</em>' attribute.
	 * @see #setIsInstruction(boolean)
	 * @see keml.KemlPackage#getInformation_IsInstruction()
	 * @model
	 * @generated
	 */
	boolean isIsInstruction();

	/**
	 * Sets the value of the '{@link keml.Information#isIsInstruction <em>Is Instruction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Instruction</em>' attribute.
	 * @see #isIsInstruction()
	 * @generated
	 */
	void setIsInstruction(boolean value);

	/**
	 * Returns the value of the '<em><b>Repeated By</b></em>' reference list.
	 * The list contents are of type {@link keml.ReceiveMessage}.
	 * It is bidirectional and its opposite is '{@link keml.ReceiveMessage#getRepeats <em>Repeats</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repeated By</em>' reference list.
	 * @see keml.KemlPackage#getInformation_RepeatedBy()
	 * @see keml.ReceiveMessage#getRepeats
	 * @model opposite="repeats"
	 * @generated
	 */
	EList<ReceiveMessage> getRepeatedBy();

	/**
	 * Returns the value of the '<em><b>Targeted By</b></em>' reference list.
	 * The list contents are of type {@link keml.InformationLink}.
	 * It is bidirectional and its opposite is '{@link keml.InformationLink#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Targeted By</em>' reference list.
	 * @see keml.KemlPackage#getInformation_TargetedBy()
	 * @see keml.InformationLink#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<InformationLink> getTargetedBy();

	/**
	 * Returns the value of the '<em><b>Causes</b></em>' containment reference list.
	 * The list contents are of type {@link keml.InformationLink}.
	 * It is bidirectional and its opposite is '{@link keml.InformationLink#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Causes</em>' containment reference list.
	 * @see keml.KemlPackage#getInformation_Causes()
	 * @see keml.InformationLink#getSource
	 * @model opposite="source" containment="true"
	 * @generated
	 */
	EList<InformationLink> getCauses();

	/**
	 * Returns the value of the '<em><b>Is Used On</b></em>' reference list.
	 * The list contents are of type {@link keml.SendMessage}.
	 * It is bidirectional and its opposite is '{@link keml.SendMessage#getUses <em>Uses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Used On</em>' reference list.
	 * @see keml.KemlPackage#getInformation_IsUsedOn()
	 * @see keml.SendMessage#getUses
	 * @model opposite="uses"
	 * @generated
	 */
	EList<SendMessage> getIsUsedOn();

	/**
	 * Returns the value of the '<em><b>Felt Trust Immediately</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Felt Trust Immediately</em>' attribute.
	 * @see #setFeltTrustImmediately(Float)
	 * @see keml.KemlPackage#getInformation_FeltTrustImmediately()
	 * @model
	 * @generated
	 */
	Float getFeltTrustImmediately();

	/**
	 * Sets the value of the '{@link keml.Information#getFeltTrustImmediately <em>Felt Trust Immediately</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Felt Trust Immediately</em>' attribute.
	 * @see #getFeltTrustImmediately()
	 * @generated
	 */
	void setFeltTrustImmediately(Float value);

	/**
	 * Returns the value of the '<em><b>Felt Trust Afterwards</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Felt Trust Afterwards</em>' attribute.
	 * @see #setFeltTrustAfterwards(Float)
	 * @see keml.KemlPackage#getInformation_FeltTrustAfterwards()
	 * @model
	 * @generated
	 */
	Float getFeltTrustAfterwards();

	/**
	 * Sets the value of the '{@link keml.Information#getFeltTrustAfterwards <em>Felt Trust Afterwards</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Felt Trust Afterwards</em>' attribute.
	 * @see #getFeltTrustAfterwards()
	 * @generated
	 */
	void setFeltTrustAfterwards(Float value);

	/**
	 * Returns the value of the '<em><b>Initial Trust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial Trust</em>' attribute.
	 * @see #setInitialTrust(Float)
	 * @see keml.KemlPackage#getInformation_InitialTrust()
	 * @model
	 * @generated
	 */
	Float getInitialTrust();

	/**
	 * Sets the value of the '{@link keml.Information#getInitialTrust <em>Initial Trust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial Trust</em>' attribute.
	 * @see #getInitialTrust()
	 * @generated
	 */
	void setInitialTrust(Float value);

	/**
	 * Returns the value of the '<em><b>Current Trust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Current Trust</em>' attribute.
	 * @see #setCurrentTrust(Float)
	 * @see keml.KemlPackage#getInformation_CurrentTrust()
	 * @model
	 * @generated
	 */
	Float getCurrentTrust();

	/**
	 * Sets the value of the '{@link keml.Information#getCurrentTrust <em>Current Trust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Current Trust</em>' attribute.
	 * @see #getCurrentTrust()
	 * @generated
	 */
	void setCurrentTrust(Float value);

} // Information
