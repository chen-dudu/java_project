
/**
 * The log class in the game
 */
public class Log extends RideObject {
	
	/** path of icon */
	public static final String PATH = "assets/log.png";
	/** rate at which log moves, pixels per ms*/
	public static final float RATE = 0.1f;
	/** constant indicating object being solid or not */
	public static final boolean IS_SOLID = false;
	
	/**
	 * Creates a new log object at the specified position
	 * @param x The x coordinate of the object
	 * @param y The y coordinate of the object
	 * @param moveRight The moving direction of object
	 */
	public Log(float x, float y, boolean right) {
		super(PATH, x, y, RATE,right, IS_SOLID);
	}
}
