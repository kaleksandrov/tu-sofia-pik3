package bg.tusofia.pik3.net.server.impl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import bg.tusofia.pik3.net.client.Client;
import bg.tusofia.pik3.net.client.impl.SimpleClientImpl;
import bg.tusofia.pik3.net.client.impl.SystemClientImpl;
import bg.tusofia.pik3.net.server.Server;

/**
 * A simple implementation fo the {@link Server} interface.
 * 
 * @author kaleksandrov
 * 
 */
public class ServerImpl implements Server {

	private int port;
	private ServerSocket serverSocket;
	private List<Client> clients;
	private Client systemClient = new SystemClientImpl();

	public ServerImpl(final int port) {
		this.port = port;
		this.clients = new ArrayList<Client>();
	}

	@Override
	public void start() {
		try {
			// Create the server
			serverSocket = new ServerSocket(port);
			System.out.println("[Server] Created!");

			// Start listening for connections. The program waits until some
			// client connects to the socket.
			System.out.format("[Server] Start listening on port %d...\n", port);

			while (true) {
				// Wait for incoming connections
				final Socket socket = serverSocket.accept();

				final Client client = new SimpleClientImpl(socket);
				clients.add(client);

				final Thread clientThread = new Thread() {
					@Override
					public void run() {
						printHeader(client);
						broadcast(systemClient,
								String.format("%s has just connected!\n", client.getName()));

						String line = client.receive();
						while (true) {
							broadcast(client, line);
							line = client.receive();
						}
					};
				};
				clientThread.start();
			}
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

	@Override
	public void stop() {
		try {
			for (Client client : clients) {
				client.disconnect();
			}
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void broadcast(final Client sender, final String message) {
		for (Client client : clients) {
			if (client != sender) {
				client.send(sender, message);
			}
		}
	}

	private void printHeader(final Client client) {
		client.send(systemClient, "=======================================");
		client.send(systemClient, "============== Greetings! =============");
		client.send(systemClient, "=======================================");
		client.send(systemClient, "");
		client.send(systemClient, String.format("===  Welcome, %s!", client.getName()));
	}
}
