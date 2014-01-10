package bg.tusofia.pik3.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientImpl implements Client {

	private String host;
	private int port;
	private String name;

	private Socket socket;
	private volatile AtomicBoolean isFinished;

	public ClientImpl(final String host, final int port, final String name) {
		this.host = host;
		this.port = port;
		this.name = name;
	}

	@Override
	public void connect() {
		try {
			socket = new Socket(host, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.format("[Client] Connected to server %s:%d!\n", host, port);

		final Thread outThread = new Thread() {
			@Override
			public void run() {
				System.out.println("Started...");
				PrintWriter out = null;
				Scanner sysIn = new Scanner(System.in);
				try {
					out = new PrintWriter(socket.getOutputStream());
					out.println(name);
					out.flush();

					while (sysIn.hasNext() && !isFinished.get()) {
						String line = sysIn.nextLine();
						if ("exit".equals(line)) {
							synchronized (isFinished) {
								isFinished.set(true);
							}
						}
						out.println(line);
						out.flush();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (out != null) {
						out.close();
					}
				}
			};
		};
		outThread.start();

		final Thread inThread = new Thread() {
			@Override
			public void run() {
				// Use a Scanner to read from the remote server

				Scanner in = null;
				try {
					in = new Scanner(socket.getInputStream());
					String line = in.nextLine();
					while (!isFinished.get()) {
						System.out.println(line);
						line = in.nextLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (in != null) {
						in.close();
					}
				}
			};
		};
		inThread.start();
	}

	@Override
	public void disconnect() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean isConnected() {
		return socket.isConnected() && !socket.isClosed();
	}

	@Override
	public void send(String message) {

	}

}
