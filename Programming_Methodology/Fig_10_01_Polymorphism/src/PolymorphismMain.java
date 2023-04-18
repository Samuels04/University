// Fig. 10.1: PolymorphismTest.java
// Assigning superclass and subclass references to superclass and
// subclass variables.

public class PolymorphismMain  
{
   public static void main( String[] args ) 
   {
      // assign superclass reference to superclass variable
      CommissionEmployee commissionEmployee = new CommissionEmployee( 
         "Sue", "Jones", "222-22-2222", 10000, .06 );                    

      // assign subclass reference to subclass variable
      BasePlusCommissionEmployee basePlusCommissionEmployee = 
         new BasePlusCommissionEmployee( 
         "Bob", "Lewis", "333-33-3333", 5000, .04, 300 );         

      // invoke toString on superclass object using superclass variable
      System.out.printf( "Call CommissionEmployee's toString with superclass reference to superclass object:\n\n%s\n\n", 
         commissionEmployee.toString() );

      // invoke toString on subclass object using subclass variable      
      System.out.printf( "Call BasePlusCommissionEmployee's toString with subclass reference to subclass object:\n\n%s\n\n", 
         basePlusCommissionEmployee.toString() );
      
      CommissionEmployee commissionEmployee2 = 
         basePlusCommissionEmployee; 
      System.out.printf( "Call BasePlusCommissionEmployee toString with superclass reference to subclass object:\n\n%s\n", 
         commissionEmployee2.toString() );
   }
   public static CommissionEmployee earnsTheMost(CommissionEmployee[] v){
      double max = Double.MAX_VALUE;
      BasePlusCommissionEmployee[] v2 = (BasePlusCommissionEmployee[]) v;
      BasePlusCommissionEmployee temp = null;
      for(int i = 0; i < v.length; i++){
         if(v2[i].getBaseSalary() > max){
            temp = v2[i];
         }
      }
      return temp;
   }
   public static void sortAscending(CommissionEmployee[] v){
      BasePlusCommissionEmployee[] v2 = (BasePlusCommissionEmployee[]) v;
      BasePlusCommissionEmployee aux = null;
      for(int i = 0; i < v.length-1; i++){
        for(int j = 0; j < v.length-1-i; j++){
         if(v2[i].getBaseSalary() > v2[i+1].getBaseSalary()){
            aux = v2[j];
            v2[j] = v2[j+1];
            v2[j+1] = aux;
           }
        }
      }
   }
} // end class PolymorphismTest

