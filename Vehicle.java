import org.newdawn.slick.Input;

/**
 * The vehicle class in the game
 * handles update and contact for all vehicle objects
 */
public class Vehicle extends Sprite implements Hitable {

	// true if vehicle is moving to the right
	private boolean direction;
	// rate at which the vehicle moves, pixels per ms
	private float rate;
	
	/**
	 * Creates a new vehicle object object at the specified position
	 * @param imageSrc The path of image file
	 * @param x The x coordinate of the object
	 * @param y The y coordinate of the object
	 * @param rate The rate at which the object is moving
	 * @param direction The moving direction of object
	 * @param isSolid A variable indicating whether the object is solid or not
	 */
	public Vehicle (String imageSrc, float x, float y, float rate, boolean direction, boolean isSolid) {
		super(imageSrc, x, y, isSolid);
		this.direction = direction;
		this.rate = rate;
	}
	
	@Override
	public void update(Input input, int delta) {
		if(getDirection()) {
			if (getX() < App.SCREEN_WIDTH + getWidth()/2) {
				setX(getX() + rate*delta);
			}
			// reappear on the other side of screen
			else { setX(-(getWidth()/2)); }
		}
		else {
			if (getX() > -(getWidth()/2)) {
				setX(getX() - rate*delta);
			}
			// reappear on the other side of screen
			else { setX(App.SCREEN_WIDTH + getWidth()/2); }
		}
		// update bounding box
		getBox().update(getX(), getY());
	}
	
	@Override
	public void contactSprite(Sprite other, int delta) {
		hit(other);
	}
	
	@Override
	public void hit(Sprite other) {
		Player player = (Player)(other);
		player.loseLive();
		player.reset();
	}
	
	/**
	 * Get the moving direction of vehicle
	 * @return The moving direction of vehicle
	 */
	public boolean getDirection() { return this.direction; }
	
	/**
	 * Change the moving direction of vehicle
	 * @param newDirection The new moving direction to be set for vehicle
	 */
	public void setDirection(boolean newDirection) { this.direction = newDirection; }
}
