import java.util.Scanner;
public class App {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int x1,y1,x2,y2;
		double radius;

		//First we ask the user for the coordinates of the points

		System.out.println("Enter the coordinates of the 1st point: ");
		System.out.println("\t X-Coordinate: ");
		x1  = sc.nextInt();
		System.out.println("Y-Coordinate: ");
		y1  = sc.nextInt();

		System.out.println("Enter the coordinates of the 2nd point: ");
		System.out.println("\t X-Coordinate: ");
		x2  = sc.nextInt();
		System.out.println("Y-Coordinate: ");
		y2  = sc.nextInt();

		
		//Then we use the constructor to create both points
		Point point1 = new Point(x1,y1);
		Point point2 = new Point(x2,y2);


		//We then show them to the user
		System.out.printf("Your points are %s and %s", point1, point2);

		//We calculate the distance between them and print it
		System.out.println("The distance between the points is " + Point.distance(point1, point2));


		System.out.println("Now change some of the coordinates.");
		System.out.println("First the X of the 1st point: ");
		x1 = sc.nextInt();
		System.out.println("Now the Y of the 2nd point: ");
		y2 = sc.nextInt();

		//We now change the points to whatever the user entered
		point1.setX(x1);
		point2.setY(y2);

		//We then show them to the user
		System.out.printf("Your points now are %s and %s", point1, point2);

		//We calculate the distance between them and print it
		System.out.println("The distance between the points now is " + Point.distance(point1, point2));



		//We now start working with the class circle
		
		System.out.print("Enter the radius of the circle: ");
		radius = sc.nextDouble();

		//We use the constructor to create the circle with the provided radius
		Circle circle = new Circle(radius);
		//We create a copy just in case 
		Circle circleCopy = new Circle(circle);


		//We calculate the area and perimeter of the circle
		System.out.printf("The area & perimeter of your circle are: %f (area) and %f (perimeter). ", circle.area(), circleCopy.perimeter());


		sc.close();
	}
}
