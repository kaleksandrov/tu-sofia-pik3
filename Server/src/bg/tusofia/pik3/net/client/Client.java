package bg.tusofia.pik3.net.client;

import java.io.PrintWriter;
import java.util.Scanner;

public interface Client {

	void disconnect();

	String getName();

	PrintWriter getOut();

	Scanner getIn();

	void send(final String sender, final String message);
}
