import java.util.Random;
import org.newdawn.slick.Input;
import utilities.BoundingBox;

/**
 * The extra life class in the game
 * handles everything related to extra life
 */
public class ExtraLife extends Sprite {

	/** path of icon */
	public static final String PATH = "assets/extralife.png";
	/** constant indicating object being solid or not */
	public static final boolean IS_SOLID = false;
	/** duration of time between each movement of extra live , in unit ms*/
	public static final int INTERVAL = 2000;
	/** duration of time extra live is visible, in unit ms*/
	public static final int VISIBLE = 14000;
	/** range of waiting of extra life */
	public static final int[] RANGE = new int[] {25000, 35000};
	
	// constant used in the class
	private final int RIGHT = 1;
	
	// random number generator
	private Random random = new Random();
	// duration of time extra live is invisible, in unit ms
	private int disappearTime;
	private LifeTimer timer;
	// indicating whether extra life is visible or not
	private boolean visible;
	// indicating whether extra life can make a move
	private boolean moveNow;
	// indicating the moving direction of extra life
	// 1 for moving right and -1 for moving left
	private int direction;
	
	/**
	 * Create an extra life object at the specified position
	 * @param x The x coordinate of the object
	 * @param y The y coordinate of the object
	 */
	public ExtraLife(float x, float y) {
		super(PATH, x, y, IS_SOLID);
		disappearTime = chooseTime();
		timer = new LifeTimer(VISIBLE, disappearTime, INTERVAL);
		visible = false;
		moveNow = false;
		direction = RIGHT;
	}
	
	@Override
	public void update(Input input, int delta) {}
	
	@Override
	public void contactSprite(Sprite other, int delta) {
		// cause no effect when invisible
		if(!visible) {
			return;
		}
		Player player = (Player)other;
		player.gainLive();
		reset();
	}
	
	/**
	 * Update extra life object
	 * @param delta Time passed since last frame (milliseconds)
	 * @param logs A log/longLog object
	 */
	public void update(int delta, Sprite logs) {
		
		moveTogether(delta, logs);
		
		visible = timer.update(visible, delta);
		if(visible) {
			moveNow = timer.moveUpdata(moveNow, delta);
			if(moveNow) {
				makeMove(delta, logs);
				moveNow = false;
			}
		}
	}
	
	/**
	 * Reset the extra life status
	 */
	public void reset() {
		visible = false;
		moveNow = false;
		direction = RIGHT;
		resetTimer();
		resetCycle();
	}
	
	@Override
	public void render() {
		if(visible) { super.render(); }
	}
	
	/**
	 * Get the new cycle flag indicating if a new cycle should begin
	 * @return A flag indicating if a new cycle should begin
	 */
	public boolean newCycle() { return timer.newCycle(); }
	
	/**
	 * Reset the new cycle flag
	 */
	public void resetCycle() { timer.resetCycle(); }
	
	// decide if extra life can keep moving in the current direction
	// if not, change direction and make a move
	private void makeMove(int delta, Sprite other) {
		BoundingBox scout = new BoundingBox(getX() + direction * getWidth(), getY(), 
				                            getWidth(), getHeight(), IS_SOLID);
		if(!scout.intersects(other.getBox())) {
			direction = -direction;
		}
		setX(getX() + direction * getWidth());
		getBox().update(getX(), getY());
	}
	
	// update the extra life coordinates so that it moves along with logs
	private void moveTogether(int delta, Sprite other) {
		RideObject logs = (RideObject)other;
		if(logs.getDirection()) {
			if (logs.getX() < App.SCREEN_WIDTH + logs.getWidth()/2) {
				setX(getX() + logs.getRate()*delta);
			}
			// reappear on the other side of screen
			else {
				setX(-logs.getWidth()/2);
			}
		}
		else {
			if (logs.getX() > -logs.getWidth()/2) {
				setX(getX() - logs.getRate()*delta);
			}
			// reappear on the other side of screen
			else {
				setX(App.SCREEN_WIDTH + logs.getWidth()/2);
			}
		}
		setY(logs.getY());
		getBox().update(getX(), getY());
	}
	
	// choose a random waiting time again and reset the timer
	private void resetTimer() {
		disappearTime = chooseTime();
		timer.reset(disappearTime);
	}
	
	// choose a random waiting time between specified range
	private int chooseTime() {
		return random.ints(RANGE[0], RANGE[1]).findFirst().getAsInt();
	}
}
