package commun;

/**
 * Service operations interface for the greeting service.
 */
public interface IGreeter extends lib.DefaultService {
	
	/**
	 * It should return the greeting message.
	 * @return a string
	 */
	String greet();
	
	
	/**
	 * Modifies the greeting message with the specified string (optional operation).
	 * @param str the new greeting message
	 * @throws UnsupportedOperationException if this operation is not implemented
	 * @throws ProhibitedAction if in the current state of the system the action cannot be executed
	 */
	default void changeGreetMsg(String str) throws ProhibitedAction {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Resets the service state to its initial state (optional operation).
	 * @throws UnsupportedOperationException if this operation is not implemented
	 */
	default void reset() {
		throw new UnsupportedOperationException();
	}

}
