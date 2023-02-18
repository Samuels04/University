package chess.units;
import chess.boardgame.Board;
//import chess.boardgame.Square;
import chess.utils.DynamicVectorPosition;
//import chess.utils.Position;

public class Bishop extends Piece{
	/**
	 * Constructor
	 * 
	 */
	public Bishop() {
		super.pos = null;
	}

	@Override
	public char toChar() {
		return 'B';
	}

	@Override
	protected DynamicVectorPosition generatePossiblePositions(Board currentBoard) {
		DynamicVectorPosition positions = new DynamicVectorPosition();

		//N -> E
		for(int i = this.getPosition().getRow(); i >=0 ; i--){

			// 'i' iterates through the rows for the bishop to be able to move north/south

			for(int j = this.getPosition().getColumn(); j < currentBoard.getColumns(); j++){

				//'j' iterates through the columns for the bishop to be able to move east/west

				positions.add(this.getPosition().north().east());
			}
		}

		//N -> W
		for(int i = this.getPosition().getRow(); i >=0 ; i--){

			// 'i' iterates through the rows for the bishop to be able to move north/south

			for(int j = this.getPosition().getColumn(); j >= 0; j--){

				//'j' iterates through the columns for the bishop to be able to move east/west

				positions.add(this.getPosition().north().west());
			}
		}

		//S -> E
		for(int i = this.getPosition().getRow(); i < currentBoard.getRows() ; i++){

			// 'i' iterates through the rows for the bishop to be able to move north/south

			for(int j = this.getPosition().getColumn(); j < currentBoard.getColumns(); j++){

				//'j' iterates through the columns for the bishop to be able to move east/west

				positions.add(this.getPosition().south().east());
			}
		}

		//S -> W
		for(int i = this.getPosition().getRow(); i < currentBoard.getRows() ; i++){

			// 'i' iterates through the rows for the bishop to be able to move north/south

			for(int j = this.getPosition().getColumn(); j >= 0 ; j--){

				//'j' iterates through the columns for the bishop to be able to move east/west

				positions.add(this.getPosition().south().west());
			}
		}


		return positions;
	}

	@Override
	public String toString() {
		return "The bishop in position " + ((this.getPosition() != null) ? this.getPosition() : "(-,-)");
	}
}