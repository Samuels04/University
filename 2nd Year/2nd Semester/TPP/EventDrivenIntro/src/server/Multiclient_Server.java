package server;

import java.io.IOException;
import lib.ChannelException;
import lib.CommServer;
import optional.Trace;

public class Multiclient_Server {

	private static void registerOperations(CommServer com) {
		// Service operations registration
		com.addFunction("greet",
				(o, x) -> ((GreeterSOO) o).greet());
		com.addAction("changeGreetMsg",
				(o, x) -> ((GreeterSOO) o).changeGreetMsg((String) x[0]));
		com.addAction("reset",
				(o, x) -> ((GreeterSOO) o).reset(), true);
		com.addFunction("checkNumberClients",
				(o, x) -> ((GreeterSOO) o).getNumberClients());
	} // registerOperations

	public static void main(String[] args) {
		CommServer com; // Communication channel of the server
		int idClient; // Client identifier
		

		try {
			// 1. Create the communication channel
			com = new CommServer();

			// Activate tracing of the server actions (optional)
			Trace.activateTrace(com);

			// Activate the message log of the server into a file (optional)
			com.activateMessageLog();

			// 2. Register the service operations
			registerOperations(com);

			// Offer the service (it remains listening)
			while (true) {
				// 3.1 Wait for a client (blocking operation) and identify it
				idClient = com.waitForClient();
				
				// 3.2 Create a thread to serve the client, where the SOO and
				// message exchange will be executed
				new Thread(new ServerThreads(idClient, com)).start();
				
			} // while
		} catch (IOException | ChannelException e) {
			// Critical exceptions, the server is stopped
			String str = e.getMessage();
			System.err.printf("Error: %s\n", str != null ? str : e.getClass().getSimpleName());
		}

	} // main

} // class Servidor
