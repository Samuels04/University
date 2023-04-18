/*
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * 
 * @author Programming Methodology Professors
 *
 */
package jeroquest.gui;

import javax.swing.JFrame; // needed for the window

import jeroquest.logic.Game;

/**
 * Class that represents graphical information by means of a panel and it object Game to show
 * 
 * @author Programming Methodology Professors
 */
public class JeroquestWindow extends JFrame {
	// version of the class 1 (necessary for possible serialisations of the objects
	// of this class)
	private static final long serialVersionUID = 1L;

	private MyPanelBoard panel = null; // panel to show the content

	/**
	 * Constructor of the window
	 * 
	 * @param theGame the object Game to visualise
	 */
	public JeroquestWindow(Game theGame) {
		// create the JFrame object that represents the window
		super("Monitor Jeroquest");

		// create a panel board
		panel = new MyPanelBoard(theGame);

		// action to do when the window is closed -> end the application
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// add the panel to the window
		add(panel);
		// set the dimensions of the window
		pack();
		// set that the window cannot change size
		setResizable(false);
		// make the window visible
		setVisible(true);
		// set window always on top
		setAlwaysOnTop(true);
	}

	/**
	 * Close the window and free the used resources
	 */
	public void close() {
		dispose();
	}

	public void updateRoundLabel() {
		panel.updateRoundLabel();
	}

	public void updateGraphicalPiece(GraphicalPiece graphicalPiece, String message) {
		panel.updateGraphicalPiece(graphicalPiece, message);
	}
}
