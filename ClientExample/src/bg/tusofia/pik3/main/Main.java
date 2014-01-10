package bg.tusofia.pik3.main;

import java.util.Scanner;

import bg.tusofia.pik3.client.Client;
import bg.tusofia.pik3.client.ClientImpl;

public class Main {

	public static void main(String[] args) {
		final Scanner in = new Scanner(System.in);

		// Enter server's host
		System.out.print("Host: ");
		final String host = in.nextLine();

		// Enter server's port
		System.out.print("Port: ");
		final int port = in.nextInt();
		in.nextLine();

		// Enter username
		System.out.print("Username: ");
		final String username = in.nextLine();

		Client client = new ClientImpl(host, port, username);
		client.connect();
	}
}
