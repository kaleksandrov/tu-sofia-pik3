package bg.tusofia.pik3.net.client;

public interface Client {

	void disconnect();

	String getName();

	void send(final Client sender, final String message);

	String receive();
}
