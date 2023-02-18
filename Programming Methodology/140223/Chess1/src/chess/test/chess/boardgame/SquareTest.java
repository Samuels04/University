package chess.boardgame;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.units.King;
import chess.units.Knight;

class SquareTest {
	// Square object for testing
	Square s;
	@BeforeEach
	void setUp() throws Exception {
		// create a Square object for testing
		s = new Square();
	}

	@Test
	void testGetKingPieceAndSetPieceKing() {
		King k = new King();
		Knight n = new Knight();
		// set knight on the square 
		s.setPiece(n);
		// set king on the square 
		s.setPiece(k);
		// test the king is on the square
		assertNotNull(s.getKingPiece());
		// test the knight is not longer on the square
		assertNull(s.getKnightPiece());
	}

	@Test
	void testGetKnightPieceAndSetPieceKnight() {
		King k = new King();
		Knight n = new Knight();
		// set king on the square 
		s.setPiece(k);
		// set knight on the square 
		s.setPiece(n);
		// test the knight is on the square
		assertNotNull(s.getKnightPiece());
		// test the king is not longer on the square
		assertNull(s.getKingPiece());
	}
	@Test
	void testSetNoPiece() {
		King k = new King();
		// set king on the square 
		s.setPiece(k);
		// free the square
		s.setNoPiece();
		// test the king is not on the square
		assertNull(s.getKingPiece());

		Knight n = new Knight();
		// set knight on the square 
		s.setPiece(n);
		// free the square
		s.setNoPiece();
		// test the knight is not on the square
		assertNull(s.getKnightPiece());
		
	}
	
	@Test
	void testEmpty() {
		King k = new King();
		// set king on the square 
		s.setPiece(k);
		// test square is not empty
		assertFalse(s.empty());
		// free the square
		s.setNoPiece();
		// test square is empty
		assertTrue(s.empty());
		
		Knight n = new Knight();
		// set knight on the square 
		s.setPiece(n);
		// test square is not empty
		assertFalse(s.empty());
		// free the square
		s.setNoPiece();
		// test the square is empty
		assertTrue(s.empty());
	}

	@Test
	void testToString() {
		// check toString is defined
		assertFalse(s.toString().contains(new String("@")));
		
		King k = new King();
		// set king on the square 
		s.setPiece(k);
		// test toString() is "K" (the King.toChar() value)
		assertTrue(s.toString().equals(String.valueOf(k.toChar())));
		Knight n = new Knight();
		// set knight on the square 
		s.setPiece(n);
		// test toString() is "N" (the Knight.toChar() value)
		System.out.println("<"+s.toString()+">");
		assertTrue(s.toString().equals(String.valueOf(n.toChar())));
	}

}
