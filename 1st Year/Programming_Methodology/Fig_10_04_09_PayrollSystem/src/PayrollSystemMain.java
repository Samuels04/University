// Fig. 10.9: PayrollSystemTest.java
// Employee hierarchy test program.

public class PayrollSystemMain 
{
   public static void main( String[] args ) {
      // create subclass objects
      SalariedEmployee salariedEmployee = new SalariedEmployee( "John", "Smith", "111-11-1111", 800.00 );
      HourlyEmployee hourlyEmployee = new HourlyEmployee( "Karen", "Price", "222-22-2222", 16.75, 40 );
      CommissionEmployee commissionEmployee = new CommissionEmployee( "Sue", "Jones", "333-33-3333", 10000, .06 );
      BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee( "Bob", "Lewis", "444-44-4444", 5000, .04, 300 );

      System.out.println( "Employees processed individually:\n" );
      
      System.out.printf( "%s\n%s: $%,.2f\n\n", 
         salariedEmployee, "earned", salariedEmployee.earnings() );
      System.out.printf( "%s\n%s: $%,.2f\n\n",
         hourlyEmployee, "earned", hourlyEmployee.earnings() );
      System.out.printf( "%s\n%s: $%,.2f\n\n",
         commissionEmployee, "earned", commissionEmployee.earnings() );
      System.out.printf( "%s\n%s: $%,.2f\n\n", 
         basePlusCommissionEmployee, 
         "earned", basePlusCommissionEmployee.earnings() );

      // create four-element Employee array
      Employee[] employees = new Employee[4]; 

      // initialize array with Employees
      employees[0] = salariedEmployee;
      employees[1] = hourlyEmployee;
      employees[2] = commissionEmployee; 
      employees[3] = basePlusCommissionEmployee;

      System.out.println( "Employees processed polymorphically:\n" );
      
      // POLYMORPHISM: generically process each element in array employees
      for (Employee currentEmployee : employees) {
         System.out.println( currentEmployee ); // invokes toString

         // determine whether element is a BasePlusCommissionEmployee
         if ( currentEmployee instanceof BasePlusCommissionEmployee ) {

            ((BasePlusCommissionEmployee) currentEmployee).setBaseSalary( 1.10 * ((BasePlusCommissionEmployee)currentEmployee).getBaseSalary() );

            System.out.printf("new base salary with 10%% increase is: $%,.2f\n",((BasePlusCommissionEmployee)currentEmployee).getBaseSalary() );
         }

         System.out.printf("earned $%,.2f\n\n", currentEmployee.earnings() );
      }

      // get type name of each object in employees array
      for ( int j = 0; j < employees.length; j++ )
         System.out.printf( "Employee %d is a %s\n", j, employees[ j ].getClass().getName() ); 

      System.out.println(earnsTheMost((CommissionEmployee[]) employees));
      
   }

   public static CommissionEmployee earnsTheMost(Employee[] v){
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


/**
/* @param employees a vector of employees
/* @param inc the value to increment commissionRate
/* @param toWhom integer that indicates to whom change the
/* value: 0 to all who have this attribute, 1 only to the ones of the class CommissionEmployee, 2 only to the ones of the class BasePlusCommissionEmployee
*/
   public static void changeCommissionRate(Employee[] employees ,double inc, int toWhom ){
   
      switch(toWhom){
         case 0:
            //only commissionEmployee and BasePlusCommissionEmployee have this attribute
            for(Employee i : employees){
               if(i instanceof CommissionEmployee){
                 ((CommissionEmployee)i).setCommissionRate(((CommissionEmployee)i).getCommissionRate() + inc);
               }
            } 
            break;
         case 1:
            for(Employee i : employees){
               if(i instanceof CommissionEmployee && !(i instanceof BasePlusCommissionEmployee)){
                  ((CommissionEmployee)i).setCommissionRate(((CommissionEmployee)i).getCommissionRate() + inc);
               }
            }
            break;
         case 2:
            for(Employee i : employees){
               if(!(i instanceof CommissionEmployee) && i instanceof BasePlusCommissionEmployee){
                  ((BasePlusCommissionEmployee)i).setCommissionRate(((BasePlusCommissionEmployee)i).getCommissionRate() + inc);
               }
            }
            break;
      }  
   }
} // end class PolymorphismTest

