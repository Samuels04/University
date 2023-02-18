package es.employee;
// Fig. 9.4: CommissionEmployee.java

// CommissionEmployee class represents a commission employee.

public class CommissionEmployee extends Object {
   private String firstName;
   private String lastName;
   private String socialSecurityNumber;
   private double grossSales; // gross weekly sales
   private double commissionRate; // commission percentage

   // five-argument constructor
   public CommissionEmployee(String first, String last, String ssn,
         double sales, double rate) {
      // implicit call to Object constructor occurs here
      firstName = first;
      lastName = last;
      socialSecurityNumber = ssn;
      setGrossSales(sales); // validate and store gross sales
      setCommissionRate(rate); // validate and store commission rate
   } // end five-argument CommissionEmployee constructor

   // set first name
   public void setFirstName(String first) {
      firstName = first; // should validate
   } // end method setFirstName

   // return first name
   public String getFirstName() {
      return firstName;
   } // end method getFirstName

   // set last name
   public void setLastName(String last) {
      lastName = last; // should validate
   } // end method setLastName

   // return last name
   public String getLastName() {
      return lastName;
   } // end method getLastName

   // set social security number
   public void setSocialSecurityNumber(String ssn) {
      socialSecurityNumber = ssn; // should validate
   } // end method setSocialSecurityNumber

   // return social security number
   public String getSocialSecurityNumber() {
      return socialSecurityNumber;
   } // end method getSocialSecurityNumber

   // set commission employee's gross sales amount
   public void setGrossSales(double sales) {
      grossSales = (sales < 0.0) ? 0.0 : sales;
   } // end method setGrossSales

   // return commission employee's gross sales amount
   public double getGrossSales() {
      return grossSales;
   } // end method getGrossSales

   // set commission employee's rate
   public void setCommissionRate(double rate) {
      commissionRate = (rate > 0.0 && rate < 1.0) ? rate : 0.0;
   } // end method setCommissionRate

   // return commission employee's rate
   public double getCommissionRate() {
      return commissionRate;
   } // end method getCommissionRate

   // calculate commission employee's pay
   public double earnings() {
      return commissionRate * grossSales;
   } 

   // return String representation of CommissionEmployee object
   @Override 
   public String toString() {
      return String.format("Commission Employee: %s %s\nSocial Security Number: %s\nGross sales: %.2f\nCommission rate: %.2f", firstName, lastName, socialSecurityNumber, grossSales, commissionRate);
   }
}
