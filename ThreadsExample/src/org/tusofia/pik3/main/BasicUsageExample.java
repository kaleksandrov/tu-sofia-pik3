package org.tusofia.pik3.main;

import org.tusofia.pik3.concurrent.MyRunnable;
import org.tusofia.pik3.concurrent.MyThread;

/**
 * A program that shows a basic usage of threads in Java
 * 
 * @author kaleksandrov
 * 
 */
public class BasicUsageExample extends Example {

	/**
	 * The entry point of the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Retrieves a reference to the main thread
		Thread mainThread = Thread.currentThread();
		System.out.println();
		System.out.println("=========== Get the current thread ==============");
		System.out.println(String.format("Hello from thread '%s'!", //
				mainThread.getName()));

		System.out.println("=================================================");
		System.out.println();

		// Create a new thread object
		Thread thread1 = new MyThread();

		// Create an anonymous thread
		Thread thread2 = new Thread() {
			@Override
			public void run() {
				System.out.println(String.format("Hello from thread '%s'!", //
						Thread.currentThread().getName()));
			}
		};

		// Create a thread from a runnable
		Runnable runnable = new MyRunnable();
		Thread thread3 = new Thread(runnable);

		// Create a thread from an anonymous runnable
		Thread thread4 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println(String.format("Hello from thread '%s'!", //
						Thread.currentThread().getName()));
			}
		});

		// Start all the threads. The order of execution is undefined.
		System.out.println("============ Start the threads ==================");
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();

		// Tells the current thread to wait the given thread to finish.
		// This way the main thread will wait the threads created before
		// to finish before continue execution
		try {
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

		System.out.println("=================================================");
		System.out.println();

		// DO NOT DO THIS!!
		// This does not start the thread. It just calls the methods run() in
		// the current thread.
		System.out.println("============= Run the threads ===================");
		// These threads jave overriden run() method
		thread1.run();
		thread2.run();

		// These thread have passed a new Runnable object and their run()
		// methods are empty
		thread3.run();
		thread4.run();

		System.out.println("=================================================");
		System.out.println();

		// Time the thread havge to sleep in milliseconds
		int millisecondsToSleep = 1000;
		int seconds = 5;

		System.out.println("==================== Sleep =====================");
		for (int second = 0; second < seconds; ++second) {
			try {
				// Sleep the current thread for the given amount of milliseconds
				System.out.println(String.format("Sleeping... %d", second));
				Thread.sleep(millisecondsToSleep);
			} catch (InterruptedException e) {
				// Throw an InterruptedException if something interrupts the
				// thread
				e.printStackTrace();
			}
		}

		System.out.println("=================================================");
		System.out.println();

		final int times = 20;
		Runnable runnable2 = new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < times; ++i) {
					System.out.println(String.format("thread '%s' - %d", //
							Thread.currentThread().getName(), //
							i));
				}
			}
		};

		Thread thread5 = new Thread(runnable2);
		Thread thread6 = new Thread(runnable2);

		System.out.println("============ Concurent execution ================");
		thread5.start();
		thread6.start();

		System.out.println("=================================================");
		System.out.println();
	}
}
