package bg.tusofia.pik3.net.client.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import bg.tusofia.pik3.net.client.Client;

public class SimpleClientImpl implements Client {

	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private String name;
	private Socket socket;
	private PrintWriter out;
	private Scanner in;

	public SimpleClientImpl(final Socket socket) {
		this.socket = socket;
		
		try {
			this.in = new Scanner(socket.getInputStream());
			this.out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.name = in.nextLine();
	}

	@Override
	public void disconnect() {
		if (socket != null && !socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public synchronized void send(Client sender, String message) {
		synchronized (out) {
			out.format("[%s](%s) %s\n", now(), sender.getName(), message);
			out.flush();
		}
	}

	@Override
	public String receive() {
		return in.nextLine();
	}

	protected String now() {
		return FORMATTER.format(new Date());
	}
}
