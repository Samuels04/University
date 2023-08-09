package jeroquest.items;
//import jeroquest.boardgame.Board;
import jeroquest.boardgame.Piece;
//import jeroquest.utils.*;

/**
 * Class wall for Jeroquest 1.1
 * @author Samuel Sanchez
 * @since 17/03/23
 */
public class Wall extends Item implements Piece {

	/**
	 * Constructor for the type wall
	 */
	public Wall(){
		super();
	}

	/**
	 * Provides a representation for the {@link Wall}
	 * @return the char representation of the wall
	 */
	public char toChar(){
		return '#';
	}

}
