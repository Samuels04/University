/*
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * 
 * @author Programming Methodology Professors
 *
 */
package jeroquest.units;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Class that represents a Dirty Rat monster character in the game
 *
 * @author Programming Methodology Professors
 */
public class DirtyRat extends Monster {

	// initial values for the attributes
	protected static final int MOVEMENT = 4;
	protected static final int ATTACK = 2;
	protected static final int DEFENCE = 2;
	protected static final int BODY = 5;

	private boolean fearful;

	/**
	 * Create a Dirty Rat from its name
	 * 
	 * @param name name of the Dirty Rat
	 */
	public DirtyRat(String name) {
		super(name, MOVEMENT, ATTACK, DEFENCE, BODY);
		this.setFearful(false);
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
		return 'R';
	}

	/************************************************
	 * Interface GraphicElement implementation
	 **********************************************/

	// Icon of a Dirty Rat
	private static Icon icon = new ImageIcon(ClassLoader.getSystemResource("jeroquest/gui/images/rat.png"));

	private static Icon icon2 = new ImageIcon(ClassLoader.getSystemResource("jeroquest/gui/images/fearful_rat.png"));

	public Icon getImage() {
		return this.isFearful() ? icon2 : icon;
	}

	/**
	 * Returns true if the parameter is an enemy
	 * 
	 * @param c the character to check if it's an enemy
	 */
	@Override
	public boolean isEnemy(Character c) {
		return super.isEnemy(c) || c.getBody() < this.getBody();
	}

	public boolean isFearful() {
		return fearful;
	}

	public void setFearful(boolean fearful) {
		this.fearful = fearful;
	}

	@Override
	public int defend(int impacts) {
		int wounds = super.defend(impacts);

		if (this.isFearful() && wounds > this.getBodyInitial()) {

			this.setBody(0);

		} else {

		}

		return wounds;
	}

	@Override
	public void resolveTurn() {

		if (!this.isFearful()) {

			super.resolveTurn();

		}
	}

	public void regenerate() {

		if (!this.isFearful()) {

			this.setBody(Math.min(this.getBodyInitial(), this.getBody() + 1));

		} else {

			this.setFearful(false);
		}

	}
}
