
public class DivisionByZeroException extends ArithmeticException {
	// A new attribute
	private static final String MESSAGE = "You introduced a 0!!";
	
	// Constructors
	public DivisionByZeroException(){
		super(MESSAGE);
	}

	// Other methods
    public void showMessage(){
       System.err.println(this.getMessage());
    }
}
