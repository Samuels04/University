/**
 * Class Bicycle with visibility public and private
 * and the constructor
 * 
 */


class Bicycle {

       private int cadence = 0;
       private int speed = 0;
       private int gear = 1;
       
    // Constructors
       public Bicycle() {
       }
       
       public Bicycle(int startCadence, int startSpeed, int startGear) {
           gear = startGear;
           cadence = startCadence;
           speed = startSpeed;
       }
       
    // Getters
       public int getCadence() {
    	   return cadence;
   	   }
   		
   	   public int getGear() {
   		   return gear;
   	   }
    
    // Setters
       public void setCadence(int newValue) {
           cadence = newValue;
       }
   	
       public void setGear(int newValue) {
           gear = newValue;
       }
   	
       public void applyBrake(int decrement) {
           speed -= decrement;
       }
   	
       public void speedUp(int increment) {
           speed += increment;
       }
       
    // Other observer
       public void printStates() {
    	    System.out.println(" -- Bicycle -- ");
            System.out.println("cadence:"+cadence+" speed:"+speed+" gear:"+gear);
       }
}

