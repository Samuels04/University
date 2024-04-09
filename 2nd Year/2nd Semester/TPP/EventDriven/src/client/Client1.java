package client;

import java.io.IOException;
import java.net.UnknownHostException;

import lib.ChannelException;
import lib.CommClient;
import lib.ProtocolMessages;

import optional.Trace;

public class Client1 {

	private static CommClient com;	// Communication channel of the client
	
	private static String greet_me() throws Exception {
		Object result = null;
		
		// Create message to send
		ProtocolMessages request = new ProtocolMessages("greet");
		
		// Send request message to the server
		com.sendEvent(request);

		// Wait for the response, unless te requested operation does not reply anything
		ProtocolMessages respuesta = com.waitReply();
		
		// Process the response message or exception
		result = com.processReply(respuesta);
		
		return (String)result;
	} 
		
    public static void main(String[] args) {
    	
	    try {
			// Create the communication channel and establish the connection with
			// the default service in localhost
			com = new CommClient();
			
			// Activate the message log of the client
			com.activateMessageLog();  // Optional; recommended
		} catch (UnknownHostException e) {
			System.err.printf("Unknown server. %s\n", e.getMessage());
			e.printStackTrace();
			System.exit(-1);	// Exit with error
		} catch (IOException | ChannelException e) {
			System.err.printf("Error: %s\n", e.getMessage());
			e.printStackTrace();
			System.exit(-1);	// Exit with error
		}
		
		try {
			for (int k = 0; k < 3; k++) {
				System.out.println(greet_me());
			}
		} catch (IOException | ChannelException e) {
			System.err.printf("Error: %s\n", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) { // Exception in the service execution
			System.err.printf("Error: %s\n", e.getMessage());
			e.printStackTrace();
		} finally {
			// Close the communication channel and disconnect the client
			com.disconnect();
		}
		
	} // main
    
    
} 
