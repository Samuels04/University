package jeroquest.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import jeroquest.boardgame.Position;
import jeroquest.units.Character;

/**
 * Class MyJLabelCharacter, models a graphical label of Java with a character
 * associated to it Having the character allows it to show the "life bar" over
 * the icon
 * 
 * @author Programming Methodology Professors
 *
 */
public class MyJLabelCharacter extends JLabel {
	// this avoids a warning referred to serialisable windows
	private static final long serialVersionUID = 1L;

	private Character character;

	/**
	 * @return the character
	 */
	public Character getCharacter() {
		return character;
	}

	/**
	 * Constructor to create the icon of a character in the board
	 * 
	 * @param character character to show
	 */
	public MyJLabelCharacter(Character character) {
		// associate the icon of the character with the JLabel
		super(character.getImage());
		this.character = character;
		// size 32x32 and placed in its position (X,Y)
		Position pos = character.getPosition();
		setBounds(pos.getCol() * MyPanelBoard.LENGTH, pos.getRow() * MyPanelBoard.LENGTH, MyPanelBoard.LENGTH,
				MyPanelBoard.LENGTH);
		// set as tooltip the description of the character
		setToolTipText(MyPanelBoard.convertToMultiline(character.toString()));

	}

	/**
	 * Create a bar showing the current life of the character The colour indicate
	 * the current value with respect to the initial one (the maximum)
	 * 
	 * @param g            Graphics object to paint over
	 * @param currentValue current life
	 * @param maximum      initial life
	 */
	private void lifeBar(Graphics g, int currentValue, int maximum) {
		// scaling so that the maximum fits in 32 pixels
		int width = (int) Math.round(currentValue * MyPanelBoard.LENGTH / maximum);

		// bar white frame
		g.setColor(Color.WHITE);
		g.drawRect(0, MyPanelBoard.LENGTH - 1 - 3, MyPanelBoard.LENGTH - 1, 3);

		// bar black background
		g.setColor(Color.BLACK);
		g.fillRect(1, MyPanelBoard.LENGTH - 1 - 2, MyPanelBoard.LENGTH - 2, 2);

		// set the colour of the bar...
		// GREEN: if it has more than 75% of life
		if (character.getBody() > 0.75 * character.getBodyInitial())
			g.setColor(Color.GREEN);
		// YELLOW: if it has between (50%..75%] of life
		else if (character.getBody() > 0.5 * character.getBodyInitial())
			g.setColor(Color.YELLOW);
		// RED: if the life is <= 50%
		else
			g.setColor(Color.RED);
		// Draw the bar
		g.fillRect(1, MyPanelBoard.LENGTH - 1 - 2, width - 2, 2);

	}

	/**
	 * Paint the character in its position and over the icon the bar of life
	 * 
	 * @param g Graphics object where to paint
	 */
	@Override
	public void paintComponent(Graphics g) {

		// the method of JLabel will show the icon of the character
		super.paintComponent(g);

		if (character.getPosition() != null) {
			this.setIcon(character.getImage());
			// size 32x32 and placed in its position (X,Y)
			Position pos = character.getPosition();
			setBounds(pos.getCol() * MyPanelBoard.LENGTH, pos.getRow() * MyPanelBoard.LENGTH, MyPanelBoard.LENGTH,
					MyPanelBoard.LENGTH);
			// set as tooltip the description of the character
			setToolTipText(MyPanelBoard.convertToMultiline(character.toString()));
			// paint over the icon the bar of life
			lifeBar(g, character.getBody(), character.getBodyInitial());
		} else
			this.setVisible(false);
	}
}
