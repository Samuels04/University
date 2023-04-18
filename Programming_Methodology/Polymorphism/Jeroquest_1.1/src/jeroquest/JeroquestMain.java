package jeroquest;

import jeroquest.boardgame.Board;
import jeroquest.boardgame.Dice;
import jeroquest.units.*;
import jeroquest.units.Character;
import jeroquest.utils.DynamicVectorPosition;
import jeroquest.utils.Position;
import jeroquest.items.*;

/**
 * Main program to test the game Jeroquest 1.1
 * 
 * @author Jorge Puente Peinador
 * @author Juan Luis Mateo
 * @version 1.1
 *
 */
public class JeroquestMain {
	// Total rounds
	static final int TOTAL_ROUNDS = 5;
	// Board dimensions
	static final int ROWS = 10;
	static final int COLS = 10;
	static final int NUM_PIECES = 6;

	public static void main(String[] args) {

		// we build the board
		Board theBoard = new Board(ROWS, COLS);

		//We create walls and traps

		Item[] items = new Item[Dice.roll(32)];
		for(int i = 0; i < items.length; i++){
			int row = Dice.roll(ROWS);
			int col = Dice.roll(COLS);
			int result = Dice.roll(2);
			if(result == 1 && theBoard.freeSquare(new Position(row-1,col-1))){
				items[i] = new Wall();
				items[i].setPosition(new Position(row,col));
			} else {
				items[i] = new Trap();
				items[i].setPosition(new Position(row,col));
			}
		}

		// We create a vector of pieces
		Character[] pieces = new Character[NUM_PIECES];
		for (int i = 0; i < pieces.length; i++) {
			int result = Dice.roll(2);
			int row = Dice.roll(ROWS);
			int col = Dice.roll(COLS);
			if (result == 1 && theBoard.freeSquare(new Position(row-1, col-1))){
				pieces[i] = new Barbarian("Default", row-1, col-1);
			} else {
				pieces[i] = new Mummy("Default", row-1, col-1);
			}
		}

		// show the board
		System.out.println(theBoard);

		int round = 1;

		while (round <= TOTAL_ROUNDS) {
			System.out.println("Round: " + round);

			for (int i = 0; i < pieces.length; i++) {
				movePiece(pieces[i], theBoard);
			}

			// show the board
			System.out.println(theBoard);

			round++;
		}
	}

	/**
	 * Move the pieces from the vector to the initial random positions on the board
	 * 
	 * @param pieces
	 * @param theBoard
	 */
	/*private static void setup(Character[] pieces, Board theBoard) {
		for (int i = 0; i < pieces.length; i++) {
			while (!theBoard.movePiece(pieces[i], new Position(Dice.roll(ROWS) - 1, Dice.roll(COLS) - 1)))
				;
		}
	}*/

	/**
	 * Move the piece to a new random valid position
	 * 
	 * @param piece    the piece to move
	 * @param theBoard the board
	 */

	private static void movePiece(Character piece, Board theBoard) {
		// Identify the possible movements from the current position of the piece
		DynamicVectorPosition positions = piece.validPositions(theBoard);
		// select position at random, if possible
		if (positions.length() > 0) {
			int selected = Dice.roll(positions.length()) - 1;

			// show movement
			System.out.printf("Move Piece: %s -> %s\n", piece.getPosition(), positions.get(selected));

			// move piece
			theBoard.movePiece(piece, positions.get(selected));
		}
	}

}
