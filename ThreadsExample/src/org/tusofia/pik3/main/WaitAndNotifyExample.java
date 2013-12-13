package org.tusofia.pik3.main;

import java.util.Scanner;

/**
 * A program that shows a basic usage of wait() and notify() methods.
 * 
 * @author kaleksandrov
 * 
 */
public class WaitAndNotifyExample extends Example {

	private static final String EXIT_KEYWORD = "exit";
	private static final Object barrier = new Object();
	private static String line;

	/**
	 * Entry point of the program
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		// Create a Runnable object that waits on the barrier lock
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				print("Waiting....");

				// Holds the lock for two reasons:
				// * wait() requires the lock
				// * avoid memory inconsistency on the line variable
				synchronized (barrier) {
					// Do this until the thread is interrupted or the exit
					// keyword is entered.
					while (!Thread.interrupted() && !isExit(line)) {
						try {
							// Wait on the barrier lock
							barrier.wait();

							doSomething();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

				print("End!");
			};

			/**
			 * Prints the current line.
			 */
			private void doSomething() {
				print(line);
			}
		};

		// Create few threads that uses the Runnable defined before
		Thread thread1 = new Thread(runnable);
		Thread thread2 = new Thread(runnable);
		Thread thread3 = new Thread(runnable);

		// Start the threads
		thread1.start();
		thread2.start();
		thread3.start();

		Scanner in = new Scanner(System.in);

		print("Enter some text to be printed from a random thread or 'exit' to quit:");
		// Read each line from the standard input
		while (in.hasNext()) {
			// Acquire the lock on the barrier for two reasosn:
			// * avoid memory inconsistency on the line variable
			// * notify() and notifyAll() methods require the lock
			synchronized (barrier) {
				// Read entered line
				line = in.nextLine();
				if (isExit(line)) {
					// Notify all threads that the exit keyword was entered
					barrier.notifyAll();
					break;
				} else {
					// Notify a random thread that some word was entered
					barrier.notify();
				}
			}
		}

		// Wait the threads to finish before exit the main thread
		try {
			thread1.join();
			thread2.join();
			thread3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		print("End!");
	}

	/**
	 * a Checks if the given value is the exit String
	 */
	private static boolean isExit(String value) {
		return EXIT_KEYWORD.equals(value);
	}

	/**
	 * Prints the name of the current thread and the given value
	 * 
	 * @param value
	 */
	protected static void print(final String value) {
		System.out.println(String.format("%s: %s", Thread.currentThread()
				.getName(), value));
	}
}
