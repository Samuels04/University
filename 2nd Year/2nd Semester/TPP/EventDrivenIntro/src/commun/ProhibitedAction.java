package commun;

/**
 * Exception for unknown service operations.
 */
public class ProhibitedAction extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5634864553348079172L;

	/**
	 * Unknown service operation.
	 */
	public ProhibitedAction() {
		super();
	}
	
	/**
	 * Unknown service operation.
	 * @param str error message
	 */
	public ProhibitedAction(String str) {
		super(str);
	}
}
