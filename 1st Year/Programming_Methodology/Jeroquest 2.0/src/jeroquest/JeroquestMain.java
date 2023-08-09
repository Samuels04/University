/*
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * 
 * @author Programming Methodology Professors
 *
 */
package jeroquest;
// Demo of the class Jeroquest

import jeroquest.logic.Controller;

/**
 * Main program to test the game Jeroquest
 * 
 * @author Programming Methodology Professors
 * @version 1.5
 *
 */
public class JeroquestMain {

	public static void main(String[] args) {

		// let's play a game with 3 Heroes against 4 Monsters
		// in a board of 7 by 10
		// in 20 turns
		Controller.getInstance().newGame(3, 4, 7, 10, 20);
		Controller.getInstance().toPlay();
	}

}
