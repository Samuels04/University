package jeroquest.logic;

import jeroquest.boardgame.Dice;
import jeroquest.boardgame.Position;
import jeroquest.gui.GraphicalPiece;
import jeroquest.gui.JeroquestWindow;
import jeroquest.gui.MyKeyboard;
//import jeroquest.units.Character;
import jeroquest.units.Hero;
import jeroquest.units.Monster;
import jeroquest.utils.DynamicVectorCharacters;

/**
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * Programming. Class Jeroquest - Represents the game Jeroquest and allows to
 * play it. For that it offers a method to create a new game and start the game.
 * 
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo
 *
 */

// The class Controller includes an object Game and the method toPlay
// that implements the logic of the game

public class Controller {

	private static final Controller INSTANCE = new Controller();
	private Game currentGame; // current game

	private Controller() {

	}

	public static Controller getInstance() {
		return INSTANCE;
	}

	/**
	 * @return the currentGame
	 */
	public Game getCurrentGame() {
		return currentGame;
	}

	private static JeroquestWindow monitor;

	/**
	 * Simulate a Jeroquest game
	 */
	public void toPlay() {
		// GUI - Create the window for the current game
		monitor = new JeroquestWindow(currentGame);

		// Start the game
		System.out.println("START OF THE GAME");

		// CONSOLE - print the initial state of the game
		System.out.println(currentGame);

		MyKeyboard.pressEnter();

		// resolve the game in successive rounds
		while (noEndOfGame()) {
			// resolve the current round
			resolveRound();

			// CONSOLE - print the current state of the game
			System.out.println(currentGame);

			MyKeyboard.pressEnter();

			// increment round
			nextRound();
		}

		// CONSOLE - show the end of the game
		System.out.println("END OF THE GAME");
		System.out.println("Winners: " + highestBody());

		MyKeyboard.pressEnter();

		// GUI - Close the window
		monitor.close();
	}

	/**
	 * create a new game from its components
	 * 
	 * @param numHeroes   how many heroes
	 * @param numMonsters how many monsters
	 * @param rows        height of the board
	 * @param columns     width of the board
	 * @param totalRounds total number of rounds
	 */
	public void newGame(int numHeroes, int numMonsters, int rows, int columns, int totalRounds) { // Ready for round 1
		currentGame = new Game(numHeroes, numMonsters, rows, columns, totalRounds);

		// place the characters in the board randomly
		placeCharacters();
	}

	/**
	 * Go to the next round
	 */
	private void nextRound() {
		currentGame.setCurrentRound(currentGame.getCurrentRound() + 1);
		monitor.updateRoundLabel();
	}

	/**
	 * Check it is the end of the game
	 * 
	 * @return true if the total number of turns has been reached or there no more
	 *         alive characters in both sides, false otherwise
	 */
	private boolean noEndOfGame() {
		return ((currentGame.getCurrentRound() <= currentGame.getTotalRounds()) && opponentsLeft());
	}

	/**
	 * Execute the round of the game: each alive character resolve its turn The
	 * round ends immediately if in any moment there are no any alive character in
	 * both sides
	 */
	private void resolveRound() {
		System.out.println("Round: " + currentGame.getCurrentRound());

		for (int x = 0; (x < currentGame.getCharacters().length()) && opponentsLeft(); x++) {
			DynamicVectorCharacters c = currentGame.getCharacters();
			shuffleCharacters(c);

			for (int i = 0; i < c.length(); i++) {
				if (c.get(i).isAlive()) {
					c.get(i).resolveTurn();
				}
			}

		}
	}

	/**
	 * Place the characters in the board randomly in valid positions: (free and
	 * inside of the board)
	 */
	private void placeCharacters() {
		int rows = currentGame.getBoard().getRows();
		int columns = currentGame.getBoard().getColumns();

		for (int i = 0; i < currentGame.getCharacters().length(); i++) {
			// search a random position inside of the board
			int row = Dice.roll(rows) - 1;
			int col = Dice.roll(columns) - 1;
			// while the position is not valid
			while (!currentGame.getBoard().movePiece(currentGame.getCharacters().get(i), new Position(row, col))) {
				// search a new random position
				row = Dice.roll(rows) - 1;
				col = Dice.roll(columns) - 1;
			}
		}
	}

	private void shuffleCharacters(DynamicVectorCharacters v) {
		for (int i = 0; i < v.length(); i++) {
			int result = Dice.roll(v.length() - 1);

			Object aux = v.get(i);
			v.set(i, v.get(result));
			v.set(result, aux);
		}
	}

	/**
	 * Obtain which side has in total more body points
	 * 
	 * @return the name of the side with more body points
	 */
	private String highestBody() { // Returns the name of the class with highest value for the total body points in
									// the current state of the game
		int cHeroes = 0;
		int cMonsters = 0;
		DynamicVectorCharacters chars = currentGame.getCharacters();

		for (int i = 0; i < chars.length(); i++) {
			if (chars.get(i) instanceof Hero)
				cHeroes += chars.get(i).getBody();
			else if (chars.get(i) instanceof Monster)
				cMonsters += chars.get(i).getBody();
		}
		if (cMonsters > cHeroes)
			return "Monsters";
		else if (cHeroes > cMonsters)
			return "Heroes";
		else
			return "Draw";
	}

	/**
	 * Check if there are characters alive of both sides
	 * 
	 * @return true if there are at least one character alive for each side
	 */

	public boolean opponentsLeft() { // Returns true if both sides have characters alive
		boolean heroesAlive = false;
		boolean monstersAlive = false;
		int x = 0;
		while ((x < currentGame.getCharacters().length()) && !(heroesAlive && monstersAlive)) {
			if (currentGame.getCharacters().get(x).isAlive())
				if (currentGame.getCharacters().get(x) instanceof Hero)
					heroesAlive = true;
				else // this second if is necessary since there can be "neutral" characters (they
						// don't inherit neither from Monster nor from Hero)
				if (currentGame.getCharacters().get(x) instanceof Monster)
					monstersAlive = true;
			x++;
		}

		return heroesAlive && monstersAlive;
	}

	/**
	 * Updates the graphical representation of a Piece in the board
	 * 
	 * @param graphicalPiece
	 */

	/**
	 * Updates the graphical representation of a Piece in the board
	 * 
	 * @param graphicalPiece icon to be updated
	 * @param message        text message to show
	 * @param waitForEnter   true if program should wait for the enter key to be
	 *                       pressed
	 */
	public void updateGraphicalPiece(GraphicalPiece graphicalPiece, String message, boolean waitForEnter) {
		if (monitor != null)
			monitor.updateGraphicalPiece(graphicalPiece, message);
		if (message.length() > 0)
			System.out.println(message);
		if (waitForEnter)
			MyKeyboard.pressEnter();
	}

	/**
	 * Updates the graphical representation of a Piece in the board
	 * 
	 * @param graphicalPiece icon to be updated
	 * @param message        text message to show
	 */
	public void updateGraphicalPiece(GraphicalPiece graphicalPiece, String message) {
		updateGraphicalPiece(graphicalPiece, message, false);
	}
}
