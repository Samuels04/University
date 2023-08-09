/**
 * Usage example of a class that implements an interface
 * 
 */

public class BicycleDemo {

     public static void main(String[] args) {

          // Create two different Bicycle objects
          Bicycle bike1 = new MountainBike(3, 20, 4, 45, "mono");
          Bicycle bike2 = new MountainBike(4, 18, 5, 90, "dual");

          // Invoke methods on those objects
          bike1.setCadence(50);
          ((MountainBike) bike1).speedUp(10);
          ((MountainBike) bike1).turnLeft(15);
          bike1.setGear(2);
          bike1.printStates();

          bike2.setCadence(50);
          ((MountainBike) bike2).speedUp(10);
          bike2.setGear(2);
          bike2.setCadence(40);
          ((MountainBike) bike2).turnRight(34);
          ((MountainBike) bike2).speedUp(10);
          bike2.setGear(3);
          bike2.printStates();

          // Create Drivable object
          RoadBike bike3 = new RoadBike(5, 22, 6, 135, 50);
          bike3.turnLeft(5);
          bike3.setGear(5);
          bike3.printStates();
     }
}
