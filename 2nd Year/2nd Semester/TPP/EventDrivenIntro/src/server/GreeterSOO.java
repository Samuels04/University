package server;

import commun.ProhibitedAction;
import commun.IGreeter;

/**
 * Class of the service operations object (SOO).
 *
 */
public class GreeterSOO implements IGreeter {
	// Area of data
	private int state; // control state
	private String message; // data state
	private static int numberClients = 0;
	private final static String DEFAULT_MSG = "Hello, I am the greeter!";

	/**
	 * Creates the service object with the default greeting.
	 */
	public GreeterSOO() {
		this(DEFAULT_MSG);
		numberClients++;
	}

	/**
	 * Creates the service object with the specified greeting.
	 * 
	 * @param str the greeting message of the object
	 */
	public GreeterSOO(String str) {
		this.message = (str == null || str.isEmpty()) ? DEFAULT_MSG : str;
		this.state = 0;
	}

	@Override
	public String greet() {
		this.state = 1;
		return this.message;
	}

	@Override
	public void changeGreetMsg(String str) throws ProhibitedAction {
		if (this.state == 0) {
			throw new ProhibitedAction();
		}
		this.message = (str == null || str.isEmpty()) ? DEFAULT_MSG : str;
	}

	@Override
	public void reset() {
		this.state = 0;
		this.message = DEFAULT_MSG;
	}
	
	@Override
	public void close() {
		IGreeter.super.close();
		numberClients--;
	}
	
	@Override
	public int getNumberClients() {
		return numberClients;
	}

}
