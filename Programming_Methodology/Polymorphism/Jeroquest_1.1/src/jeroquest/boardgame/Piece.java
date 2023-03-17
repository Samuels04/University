package jeroquest.boardgame;

import jeroquest.utils.Position;

public interface Piece {

	/**
	 * Get the position in the board
	 * 
	 * @return the position in the board
	 */
	public Position getPosition();

	/**
	 * Set the position of the piece in the board
	 * 
	 * @param pos new position of the piece in the board
	 */
	public void setPosition(Position pos);

	public abstract char toChar();
}
