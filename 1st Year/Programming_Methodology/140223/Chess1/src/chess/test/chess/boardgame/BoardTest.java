package chess.boardgame;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.units.King;
import chess.units.Knight;
import chess.utils.Position;

class BoardTest {
	// Board object for testing
	Board b;

	@BeforeEach
	void setUp() throws Exception {
		b = new Board(3, 4);
	}

	@Test
	void testBoardAndGetRowsAndGetColumns() {
		// test rows = 3
		assertEquals(3, b.getRows());
		// test columns = 4
		assertEquals(4, b.getColumns());
	}

	@Test
	void testRemovePieceKing() {
		King k = new King();
		Position pos = new Position(1,0);
		// move king to square (1,0)
		b.movePiece(k, pos);
		// remove king from board
		b.removePiece(k);
		// test king is not on board
		assertNull(k.getPosition());
		// test the square (1,0) is empty
		assertTrue(b.freeSquare(pos));
	}

	@Test
	void testRemovePieceKnight() {
		Knight n = new Knight();
		Position pos = new Position(1,2);		
		// move knight to square (1,2)
		b.movePiece(n, pos);
		// remove knight from board
		b.removePiece(n);
		// test knight is not on board
		assertNull(n.getPosition());
		// test the square (1,2) is empty
		assertTrue(b.freeSquare(pos));
	}

	@Test
	void testFreeSquare() {
		King k = new King();
		Position pos = new Position(1,3);
		// move king to square (1,3)
		b.movePiece(k, pos);
		assertFalse(b.freeSquare(pos));
		// remove king from board
		b.removePiece(k);
		// test the square (1,3) is empty
		assertTrue(b.freeSquare(pos));
	}

	@Test
	void testMovePieceKingXYLocation() {
		King k = new King();
		Position pos = new Position(2,0);
		// test success in moving king to square (2,0)
		assertTrue(b.movePiece(k, pos));
		// test king position is (2,0)
		assertEquals(pos, k.getPosition());
		// test square (2,0) contains a piece
		assertFalse(b.freeSquare(pos));
		King k2 = new King();
		// test failure in moving a piece to occupied square (2,0)
		assertFalse(b.movePiece(k2, pos));
	}

	@Test
	void testMovePieceKnightXYLocation() {
		Knight n = new Knight();
		Position pos = new Position(2,1);
		// test success in moving king to square (2,1)
		assertTrue(b.movePiece(n, pos));
		// test knight position is (2,1)
		assertEquals(pos, n.getPosition());
		// test square (2,0) contains a piece
		assertFalse(b.freeSquare(pos));
		Knight n2 = new Knight();
		// test failure in moving a piece to occupied square (2,1)
		assertFalse(b.movePiece(n2, pos));
	}

	@Test
	void testToString() {
		// check toString is defined
		assertFalse(b.toString().contains(new String("@")));
	}

}
