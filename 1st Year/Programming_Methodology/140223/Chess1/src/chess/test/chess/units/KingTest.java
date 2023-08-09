package chess.units;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.boardgame.Board;
import chess.utils.DynamicVectorPosition;
import chess.utils.Position;

class KingTest {
	// King object for testing
	King k;

	/**
	 * This action is executed before every test
	 * @throws Exception if an exception is produced
	 */
	@BeforeEach
	void setUp() throws Exception {
		// create a King object for testing
		k = new King();
	}

	@Test
	void testGetSetPosition() {
		k.setPosition(new Position(1,2));
		// test the new position
		assertEquals(new Position(1,2), k.getPosition());
	}


	@Test
	void testToChar() {
		// test King as a char is 'K'
		assertEquals('K', k.toChar());
	}

	@Test
	void testValidPositions() {
		Board b = new Board(5,5);
		// Center King on the board
		k.setPosition(new Position(2,2));
		// get possible movements from current location on board b
		DynamicVectorPosition positions = k.validPositions(b);
		// check all valid positions
		// N
		assertTrue(positions.member(new Position(1,2)));
		// S
		assertTrue(positions.member(new Position(3,2)));
		// E
		assertTrue(positions.member(new Position(2,3)));
		// W
		assertTrue(positions.member(new Position(2,1)));
		// NE
		assertTrue(positions.member(new Position(1,3)));
		// NW
		assertTrue(positions.member(new Position(1,1)));
		// SE
		assertTrue(positions.member(new Position(2,3)));
		// SW
		assertTrue(positions.member(new Position(2,1)));
	}

	@Test
	void testToString() {
		// check toString is defined
		assertFalse(k.toString().contains(new String("@")));
	}

}
