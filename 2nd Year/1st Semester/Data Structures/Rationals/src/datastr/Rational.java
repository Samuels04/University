package datastr;

/**
 * ADT for rational numbers of the form {@code a/b} with
 * {@code a} and {@code b} integers ({@code b≠0}).
 *
 * <p>The denominator must be positive</p>
 */
public interface Rational implements Comparable<Rational> {

    /**
     * Returns the greatest common divisor of the given integers.
     *
     * @param a the first integer
     * @param b the second integer
     * @return the greatest common divisor of the specified
     * integers
     * @throws IllegalArgumentException if {@code a=0}
     * or {@code b=0 }
     */
    static int gcd(final int a, final int b) {
    	if (a == 0 || b == 0) {
    		throw new IllegalArgumentException();
    	}
    	
        int n1 = a > 0 ? a : -a;
        int n2 = b > 0 ? b : -b;

        while (n2 != 0) {
            int temp = n1;
            n1 = n2;
            n2 = temp % n2;
        }

        return n1;
    }

    /**
     * Returns the numerator of this rational.
     *
     * @return the numerator of the rational
     */
    int numerator();

    /**
     * Returns the denominator of this rational.
     *
     * @return the denominator of the rational
     */
    int denominator();
    
    /**
     * Changes the numerator of the given rational for the
     * specified value (optional operation).
     * 
     * @param n the new value of the numerator
     * @throws UnsupportedOperationException if the operation
     * is not supported
     */
    default void setNumerator(final int n) {
        throw new UnsupportedOperationException();
    }

    /**
     * Changes the denominator of the given rational for the
     * specified value (optional operation).
     * 
     * @param n the new value of the denominator
     * @throws UnsupportedOperationException if the operation
     * is not supported
     */
    default void setDenominator(final int d) {
        throw new UnsupportedOperationException();
    }

    /**
     * Changes this rational to its irreducible fraction.
     * 
     * @throws UnsupportedOperationException if the operation
     * is not supported
     */
    default void reduce() {
        int gcd = gcd(this.numerator(), this.denominator());
        setNumerator(this.numerator() / gcd);
        setDenominator(this.denominator() / gcd);
    }

    @Override
    public int compareTo(Rational o) {
        return (this.denominator() == o.denominator()) && (this.numerator() == o.numerator())
    }


}
