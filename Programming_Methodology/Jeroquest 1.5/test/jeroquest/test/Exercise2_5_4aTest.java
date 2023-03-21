package jeroquest.test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import jeroquest.boardgame.Position;
import jeroquest.logic.Controller;
import jeroquest.units.Character;
import jeroquest.units.Hero;
import jeroquest.units.Weapon;
import jeroquest.utils.DynamicVectorCharacters;

class Exercise2_5_4aTest {

	@Test
	void testCharacterVictimHighestBody() {

		for (int j = 0; j < 10; j++) {

			Controller.getInstance().newGame(1, 4, 7, 10, 20);

			Character hero = Controller.getInstance().getCurrentGame().getCharacters()[0];
			Controller.getInstance().getCurrentGame().getBoard().movePiece(hero, new Position(4, 5));
			Controller.getInstance().getCurrentGame().getBoard()
					.movePiece(Controller.getInstance().getCurrentGame().getCharacters()[1], new Position(3, 5));
			Controller.getInstance().getCurrentGame().getBoard()
					.movePiece(Controller.getInstance().getCurrentGame().getCharacters()[2], new Position(4, 4));
			Controller.getInstance().getCurrentGame().getBoard()
					.movePiece(Controller.getInstance().getCurrentGame().getCharacters()[3], new Position(5, 5));
			Controller.getInstance().getCurrentGame().getBoard()
					.movePiece(Controller.getInstance().getCurrentGame().getCharacters()[4], new Position(4, 6));

			// Find the character(s) with highest body
			int highestBody = 0;
			DynamicVectorCharacters charsWithHighestBody = new DynamicVectorCharacters();
			for (int i = 1; i < Controller.getInstance().getCurrentGame().getCharacters().length; i++) {
				if (Controller.getInstance().getCurrentGame().getCharacters()[i].getBody() == highestBody) {
					charsWithHighestBody.add(Controller.getInstance().getCurrentGame().getCharacters()[i]);
				} else if (Controller.getInstance().getCurrentGame().getCharacters()[i].getBody() > highestBody) {
					highestBody = Controller.getInstance().getCurrentGame().getCharacters()[i].getBody();
					charsWithHighestBody = new DynamicVectorCharacters();
					charsWithHighestBody.add(Controller.getInstance().getCurrentGame().getCharacters()[i]);
				}
			}
			// We want that the hero always kills the target
			((Hero) hero).setWeapon(new Weapon("Atomic bomb", 100));

			hero.actionCombat();

			assertTrue(anyDeadCharacter(charsWithHighestBody));
		}
	}

	private boolean anyDeadCharacter(DynamicVectorCharacters characters) {
		for (int i = 0; i < characters.length(); i++) {
			if (!characters.get(i).isAlive())
				return true;
		}
		return false;
	}
}
