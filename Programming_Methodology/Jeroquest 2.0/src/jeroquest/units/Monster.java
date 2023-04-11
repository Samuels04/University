/*
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * 
 * @author Programming Methodology Professors
 *
 */
package jeroquest.units;

import jeroquest.boardgame.Dice;

/**
 * Abstract class that represents the monsters in the game
 * 
 * @author Programming Methodology Professors
 */
public abstract class Monster extends Character {

	/**
	 * Create a monster from its name and initial values for the attributes,
	 * initially its position is null (outside of the board)
	 * 
	 * @param name     name of the character
	 * @param movement squares to move per turn
	 * @param attack   dices to roll for an attack without a weapon
	 * @param defence  dices to roll for the defence
	 * @param body     initial life
	 */
	public Monster(String name, int movement, int attack, int defence, int body) {
		super(name, movement, attack, defence, body);
	}

	/**
	 * The monster defends itself from an attack (Implementation of an abstract
	 * inherited method)
	 * 
	 * @param impacts the total number of impacts to try to block or suffer
	 * @return the number of suffered wounds
	 */
	public int defend(int impacts) {
		int wounds = 0;

		// it tries to block the impacts with the defence
		for (int totalDefenceDices = getDefence(); (impacts > 0) && (totalDefenceDices > 0); totalDefenceDices--)
			if (Dice.roll() == 6) // a 6 is necessary to block an impact
				impacts--;

		// if any unblocked impact, decrement body points
		if (impacts > 0) {
			// the life of a character cannot be lower then zero
			wounds = Math.min(getBody(), impacts);
			setBody(getBody() - wounds);
		}

		return wounds;
	}

	/**
	 * Check if the character given as argument is an enemy of the current one. An
	 * enemy will be all characters that are monsters (Overridden method)
	 * 
	 * @param c character to check
	 * @return true if it an enemy of the current character
	 */
	@Override
	public boolean isEnemy(Character c) {
		return (c instanceof Hero);
	}
}
