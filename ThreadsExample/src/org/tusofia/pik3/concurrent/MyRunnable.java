package org.tusofia.pik3.concurrent;

public class MyRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println(String.format("Hello from thread '%s'!", //
				Thread.currentThread().getName()));
	}
}
