package bg.tusofia.pik3.main;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClientExample {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("localhost", 5555);
		Scanner in = new Scanner(client.getInputStream());
		System.out.println(in.nextLine());
	}
}
