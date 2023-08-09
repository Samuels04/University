public class UsingExceptions {
   public static void main(String[] args) {

      try {

         method1();

      } catch (Exception exception) {

         System.err.printf("%s\n\n", exception.getMessage());
         exception.printStackTrace();

         StackTraceElement[] traceElements = exception.getStackTrace();

         System.out.println("\nStack trace from getStackTrace:");

         System.out.println("Class\t\tFile\t\t\tLine\tMethod");

         for (StackTraceElement element : traceElements) {

            System.out.printf("%s\t", element.getClassName());

            System.out.printf("%s\t", element.getFileName());

            System.out.printf("%s\t", element.getLineNumber());

            System.out.printf("%s\n", element.getMethodName());

         }

      }

   }

   public static void method1() throws Exception {

      try {
         method2();
      } catch (Exception e) {
         throw new Exception(e.getMessage() + "caught by method 1", e.getCause());
      }

   }

   public static void method2() throws Exception {

      try {
         method3();
      } catch (Exception e) {
         throw new Exception(e.getMessage() + "caught by method 2", e.getCause());
      }

   }

   public static void method3() throws Exception {

      throw new Exception("Exception thrown in method3");

   }

}