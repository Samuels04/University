package server;

import lib.CommServer;
import lib.ProtocolMessages;
import optional.Trace;

public class Server {
	
	private static void registerOperations(CommServer com) {
		com.addFunction("greet", (o, x) -> ((GreeterSOO)o).greet());
		com.addFunction("reset", (o, x) -> ((GreeterSOO) o).changeGreetMsg(String.format("%o", x)));
		
	}
	
	public static void main(String[] args) throws Exception {
		CommServer com;				// Communication channel of the server
		GreeterSOO objService;		// Service operations object
		ProtocolMessages request;	// request message (received from the client)
		ProtocolMessages response;	// response message (sent to the client)
		int idClient;				// identifier of the client
		
		// 1. Create the communication channel
		com = new CommServer();
		
		// 2. Register the service operations
		registerOperations(com);
			
		// optional operations
		Trace.activateTrace(com); // Activates the action tracking in the server console
		com.activateMessageLog(); // Activates the message log of the server into a file
		
		// 3. Infinite loop to serve clients
		while (true) {
			// 3.1 Wait for a client (blocking wait) and identify it
			idClient = com.waitForClient();
			
			// 3.2 Create the service object for the connected client
			objService = new GreeterSOO("Good afternoon...");
			
			// 3.3 Exchange messages with the client
			while (!com.closed(idClient)) {
				// 3.3.1 Wait to receive the serialized order from the client
				request = com.waitEvent(idClient);
	    		
				// 3.3.2 Process the received order (deserialization and execution
				//			via the registered operations)
	    		response = com.processEvent(idClient, objService, request);

	    		if (response != null) { // If there is a response to be sent
					// 3.3.3 Send the response to the client
	    			com.sendReply(idClient, response);
    			}
			}
			
			// 3.4 Close the service object of the disconnected client
			objService.close();
		}
	}
}
