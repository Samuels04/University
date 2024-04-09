package server;

import commun.IGreeter;

/**
 * Class of the service operations object (SOO).
 *
 */
public class GreeterSOO implements IGreeter {
	// Area of data
	private int state;		// control state
	private String str;		// data state
	
	private final static String DEFAULT_MSG = "Hello, I am the greeter!";
	
	/**
	 * Creates the service object with the default greeting.
	 */
	public GreeterSOO() {
		this(DEFAULT_MSG);
	}
	
	/**
	 * Creates the service object with the specified greeting.
	 * @param str the greeting message of the object
	 */
	public GreeterSOO(String str) {
		this.str = (str == null || str.isEmpty()) ? DEFAULT_MSG : str;
		this.state = 0;
	}
	
	@Override
	public String greet() {
		return str;
	}

}
