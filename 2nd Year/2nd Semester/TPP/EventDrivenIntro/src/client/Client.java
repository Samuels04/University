package client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

import lib.ChannelException;
import lib.CommClient;
import lib.ProtocolMessages;
import lib.UnknownOperation;
import commun.ProhibitedAction;

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
		Scanner sin = new Scanner(System.in);
		int option;

		do {
			// Display the menu
			System.out.println("\n0. Exit");
			System.out.println("1. Ask the service to greet");
			System.out.println("2. Change the service greeting");
			System.out.println("3. Number of connected users");
			System.out.println("4. Reset the state to the beginning");
			System.out.print("\nOption? ");

			// Read the chosen option
			try {
				option = sin.nextInt();
			} catch (InputMismatchException e) {
				option = -1;
			}
			sin.nextLine();

			// Throw the event and show, if applicable,
			// the result of the service operation
			try {
				switch (option) {
					case 0:
						break;
					case 1:
						String greeting = (String) sendWithResponse("greet");
						System.out.println("System response: " + greeting);
						break;
					case 2:
						System.out.print("Enter new greeting message: ");
						String newGreeting = sin.nextLine();
						sendWithoutResponse("changeGreeting", newGreeting);
						System.out.println("Greeting message changed succesfully");
						break;
					case 3:
						int numberUsers = (int) sendWithResponse("checkNumberClients");
						System.out.printf("There are %d users\n", numberUsers);
						break;
					case 4:
						sendWithoutResponse("reset");
						System.out.println("State changed succesfully");
						break;
					default:
						System.err.println("Invalid option");
				}
			} catch (UnknownOperation | ProhibitedAction e) {
				Client.exceptionInfo(e);
			} catch (IOException | ChannelException f) {
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();	
			}
		} while (option != 0);

		sin.close();
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

} // class Client
