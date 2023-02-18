public class Point {
	//We define the attributes, in this case the x & y coordinates of the point

	private int x;
	private int y;


	//We define the getters and setters

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}


	//We define the constructor

	public Point(int x, int y) {
		setX(x);
		setY(y);
	}


	public static double distance(Point point1, Point point2){
		return Math.sqrt( Math.pow((point2.getX() - point1.getX()),2.0) + Math.pow((point2.getY() - point1.getY()),2.0));
	}

	/* We define the toString method that will provide the string representation of an object
	of the type Point */ 

	@Override
	public String toString(){
		return String.format("Point (%d, %d)", getX(), getY());
	}


	
}
