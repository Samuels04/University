package jeroquest.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jeroquest.boardgame.Dice;
import jeroquest.boardgame.Position;
import jeroquest.logic.Controller;
import jeroquest.logic.Game;
import jeroquest.units.Character;
import jeroquest.units.Hero;
import jeroquest.units.Weapon;

class Exercise2_5_4b_eTest {

	@Test
	void testRemoveDeadCharacters() {
		Controller.getInstance().newGame(1, 1, 7, 10, 20);
		Game currentGame = Controller.getInstance().getCurrentGame();

		Character hero = currentGame.getCharacters().get(0);
		currentGame.getBoard().movePiece(hero, new Position(4, 5));
		currentGame.getBoard().movePiece(currentGame.getCharacters().get(1), new Position(3, 5));

		// Initially there are 2 characters
		assertEquals(2, currentGame.getCharacters().length());
		// We want that the hero always kills the target
		((Hero) hero).setWeapon(new Weapon("Atomic bomb", 100));
		hero.actionCombat();
		// After the combat the monster dies and the vector has one single character
		assertEquals(1, currentGame.getCharacters().length());

	}

	@Test
	void testHeroMoveAndAttack() {
		Dice.setSeed(7L);
		// create a new game with a hero and a horizontal board of 1 row by 4 columns
		Controller.getInstance().newGame(1, 2, 1, 4, 20);
		Game currentGame = Controller.getInstance().getCurrentGame();

		Character hero = currentGame.getCharacters().get(0);
		// move the hero away from the monsters
		currentGame.getBoard().movePiece(hero, new Position(0, 0));

		// Force to produce only 1 wound
		Dice.setSeed(1L);
		hero.resolveTurn();
		// the hero has attacked the monster at position (0,1),
		// a Mummy who got one wound
		assertEquals(1, currentGame.getCharacters().get(2).getBody());

	}

	@Test
	void testStatistics() {
		Dice.setSeed(2L);

		Controller.getInstance().newGame(2, 2, 2, 2, 2);
		Game currentGame = Controller.getInstance().getCurrentGame();

		Dice.setSeed(0L);
		currentGame.getCharacters().get(0).resolveTurn(); // Dwarf0 attacks to Mummy1 who blocks the attack
		currentGame.getCharacters().get(1).resolveTurn(); // Dwarf1 attacks to Mummy1 and inflicts 1 wound
		currentGame.getCharacters().get(2).resolveTurn(); // Globin0 attacks to Dwarf0 who blocks the attack
		currentGame.getCharacters().get(3).resolveTurn(); // Mummy1 attacks to Dwarf0 and inflicts 1 wound
		assertEquals(2, currentGame.getStatistics().getHeroesAttacks());
		assertEquals(1, currentGame.getStatistics().getHeroesDamageInflicted());
		assertEquals(2, currentGame.getStatistics().getMonstersAttacks());
		assertEquals(1, currentGame.getStatistics().getMonstersDamageInflicted());

	}
}
