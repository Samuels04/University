package datastr;

import pairs.estdatos.Pair;

/**
 * Immutable type of rational numbers.
 */
public class ImmutableRational extends AbstractRational {
    /**
     * The pair {@code (numerator, denominator)}.
     */
    private Pair<Integer, Integer> p;

    @Override
    public int denominator() {
        return p.second();
    }

    @Override
    public int numerator() {
        return p.first();
    }
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
		if (d == 0)
            throw new IllegalArgumentException();
        
        if (d > 0) 
            p = new ImmutablePair<>(n,d);
        else    
            p = new ImmutablePair<>(-n,-d);
    }

    /**
     * Conversion constructor.
     *
     * @param r the rational to be copied
     */
    public ImmutableRational(final Rational r) {
        p.setFirst(r.numerator());
        p.setSecond(r.denominator());
    }



}
