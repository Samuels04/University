package jeroquest.units;

//import javax.swing.Icon;

import jeroquest.logic.Controller;

public class Zombie extends Monster {

	// initial values for the attributes
	protected static final int MOVEMENT = 4;
	protected static final int ATTACK = 3;
	protected static final int DEFENCE = 0;
	protected static final int BODY = 3;

	/**
	 * Create a Dirty Rat from its name
	 * 
	 * @param name name of the Dirty Rat
	 */
	public Zombie(String name) {
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
		return 'Z';
	}

	/************************************************
	 * Interface GraphicElement implementation
	 **********************************************/

	/*  Icon of a Dirty Rat
	private static Icon icon = new ImageIcon(ClassLoader.getSystemResource("jeroquest/gui/images/zombie.gif"));

	public Icon getImage() {
		return icon;
	}*/ 

	/**
	 * Returns true if the parameter is an enemy
	 * 
	 * @param c the character to check if it's an enemy
	 */
	@Override
	public boolean isEnemy(Character c) {
		return super.isEnemy(c) || c.getBody() < this.getBody();
	}

	@Override
	public int defend(int impacts) {
		return impacts*0;
	}

	@Override
	public int attack() {
		int impacts = 0;
		Character[] characters = Controller.getInstance().getCurrentGame().getCharacters();

		for(Character c : characters){
			if(c.isAtRange(this.getPosition()) && c instanceof Barbarian){
				impacts++;
			} else if (c.isAtRange(this.getPosition()) && !(c instanceof Barbarian)){
				impacts++;
			}
		}

		return impacts;

	}

	public void degradation(){
		while (this.getBody() >= 0){
			this.setBody(this.getBody() - 1);
		}
	}
	
}