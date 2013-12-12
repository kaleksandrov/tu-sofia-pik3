package org.tusofia.pik3.main;

public class MyRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println(String.format("Hellow from '%s' thread!", //
				Thread.currentThread().getName()));
	}
}
