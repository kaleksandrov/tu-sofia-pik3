package bg.tusofia.pik3.zoo;

/**
 * This class extends the Mammal class. It inherits all of its public/protected
 * methods and fields.
 * 
 * @author Kiril Aleksandrov
 * 
 */
public class Cat extends Mammal {

	/* Constructors */

	public Cat() {
		System.out.println("CAT : Created!");
	}

	@Override
	/**
	 * Override the walk method from the super class.
	 */
	public void walk() {
		System.out.println("CAT : I am one happy walking cat :)");
	}
	
	@Override
	/**
	 * Override the "speak" method from the super class.
	 */
	public void speak() {
		System.out.println("CAT : Speak-speak-speak");
	}
}
