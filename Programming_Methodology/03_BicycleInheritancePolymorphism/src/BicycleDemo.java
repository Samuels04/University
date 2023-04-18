/**
 * Simple example using polymorphism and downcasting
 * 
 */

class BicycleDemo {
	public static void main(String[] args){
	    Bicycle bike01, bike02, bike03, bike04, bike05, bike06;
	    
	    bike01 = new Bicycle(20, 10, 1);
		bike02 = new MountainBike(20, 10, 5, "Dual");
		bike03 = new RoadBike(40, 20, 8, 23);
		bike04 = new MountainBike(25, 12,7,"Single");
		bike05 = new RoadBike(37, 23, 2, 27);
		bike06 = new Bicycle(25, 16, 2);
        
		// JVM calls the appropriate method for the referenced object
		// and not the method for the variable type
		//bike01.printStates();
		//bike02.printStates();
		//bike03.printStates();
		
		Bicycle [] vectorBikes = {bike01, bike02, bike03, bike04, bike05, bike06};
		
		for (int i = 0; i < vectorBikes.length; i++){
			System.out.format("%nBicycle [%d]:%n",i);
			vectorBikes[i].printStates();
		}
		
		// To use the specific methods for each class
		// it is necessary to do downcast
		
		//MountainBike m2 = (MountainBike) bike02;
		//m2.setSuspension("Mono");
		((MountainBike)bike02).setSuspension("Mono");
		
		for (int i = 0; i < vectorBikes.length; i++){
			System.out.format("%nBicycle [%d]:%n",i);
			vectorBikes[i].printStates();
		}

		bicyclesInfo(vectorBikes);

	}
	/**
	 * Iterates over a vector of Bicycles and shows the info 
	 * corresponding to each sort of bike
	 * @param bikes
	 */
	public static void bicyclesInfo(Bicycle[] bikes){
		for(int i = 0; i< bikes.length; i++){
			//if it's a mountain bike, print the type of suspension
			if(bikes[i] instanceof MountainBike){
				System.out.println(((MountainBike)bikes[i]).getSuspension());
			}

			//if it's a road bike, print the tyre width
			if(bikes[i] instanceof RoadBike){
				System.out.println(((RoadBike)bikes[i]).getTireWidth());
			}
			//if it's a bike, print the tyre width
			if(bikes[i] instanceof Bicycle){
				bikes[i].printStates();
			}
		}
	}
}



