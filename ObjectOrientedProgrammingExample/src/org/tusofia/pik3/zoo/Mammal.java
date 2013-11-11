package org.tusofia.pik3.zoo;

/**
 * This class extends the Animal class so it inherits all of its public and
 * protected methods and fields.
 * 
 * @author Kiril Aleksandrov
 * 
 */
public class Mammal extends Animal {

	/**
	 * This is an example of an inner class. It is defined inside another class.
	 * The access modifier is "protected" so this class is visible here an in
	 * all child classes.
	 * 
	 * @author Kiril Aleksandrov
	 * 
	 */
	protected static class Color {

		/* Members */

		private int red;
		private int green;
		private int blue;

		public Color(int red, int green, int blue) {
			this.red = red;
			this.green = green;
			this.blue = blue;
		}

		/* Getters */

		public int getRed() {
			return red;
		}

		public int getGreen() {
			return green;
		}

		public int getBlue() {
			return blue;
		}
	}

	/* Members */

	/**
	 * This is a private member of the class. It is visible only in this class
	 * (children have no access). The field is accessed via getter and setter
	 * methods.
	 */
	private boolean isMale;
	protected Color color;
	protected int age;

	/* Constructors */

	public Mammal() {
		// Here implicitly is called the super() constructor.
		System.out.println("MAMMAL : Created!");
	}

	public Mammal(String name) {
		// Exlicitly call the super() constructor.
		super(name);

		System.out.println("MAMMAL : Created!");
	}

	/* Public methods */

	@Override
	public void speak() {
		System.out.println("MAMMAL: Waaaaaaaa!!");
	}

	/* Getters & Setters */

	public boolean isMale() {
		return isMale;
	}

	public void setMale(boolean isMale) {
		// The "this" keyword references the current object. It is needed to
		// distinguish the field from method argument because their names are
		// the same.
		this.isMale = isMale;
	}
}
