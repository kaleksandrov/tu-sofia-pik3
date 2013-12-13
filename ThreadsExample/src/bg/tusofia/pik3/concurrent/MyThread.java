package bg.tusofia.pik3.concurrent;

/**
 * Custom thread implementation. This class extends the {@link Thread} class and
 * overrides the run method.
 * 
 * @author kaleksandrov
 * 
 */
public class MyThread extends Thread {

	@Override
	public void run() {

		// getName() method is derived from the Thread class
		System.out.println(String.format("Hello from thread '%s'!", //
				Thread.currentThread().getName()));
	}
}
