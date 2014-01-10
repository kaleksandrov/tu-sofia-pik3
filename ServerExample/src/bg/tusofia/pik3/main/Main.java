package bg.tusofia.pik3.main;

import java.util.Scanner;

import bg.tusofia.pik3.net.server.Server;
import bg.tusofia.pik3.net.server.impl.ServerImpl;

/**
 * The main class of the Server application.
 * 
 * @author kaleksandrov
 * 
 */
public class Main {

	/**
	 * The entry point of the application.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		final Scanner in = new Scanner(System.in);

		// Enter the port to listen
		System.out.print("Port: ");
		final int port = in.nextInt();
		in.nextLine();

		// Start the server
		Server server = new ServerImpl(port);
		server.start();
	}
}
