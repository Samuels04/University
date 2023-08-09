package jeroquest.utils;

import jeroquest.boardgame.Position;

/**
 * Programming Methodology Practice. Jeroquest - An example of Object Oriented
 * Programming. Class DynamicVectorPosition - Class that allows to represent
 * dynamic vector of XYLocation
 * 
 * @author Jorge Puente Peinador y Ramiro Varela Arias
 * @author Juan Luis Mateo
 *
 */
public class DynamicVectorPosition extends DynamicVectorObjects {

	public DynamicVectorPosition() {

	}

	public DynamicVectorPosition(DynamicVectorPosition vDE) {
		super(vDE);
	}

	public DynamicVectorPosition(int size) {
		super(size);
	}

	public DynamicVectorPosition(Integer[] v) {
		super((Object[]) v);
	}

	@Override
	public Position get(int i) {
		return (Position) super.get(i);
	}

	// Other methods
	@Override
	public Position[] vectorNormal() { // return a Position[] as the integer of vOI
		Position[] temp = new Position[this.length()];
		for (int i = 0; i < temp.length; i++)
			temp[i] = this.get(i);
		return temp;
	}

}
