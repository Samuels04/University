package jeroquest.units;

//import javax.management.DynamicMBean;

/**
 * Programming Methodology Practice.
 * Jeroquest - An example of Object Oriented Programming.
 * Class Character
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo
 *
 */

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jeroquest.boardgame.Dice;
import jeroquest.boardgame.Piece;
import jeroquest.boardgame.Position;
import jeroquest.gui.GraphicalPiece;
import jeroquest.logic.Controller;
import jeroquest.logic.Game;
import jeroquest.utils.DynamicVectorCharacters;
import jeroquest.utils.DynamicVectorPosition;

public abstract class Character implements Piece, GraphicalPiece {
	// attributes with the current values for the character
	private int movement; // units of movement per turn
	private int attack; // total of attack dices
	private int defence; // total of defence dices
	private int body; // body points

	// attributes with the initial values for the character
	private final int movementInitial; // units of movement per turn
	private final int attackInitial; // total of attack dices
	private final int defenceInitial; // total of defence dices
	private final int bodyInitial; // body points

	private String name; // character's name

	/**
	 * Create a character from its name and its initial values for the attributes,
	 * initially its position is null (outside of the board)
	 * 
	 * @param name     character's name
	 * @param movement units of movement per turn
	 * @param attack   total of attack dices
	 * @param defence  total of defence dices
	 * @param body     body points
	 */
	public Character(String name, int movement, int attack, int defence, int body) {

		this.name = name;

		// setting the initial values
		this.movementInitial = movement;
		this.attackInitial = attack;
		this.defenceInitial = defence;
		this.bodyInitial = body;

		// setting the current values
		this.movement = movement;
		this.attack = attack;
		this.defence = defence;
		this.body = body;

		// null position (outside of the board)
		this.position = null;
	}

	/**
	 * Get the units of movement per turn
	 * 
	 * @return character's units of movement
	 */
	public int getMovement() {
		return movement;
	}

	/**
	 * Get the dices for attack
	 * 
	 * @return character's attack dices
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * Get the dices for defence
	 * 
	 * @return character's defence dices
	 */
	public int getDefence() {
		return defence;
	}

	/**
	 * Get the body points
	 * 
	 * @return character's body points
	 */
	public int getBody() {
		return body;
	}

	/**
	 * Getter for movementInitial
	 * 
	 * @return movementInitial
	 */
	public int getMovementInitial() {
		return movementInitial;
	}

	/**
	 * Getter for attackInitial
	 * 
	 * @return attackInitial
	 */
	public int getAttackInitial() {
		return attackInitial;
	}

	/**
	 * Getter for defenceInitial
	 * 
	 * @return defenceInitial
	 */
	public int getDefenceInitial() {
		return defenceInitial;
	}

	/**
	 * Getter for bodyInitial
	 * 
	 * @return bodyInitial
	 */
	public int getBodyInitial() {
		return bodyInitial;
	}

	/**
	 * Get the name
	 * 
	 * @return the name of the character
	 */
	public String getName() {
		return name;
	}

	protected void setMovement(int movement) {
		this.movement = movement;
	}

	protected void setAttack(int attack) {
		this.attack = attack;
	}

	protected void setDefence(int defence) {
		this.defence = defence;
	}

	protected void setBody(int body) {
		this.body = body;
	}

	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * Checks if a character has any body points left
	 * 
	 * @return true if it is alive, false otherwise
	 */
	public boolean isAlive() {
		return body > 0;
	}

	/**
	 * Computes the number of impacts that the attack of the character will make,
	 * for that it rolls as many dices as dictated by the attribute attack
	 * 
	 * @return the number of impacts made
	 */
	public int attack() {
		int impacts = 0;
		for (int x = 0; x < getAttack(); x++)
			if (Dice.roll() > 3)
				impacts++;
		return impacts;
	}

