package characters;

public class Hero extends Character {
	private String player;

	/**
	 * Create a new hero with its name, the name of the player
	 * and the initial attributes
	 * 
	 * @param nameOfTheHero   The name of the Hero
	 * @param nameOfThePlayer The name of the Player
	 * @param itsMovement     no. of tiles to move per turn
	 * @param itsAttack       no. of dices to roll for an attack
	 * @param itsDefence      no. of dices to roll for defence
	 * @param itsBody         initial body points
	 */
	public Hero(String nameOfTheHero, String nameOfThePlayer, int itsMovement, int itsAttack, int itsDefence,
			int itsBody) {
		super(nameOfThePlayer, itsMovement, itsAttack, itsDefence, itsBody);
		this.setPlayer(nameOfThePlayer);
	}

	/**
	 * Getter for the attribute player
	 * 
	 * @return The name of the player
	 */
	public String getPlayer() {
		return player;
	}

	/**
	 * Set the new name for the player
	 * 
	 * @param player The new name
	 */
	public void setPlayer(String player) {
		this.player = player;
	}

	/**
	 * The hero defends itself from an attack (Implementing an inherited
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
			System.out.printf("The hero " + this.getName() + " cannot block %d impacts%s", impacts,
					(isAlive() ? "\n" : " and dies\n"));
		} else {
			System.out.printf("The hero " + this.getName() + " blocks completely the attack\n");
		}

		return impacts;
	}

}
