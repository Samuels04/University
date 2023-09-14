package datastr;

import estdatos.Pair;

/**
 * Immutable type of rational numbers.
 */
public class ImmutableRational extends AbstractRational {
    /**
     * The pair {@code (numerator, denominator)}.
     */
    private Pair<Integer, Integer> p;

    /**
     * Creates the rational {@code n/d}. The denominator
	 * must be positive.
     *
     * @param n the numerator of the rational
     * @param d the denominator of the rational
     * @throws IllegalArgumentException if the denominator
     * is zero.
     */
    public ImmutableRational(final int n, final int d) {
		
    }

    /**
     * Conversion constructor.
     *
     * @param r the rational to be copied
     */
    public ImmutableRational(final Rational r) {

    }

}
