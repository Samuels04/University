package datastr;

public interface Poly {
	
	/**
	 * Inserts the term with the specified coefficient and exponent
	 * in the polynomial. If the exponent of the term to be added
	 * already exists in the polynomial, the coefficients are summed.
	 * @param coef the coefficient of the term to be added
	 * @param exp the exponent of the term to be added
	 * @return 0 if the term with exponent exp did not exist, or the
     * previous coefficient, otherwise.
	 */
    public Double put(Integer exp, Double coef);
    
	/**
	 * Deletes from the polynomial, if it exists, the term with the
	 * specified exponent.
	 * @param exp the exponent of the term to be removed
	 * @return the coefficient value of the removed term or null
	 * if there was no term with the given exponent in the polynomial
	 */
	public Double remove(Integer exp);
	
	/**
	 * Returns the coefficient of the term with the specified exponent.
	 * If the term is not found, returns 0.
	 * @param exp the exponent of the term to be found
	 * @return the coefficient of the term with the specified exponent,
	 * if it exists. Otherwise, 0.
	 */
	public Double coefficient (Integer exp);
		
	/**
	 * Returns the polynomial's degree (0 if there are no terms)
	 * @return the polynomial's degree
	 */
    public Integer degree();
    
     
	/**
	 * Returns the result of evaluating the polynomial for a given value
	 * of the parameter x
	 * @param x the value of the parameter x
	 * @return the result of evaluating the polynomial for x
	 */
    public Double evaluate(Integer x);
	
  

}
