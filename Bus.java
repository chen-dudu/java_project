/**
 * The bus class in the game
 */
public class Bus extends Vehicle {
	
	/** path of icon */
	public static final String PATH = "assets/bus.png";
	/** rate at which bus moves, pixels per ms*/
	public static final float RATE = 0.15f;
	/** constant indicating object being solid or not */
	public static final boolean IS_SOLID = false;
	
	/**
	 * Creates a new bus object at the specified position
	 * @param x The x coordinate of the object
	 * @param y The y coordinate of the object
	 * @param moveRight The moving direction of object
	 */
	public Bus(float x, float y, boolean moveRight) {
		super(PATH, x, y, RATE, moveRight, IS_SOLID);
	}
}