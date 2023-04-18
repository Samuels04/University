package jeroquest;
// Demo of the class Jeroquest

import jeroquest.logic.Controller;

/**
 * Main program to test the game Jeroquest
 * 
 * @author Jorge Puente Peinador
 * @author Juan Luis Mateo
 * @version 1.4
 *
 */
public class JeroquestMain {

	public static void main(String[] args) {

		// let's play a game with 3 Heroes against 4 Monsters
		// in a board of 7 by 10
		// in 20 turns
		Controller.getInstance().newGame(4, 6, 4, 4, 12);
		Controller.getInstance().toPlay();
	}

}
