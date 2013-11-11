package org.tusofia.pik3.zoo;

/**
 * This is an abstract class. Abstract classes cannot be instantiated, they can
 * only be extended.
 * 
 * @author Kiril Aleksandrov
 * 
 */
public abstract class Animal {

	/* Constants */

	/**
	 * This is a private constant.
	 */
	@SuppressWarnings("unused")
	private static final int ANIMAL_SECRET_CONSTANT = 123;

	/**
	 * This is a public constant.
	 */
	public static final int ANIMAL_NOT_SO_SECRET_CONSTANT = 321;

	/* Fields */

	/**
	 * A static counter. This field is a property of the class, not of the
	 * current object. It is shared between all Animal instances. The field is
	 * private so it is visible only in this class (child classes have no
	 * access).
	 */
	private static int counter;

	/**
	 * This field is visible only by the children of this class. Outer access is
	 * performed via getter and setter methods.
	 */
	protected String name;

	/* Constructors */

	public Animal() {
		// Increment the count of all animals
		counter++;

		System.out.println("ANIMAL : Created!");
	}

	/**
	 * Overloaded constructor. Overloading is defining the same
	 * method/constructor but with different parameters.
	 */
	public Animal(String name) {
		// Call the other constructor so the counter will be incremented. Using
		// the "this" keyword that is referencing the current object.
		this();
		this.name = name;
	}

	/* Public methods */

	public void walk() {
		System.out.println("ANIMAL : I am walking");
	}

	public static int getCount() {
		return counter;
	}

	/**
	 * Abstract methods have no implementation. They have only a declaration (a
	 * signature). This is the reason why abstract classes cannot be
	 * instantiated.
	 */
	public abstract void speak();

	/**
	 * This method is executed when the object is collected by the Garbage
	 * Collector. DO NOT USE THIS METHOD!! YOU DON'T KNOW WHEN THE OBJECT WILL
	 * BE GARBAGE COLLECTED!!
	 */
	@Override
	protected void finalize() {
		System.out.println("ANIMAL : Destroyed");
	};

	/* Getters & Setters */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		// The "this" keyword references the current object. It is needed to
		// distinguish the field from method argument because their names are
		// the same.
		this.name = name;
	}
}
