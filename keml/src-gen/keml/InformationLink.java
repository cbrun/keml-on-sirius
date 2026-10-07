/**
 */
package keml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Information Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link keml.InformationLink#getLinkText <em>Link Text</em>}</li>
 *   <li>{@link keml.InformationLink#getType <em>Type</em>}</li>
 *   <li>{@link keml.InformationLink#getTarget <em>Target</em>}</li>
 *   <li>{@link keml.InformationLink#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @see keml.KemlPackage#getInformationLink()
 * @model
 * @generated
 */
public interface InformationLink extends EObject {
	/**
	 * Returns the value of the '<em><b>Link Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Link Text</em>' attribute.
	 * @see #setLinkText(String)
	 * @see keml.KemlPackage#getInformationLink_LinkText()
	 * @model
	 * @generated
	 */
	String getLinkText();

	/**
	 * Sets the value of the '{@link keml.InformationLink#getLinkText <em>Link Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link Text</em>' attribute.
	 * @see #getLinkText()
	 * @generated
	 */
	void setLinkText(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link keml.InformationLinkType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see keml.InformationLinkType
	 * @see #setType(InformationLinkType)
	 * @see keml.KemlPackage#getInformationLink_Type()
	 * @model
	 * @generated
	 */
	InformationLinkType getType();

	/**
	 * Sets the value of the '{@link keml.InformationLink#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see keml.InformationLinkType
	 * @see #getType()
	 * @generated
	 */
	void setType(InformationLinkType value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link keml.Information#getTargetedBy <em>Targeted By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Information)
	 * @see keml.KemlPackage#getInformationLink_Target()
	 * @see keml.Information#getTargetedBy
	 * @model opposite="targetedBy" required="true"
	 * @generated
	 */
	Information getTarget();

	/**
	 * Sets the value of the '{@link keml.InformationLink#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Information value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link keml.Information#getCauses <em>Causes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' container reference.
	 * @see #setSource(Information)
	 * @see keml.KemlPackage#getInformationLink_Source()
	 * @see keml.Information#getCauses
	 * @model opposite="causes" required="true" transient="false"
	 * @generated
	 */
	Information getSource();

	/**
	 * Sets the value of the '{@link keml.InformationLink#getSource <em>Source</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' container reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Information value);

} // InformationLink
