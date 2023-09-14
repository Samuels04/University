package datastr;

import java.util.Objects;

/**
 * Abstract class for rational numbers.
 */
public abstract class AbstractRational implements Rational {
	private final static Object obj = new Object();
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = obj.hashCode();
		result = prime * result + Objects.hash(this.numerator(), this.denominator());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Rational))
			return false;
		Rational other = (Rational) obj;
		return this.numerator()  == other.numerator() &&
				this.denominator() == other.denominator();
	}
    
}
