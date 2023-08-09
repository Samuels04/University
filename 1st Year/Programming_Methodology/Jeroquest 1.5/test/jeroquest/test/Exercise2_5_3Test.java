package jeroquest.test;

<<<<<<< Updated upstream
/*
 * import static org.junit.jupiter.api.Assertions.*;
 * 
 * import org.junit.jupiter.api.Test;
 * 
 * import jeroquest.boardgame.Position;
 * import jeroquest.logic.Controller;
 * import jeroquest.logic.Game;
 * import jeroquest.units.Character;
 * import jeroquest.utils.DynamicVectorCharacters;
 * 
 * class Exercise2_5_3Test {
 * 
 * @Test
 * void testCharacterValidPositions() {
 * 
 * // create a new game with a hero and a board of 3 rows by 3 columns
 * Controller.getInstance().newGame(1, 0, 3, 3, 20);
 * 
 * Character hero =
 * Controller.getInstance().getCurrentGame().getCharacters()[0];
 * // the hero starts in the lower square (1,1)
 * Controller.getInstance().getCurrentGame().getBoard().movePiece(hero, new
 * Position(1, 1));
 * 
 * assertEquals(4, hero.validPositions().length());
 * assertTrue(hero.validPositions().member(new Position(1, 0)));
 * assertTrue(hero.validPositions().member(new Position(0, 1)));
 * assertTrue(hero.validPositions().member(new Position(1, 2)));
 * assertTrue(hero.validPositions().member(new Position(2, 1)));
 * }
 * 
 * @Test
 * void testJeroquestValidTargets() {
 * // We check that in several random new games the validTargets follow the 3
 * // established rules: at range, alive, enemy
 * for (int i = 0; i < 10; i++) {
 * Controller.getInstance().newGame(2, 2, 4, 4, 20);
 * Game currentGame = Controller.getInstance().getCurrentGame();
 * // placing the characters
 * currentGame.getBoard().movePiece(currentGame.getCharacters()[0], new
 * Position(0, 0));
 * currentGame.getBoard().movePiece(currentGame.getCharacters()[1], new
 * Position(0, 1));
 * currentGame.getBoard().movePiece(currentGame.getCharacters()[2], new
 * Position(1, 0));
 * currentGame.getBoard().movePiece(currentGame.getCharacters()[3], new
 * Position(1, 1));
 * // artificially one of the characters will be dead
 * currentGame.getCharacters()[0].defend(100);
 * 
 * for (int j = 1; j < currentGame.getCharacters().length; j++) {
 * Character c = currentGame.getCharacters()[j];
 * DynamicVectorCharacters targets = c.validTargets();
 * for (int k = 0; k < targets.length(); k++) {
 * assertTrue(c.isAtRange(targets.get(k).getPosition()));
 * assertTrue(targets.get(k).isAlive());
 * assertTrue(c.isEnemy(targets.get(k)));
 * }
 * }
 * }
 * }
 * 
 * }
 */
=======
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jeroquest.boardgame.Position;
import jeroquest.logic.Controller;
import jeroquest.logic.Game;
import jeroquest.units.Character;
import jeroquest.utils.DynamicVectorCharacters;

class Exercise2_5_3Test {

	@Test
	void testCharacterValidPositions() {

		// create a new game with a hero and a board of 3 rows by 3 columns
		Controller.getInstance().newGame(1, 0, 3, 3, 20);

		Character hero = Controller.getInstance().getCurrentGame().getCharacters().get(0);
		// the hero starts in the lower square (1,1)
		Controller.getInstance().getCurrentGame().getBoard().movePiece(hero, new Position(1, 1));

		assertEquals(4, hero.validPositions().length());
		assertTrue(hero.validPositions().member(new Position(1, 0)));
		assertTrue(hero.validPositions().member(new Position(0, 1)));
		assertTrue(hero.validPositions().member(new Position(1, 2)));
		assertTrue(hero.validPositions().member(new Position(2, 1)));
	}

	@Test
	void testJeroquestValidTargets() {
		// We check that in several random new games the validTargets follow the 3
		// established rules: at range, alive, enemy
		for (int i = 0; i < 10; i++) {
			Controller.getInstance().newGame(2, 2, 4, 4, 20);
			Game currentGame = Controller.getInstance().getCurrentGame();
			// placing the characters
			currentGame.getBoard().movePiece(currentGame.getCharacters().get(0), new Position(0, 0));
			currentGame.getBoard().movePiece(currentGame.getCharacters().get(1), new Position(0, 1));
			currentGame.getBoard().movePiece(currentGame.getCharacters().get(2), new Position(1, 0));
			currentGame.getBoard().movePiece(currentGame.getCharacters().get(3), new Position(1, 1));
			// artificially one of the characters will be dead
			currentGame.getCharacters().get(0).defend(100);

			for (int j = 1; j < currentGame.getCharacters().length(); j++) {
				Character c = currentGame.getCharacters().get(j);
				DynamicVectorCharacters targets = c.validTargets();
				for (int k = 0; k < targets.length(); k++) {
					assertTrue(c.isAtRange(targets.get(k).getPosition()));
					assertTrue(targets.get(k).isAlive());
					assertTrue(c.isEnemy(targets.get(k)));
				}
			}
		}
	}

}
>>>>>>> Stashed changes
