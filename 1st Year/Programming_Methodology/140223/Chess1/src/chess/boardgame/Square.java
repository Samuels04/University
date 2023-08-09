package chess.boardgame;

import chess.units.Bishop;
import chess.units.King;
import chess.units.Knight;
import chess.units.Rook;

/**
 * Programming Methodology Practice. MiniChess - An example of Object Oriented
 * Programming. Class Square - class that represents each one of the squares in
 * the board
 * 
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo
 *
 */

public class Square {
	private King kingPiece; // the king piece that is in this square
	private Knight knightPiece; // the knight piece that is in this square
	private Bishop bishopPiece; //the bishop piece that is in this square
	private Rook rookPiece; //the rook piece that is in this square

	/**
	 * Constructor for an empty square (without a piece)
	 */
	public Square() {
		kingPiece = null;
		knightPiece = null;
		bishopPiece = null;
		rookPiece = null;
	}

	/**
	 * Get the piece in this square
	 * 
	 * @return the piece in this square, or null if there isn't any
	 */
	public King getKingPiece() {
		return kingPiece;
	}

	public Knight getKnightPiece() {
		return knightPiece;
	}

	/**
	 * Set the piece in the square
	 * 
	 * @param piece the piece that is placed in the square
	 */
	public void setPiece(King piece) {
		setNoPiece();
		this.kingPiece = piece;
	}

	public void setPiece(Knight piece) {
		setNoPiece();
		this.knightPiece = piece;
	}
	/**
	 * Set the piece in the square
	 * 
	 * @param piece the piece that is placed in the square
	 */
	public void setPiece(Bishop piece) {
		setNoPiece();
		this.bishopPiece = piece;
	}
	/**
	 * Set the piece in the square
	 * 
	 * @param piece the piece that is placed in the square
	 */
	public void setPiece(Rook piece) {
		setNoPiece();
		this.rookPiece = piece;
	}

	public void setNoPiece() {
		this.kingPiece = null;
		this.knightPiece = null;
	}

	/**
	 * Checks if the square has a piece in it
	 * 
	 * @return true if it doesn't a piece
	 */
	public boolean empty() {
		return (kingPiece == null && knightPiece == null);
	}

	/**
	 * Generate a printable version of the object as String (Overridden method)
	 * 
	 * @return The printable version of the square as String
	 */
	@Override
	public String toString() {
		if (kingPiece != null)
			return String.format("%c", kingPiece.toChar());
		if (knightPiece != null)
			return String.format("%c", knightPiece.toChar());
		return "-";

	}
}
