public class Circle {
	//We define the attribute(s) of the class

	private double radius;


	//we define the getter(s) and setter(s)

	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}


	//We create the constructors

	//	First the copy constructor
	public Circle(Circle circle) {
		setRadius(circle.getRadius());
	}
	//	Then the default constructor
	public Circle(double radius){
		setRadius(radius);
	}


	//Then we define the rest of the methods of the class

	public double perimeter(){
		return Math.PI * 2 * getRadius();
	}
	public double area(){
		return Math.PI * Math.pow(getRadius(), 2.0);
	}
}
