
public class CirclewithCentre {
	private Point centre;

	private Circle circle;

	// We define the constructor
	public CirclewithCentre(int x, int y, double r) {
		centre = new Point(x, y);

		circle = new Circle(r);
	}

	public double getRadius() {
		return circle.getRadius();
	}

	public void setRadius(double r) {
		circle.setRadius(r);
	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(int x, int y) {
		centre.setX(x);
		centre.setY(y);
	}

	public void setCentre(Point point) {
		centre.setX(point.getX());
		centre.setY(point.getY());
	}

	public double area() {
		return this.circle.area();
	}

	public double perimeter() {
		return this.circle.perimeter();
	}

	public boolean overlaps(CirclewithCentre c2) {
		if (this.getCentre().distance(c2.getCentre()) < 2 * this.getRadius()) {
			return true;
		}
		return false;
	}
}
