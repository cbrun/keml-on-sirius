/**
 */
package keml;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Information Link Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see keml.KemlPackage#getInformationLinkType()
 * @model
 * @generated
 */
public enum InformationLinkType implements Enumerator {
	/**
	 * The '<em><b>SUPPLEMENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUPPLEMENT_VALUE
	 * @generated
	 * @ordered
	 */
	SUPPLEMENT(0, "SUPPLEMENT", "SUPPLEMENT"),

	/**
	 * The '<em><b>SUPPORT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUPPORT_VALUE
	 * @generated
	 * @ordered
	 */
	SUPPORT(1, "SUPPORT", "SUPPORT"),

	/**
	 * The '<em><b>STRONG SUPPORT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STRONG_SUPPORT_VALUE
	 * @generated
	 * @ordered
	 */
	STRONG_SUPPORT(2, "STRONG_SUPPORT", "STRONG_SUPPORT"),

	/**
	 * The '<em><b>ATTACK</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ATTACK_VALUE
	 * @generated
	 * @ordered
	 */
	ATTACK(3, "ATTACK", "ATTACK"),

	/**
	 * The '<em><b>STRONG ATTACK</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STRONG_ATTACK_VALUE
	 * @generated
	 * @ordered
	 */
	STRONG_ATTACK(4, "STRONG_ATTACK", "STRONG_ATTACK");

	/**
	 * The '<em><b>SUPPLEMENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUPPLEMENT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SUPPLEMENT_VALUE = 0;

	/**
	 * The '<em><b>SUPPORT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUPPORT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SUPPORT_VALUE = 1;

	/**
	 * The '<em><b>STRONG SUPPORT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STRONG_SUPPORT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int STRONG_SUPPORT_VALUE = 2;

	/**
	 * The '<em><b>ATTACK</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ATTACK
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ATTACK_VALUE = 3;

	/**
	 * The '<em><b>STRONG ATTACK</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STRONG_ATTACK
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int STRONG_ATTACK_VALUE = 4;

	/**
	 * An array of all the '<em><b>Information Link Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final InformationLinkType[] VALUES_ARRAY = new InformationLinkType[] { SUPPLEMENT, SUPPORT,
			STRONG_SUPPORT, ATTACK, STRONG_ATTACK, };

	/**
	 * A public read-only list of all the '<em><b>Information Link Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<InformationLinkType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Information Link Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static InformationLinkType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			InformationLinkType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Information Link Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static InformationLinkType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			InformationLinkType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Information Link Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static InformationLinkType get(int value) {
		switch (value) {
		case SUPPLEMENT_VALUE:
			return SUPPLEMENT;
		case SUPPORT_VALUE:
			return SUPPORT;
		case STRONG_SUPPORT_VALUE:
			return STRONG_SUPPORT;
		case ATTACK_VALUE:
			return ATTACK;
		case STRONG_ATTACK_VALUE:
			return STRONG_ATTACK;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private InformationLinkType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
		return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}

} //InformationLinkType
