package bg.tusofia.pik3.main;

import bg.tusofia.pik3.net.server.Server;
import bg.tusofia.pik3.net.server.ServerImpl;

public class Main {

	public static void main(String[] args) {
		Server server = new ServerImpl(5555);
		server.start();
	}
}
