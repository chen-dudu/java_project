import org.newdawn.slick.Input;

/**
 * The bike class in the game
 * handles updating for bikes
 */
public class Bike extends Vehicle {

	/** path of icon */
	public static final String PATH = "assets/bike.png";
	/** rate at which bike moves, pixels per ms */
	public static final float RATE = 0.2f;
	/** constant indicating object being solid or not */
	public static final boolean IS_SOLID = false;
	/** range in which bike can move */
	public static final float[] BOUND = {24, 1000};
	
	/**
	 * Creates a new bike object at the specified position
	 * @param x The x coordinate of the object
	 * @param y The y coordinate of the object
	 * @param moveRight The moving direction of object
	 */
	public Bike(float x, float y, boolean moveRight) {
		super(PATH, x, y, RATE, moveRight, IS_SOLID);
	}
	
	@Override
	public void update(Input input, int delta) {
		if(getDirection()) {
			if (getX() < BOUND[1]) {
				setX(getX() + RATE*delta);
			}
			// reach boundary, turn around
			else { setDirection(!getDirection()); }
		}
		else {
			if (getX() > BOUND[0]) {
				setX(getX() - RATE*delta);
			}
			// reach boundary, turn around
			else { setDirection(!getDirection()); }
		}
		// update boundingbox
		getBox().update(getX(), getY());
	}
}
