/*
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * 
 * @author Programming Methodology Professors
 *
 */
package jeroquest.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import jeroquest.logic.Game;
import jeroquest.units.Character;

/**
 * Class that represents a panel with the elements of the current game:
 * the board, the characters and the current round
 * 
 * @author Programming Methodology Professors
 *
 */
public class MyPanelBoard extends JLayeredPane {
	// this avoids a warning referred to serialisable windows
	private static final long serialVersionUID = 1L;

	// specifies the width and height of the squares that form the board
	protected static final int LENGTH = 32;

	private Map<GraphicalPiece, Component> map;

	private Game theGame = null; // game to show

	// icons of the board elements
	private Icon square = new ImageIcon(ClassLoader.getSystemResource("jeroquest/gui/images/square.png"));

	// Label with the current round
	private JLabel currentRoundJLabel = null;

	// Label showing the description of each action in the game
	private JLabel statusBarJLabel = null;

	/**
	 * Panel constructor with the characters and the board
	 * 
	 * @param g the game
	 */
	public MyPanelBoard(Game g) {
		this.map = new HashMap<GraphicalPiece, Component>();
		this.theGame = g;
		initialiseBoard(g.getBoard().getRows(), g.getBoard().getColumns());
	}

	/**
	 * Create the graphical representation of the board in the panel
	 * 
	 * @param rows    number of rows in the board
	 * @param columns number of columns in the board
	 */
	private void initialiseBoard(int rows, int columns) {
		// create the matrix of squares
		// 1 extra column for the labels counting the rows
		// 3 extra rows for: 1- the labels counting the columns
		// 2- the count of rounds
		// 3- the status bar that shows the detail of each action in the game
		setPreferredSize(new Dimension(32 * (1 + columns), LENGTH * (3 + rows)));

		// create the labels for the coordinates
		for (Integer x = 0; x < columns; x++) {
			JLabel label = new JLabel(x.toString());
			label.setBounds(LENGTH / 2 + x * LENGTH, rows * LENGTH, LENGTH, LENGTH);
			add(label, Integer.valueOf(1));
		}
		for (Integer x = 0; x < rows; x++) {
			JLabel label = new JLabel(x.toString());
			label.setBounds(LENGTH / 2 + columns * LENGTH, x * LENGTH, LENGTH, LENGTH);
			add(label, Integer.valueOf(1));
		}

		// create a grid with the size of the board filling each square
		for (int x = 0; x < rows; x++)
			for (int y = 0; y < columns; y++) {
				// label with the image of an empty square
				JLabel c = new JLabel(square);
				// dimensions (32x32), position (X,Y)
				c.setBounds(y * LENGTH, x * LENGTH, LENGTH, LENGTH);
				// show the icon in its position
				add(c, Integer.valueOf(1));
			}

		// create the label with the current round
		currentRoundJLabel = new JLabel(
				"Round: " + this.theGame.getCurrentRound() + "/" + this.theGame.getTotalRounds());
		currentRoundJLabel.setBounds(LENGTH / 2, (rows + 1) * LENGTH, 128, LENGTH);
		add(currentRoundJLabel, Integer.valueOf(1));

		// create the label for the status bar
		statusBarJLabel = new JLabel();
		statusBarJLabel.setBounds(LENGTH / 2, (rows + 2) * LENGTH, 600, LENGTH);
		add(statusBarJLabel, Integer.valueOf(1));

		initialiseCharacters();
	}

	/**
	 * Convert the char \n of a String to <br>
	 * in HTML to make that the tool tip of a JLabel can be visualised correctly
	 * 
	 * @param orig string to format
	 * @return a formatted String as HTML
	 */
	public static String convertToMultiline(String orig) {
		return "<html>" + orig.replaceAll("\n", "<br>") + "</html>";
	}

	public void updateRoundLabel() {
		currentRoundJLabel.setText("Round: " + this.theGame.getCurrentRound() + "/" + this.theGame.getTotalRounds());
	}

	/**
	 * Show all characters in the board as icons, where their "tool tip" is the
	 * information of the method toString() of each object
	 */
	private void initialiseCharacters() {
		// show the current round
		currentRoundJLabel.setText("Round: " + this.theGame.getCurrentRound() + "/" + this.theGame.getTotalRounds());
		// delete labels for previous characters
		for (Component comp : map.values())
			this.remove(comp);
		map = new HashMap<GraphicalPiece, Component>();

		// recreate the characters
		for (Character cj : theGame.getCharacters()) {
			if (cj.getPosition() != null) {
				// a JLabel is created the icon
				JLabel lab = new MyJLabelCharacter(cj);
				map.put(cj, lab);

				// the label is added to the layer 2
				add(lab, Integer.valueOf(2));
			}
		}
		// update its container what will refresh the screen
		validate();
		repaint();
	}

	public void updateGraphicalPiece(GraphicalPiece graphicalPiece, String message) {
		if (message.length() > 0)
			statusBarJLabel.setText(message);
		if (graphicalPiece != null) {
			Component comp = map.get(graphicalPiece);
			comp.repaint();
		}
	}
}
