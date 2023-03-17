package jeroquest.items;
import jeroquest.utils.*;

public class Trap extends Item {
	private boolean activated;

	/**
	 * Constructor for the type trap
	 */
	public Trap(){
		this.setPosition(new Position(0,0));
	}

	/**
	 * Method to know if a trap is activated or not
	 * @return True if the trap is activated, false otherwise
	 */
	public boolean isActivated(){
		return this.activated;
	}

	/**
	 * Sets the activation status of the trap
	 * @param status The desired activation status of the trap
	 */
	public void setActivated(boolean status){
		this.activated = status;
	}
}