	/**
	 * Simulates an combat with other character, it does the attack and the defence.
	 * If the attack kills the opponent it is moved out of the board
	 * 
	 * @param c character being attacked
	 */
	public void combat(Character c) { // attacks to c and c defends itself
		int impacts = this.attack();
		int wounds = c.defend(impacts);

		DynamicVectorCharacters ch = Controller.getInstance().getCurrentGame().getCharacters();

		if (!c.isAlive()) {
			Controller.getInstance().getCurrentGame().getBoard().removePiece(c);
			ch.remove(ch.position(c));
		}
		if (wounds > 0) {
			Controller.getInstance().updateGraphicalPiece(c, String.format("%s cannot block %d impacts%s", c.getName(),
					wounds, (c.isAlive() ? "" : " and dies")));
		} else {
			Controller.getInstance().updateGraphicalPiece(null, String.format("%s blocks the attack", c.getName()));
		}
	}

	/**
	 * Defence from an attack (abstract method: each subclass must define its own
	 * behaviour)
	 * 
	 * @param impacts total number of impacts to try to block or suffer
	 * @return the number of suffered wounds
	 */
	public abstract int defend(int impacts);

	/**
	 * Checks that the character given as argument is an enemy. An enemy is any
	 * character that is not of the same type
	 * 
	 * @param c character to test the hostility
	 * @return true if it is an enemy
	 */
	public boolean isEnemy(Character c) {
		return this.getClass() != c.getClass();
	}

	/**
	 * Combat action for the character
	 * 
	 * @return true if the character has to fight an enemy, false otherwise
	 */
	public boolean actionCombat() {
		// Attack a random enemy
		DynamicVectorCharacters targets = validTargets();

		if (targets.length() > 0) {
			Character target = getHighestBodyPoints(targets);
			String message = String.format("%s %s attacks to %s %s", getName(), getPosition(), target.getName(),
					target.getPosition());
			Controller.getInstance().updateGraphicalPiece(null, message);
			this.combat(target);

			return true;
		}
		return false;
	}

	/**
	 * Movement action for the character
	 * 
	 * @return the number of squares that it has moved
	 */
	public int actionMovement() {
		// Random movement in the board
		DynamicVectorPosition validPositions = validPositions();
		int steps = 0;
		while ((validPositions.length() > 0) && (steps < getMovement())) {
			// if it can it moves in a direction chosen randomly
			Position newPosition = validPositions.get(Dice.roll(validPositions.length()) - 1);
			steps++;
			String message = String.format("%s %s step %d out of %d to %s", this.getName(), this.getPosition(), steps,
					getMovement(), newPosition);
			Controller.getInstance().getCurrentGame().getBoard().movePiece(this, newPosition);

			// window
			Controller.getInstance().updateGraphicalPiece(this, message, true);

			validPositions = validPositions();
		}

		return steps;
	}

	/**
	 * AI: character's Artificial Intelligence It does the actions in a given turn:
	 * attack and movement
	 * 
	 */
	public void resolveTurn() {

		// Attack to a random enemy
		actionCombat();

		// Move randomly through the board
		actionMovement();

		// Possibles improvement (among others):
		// - Move towards the closest enemy / with less body points /...
		// A.- First in Xs and later in Ys
		// B.- First in the coordinate with difference with the target's position
		// - AI: check if there is free way until the target
		// - What to do if our way is blocked by allies?
		// - Stop is there is an enemy at attack range
		// and if the square is free and inside of the board move to that position

	}

	/**
	 * Returns the {@link Character} that is going to be attacked
	 * 
	 * @return the target for the character in its current position
	 */
	public DynamicVectorCharacters validTargets() {
		// search targets
		DynamicVectorCharacters validTargets = new DynamicVectorCharacters();

		Game currentGame = Controller.getInstance().getCurrentGame();
		DynamicVectorCharacters characters = currentGame.getCharacters();

		for (int i = 0; i < characters.length(); i++) {
			if (characters.get(i).isAlive() && this.isEnemy(characters.get(i))
					&& this.isAtRange(characters.get(i).getPosition())) {
				validTargets.add(i);
			}
		}

		return validTargets;
	}

