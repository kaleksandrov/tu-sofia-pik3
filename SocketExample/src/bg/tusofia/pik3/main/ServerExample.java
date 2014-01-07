package bg.tusofia.pik3.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import bg.tusofia.pik3.util.Constants;

/**
 * A program that shows a basic usage of sockets
 * 
 * @author kaleksandrov
 * 
 */
public class ServerExample {

	private static final String MESSAGE = "Hello from the server!";

	/**
	 * The entry point of the program
	 */
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// Create the server
			serverSocket = new ServerSocket(Constants.SERVER_PORT);
			System.out.println("[Server] Created!");

			// Start listening for connections. The program waits until some
			// client connects to the socket.
			System.out.format("[Server] Start listening on port %d...\n", Constants.SERVER_PORT);
			Socket server = serverSocket.accept();

			System.out.println("[Server] Someone has just connected!");

			// Use a PrintWriter to send data through the socket
			PrintWriter out = new PrintWriter(server.getOutputStream());

			// Send a message
			System.out.format("[Server] Sending message '%s'...\n", MESSAGE);
			out.println(MESSAGE);

			// Flush the output to be sure the data has been send
			out.flush();
			System.out.println("[Server] Message sent!");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (serverSocket != null) {
				try {
					// Close the server
					serverSocket.close();
					System.out.println("[Server] Closed!");
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
}
