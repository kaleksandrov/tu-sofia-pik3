package bg.tusofia.pik3.net.server;

import bg.tusofia.pik3.net.client.Client;

/**
 * An interface describing server's basic functionality.
 * 
 * @author kaleksandrov
 * 
 */
public interface Server {

	/**
	 * Starts listening for incoming connections on the given port number.
	 */
	void start();

	/**
	 * Disconnects all clients and stop the server.
	 */
	void stop();

	/**
	 * Sends a message to all clients connected.
	 * 
	 * @param sender
	 *            The client that sends the message.
	 * @param message
	 *            The message to be sent.
	 */
	void broadcast(final Client sender, final String message);
}
