package org.tusofia.pik3.main;

/**
 * A program that shows a basic usage of synchronisations
 * 
 * @author kaleksandrov
 * 
 */
public class SynchronisationExample {

	private static int counter1;
	private static int counter2;
	private static int counter3;
	private static Object lock = new Object();

	/**
	 * Methods that increments the counter and prints its value. The method is
	 * NOT synchronized so it is possible to have memory inconsistency
	 * ("dirty reads").
	 */
	private static void incrementAndPrintNotSynchronized() {
		counter1++;
		System.out.println(String.format("Counter = %d", counter1));
	}

	/**
	 * Methods that increments the counter and prints its value. The method is
	 * synchronized so it is NOT possible to have memory inconsistency
	 * ("dirty reads"). The lock is performed over SynchronisationExample.class
	 * object.
	 */
	private static synchronized void incrementAndPrintSynchronizedMethod() {
		counter2++;
		System.out.println(String.format("Counter = %d", counter2));
	}

	/**
	 * Methods that increments the counter and prints its value. The method is
	 * synchronized so it is NOT possible to have memory inconsistency
	 * ("dirty reads"). The lock is performed over SynchronisationExample.class
	 * object.
	 */
	private static void incrementAndPrintSynchronizedBlock() {
		synchronized (lock) {
			counter3++;
			System.out.println(String.format("Counter = %d", counter3));
		}
	}

	/**
	 * The entry point of the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Runnable runnable1 = new Runnable() {
			@Override
			public void run() {
				incrementAndPrintNotSynchronized();
			}
		};

		Thread thread1 = new Thread(runnable1);
		Thread thread2 = new Thread(runnable1);
		Thread thread3 = new Thread(runnable1);
		Thread thread4 = new Thread(runnable1);

		System.out.println("========= Memory incosistency example ===========");
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();

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

		Runnable runnable2 = new Runnable() {
			@Override
			public void run() {
				incrementAndPrintSynchronizedMethod();
			}
		};

		Thread thread5 = new Thread(runnable2);
		Thread thread6 = new Thread(runnable2);
		Thread thread7 = new Thread(runnable2);
		Thread thread8 = new Thread(runnable2);

		System.out.println("============== Synchronized method ==============");
		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();

		try {
			thread5.join();
			thread6.join();
			thread7.join();
			thread8.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

		System.out.println("=================================================");
		System.out.println();

		Runnable runnable3 = new Runnable() {
			@Override
			public void run() {
				incrementAndPrintSynchronizedBlock();
			}
		};

		Thread thread9 = new Thread(runnable3);
		Thread thread10 = new Thread(runnable3);
		Thread thread11 = new Thread(runnable3);
		Thread thread12 = new Thread(runnable3);

		System.out.println("============== Synchronized block ===============");
		thread9.start();
		thread10.start();
		thread11.start();
		thread12.start();

		try {
			thread9.join();
			thread10.join();
			thread11.join();
			thread12.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

		System.out.println("=================================================");
		System.out.println();

	}
}
