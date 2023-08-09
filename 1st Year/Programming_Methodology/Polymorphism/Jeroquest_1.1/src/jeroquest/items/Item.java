package jeroquest.items;

import jeroquest.utils.Position;

public abstract class Item {
	private Position pos;

	/**
	 * Constructor for the type Item
	 */
	public Item(){
		this.setPosition(new Position(0, 0));
	}

	/**
	 * Provides the position of the character in the {@link Board}
	 * @return The position of the {@link Character}
	 */
	public Position getPosition() {
		return pos;
	}

	/**
	 * Sets the current position of the character to the one passed as parameter 
	 * @param position The desired new position of the character
	 */
	public void setPosition(Position position){
		this.pos = position;
	}
}
