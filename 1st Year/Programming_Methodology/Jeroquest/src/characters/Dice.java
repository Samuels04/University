package characters;

/**
 * Programming Methodology Practice.
 * Jeroquest - An example of Object Oriented Programming.
 * Class Dice
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo Cerdán
 * @version 1
 * 
 */

import java.util.Random; // random number generator

public class Dice {

	private static Random generator = new Random(); // random number generator

	/**
	 * Sets the seed to generate random numbers
	 * 
	 * @param seed the initial seed
	 */
	public static void setSeed(long seed) {
		generator.setSeed(seed);
	}

	/**
	 * roll a 6 sides dice: 1D6
	 * 
	 * @return the result of rolling the dice 1D6
	 */
	public static int roll() {
		return roll(6); // by default a dice of 6 sides
	}

	/**
	 * Roll a dice of N sides
	 * 
	 * @param sides the number of sides N: 1DN
	 * @return the result of rolling the dice 1DN
	 */
	public static int roll(int sides) // It needs: the number of sides
	{
		return generator.nextInt(sides) + 1;
	}

	/**
	 * Function to test empirically that all sides are equally probable
	 */
	public static void testDice() {
		final int TRIES = 1000000;
		double total[] = { 0, 0, 0, 0, 0, 0 };
		for (int x = 0; x < TRIES; x++)
			total[roll() - 1]++;
		for (int x = 0; x < 6; x++)
			System.out.printf("%d: %f\n", x, total[x] / TRIES);
	}
}
