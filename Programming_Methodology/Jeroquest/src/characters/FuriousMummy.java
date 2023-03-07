package characters;

public class FuriousMummy extends Mummy {
	// initial value for the attributes
	protected static final int MOVEMENT = 4;
	protected static final int ATTACK = 6;
	protected static final int DEFENCE = 0;
	protected static final int BODY = 2;

	/**
	 * Create a new Furious Mummy with a name
	 * 
	 * @param name The name of the Furious mummy
	 */
	public FuriousMummy(String name) {
		super(name);

	}

	/**
	 * The furious mummy defends itself from an attack (Implementing an inherited
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
			System.out.printf("The furious mummy " + this.getName() + " cannot block %d impacts%s", impacts,
					(isAlive() ? "\n" : " and dies\n"));
		} else {
			System.out.printf("The furious mummy " + this.getName() + " blocks completely the attack\n");
		}

		return impacts;
	}

	/**
	 * Generate a printable String version of the object (overriden method)
	 * 
	 * @return the furious-mummy's printable info as a String
	 */
	public String toString() {
		return String.format("The furious mummy: %s", super.toString());
	}
}
