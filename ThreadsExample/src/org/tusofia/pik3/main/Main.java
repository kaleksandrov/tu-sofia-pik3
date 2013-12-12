package org.tusofia.pik3.main;

public class Main {
	public static void main(String[] args) {
		// Start a thread
		final String mainThreadName = Thread.currentThread().getName();
		System.out.println(mainThreadName);
		new MyThread().start();
		new MyThread().run();
		new Thread(new MyRunnable()).start();

		// Sleep a thread
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
