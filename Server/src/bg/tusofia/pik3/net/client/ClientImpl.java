package bg.tusofia.pik3.net.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ClientImpl implements Client {

	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private String name;
	private Socket socket;
	private PrintWriter out;
	private Scanner in;

	public ClientImpl(final String name, final Socket socket) {
		this.name = name;
		this.socket = socket;

		try {
			this.in = new Scanner(socket.getInputStream());
			this.out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	public PrintWriter getOut() {
		return out;
	}

	@Override
	public Scanner getIn() {
		return in;
	}

	@Override
	public synchronized void send(String sender, String message) {
		synchronized (out) {
			out.format("[%s](%s) %s\n", now(), sender, message);
			out.flush();
		}
	}

	private String now() {
		return FORMATTER.format(new Date());
	}
}
