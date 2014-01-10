package bg.tusofia.pik3.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bg.tusofia.pik3.net.client.Client;
import bg.tusofia.pik3.net.client.ClientImpl;

public class ServerImpl implements Server {

	private int port;
	private ServerSocket serverSocket;

	private List<Client> clients;

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
				final Socket socket = serverSocket.accept();
				final Client client = new ClientImpl("test", socket);
				clients.add(client);

				Thread t = new Thread() {
					@Override
					public void run() {
						System.out.println("[Server] Someone has just connected!");
						client.send(client.getName(), "Welcome!");

						Scanner in = client.getIn();
						String line = in.nextLine();
						while (true) {
							System.out.println("Read line : " + line);
							broadcast(client, line);
							line = in.nextLine();
						}
					};
				};
				t.start();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void broadcast(final Client sender, final String message) {
		for (Client client : clients) {
			client.send(sender.getName(), message);
		}
	}
}
