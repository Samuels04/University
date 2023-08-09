import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Exercise2_2_2Test {

	/**
	 * Test for the method {@link PayrollSystemMain#earnsTheMost(Employee[])}. It
	 * checks that it is safe to invoke the method with a null or empty vector as
	 * well.
	 */
	@Test
	void testEarnsTheMost() {
		Employee v[] = getVectorOfEmployees();
		assertEquals(null, PayrollSystemMain.earnsTheMost(v));
		assertEquals(null, PayrollSystemMain.earnsTheMost(new CommissionEmployee[0]));
		assertEquals(v[0], PayrollSystemMain.earnsTheMost(v));
	}

	/**
	 * Test for the method {@link PayrollSystemMain#sortAscending(Employee[])}. It
	 * checks that it is safe to invoke the method with a null or empty vector.
	 */
	@Test
	void testsortAscending() {
		PayrollSystemMain.sortAscending(null);
		PayrollSystemMain.sortAscending(new Employee[0]);

		Employee[] employees = getVectorOfEmployees();
		Employee[] employees2 = getVectorOfEmployees();
		PayrollSystemMain.sortAscending(employees);
		for (int x = 0; x < employees.length; x++)
			assertEquals(employees2[employees.length - x - 1].earnings(), employees[x].earnings());
	}

	/**
	 * Test for the static method
	 * {@link PayrollSystemMain#changeCommissionRate(Employee[], double, int)}. It
	 * checks that is is safe to invoke the method with a null or empty vector, also
	 * checks the correct behaviour depending on the value of the parameter toWhom,
	 * including no modification when this value is different to the expected ones.
	 */
	@Test
	void testChangeCommissionRate() {
		// it must be save to invoke the method with a null or empty vector
		PayrollSystemMain.changeCommissionRate(null, 0.1, 0);
		PayrollSystemMain.changeCommissionRate(new CommissionEmployee[0], 0.1, 0);

		// both changed with toWhom=0
		Employee[] employees = getVectorOfEmployees();
		PayrollSystemMain.changeCommissionRate(employees, 0.1, 0);
		assertEquals(.16, ((CommissionEmployee) employees[2]).getCommissionRate());
		assertEquals(.14, ((CommissionEmployee) employees[3]).getCommissionRate());

		// only CommissionEmployee changed with toWhom=1
		employees = getVectorOfEmployees();
		PayrollSystemMain.changeCommissionRate(employees, 0.1, 1);
		assertEquals(.16, ((CommissionEmployee) employees[2]).getCommissionRate());
		assertEquals(.04, ((CommissionEmployee) employees[3]).getCommissionRate());

		// only BasePlusCommissionEmployee changed with toWhom=2
		employees = getVectorOfEmployees();
		PayrollSystemMain.changeCommissionRate(employees, 0.1, 2);
		assertEquals(.06, ((CommissionEmployee) employees[2]).getCommissionRate());
		assertEquals(.14, ((CommissionEmployee) employees[3]).getCommissionRate());

		// values of the parameter toWhom different to 0, 1 or 2 do not make any change
		employees = getVectorOfEmployees();
		PayrollSystemMain.changeCommissionRate(employees, 0.1, -1);
		assertEquals(.06, ((CommissionEmployee) employees[2]).getCommissionRate());
		assertEquals(.04, ((CommissionEmployee) employees[3]).getCommissionRate());

		employees = getVectorOfEmployees();
		PayrollSystemMain.changeCommissionRate(employees, 0.1, 5);
		assertEquals(.06, ((CommissionEmployee) employees[2]).getCommissionRate());
		assertEquals(.04, ((CommissionEmployee) employees[3]).getCommissionRate());
	}

	/**
	 * Auxiliary method to create a vector of employees to be used in the test
	 * methods
	 * 
	 * @return a vector of employees
	 */
	private Employee[] getVectorOfEmployees() {
		SalariedEmployee salariedEmployee = new SalariedEmployee("John", "Smith", "111-11-1111", 800.00);
		HourlyEmployee hourlyEmployee = new HourlyEmployee("Karen", "Price", "222-22-2222", 16.75, 40);
		CommissionEmployee commissionEmployee = new CommissionEmployee("Sue", "Jones", "333-33-3333", 10000, .06);
		BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee("Bob", "Lewis",
				"444-44-4444", 5000, .04, 300);
		Employee[] employees = { salariedEmployee, hourlyEmployee, commissionEmployee, basePlusCommissionEmployee };
		return employees;
	}

}
