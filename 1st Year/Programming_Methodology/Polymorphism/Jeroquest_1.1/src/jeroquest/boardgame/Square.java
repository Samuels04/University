package jeroquest.boardgame;

/**
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * Programming. Class Square - class that represents each one of the squares in
 * the board
 * 
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo
 *
 */

public class Square {

	// The piece that is in this square
	private Piece piece;

	/**
	 * Constructor for an empty square (without a piece)
	 */
	public Square() {
		setPiece(null);
	}

	/**
	 * Get the piece in this square
	 * 
	 * @return the piece in this square, or null if there isn't any
	 */
	public Piece getPiece() {
		return this.piece;
	}

	/**
	 * Set the piece in the square
	 * 
	 * @param piece the piece that is placed in the square
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	/**
	 * Checks if the square has a piece in it
	 * 
	 * @return true if there isn't a piece
	 */
	public boolean empty() {
		return this.piece == null;
	}

	/**
	 * Generate a printable version of the object as String (Overridden method)
	 * 
	 * @return The printable version of the square as String
	 */
	@Override
	public String toString() {
		if (this.piece == null)
			return "-";
		return String.format("%c", piece.toChar());
	}
}
