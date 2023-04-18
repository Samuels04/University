package jeroquest.units;

import jeroquest.utils.*;

import jeroquest.boardgame.Dice;

/**
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * Programming. Class Mummy
 * 
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo Cerdán
 * @version 1.1
 * 
 */

public class Mummy extends Character {

	// Valores iniciales de los atributos
	protected static final int MOVEMENT = 4;
	protected static final int ATTACK = 3;
	protected static final int DEFENCE = 4;
	protected static final int BODY = 2;
	protected static Position POSITION;

	/**
	 * Create a {@link Mummy} with its name
	 * 
	 * @param itsName Mummy's name
	 */
	public Mummy(String itsName, int itsRow, int itsColumn) {
		// setting the attributes with the initial values
		super(itsName, MOVEMENT, ATTACK, DEFENCE, BODY, new Position(itsRow, itsColumn));
	}

	/**
	 * The mummy defends itself from an attack (Implementing an inherited abstract
	 * method)
	 * 
	 * @param impacts the total number of impacts to try to block or receive
	 * @return the number of wounds suffered
	 */
	public int defend(int impacts) {
		// trying to block the impacts with its defence
		for (int totalDefenceDices = getDefence(); (impacts > 0) && (totalDefenceDices > 0); totalDefenceDices--)
			if (Dice.roll() == 6) // a 6 is necessary to block an impact
				impacts--;

		// if there are not blocked impacts reduce body points
		if (impacts > 0) {
			// a character life cannot be lower than zero
			setBody(Math.max(0, getBody() - impacts));
			System.out.printf("The monster " + this.getName() + " cannot block %d impacts%s", impacts,
					(isAlive() ? "\n" : " and dies\n"));
		} else {
			System.out.printf("The monster " + this.getName() + " blocks completely the attack\n");
		}

		return impacts;
	}

	/**
	 * Provides the character representation of the {@link Mummy}
	 * 
	 * @return The character representation of the mummy
	 */
	@Override
	public char toChar() {
		return 'M';
	}
}

