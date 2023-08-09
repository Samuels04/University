
public class BasePlusCommissionEmployee {
   private CommissionEmployee ce;
   private double baseSalary;

   public BasePlusCommissionEmployee(String first, String last, String ssn, double sales, double rate, double salary) {

      ce = new CommissionEmployee(first, last, ssn, sales, rate);
      setBaseSalary(salary);
   }

   public void setBaseSalary(double salary) {
      baseSalary = (salary < 0.0) ? 0.0 : salary;
   }

   public double getBaseSalary() {
      return baseSalary;
   }

   public double earnings() {
      return getBaseSalary() + (ce.getCommissionRate() * ce.getGrossSales());
   }

   @Override
   public String toString() {

      return String.format("%s %n%s: %f%n", ce.toString(), "base salary", baseSalary);
   }
}
