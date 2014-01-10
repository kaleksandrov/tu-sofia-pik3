package bg.tusofia.pik3.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientImpl implements Client {

	private String host;
	private int port;
	private String name;

	private Socket socket;
	private volatile boolean isFinished;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.format("[Client] Connected to server %s:%d!\n", host, port);

		Thread t2 = new Thread() {
			@Override
			public void run() {
				System.out.println("Started...");
				PrintWriter out = null;
				Scanner sysIn = new Scanner(System.in);
				try {
					out = new PrintWriter(socket.getOutputStream());
					while (sysIn.hasNext() && !isFinished) {
						String line = sysIn.nextLine();
						System.out.println("Read line : " + line);

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
		t2.start();

		Thread t1 = new Thread() {
			@Override
			public void run() {
				// Use a Scanner to read from the remote server

				Scanner in = null;
				try {
					in = new Scanner(socket.getInputStream());
					String line = in.nextLine();
					while (!"exit".equals(line) && !isFinished) {
						System.out.println(line);
						line = in.nextLine();
					}

					isFinished = true;
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (in != null) {
						in.close();
					}
				}
			};
		};
		t1.start();
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void send(String message) {
		// TODO Auto-generated method stub

	}

}
