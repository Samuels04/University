/*
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * 
 * @author Programming Methodology Professors
 *
 */
package jeroquest.boardgame;

/**
 * Class that represents positions in a 2D board
 *
 * @author Programming Methodology Professors
 */
public class Position {

	private int row, col; // 2D coordinates

	/**
	 * Create a location from its coordinates
	 * 
	 * @param row the row
	 * @param col the column
	 */
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * Get the row coordinate
	 * 
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Set the row coordinate
	 * 
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Get the column coordinate
	 * 
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Set the column coordinate
	 * 
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * Get the position immediately to the North from the current one
	 * 
	 * @return the position to the north from the current
	 */
	public Position north() {
		return new Position(this.row - 1, this.col);
	}

	/**
	 * Get the position immediately to the South from the current one
	 * 
	 * @return the position to the south from the current
	 */
	public Position south() {
		return new Position(this.row + 1, this.col);
	}

	/**
	 * Get the position immediately to the East from the current one
	 * 
	 * @return the position to the east from the current
	 */
	public Position east() {
		return new Position(this.row, this.col + 1);
	}

	/**
	 * Get the position immediately to the West from the current one
	 * 
	 * @return the position to the west from the current
	 */
	public Position west() {
		return new Position(this.row, this.col - 1);
	}

	/**
	 * Operation to check if two objects Position are the same
	 * 
	 * @param o object to compare to
	 * @return true if the object Position has the same coordinates
	 */
	@Override
	public boolean equals(Object o) {
		if (null == o || !(o instanceof Position)) {
			return super.equals(o);
		}
		Position anotherLoc = (Position) o;
		return ((anotherLoc.getRow() == row) && (anotherLoc.getCol() == col));
	}

	/**
	 * Generate a printable version of the object as a String (overridden method)
	 * 
	 * @return The printable version as String of the object
	 */
	@Override
	public String toString() {
		return "(" + row + "," + col + ")";
	}

}