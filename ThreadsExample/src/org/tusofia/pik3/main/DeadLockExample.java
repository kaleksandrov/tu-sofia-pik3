package org.tusofia.pik3.main;

import org.tusofia.pik3.concurrent.Person;

/**
 * A program that shows an example of a dead lock.
 * 
 * @author kaleksandrov
 * 
 */
public class DeadLockExample {

	/**
	 * The entry point of the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final Person alphonse = new Person("Alphonse");
		final Person gaston = new Person("Gaston");
		new Thread(new Runnable() {
			public void run() {
				alphonse.bow(gaston);
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				gaston.bow(alphonse);
			}
		}).start();
	}
}
