package bg.tusofia.pik3.net.server;

import bg.tusofia.pik3.net.client.Client;

public interface Server {

	void start();

	void stop();

	void broadcast(final Client sender, final String message);
}
