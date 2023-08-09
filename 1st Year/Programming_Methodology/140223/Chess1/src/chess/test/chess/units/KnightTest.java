package chess.units;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.boardgame.Board;
import chess.utils.DynamicVectorPosition;
import chess.utils.Position;

class KnightTest {
	// knight object for testing
	Knight k;

	/**
	 * This action is executed before every test
	 * @throws Exception if an exception is produced
	 */
	@BeforeEach
	void setUp() throws Exception {
		// create a knight object for testing
		k = new Knight();
	}

	@Test
	void testGetSetPosition() {
		k.setPosition(new Position(1,2));
		// test the new position
		assertEquals(new Position(1,2), k.getPosition());
	}


	@Test
	void testToChar() {
		// test Knight as a char is 'N'
		assertEquals('N', k.toChar());
	}

	@Test
	void testValidPositions() {
		Board b = new Board(5,5);
		// Center knight on the board
		k.setPosition(new Position(2,2));
		// get possible movements from current location on board b
		DynamicVectorPosition positions = k.validPositions(b);
		// check all valid positions
		// NNW
		assertTrue(positions.member(new Position(0,1)));
		// NNE
		assertTrue(positions.member(new Position(0,3)));
		// WWN
		assertTrue(positions.member(new Position(1,0)));
		// WWS
		assertTrue(positions.member(new Position(1,4)));
		// EEN
		assertTrue(positions.member(new Position(3,0)));
		// EES
		assertTrue(positions.member(new Position(3,4)));
		// SSW
		assertTrue(positions.member(new Position(4,1)));
		// SSE
		assertTrue(positions.member(new Position(4,3)));
	}

	@Test
	void testToString() {
		// check toString is defined
		assertFalse(k.toString().contains(new String("@")));
	}

}
