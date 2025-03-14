package main.java.cliente;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

import lib.ChannelException;
import lib.CommClient;
import lib.ProtocolMessages;
import lib.UnknownOperation;

public class Client {

	private static CommClient com; // Communication channel of the client (singleton)

	/**
	 * Sends the corresponding message for an event that will not have a response
	 * from the server. To create the message, the key with which the event was
	 * registered on the server is required, along with any additional information
	 * needed (arguments required by the event handler).
	 * 
	 * @param id   identifier of the event
	 * @param args arguments required by the event handler (operation
	 *             of the service). This parameter is optional.
	 * @throws IOException      connection failed
	 * @throws ChannelException communication channel error
	 */
	private static void sendWithoutResponse(String id, Object... args)
			throws IOException, ChannelException {
		ProtocolMessages request;

		// Create message to send
		request = new ProtocolMessages(id, args);

		// Send request message to the server
		// (The associated function will also be executed)
		Client.com.sendEvent(request);
	}

	/**
	 * Sends the corresponding message for an event that requires a response
	 * from the server. The message sending is synchronous, and the response message
	 * (which may be an exception) must be awaited. Upon receiving the response
	 * message,
	 * it is processed, and an object is returned or an exception is thrown.
	 * Returns {@code null} if the function associated with the event does not
	 * return any
	 * result (it is of type {@code void}).
	 * 
	 * @param id   identifier of the event
	 * @param args arguments required by the function associated with the event
	 *             (optional)
	 * @return the value returned by the function associated with the event or
	 *         {@code null}
	 *         if the function returns {@code void}.
	 * @throws IOException            connection failed
	 * @throws ChannelException       communication channel error
	 * @throws ClassNotFoundException received message is not in the correct format
	 * @throws UnknownOperation       if the event sent to the server is unknown
	 * @throws Exception              generic exceptions associated with the
	 *                                specific operation
	 */
	private static Object sendWithResponse(String id, Object... args)
			throws IOException, ChannelException, ClassNotFoundException,
			UnknownOperation, Exception {
		ProtocolMessages response;
		Object result = null;

		// Send request message to the server
		Client.sendWithoutResponse(id, args);

		// Wait for the response
		response = Client.com.waitReply();

		// Process the return value of the function associated with the event or
		// throw an exception
		result = Client.com.processReply(response);

		// Return the result
		return result;
	}

	/**
	 * Shows the information of an exception in the standard error output.
	 * 
	 * @param e exception to show
	 */
	private static void exceptionInfo(Exception e) {
		String str = e.getMessage();
		System.err.printf("Error: %s\n",
				str != null ? str : e.getClass().getSimpleName());
	}

	/**
	 * Main loop of the client. In this case, the client allows invoking any of the
	 * service operations (triggering the corresponding event) by selecting an
	 * option
	 * from a menu displayed repetitively.
	 * <p>
	 * For each event triggered, the result (object or exception) of the service
	 * operation evaluation is displayed on the console, if applicable.
	 * </p>
	 */
	public static void run() {
		Scanner s = new Scanner(System.in);
		
		String guess;
		
		String differences;
		
		String secretWord;

		int endedGame;
		
		try {
			secretWord = (String) sendWithResponse("newWord");

			do {
			
				display("Please enter a guess: ");
			
				guess = s.nextLine();
			
				differences = (String) sendWithResponse("differences", guess);
			
				display(differences);
				
				endedGame = (int) sendWithResponse("ended", guess);
				
			} while (endedGame == 0);
			
			switch(endedGame) {
				case 1:
					display("You've run out of attempts!");
					break;
				case 2:
					display("You've guessed it!");
					break;
			}
			
			display(String.format("The solution was: %s", secretWord));
			
			display("\n\n\n");
			
			display("Do you want to try again? [Y/n]");
			
			String option = s.nextLine();	
			
			if(option.equals("Y"))
				sendWithoutResponse("restart");
			else
				System.exit(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		s.close();
	}

	public static void main(String[] args) {

		try {
			// 1. Create the communication channel and establish the
			// connection with the default service on localhost
			Client.com = new CommClient();

			// Activate the received and sent message log (optional operation)
			Client.com.activateMessageLog();
		} catch (UnknownHostException e) {
			Client.exceptionInfo(e);
			System.exit(-1); // End with error
		} catch (IOException | ChannelException e) {
			Client.exceptionInfo(e);
			System.exit(-1); // End with error
		}

		// Execute the main loop of the client
		Client.run();

		// Close the communication channel and disconnect the client
		Client.com.disconnect();

	} // main
	
	
	private static void display(Object o) {
		System.out.println(o);
	}

} // class Client