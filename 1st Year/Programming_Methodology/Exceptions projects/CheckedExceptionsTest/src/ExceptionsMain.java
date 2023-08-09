// Checked exception 
 class MyCheckedException extends Exception{ 
	 // The user defined exceptions must inherit either from Exception
	 // or any of its subclasses if the fit the type of the new exception
	 // They will never inherit from RuntimeException as these are associated
	 // with programming errors not detected by the developer
	 MyCheckedException(String msg){
		super(msg);
	}
}

public class ExceptionsMain {

	// clause throws and checked exceptions (eg: MyCheckedException). Three rules:

	// RULE 1.- It must be declared in the method where there is the possibility that 
	// the checked exception is generated
	public static void f3() throws MyCheckedException
	{
		// RULE 2.- It can only be thrown in the method where it is declared via 
		// throws -> throw new MyCheckedException("Exception message...");

		// Let's throw a checked exception
		throw new MyCheckedException("MyException in f3()");
	}

	public static void f1() {
		try{
			f3(); // <- method that declares that it can generate an exception MyCheckedException
		}
		// RULE 3a.- It should only be caught in the method that invokes directly the method that generates the exception
		catch(MyCheckedException e){  
			// Therefore, if this catch block is commented out, an compiler error is produced.
			// This is due to the invocation in the try block to f3() that declares that can generate an exception MyCheckedException
			System.err.println(e.getMessage());
			System.err.println("\n------------");
		}
	}

	// RULE 3b.- If the method f2() doesn't want to handle the exception, 
	// it can delegate its treatment to the methods that invoke f2(), 
	public static void f2() throws MyCheckedException 	//<-- for that it must include a throws clause in its declaration
	{
			f3(); // <- when declaring that it can generate an exception MyCheckedException it is not mandatory to have the blocks try/catch/finally
	}
	
	// RULE 3a + 3b .- Both things together
	public static void f0() throws MyCheckedException
	{
		try{
			f3(); // <- method that declares that can generate an exception MyCheckedException
		}
		// RULE 3a.- It should only be caught in the method that invokes directly the method that generates the exception
		catch(MyCheckedException e){ 
			// Therefore, if this catch block is commented out, an compiler error is produced.
			// This is due to the invocation in the try block to f3() that declares that can generate an exception MyCheckedException
			System.err.println(e.getMessage());
			throw e;
		}
	}

	
	// With throws it is also possible to declare (just information purposes)
	//  unchecked exceptions that the method can throw
	public static void g1() throws RuntimeException
	{
		throw new RuntimeException("Something happened");
	}
	

	public static void main(String[] args) {
		
		// invoking a method that handles correctly the declared checked exceptions
		f1();
		
		// that gives an error because there is no try/catch/finally block for a method that declares 
		// that it can generate checked exceptions
		 f2();

		
		// invoking a method that declares that it can throw unchecked exceptions,
		// it is not mandatory to catch and treat them
		g1(); 
	}

}

