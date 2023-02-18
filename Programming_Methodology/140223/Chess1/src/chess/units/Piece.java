package chess.units;

import chess.boardgame.Board;
import chess.utils.DynamicVectorPosition;
import chess.utils.Position;

public abstract class Piece {
	protected Position pos;

	/**
	 * Constructor
	 * 
	 */
	public Piece() {
		this.pos = null;
	}

	/**
	 * Get the position in the board
	 * 
	 * @return the position in the board
	 */
	public Position getPosition() {
		return pos;
	}

	/**
	 * Set the position of the piece in the board
	 * 
	 * @param pos new position of the piece in the board
	 */
	public void setPosition(Position pos) {
		this.pos = pos;
	}

	/**
	 * Returns an array with the valid squares where a Piece can move directly from
	 * its current position
	 * 
	 * @param currentBoard the board whith the Pieces
	 * @return the vector of positions (possibly free) where it can move
	 */
	public DynamicVectorPosition validPositions(Board currentBoard) {

		DynamicVectorPosition positions = new DynamicVectorPosition();

		positions = generatePossiblePositions(currentBoard);

		filterValidPositions(currentBoard, positions);

		return positions;
	}

	/**
	 * Given a board and a vector of positions, eliminate the invalid positions
	 * w.r.t. the board
	 * 
	 * @param board     the board
	 * @param positions the vector of positions
	 */
	protected void filterValidPositions(Board board, DynamicVectorPosition positions) {
		int x = 0;
		while (x < positions.length()) {
			if (!board.freeSquare(positions.get(x)))
				positions.remove(x);
			else
				x++;
		}
	}

	protected abstract DynamicVectorPosition generatePossiblePositions(Board currentBoard);

	protected abstract char toChar();

}
