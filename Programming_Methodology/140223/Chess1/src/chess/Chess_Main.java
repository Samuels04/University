package chess;

import chess.boardgame.Board;
import chess.boardgame.Dice;
import chess.units.Bishop;
import chess.units.King;
import chess.units.Knight;
import chess.units.Rook;
import chess.utils.DynamicVectorPosition;
import chess.utils.Position;

/**
 * Main program to test the mini-chess game
 * 
 * @author Jorge Puente Peinador
 * @author Juan Luis Mateo
 * @version 1.0
 *
 */
public class Chess_Main {
	// Total rounds
	static final int TOTAL_ROUNDS = 5;
	// Board dimensions
	static final int ROWS = 8;
	static final int COLS = 8;

	public static void main(String[] args) {

		// we build a 4x4 board
		Board theBoard = new Board(ROWS, COLS);

		// We create a King piece "and a Knight"
		King theKing = new King();
		Knight theKnight = new Knight();
		Bishop theBishop = new Bishop();
		Rook theRook = new Rook();

		// move the King, Knight, Bishop and Rook to the initial position
		setup(theKing, theKnight, theBishop, theRook, theBoard);

		// show the board, the king "and the knight"
		System.out.println(theBoard);
		System.out.println(theKing);
		System.out.println(theKnight);
		System.out.println(theBishop);

		int round = 1;

		while (round <= TOTAL_ROUNDS) {
			System.out.println("Round: " + round);

			// move the king
			movePiece(theKing, theBoard);
			// move the knight
			movePiece(theKnight, theBoard);
			// move the Bishop
			movePiece(theBishop, theBoard);
			// move the rook
			movePiece(theRook, theBoard);

			// show the board, the king "and the knight"
			System.out.println(theBoard);
			System.out.println(theKing);
			System.out.println(theKnight);
			System.out.println(theBishop);
			System.out.println(theRook);

			round++;
		}

	}

	/**
	 * Move the King, Knight, Bishop and Rook to the initial position on the board
	 * 
	 * @param theKing
	 * @param theKnight
	 * @param theBishop
	 * @param theRook
	 * @param theBoard
	 */
	private static void setup(King theKing, Knight theKnight, Bishop theBishop, Rook theRook, Board theBoard) {
		// We place the king in the (0,0) square of the board
		theBoard.movePiece(theKing, new Position(0, 0));
		// We place the knight in the (4,4) square of the board
		theBoard.movePiece(theKnight, new Position(4, 4));
		//We place the bishop in the (1,1) square of the board
		theBoard.movePiece(theBishop, new Position(1,1));
		//We place the rook in the (2,2) square 
		theBoard.movePiece(theRook, new Position(2,2));
	}

	/**
	 * Move the king to a new random valid position
	 * 
	 * @param theKing  the piece to move
	 * @param theBoard the board
	 */
	private static void movePiece(King theKing, Board theBoard) {
		// Identify the possible movements from the current position of the king
		DynamicVectorPosition positions = theKing.validPositions(theBoard);
		// show positions
		showPositions(positions);
		// select position at random, if possible
		if (positions.length() > 0) {
			int selected = Dice.roll(positions.length()) - 1;

			// show movement
			System.out.printf("Move King: %s -> %s\n", theKing.getPosition(), positions.get(selected));

			// move piece
			theBoard.movePiece(theKing, positions.get(selected));
		}
	}

	/**
	 * Move the knight to a new random valid position
	 * 
	 * @param theKnigt the piece to move
	 * @param theBoard the board
	 */
	private static void movePiece(Knight theKnight, Board theBoard) {
		// Identify the possible movements from the current position of the king
		DynamicVectorPosition positions = theKnight.validPositions(theBoard);
		// show positions
		showPositions(positions);
		// select position at random, if possible
		if (positions.length() > 0) {
			int selected = Dice.roll(positions.length()) - 1;

			// show movement
			System.out.printf("Move Knight: %s -> %s\n", theKnight.getPosition(), positions.get(selected));

			// move piece
			theBoard.movePiece(theKnight, positions.get(selected));
		}
	}
	/**
	 * Move the bishop to a new random valid position
	 * 
	 * @param theBishop the piece to move
	 * @param theBoard the board
	 */
	private static void movePiece(Bishop theBishop, Board theBoard) {
		// Identify the possible movements from the current position of the king
		DynamicVectorPosition positions = theBishop.validPositions(theBoard);
		// show positions
		showPositions(positions);
		// select position at random, if possible
		if (positions.length() > 0) {
			int selected = Dice.roll(positions.length()) - 1;

			// show movement
			System.out.printf("Move Knight: %s -> %s\n", theBishop.getPosition(), positions.get(selected));

			// move piece
			theBoard.movePiece(theBishop, positions.get(selected));
		}
	}

	/**
	 * Move the rook to a new random valid position
	 * 
	 * @param theRook the piece to move
	 * @param theBoard the board
	 */
	private static void movePiece(Rook theRook, Board theBoard) {
		// Identify the possible movements from the current position of the king
		DynamicVectorPosition positions = theRook.validPositions(theBoard);
		// show positions
		showPositions(positions);
		// select position at random, if possible
		if (positions.length() > 0) {
			int selected = Dice.roll(positions.length()) - 1;

			// show movement
			System.out.printf("Move Knight: %s -> %s\n", theRook.getPosition(), positions.get(selected));

			// move piece
			theBoard.movePiece(theRook, positions.get(selected));
		}
	}

	private static void showPositions(DynamicVectorPosition positions) {
		System.out.printf("%d possibilities:", positions.length());
		for (int x = 0; x < positions.length(); x++)
			System.out.printf(" %s", positions.get(x));
		System.out.println();
	}

}

 
