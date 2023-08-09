// Fig. 10.13: Employee.java
// Employee abstract superclass implements Payable.

public abstract class Employee implements Payable {
   private String firstName;
   private String lastName;
   private String socialSecurityNumber;

   public Employee(String first, String last, String ssn) {
      firstName = first;
      lastName = last;
      socialSecurityNumber = ssn;
   }

   public void setFirstName(String first) {
      firstName = first; // should validate
   }

   public String getFirstName() {
      return firstName;
   }

   public void setLastName(String last) {
      lastName = last; // should validate
   }

   public String getLastName() {
      return lastName;
   }

   public void setSocialSecurityNumber(String ssn) {
      socialSecurityNumber = ssn; // should validate
   }

   public String getSocialSecurityNumber() {
      return socialSecurityNumber;
   }

   @Override
   public String toString() {
      return String.format("%s %s\nsocial security number: %s",
            getFirstName(), getLastName(), getSocialSecurityNumber());
   }

}
