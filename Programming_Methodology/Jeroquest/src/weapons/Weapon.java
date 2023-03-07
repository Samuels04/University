package weapons;

/**
 * Class for Programming Methodology's heroquest game
 * 
 * @author Samuel Sanchez
 * @since 28/02/23
 * @version 1
 */
public class Weapon {
	private String name;

	private int attack;

	/**
	 * Creates a new weapon
	 * 
	 * @param name   The name of the weapon
	 * @param attack The attack of the weapon
	 */
	public Weapon(String name, int attack) {
		this.setAttack(attack);
		this.setName(name);
	}

	/**
	 * Sets the name of the weapon
	 * 
	 * @param name The new name of the weapon
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return the name of the weapon
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the attack the weapon produces
	 * 
	 * @param attack The new attack the weapon produces
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}

	/**
	 * Getter for the attribute 'attack'
	 * 
	 * @return the attack the weapon produces
	 */
	public int getAttack() {
		return this.attack;
	}

	/**
	 * Generate a printable String version of the weapon
	 * 
	 * @return The weapon's printable version
	 */
	@Override
	public String toString() {
		return String.format("Weapon: \n\t Name: %s \n\t Attack: %d", this.getName(), this.getAttack());
	}
}
