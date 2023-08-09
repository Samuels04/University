import java.util.Scanner;

public class UserDefinedExceptionsMain {
	
	public static int quotient(int numerator, int denominator) throws DivisionByZeroException {

		if (denominator == 0) throw new DivisionByZeroException();
			return numerator / denominator;
	}

	public static void main(String[] args){

		Scanner sc = new Scanner(System.in);
		boolean continueLoop = true; 

		do {
			try {
				System.out.print("Please enter an integer numerator: ");
				int numerator = sc.nextInt();

				System.out.print("Please enter an integer denominator: ");
				int denominator = sc.nextInt();

				int result = quotient(numerator, denominator);

				System.out.printf("\nResult: %d / %d = %d\n", numerator, denominator, result);

				continueLoop = false;
			} 

			catch (DivisionByZeroException e) {

				e.showMessage();

				System.out.println("A zero is not valid, try again.\n");

				
			}
			catch (ArithmeticException arithmeticException) {

				System.err.printf("\nException: %s\n", arithmeticException);

				System.out.println("Zero is an invalid denominator. Please try again.\n");
				
			}
		} while (continueLoop);

		sc.close();
	}
}