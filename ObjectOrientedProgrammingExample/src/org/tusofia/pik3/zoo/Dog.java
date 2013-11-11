package org.tusofia.pik3.zoo;

/**
 * This class extends the Mammal class. It inherits all of its public/protected
 * methods and fields.
 * 
 * @author Kiril Aleksandrov
 * 
 */
public class Dog extends Mammal implements Barking {

	/* Constant */

	/**
	 * This is a private constant.
	 */
	private static final Color BLACK = new Color(0, 0, 0);

	/* Constructors */

	public Dog() {
		// Here implicitly is called the super() constructor.

		// Access the "color" property from the super class (Mammal)
		color = BLACK;

		// Access the "age" property from the super class (Mammal) using the
		// keyword "super". It references the super class object.
		super.age = 20;
		super.name = "Nameless doggy :/";

		System.out.println("DOG : Created!");
	}

	public Dog(String name) {
		// Call the overloaded constructor.
		this();

		// Access the derived property "name" property (from the supper class
		// Mammal) using the keyword "this". It references to the current
		// object.
		this.name = name;
	}

	/* Public methods */

	@Override
	/**
	 * Public method that is overloaded.
	 */
	public void bark() {
		System.out.println("DOG : Bau-bau!");
	}

	@Override
	/**
	 * Public method that is overloaded.
	 * 
	 * @param barkSound
	 */
	public void bark(String barkSound) {
		System.out.println("DOG : " + barkSound);
	}

	@Override
	/**
	 * Override the "speak" method from the super class.
	 */
	public void speak() {
		System.out.println("DOG : Speaaaaaaaaaak!!!!");
	}
}