	/**
	 * Check if the character at position pos is at attack range
	 * 
	 * @param pos position of the second character
	 * @return true is the squares are adjacent, false otherwise
	 */
	public boolean isAtRange(Position pos) {
		// pos to the North from this character
		if ((this.getPosition().getRow() == pos.getRow() - 1) && (this.getPosition().getCol() == pos.getCol()))
			return true;

		// po to the South from this character
		if ((this.getPosition().getRow() == pos.getRow() + 1) && (this.getPosition().getCol() == pos.getCol()))
			return true;

		// pos to the East from this character
		if ((this.getPosition().getCol() == pos.getCol() - 1) && (this.getPosition().getRow() == pos.getRow()))
			return true;

		// pos2 to the West from this character
		if ((this.getPosition().getCol() == pos.getCol() + 1) && (this.getPosition().getRow() == pos.getRow()))
			return true;

		return false;
	}

	/**
	 * Returns an array with the valid squares where a character can move directly
	 * from its current position:(N, S, E and W)
	 * 
	 * @return the vector of positions (possibly free) where it can move
	 */
	public DynamicVectorPosition validPositions() {

		DynamicVectorPosition positions = new DynamicVectorPosition();
		Game currentGame = Controller.getInstance().getCurrentGame();

		Position position = this.getPosition().south();
		if (currentGame.getBoard().freeSquare(position))
			positions.add(position);

		position = this.getPosition().west();
		if (currentGame.getBoard().freeSquare(position))
			positions.add(position);

		position = this.getPosition().north();
		if (currentGame.getBoard().freeSquare(position))
			positions.add(position);

		position = this.getPosition().east();
		if (currentGame.getBoard().freeSquare(position))
			positions.add(position);

		return positions;
	}

	/**
	 * Returns the character with the highest body points from a
	 * {@link DynamicVectorCharacters}
	 * 
	 * @param v The {@link DynamicVectorCharacters}
	 * @return The character with the highest body points
	 */
	public Character getHighestBodyPoints(DynamicVectorCharacters v) {
		int maxBody = Integer.MIN_VALUE;
		Character characterWithMaxBodyPoints = null;

		for (int i = 0; i < v.length(); i++) {
			if (v.get(i).getBody() > maxBody) {
				maxBody = v.get(i).getBody();
				characterWithMaxBodyPoints = v.get(i);
			}
		}
		return characterWithMaxBodyPoints;
	}

	/**
	 * Generate a printable representation of the object (overridden method)
	 * 
	 * @return The printable representation of the character
	 */
	@Override
	public String toString() {
		return String.format("%s (moves:%d attack:%d defence:%d body:%d/%d)", getName(), getMovement(), getAttack(),
				getDefence(), getBody(), getBodyInitial());
	}

	/************************************************
	 * Interface GraphicElement implementation
	 **********************************************/

	// Icon for an abstract character
	private static Icon icon = new ImageIcon(ClassLoader.getSystemResource("jeroquest/gui/images/unknown.png"));

	/**
	 * Returns the icon associated to an abstract character
	 * 
	 * @return the icon associated to the class Character
	 */
	public Icon getImage() {
		return icon;
	}

	/**
	 * Shows an icon representing the character in the layer 2
	 * 
	 * @param w graphic panel to show the icon
	 */

	/************************************************
	 * Interface Piece implementation
	 **********************************************/
	// by composition
	private Position position; // position in the board

	/**
	 * Get the position in the board
	 * 
	 * @return the position of the character in the board
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Set the position of the character in the board
	 * 
	 * @param pos new position of the character in the board
	 */
	public void setPosition(Position pos) {
		position = pos;
	}

	/**
	 * Generate a text version to represent the character in the board
	 * 
	 * @return a text (as char) representation of the character
	 */
	public char toChar() {
		// if the subclass doesn't change it, the characters will appear
		// as '?'s
		return '?';
	}

}
