package characters;

import weapons.*;

/**
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * Programming. Class Barbarian
 * 
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo Cerdán
 * @version 1
 * 
 */

public class Barbarian extends Hero {
	// initial value for the attributes
	protected static final int MOVEMENT = 7;
	protected static final int ATTACK = 1;
	protected static final int DEFENCE = 2;
	protected static final int BODY = 8;
	private Weapon weapon;

	/**
	 * Create a barbarian with its name and its default weapon
	 * 
	 * @param itsName Barbarian's name
	 */

	public Barbarian(String itsName) {
		// setting the attributes with the initial values
		super(itsName, "no player", MOVEMENT, ATTACK, DEFENCE, BODY);
		this.setWeapon(new Weapon("broadsword", 3));
	}

	/**
	 * The barbarian defends itself from an attack (Implementing an inherited
	 * abstract method)
	 * 
	 * @param impacts the total number of impacts to try to block or receive
	 * @return the number of wounds suffered
	 */
	@Override
	public int defend(int impacts) {
		// trying to block the impacts with its defence
		for (int totalDefenceDices = getDefence(); (impacts > 0) && (totalDefenceDices > 0); totalDefenceDices--)
			if (Dice.roll() > 4) // a 5 or 6 is necessary to block an impact
				impacts--;

		// if there are unblocked impacts reduce body points
		if (impacts > 0) {
			// a character life cannot be lower than zero
			setBody(Math.max(0, getBody() - impacts));
			System.out.printf("The barbarian " + this.getName() + " cannot block %d impacts%s", impacts,
					(isAlive() ? "\n" : " and dies\n"));
		} else {
			System.out.printf("The barbarian " + this.getName() + " blocks completely the attack\n");
		}

		return impacts;
	}

	/**
	 * Getter for the attribute weapon
	 * 
	 * @return The current barbarian's weapon
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * Sets the weapon of the barbarian to the one passed
	 * 
	 * @param weapon
	 */
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	/**
	 * Get the dices for the dices fo the attack
	 * (overriden method)
	 * 
	 * @return the dices for the attack
	 */
	@Override
	public int getAttack() {
		if (this.getWeapon() == null)
			return super.getAttack();
		else
			return this.getWeapon().getAttack();
	}

	/**
	 * Computes the attack of a character getting the number of impacts,
	 * to do so roll as many dices as indicated by the attribute attack
	 * (overridden method)
	 * 
	 * @return the number of impacts
	 */
	@Override
	public int attack() {
		int impacts = 0;
		for (int x = 0; x < this.getAttack(); x++)
			if (Dice.roll() > 3)
				impacts++;
		return impacts;
	}

	/**
	 * Generate a printable String version of the object (overriden method)
	 * 
	 * @return the barbarian's printable info as a String
	 */
	public String toString() {
		return String.format("The barbarian: %s \n Its weapon: %s", super.toString(), this.getWeapon().toString());
	}

}
