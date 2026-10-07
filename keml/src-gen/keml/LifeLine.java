/**
 */
package keml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Life Line</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * LifeLines are defined by the UML standard as essential parts of a sequence diagram.
 * The xPosition positions the life line on the horizontal axis. Vertically, it starts as high as possible.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link keml.LifeLine#getName <em>Name</em>}</li>
 *   <li>{@link keml.LifeLine#getXPosition <em>XPosition</em>}</li>
 * </ul>
 *
 * @see keml.KemlPackage#getLifeLine()
 * @model abstract="true"
 * @generated
 */
public interface LifeLine extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see keml.KemlPackage#getLifeLine_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link keml.LifeLine#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>XPosition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XPosition</em>' attribute.
	 * @see #setXPosition(int)
	 * @see keml.KemlPackage#getLifeLine_XPosition()
	 * @model
	 * @generated
	 */
	int getXPosition();

	/**
	 * Sets the value of the '{@link keml.LifeLine#getXPosition <em>XPosition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XPosition</em>' attribute.
	 * @see #getXPosition()
	 * @generated
	 */
	void setXPosition(int value);

} // LifeLine
