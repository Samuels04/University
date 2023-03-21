/*
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * Programming. Interface Piece - interface that models the behaviour of a piece
 * in the board
 * 
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo
 *
 */
package jeroquest.boardgame;

/**
 * Interface representing any class that can function as a board game piece
 *
 */
public interface Piece {

	/**
	 * Get the position in the board
	 * 
	 * @return the position in the board
	 */
	Position getPosition();

	/**
	 * Set the position of the piece in the board
	 * 
	 * @param pos new position of the piece in the board
	 */
	void setPosition(Position pos);

	/**
	 * Return a char that represents the piece
	 * 
	 * @return the char representation of the piece
	 */
	char toChar();

}
