package characters;

//import characters.*;
import weapons.*;

/**
 * Dwarf class for the Heroquest game
 * 
 * @author Samuel Sanchez
 * @version 1
 * @since 28/02/23
 */
public class Dwarf extends Character {
	// We define the attributes
	protected static final int MOVEMENT = 6;
	protected static final int ATTACK = 1;
	protected static final int DEFENCE = 2;
	protected static final int BODY = 7;

	private Weapon weapon;

	/**
	 * Create a dwarf with its name and the default weapon
	 * 
	 * @param name
	 */
	public Dwarf(String name) {
		super(name, MOVEMENT, ATTACK, DEFENCE, BODY);
		this.setWeapon(new Weapon("Handaxe", 2));
	}

	/**
	 * Getter for the attribute weapon
	 * 
	 * @return The current dwarf's weapon
	 */
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * Sets the weapon of the dwarf to the one passed
	 * 
	 * @param weapon
	 */
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
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
	 * Get dices for attack
	 * (overridden method)
	 * 
	 * @return character's attack dices
	 */
	@Override
	public int getAttack() {
		if (this.getWeapon() == null)
			return super.getAttack();
		else
			return this.getWeapon().getAttack();
	}

	/**
	 * Generate a printable String version of the object (overriden method)
	 * 
	 * @return the dwarf's printable info as a String
	 */
	public String toString() {
		return String.format("The dwarf: %s \n Its weapon: %s", super.toString(), this.getWeapon().toString());
	}

}
