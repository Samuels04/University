package chess.units;
import chess.boardgame.Board;
//import chess.boardgame.Square;
import chess.utils.DynamicVectorPosition;
//import chess.utils.Position;

public class Rook extends Piece {
	/**
	 * Constructor
	 * 
	 */
	public Rook() {
		super.pos = null;
	}

	@Override
	public char toChar() {
		return 'R';
	}

	@Override
	protected DynamicVectorPosition generatePossiblePositions(Board currentBoard) {
		DynamicVectorPosition positions = new DynamicVectorPosition();

		// N
		for(int i = this.getPosition().getRow(); i >= 0; i--) {
			positions.add(this.getPosition().north());
		}
		// S
		for(int i = this.getPosition().getRow(); i < currentBoard.getRows(); i++) {
			positions.add(this.getPosition().south());
		}
		// W
		for(int i = this.getPosition().getColumn(); i >=0; i--) {
			positions.add(this.getPosition().west());
		}
		// E
		for(int i = this.getPosition().getColumn(); i < currentBoard.getColumns(); i++) {
			positions.add(this.getPosition().east());
		}

		return positions;
	}

	@Override
	public String toString() {
		return "The rook in position " + ((this.getPosition() != null) ? this.getPosition() : "(-,-)");
	}
}