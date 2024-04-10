package server;

import java.io.IOException;

import lib.ChannelException;
import lib.CommServer;
import lib.ProtocolMessages;

public class ServerThreads implements Runnable {
	private int idClient; // ID of the client
	private CommServer com; // communication channel of the server

	/**
	 * Creates a thread of execution for the client's OOS identified
	 * as specified.
	 * 
	 * @param idClient the client identifier
	 * @param com      the server communication channel
	 */
	public ServerThreads(int idClient, CommServer com) {
		this.idClient = idClient;
		this.com = com;
	}

	/**
	 * Executes the thread corresponding to a client. Creates the object of
	 * service operations (OOS) for this client and allows
	 * conversation with the client (exchanging messages) until
	 * the client disconnects.
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		GreeterSOO objService = null; // service operations object
		ProtocolMessages request; // incoming message (client request)
		ProtocolMessages response; // outgoing message (response to the client)

		try {
			// 1. Create the service object in the thread for a client
			objService = new GreeterSOO();

			// 2. Exchange messages with that client
			while (!com.closed(idClient)) {
				try {
					// 2.1 Wait to receive the serialized request from the client
					request = com.waitEvent(idClient);

					// 2.2 Evaluate the received order
					response = com.processEvent(idClient, objService, request);

					if (response != null) { // operation with response
						// 2.3 Send the response to the client
						com.sendReply(idClient, response);
					}
				} catch (ClassNotFoundException e) {
					// non critical exception (a message is lost)
					System.err.printf(
							"Error in client request %d: %s\n",
							idClient, e.getMessage());
				}
			}
		} catch (IOException | ChannelException e) {
			String str = e.getMessage();
			System.err.printf("Error: %s\n",
					str != null ? str : e.getClass().getSimpleName());
		} finally {
			// 3. Close the service object for the disconnected client
			if (objService != null) {
				objService.close();
			}
		}
	}

}
