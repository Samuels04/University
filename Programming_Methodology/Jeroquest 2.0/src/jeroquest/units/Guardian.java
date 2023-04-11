package jeroquest.units;

import javax.swing.Icon;
import javax.swing.ImageIcon;

//import jeroquest.boardgame.Dice;
//import jeroquest.logic.Controller;
import jeroquest.utils.DynamicVectorCharacters;

public class Guardian extends Character {

	// ? Initial value for the attributes
	protected static final int MOVEMENT = 5;
	protected static final int ATTACK = 5;
	protected static final int DEFENCE = 5;
	protected static final int BODY = 5;

	/**
	 * Constructor for the class Guardian
	 * 
	 * @param name The name of the guardian
	 */
	public Guardian(String name) {
		super(name, MOVEMENT, ATTACK, DEFENCE, BODY);
	}

	/************************************************
	 * Interface Piece implementation
	 **********************************************/

	/**
	 * Generate a text representation of the character in the board (implementing an
	 * abstract method)
	 * 
	 * @return the text representation of the object in the board
	 */
	public char toChar() {
		return 'G';
	}

	/************************************************
	 * Interface GraphicElement implementation
	 **********************************************/

	// Icon of a guardian
	private static Icon icon = new ImageIcon(ClassLoader.getSystemResource("jeroquest/gui/images/guardian.png"));

	public Icon getImage() {
		return icon;
	}

	/**
	 * Guardians don't defend, since they aren't considered
	 * as enemies.
	 */
	@Override
	public int defend(int impacts) {
		return 0;
	}

	/**
	 * Guardians don't attack, they only penalise other characters.
	 */
	@Override
	public int attack() {
		return 0;
	}

	@Override
	public boolean isEnemy(Character c) {
		return c.isViolent();
	}

	/**
	 * Combat action for the character
	 * 
	 * @return true if the character has to fight an enemy, false otherwise
	 */
	@Override
	public boolean actionCombat() {
		// Attack a random enemy
		DynamicVectorCharacters targets = validTargets();

		Character[] targetsn = targets.vectorNormal();

		if (targets.length() > 0) {

			for (Character c : targetsn) {

				if (c instanceof Hero && !(((Hero) c).getWeapon() == null)) {

					((Hero) c).setWeapon(null);

				} else if (c instanceof Hero && ((Hero) c).getWeapon() == null) {

					((Hero) c).setWeapon(new Weapon("Handcuffs", 0));

				} else if (c instanceof Monster) {

					// TODO finish implementation of the resolve turn. See comment below
					/*
					 * We can add an attribute to the class monster to keep track of the
					 * number of tracks it is penalised for. Then, we can modify the resolve turn
					 * method so that if the monster is penalised for that turn it will not play
					 */

				}
			}
		}
		return false;
	}

}
