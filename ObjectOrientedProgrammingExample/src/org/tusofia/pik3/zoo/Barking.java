package org.tusofia.pik3.zoo;

/**
 * This is an interface that defines two methods that are overloaded. Overloaded
 * methods are methods that have the same name and return type but different
 * arguments.
 * 
 * @author Kiril Aleksandrov
 * 
 */
public interface Barking {

	/**
	 * Declare methods with no arguments.
	 */
	void bark();

	/**
	 * Declare method with a single argument.
	 */
	void bark(String barkSound);
}
