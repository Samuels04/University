

import datastr.AbstractRational;
import datastr.ImmutableRational;

/**
 * Class for the main program of rational numbers.
 */
public final class App {
	
	private static void compare(final Rational a, final Rational b) {
		int x = a.compareTo(b);
		System.out.printf("%s.compareTo(%s): %d\n", a, b, x);
		if (x == 0) {
			System.out.printf("%s = %s\n", a, b);
		} else
			if (x > 0) {
				System.out.printf("%s > %s\n", a, b);
			} else {
				System.out.printf("%s < %s\n", a, b);
			}
	}
	
    private App() {
    }

    /**
     * Main program.
     *
     * @param args arguments of the program
     */
    public static void main(final String[] args) {
    	final Rational rat1 = new ImmutableRational(2, 3);
    	final Rational rat2 = new ImmutableRational(4, 6);
    	final Rational rat3 = new ImmutableRational(2, -5);
    	
    	System.out.printf("¿rat1=rat2? %b\n", rat1.equals(rat2));
    	System.out.printf("rat1.hasCode(): %d\n", rat1.hashCode());
    	System.out.printf("rat2.hasCode(): %d\n", rat2.hashCode());
    	
        System.out.printf("Rational rat1: %s\n", rat1);
        System.out.printf("Rational rat2: %s\n", rat2);
        System.out.printf("Rational rat3: %s\n", rat3);
        compare(rat1, rat2);
        compare(rat1, rat3);
        compare(rat2, rat3);
		// Error, unsupported operation
        rat3.setNumerator(3);
        System.out.printf("Rational r3: %s\n", rat3);
    }
}
