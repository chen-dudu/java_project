import org.newdawn.slick.Input;

/**
 * Turtle in the game
 * handles update and render for turtle object
 */
public class Turtle extends RideObject {

	/** path of icon */
	public static final String PATH = "assets/turtles.png";
	/** rate at which turtle moves, pixels per ms */
	public static final float RATE = 0.085f;
	/** constant indicating object being solid or not */
	public static final boolean IS_SOLID = false;
	/** duration of time turtle is visible, ms */
	public static final int VISIBLE = 7000;
	/** duration of time turtle is invisible, ms */
	public static final int INVISIBLE = 2000;
	
	// variable indicating whether turtle is on the surface of water or not
	private boolean surface;
	private TurtleTimer timer;
	
	/**
	 * Creates a new turtle object
	 * @param x The x coordinate of the object
	 * @param y The y coordinate of the object
	 * @param moveRight The moving direction of object
	 */
	public Turtle(float x, float y, boolean moveRight) {
		super(PATH, x, y, RATE, moveRight, IS_SOLID);
		surface = true;
		timer = new TurtleTimer(VISIBLE, INVISIBLE);
	}
	
	@Override
	public void update(Input input, int delta) {
		if(getDirection()) {
			if (getX() < App.SCREEN_WIDTH + getWidth()/2) {
				setX(getX() + RATE*delta);
			}
			else {
				setX(-(getWidth()/2));
			}
		}
		else {
			if (getX() > -getWidth()/2) {
				setX(getX() - RATE*delta);
			}
			else {
				setX(App.SCREEN_WIDTH + getWidth()/2);
			}
		}
		// update boundingbox
		getBox().update(getX(), getY());
		// update turtle's timer
		surface = timer.update(surface, delta);
	}
	
	@Override
	public void render() {
		// render turtle if it is on the surface
		if(surface) {super.render();}
	}
	
	/**
	 * Get the status of turtle (being on the surface of water or not)
	 * @return The status of turtle (being on the surface of water or not)
	 */
	public boolean getStatus() { return surface; }
}
