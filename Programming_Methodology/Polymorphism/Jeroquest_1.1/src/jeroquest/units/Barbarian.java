package jeroquest.units;

import jeroquest.utils.*;

import jeroquest.boardgame.Dice;

/**
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * Programming. Class Barbarian
 * 
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo Cerdán
 * @version 1.1
 * 
 */

public class Barbarian extends Character {
	// initial value for the attributes
	protected static final int MOVEMENT = 7;
	protected static final int ATTACK = 1;
	protected static final int DEFENCE = 2;
	protected static final int BODY = 8;
	protected static Position Position;

	/**
	 * Create a barbarian with its name
	 * 
	 * @param itsName Barbarian's name
	 * @param initialRow Barbarian's initial row 
	 * @param initialColumn Barbarian's initial column
	 */
	public Barbarian(String itsName, int initialRow, int initialColumn) {
		// setting the attributes with the initial values
		super(itsName, MOVEMENT, ATTACK, DEFENCE, BODY, new Position(initialRow, initialColumn));
	}

	/**
	 * The {@link Barbarian} defends itself from an attack (Implementing an
	 * inherited
	 * abstract method)
	 * 
	 * @param impacts the total number of impacts to try to block or receive
	 * @return the number of wounds suffered
	 */
	public int defend(int impacts) {
		// trying to block the impacts with its defence
		for (int totalDefenceDices = getDefence(); (impacts > 0) && (totalDefenceDices > 0); totalDefenceDices--)
			if (Dice.roll() > 4) // a 5 or 6 is necessary to block an impact
				impacts--;

		// if there are unblocked impacts reduce body points
		if (impacts > 0) {
			// a character life cannot be lower than zero
			setBody(Math.max(0, getBody() - impacts));
			System.out.printf("The hero " + this.getName() + " cannot block %d impacts%s", impacts,
					(isAlive() ? "\n" : " and dies\n"));
		} else {
			System.out.printf("The hero " + this.getName() + " blocks completely the attack\n");
		}

		return impacts;
	}

	/**
	 * Provides the character representation of the {@link Barbarian}
	 * 
	 * @return The character representation of the barbarian
	 */
	@Override
	public char toChar() {
		return 'B';
	}
}
