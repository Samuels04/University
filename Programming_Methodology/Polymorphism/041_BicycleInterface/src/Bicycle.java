/* 
 * Class Bicycle, implements the interface Drivable
 *
 **/

abstract class Bicycle {

    int cadence = 0;
    int speed = 0;
    int gear = 1;
    int direction = 0; // 0 - 359

    /**
     * Constructor with the 3 main parameters
     * 
     * @param startCadence The initial cadence
     * @param startSpeed   The initial speed
     * @param startGear    The initial gear
     */
    public Bicycle(int startCadence, int startSpeed, int startGear, int startDirection) {
        this.setCadence(startCadence);
        this.setSpeed(startSpeed);
        this.setGear(startGear);
        this.setDirection(startDirection);
    }

    /**
     * Constructor with no parameters
     */
    public Bicycle() {
        this.setCadence(0);
        this.setGear(1);
        this.setSpeed(0);
    }

    public void setCadence(int newValue) {
        cadence = newValue;
    }

    public int getCadence() {
        return cadence;
    }

    public void setGear(int newValue) {
        gear = newValue;
    }

    public int getGear() {
        return gear;
    }

    public void setSpeed(int newValue) {
        speed = newValue;
    }

    public int getSpeed() {
        return speed;
    }

    public void setDirection(int newValue) {
        direction = newValue;
    }

    public int getDirection() {
        return direction;
    }

    void printStates() {
        System.out.println(" -- Bicycle -- ");
        System.out.println("cadence:" + cadence + " speed:" + speed + " gear:" + gear + " direction:" + direction);
    }
}
