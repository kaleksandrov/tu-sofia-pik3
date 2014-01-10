package bg.tusofia.pik3.client;

public interface Client {

	void connect();

	void disconnect();

	boolean isConnected();

	void send(final String message);
}
