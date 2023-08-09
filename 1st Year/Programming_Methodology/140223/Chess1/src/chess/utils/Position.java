package chess.utils;

/**
 * Programming Methodology Practice. MiniChess - An example of Object Oriented
 * Programming. Class Position - class that represents positions in a 2D board
 * 
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo
 *
 */

public class Position {

	private int row, column; // 2D coordinates

	/**
	 * Create a location from its coordinates
	 * 
	 * @param row    the row
	 * @param column the column
	 */
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * Get the row coordinate
	 * 
	 * @return the row coordinate
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Get the column coordinate
	 * 
	 * @return the column coordinate
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Set the row coordinate
	 * 
	 * @param row the new row coordinate
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Set the column coordinate
	 * 
	 * @param column the new column coordinate
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Get the position immediately to the North from the current one
	 * 
	 * @return the position to the north from the current
	 */
	public Position north() {
		return new Position(this.row - 1, this.column);
	}

	/**
	 * Get the position immediately to the South from the current one
	 * 
	 * @return the position to the south from the current
	 */
	public Position south() {
		return new Position(this.row + 1, this.column);
	}

	/**
	 * Get the position immediately to the East from the current one
	 * 
	 * @return the position to the east from the current
	 */
	public Position east() {
		return new Position(this.row, this.column + 1);
	}

	/**
	 * Get the position immediately to the West from the current one
	 * 
	 * @return the position to the west from the current
	 */
	public Position west() {
		return new Position(this.row, this.column - 1);
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
		return ((anotherLoc.getRow() == row) && (anotherLoc.getColumn() == column));
	}

	/**
	 * Generate a printable version of the object as a String (overridden method)
	 * 
	 * @return The printable version as String of the object
	 */
	@Override
	public String toString() {
		return "(" + row + "," + column + ")";
	}

	/**
	 * Euclidean distance to another Position
	 * 
	 * @param loc the other location
	 * @return euclidean distance
	 */
	public double distanceTo(Position loc) {
		return Math.sqrt(Math.pow(this.getRow() - loc.getRow(), 2) + Math.pow(this.getColumn() - loc.getColumn(), 2));
	}

}