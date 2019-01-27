
/**
 * The race car class in the game
 */
public class Racecar extends Vehicle {

	/** path of icon */
	public static final String PATH = "assets/racecar.png";
	/** rate at which race car moves, pixels per ms*/
	public static final float RATE = 0.5f;
	/** constant indicating object being solid or not */
	public static final boolean IS_SOLID = false;
	
	/**
	 * Creates a new race car object at the specified position
	 * @param x The x coordinate of the object
	 * @param y The y coordinate of the object
	 * @param moveRight The moving direction of object
	 */
	public Racecar(float x, float y, boolean moveRight) {
		super(PATH, x, y, RATE, moveRight, IS_SOLID);
	}
}
