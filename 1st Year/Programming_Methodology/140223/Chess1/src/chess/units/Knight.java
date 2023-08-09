package chess.units;

import chess.boardgame.Board;
import chess.utils.DynamicVectorPosition;
//import chess.utils.Position;

/**
 * Programming Methodology Practice. MiniChess - An example of Object Oriented
 * Programming. Class Knight - class that represents knight piece in chess
 * 
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo
 *
 */
public class Knight extends Piece {
	/**
	 * Constructor
	 * 
	 */
	public Knight() {
		super.pos = null;
	}

	@Override
	public char toChar() {

		return 'N';
	}

	/**
	 * Generate all possible positions after all possible movements of the Piece
	 * 
	 * @param currentBoard the board
	 * @return the dynamic vector of XYLocations of possible movements
	 */
	@Override
	protected DynamicVectorPosition generatePossiblePositions(Board currentBoard) {
		DynamicVectorPosition positions = new DynamicVectorPosition();

		// S -> S -> E
		positions.add(this.getPosition().south().south().east());

		// S -> S -> W
		positions.add(this.getPosition().south().south().west());

		// N -> N -> E
		positions.add(this.getPosition().north().north().east());

		// N -> N -> W
		positions.add(this.getPosition().north().north().west());

		// E -> E -> N
		positions.add(this.getPosition().east().east().north());

		// E -> E -> S
		positions.add(this.getPosition().east().east().south());

		// W -> W -> N
		positions.add(this.getPosition().west().west().north());

		// W -> W -> S
		positions.add(this.getPosition().west().west().south());

		return positions;
	}

}
