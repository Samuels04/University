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
		if (this.hashCode() == obj.hashCode())
			return true;
		return false;
	}

	@Override
	public String toString() {
		return this.denominator() == 1 ? String.format("%d", this.numerator()) : 
			String.format("%d/%d", this.numerator(), this.denominator());
	}
    
}
