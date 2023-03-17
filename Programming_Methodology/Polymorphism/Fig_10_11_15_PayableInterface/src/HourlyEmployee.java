public class HourlyEmployee extends Employee{
	private int workedHours;
	private double salary;

	/**
	 * Constructor for the class {@link HourlyEmployee}
	 * @param first The first name of the {@link Employee}
	 * @param last The last name of the {@link Employee}
	 * @param ssn The Social Security Number of the {@link Employee}
	 * @param salary The salary of the {@link Employee}
	 */
	public HourlyEmployee(String first, String last, String ssn, double salary){
		super(first, last, ssn);
		this.setSalary(salary);
	}
	@Override
	public double getPaymentAmount() {
		if(this.getWorkedHours() <=40){
			return this.getSalary()*this.getWorkedHours();
		} else {
			double hoursOver40  = this.getWorkedHours()*1.5;
			return this.getSalary()*hoursOver40;
		}
	}

	/**
	 * Getter for the workedHours attribute
	 * @return The number of hours the employee has worked
	 */
	public int getWorkedHours() {
		return workedHours;
	}
	/**
	 * Setter for the workedHours attribute
	 * @param workedHours The new no. of hours the employee has worked
	 */
	public void setWorkedHours(int workedHours) {
		this.workedHours = workedHours;
	}
	/**
	 * Getter for the salary
	 * @return The employee's salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * Setter for the salary
	 * @param salary The new salary of the employee
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return String.format("Hourly-salaried employee: %s\n Hourly Salary: %,.2f\nHours worked: %d", super.toString(), this.getSalary(), this.getWorkedHours());
	}
	
}
