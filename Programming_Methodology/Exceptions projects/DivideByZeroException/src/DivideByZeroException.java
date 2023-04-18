import java.util.InputMismatchException;
import java.util.Scanner;

public class DivideByZeroException {
  
   public static int quotient( int numerator, int denominator){
      if(denominator == 0) throw new ArithmeticException();
         return numerator / denominator;
   }

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      boolean continueLoop = true;

      do {

         try {

            System.out.print("Please enter an integer numerator: ");
            int numerator = sc.nextInt();

            System.out.print("Please enter an integer denominator: ");
            int denominator = sc.nextInt();

            int result = quotient(numerator, denominator);
            System.out.printf("Result: %d / %d = %d", numerator, denominator, result);

            continueLoop = false;
         }

         catch (InputMismatchException inputMismatchException) {

            System.err.printf("Exception: %s", inputMismatchException);
            sc.nextLine();

            System.out.println("You must enter integers. Please try again.");
         } 

         catch (ArithmeticException arithmeticException) {

            System.err.printf("Exception: %s", arithmeticException);

            System.out.println("Zero is an invalid denominator. Please try again.");
         }

      }
      while (continueLoop);
   } 
}