package bg.tusofia.pik3.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * A program that shows a basic usage of sockets
 * 
 * @author kaleksandrov
 * 
 */
public class SocketExample {

	/**
	 * The entry point of the program
	 * 
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException,
			IOException {

		ServerSocket serverSocket = new ServerSocket(5555);
		Socket server = serverSocket.accept();
		PrintWriter out = new PrintWriter(server.getOutputStream());
		out.println("Hello");
		out.flush();
	}
}
