package bg.tusofia.pik3.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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

		client();
	}

	public static void client() throws UnknownHostException, IOException {
		String hostName = "localhost";
		int portNumber = 8080;

		Socket echoSocket = new Socket(hostName, portNumber);
		PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				echoSocket.getInputStream()));
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in));

	}
}
