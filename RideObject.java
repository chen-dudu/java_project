import org.newdawn.slick.Input;

/**
 * The ride object class in the game
 * handles update and contact for all ride objects
 */
public abstract class RideObject extends Sprite implements Rideable {

	// true if vehicle is moving to the right
	private boolean direction;
	// rate at which the ride object moves, pixels per ms
	private float rate;
	
	/**
	 * Creates a new ride object object at the specified position
	 * @param imageSrc The path of image file
	 * @param x The x coordinate of the object
	 * @param y The y coordinate of the object
	 * @param rate The rate at which the object is moving
	 * @param direction The moving direction of object
	 * @param isSolid A variable indicating whether the object is solid or not
	 */
	public RideObject (String imageSrc, float x, float y, float rate, boolean direction, boolean isSolid) {
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
			if (getX() > - getWidth()/2) {
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
		ride(other, delta);
	}
	
	@Override
	public void ride(Sprite other, int delta) {
		Player player = (Player)(other);
		if(getDirection()) {
			if (player.getX() < App.SCREEN_WIDTH - player.getWidth()/2) {
				player.setX(player.getX() + rate*delta);
			}
			// nothing happen if player is going to be out of the screen
			// i.e. stay at the edge of screen
		}
		else {
			if (player.getX() > player.getWidth()/2) {
				player.setX(player.getX() - rate*delta);
			}
			// nothing happen if player is going to be out of the screen
			// i.e. stay at the edge of screen
		}
		// update player's bounding box
		player.getBox().update(player.getX(), player.getY());
	}
	
	/**
	 * Get the moving direction of ride object
	 * @return The moving direction of ride object
	 */
	public boolean getDirection() { return direction; }
	
	/**
	 * Get the rate at which ride object moves
	 * @return The rate at which ride object moves
	 */
	public float getRate() { return rate; }
}
