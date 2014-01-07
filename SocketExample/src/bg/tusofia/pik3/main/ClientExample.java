package bg.tusofia.pik3.main;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import bg.tusofia.pik3.util.Constants;

/**
 * A program that shows a basic usage of sockets
 * 
 * @author kaleksandrov
 * 
 */
public class ClientExample {

	/**
	 * The entry point of the client application.
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Socket client = null;
		try {
			// Connect to the server
			client = new Socket(Constants.SERVER_HOST, Constants.SERVER_PORT);
			System.out.format("[Client] Connected to server %s:%d!\n", Constants.SERVER_HOST,
					Constants.SERVER_PORT);

			// Use a Scanner to read from the remote server
			Scanner in = new Scanner(client.getInputStream());

			// Read from the server
			System.out.format("[Client] Message from the server : '%s'\n", in.nextLine());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (client != null) {
				try {
					// Disconnect from the server
					client.close();
					System.out.println("[Client] Disconnected!");
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
}
