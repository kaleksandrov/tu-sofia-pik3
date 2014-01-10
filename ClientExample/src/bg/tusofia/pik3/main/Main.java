package bg.tusofia.pik3.main;

import bg.tusofia.pik3.client.Client;
import bg.tusofia.pik3.client.ClientImpl;

public class Main {

	public static void main(String[] args) {
		Client client = new ClientImpl("localhost", 5555, "Kiro");
		client.connect();
	}
}
