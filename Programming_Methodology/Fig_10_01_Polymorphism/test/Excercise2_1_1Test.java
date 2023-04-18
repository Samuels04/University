import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Excercise2_1_1Test {

	/**
	 * Test for the method
	 * {@link PolymorphismMain#earnsTheMost(CommissionEmployee[])}. It checks that
	 * it is safe to invoke the method with a null or empty vector as well.
	 */
	@Test
	void testEarnsTheMost() {
		CommissionEmployee highest = new BasePlusCommissionEmployee("Bob1", "Lewis1", "333", 10000, .06, 100);
		CommissionEmployee v[] = { new CommissionEmployee("Sue1", "Jones1", "222", 100, .06), highest,
				new CommissionEmployee("Sue2", "Jones2", "444", 1000, .06),
				new BasePlusCommissionEmployee("Bob2", "Lewis2", "555", 1000, .06, 100) };
		assertEquals(null, PolymorphismMain.earnsTheMost(null));
		assertEquals(null, PolymorphismMain.earnsTheMost(new CommissionEmployee[0]));
		assertEquals(highest, PolymorphismMain.earnsTheMost(v));
	}

	/**
	 * Test for the method
	 * {@link PolymorphismMain#sortAscending(CommissionEmployee[])}. It checks that
	 * it is safe to invoke the method with a null or empty vector together with
	 * three different scenarios for the input vector: already sorted, reverse order
	 * or random order.
	 */
	@Test
	void testsortAscending() {
		PolymorphismMain.sortAscending(null);
		PolymorphismMain.sortAscending(new CommissionEmployee[0]);

		CommissionEmployee v1[], vSorted[] = { new CommissionEmployee("Sue1", "Jones1", "222", 100, .06),
				new CommissionEmployee("Sue2", "Jones2", "444", 1000, .06),
				new BasePlusCommissionEmployee("Bob2", "Lewis2", "555", 1000, .06, 100),
				new BasePlusCommissionEmployee("Bob1", "Lewis1", "333", 10000, .06, 100) };
		v1 = vSorted.clone();

		// sort a sorted vector
		PolymorphismMain.sortAscending(v1);
		for (int x = 0; x < v1.length; x++)
			assertEquals(vSorted[x].earnings(), v1[x].earnings());

		CommissionEmployee v2[] = { new BasePlusCommissionEmployee("Bob1", "Lewis1", "333", 10000, .06, 100),
				new BasePlusCommissionEmployee("Bob2", "Lewis2", "555", 1000, .06, 100),
				new CommissionEmployee("Sue2", "Jones2", "444", 1000, .06),
				new CommissionEmployee("Sue1", "Jones1", "222", 100, .06) };
		// sort a vector in inverse order
		PolymorphismMain.sortAscending(v2);
		for (int x = 0; x < v2.length; x++)
			assertEquals(vSorted[x].earnings(), v2[x].earnings());

		CommissionEmployee v3[] = { new CommissionEmployee("Sue2", "Jones2", "444", 1000, .06),
				new BasePlusCommissionEmployee("Bob2", "Lewis2", "555", 1000, .06, 100),
				new BasePlusCommissionEmployee("Bob1", "Lewis1", "333", 10000, .06, 100),
				new CommissionEmployee("Sue1", "Jones1", "222", 100, .06) };
		// sort a vector in random order
		PolymorphismMain.sortAscending(v3);
		for (int x = 0; x < v3.length; x++)
			assertEquals(vSorted[x].earnings(), v3[x].earnings());
	}
}
