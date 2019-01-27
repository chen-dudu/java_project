import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import utilities.BoundingBox;

/**
 * Sprite class in the game
 * handles rendering for all sprites
 */
public abstract class Sprite {
	
	// x coordinate of sprite
	private float x;
	// y coordinate y of sprite
	private float y;
	// image object of sprite
	private Image sprite;
	// bounding box of sprite
	private BoundingBox box;
	
	/**
	 * Create a new sprite object at the specified position
	 * @param imageSrc The path of image file
	 * @param x The x coordinate of the object
	 * @param y y The y coordinate of the object
	 * @param isSolid A variable indicating whether the object is solid or not
	 */
	public Sprite(String imageSrc, float x, float y, boolean isSolid) {
		this.x = x;
		this.y = y;
		try {
			sprite = new Image(imageSrc);
			box = new BoundingBox(sprite, x, y, isSolid);
		} catch (Exception e) {
			//e.printStackTrace();
			System.exit(0);;
		}
	}
	
	/** Render sprite object */
	public void render() { sprite.drawCentered(x, y); }
	
	/**
	 * Update sprite object
	 * @param input The Slick input object 
	 * @param delta Time passed since last frame (milliseconds)
	 */
	public abstract void update(Input input, int delta);
	
	/**
	 * Have contact with other sprite
	 * @param other Object this has contact with
	 * @param delta Time passed since last frame (milliseconds)
	 */
	public abstract void contactSprite(Sprite other, int delta);
	
	/** 
	 * Get the x coordinate of sprite
	 * @return The x coordinate of sprite 
	 */
	public float getX() { return x; }
	
	/** 
	 * Get the y coordinate of sprite
	 * @return The y coordinate of sprite 
	 */
	public float getY() { return y; }
	
	/** 
	 * Get the width of sprite
	 * @return The width of sprite 
	 */
	public int getWidth() { return sprite.getWidth(); }
	
	/** 
	 * Get the heigh of sprite
	 * @return The height of sprite 
	 */
	public int getHeight() { return sprite.getHeight(); }
	
	/** 
	 * Get the bounding box of sprite
	 * @return The boundingbox of sprite 
	 */
	public BoundingBox getBox() { return box; }
	
	/**
	 * Change the x coordinate of sprite to the specified value
	 * Also update the boundingbox of the sprite
	 * @param newX The new x coordinate
	 */
	public void setX(float newX) {
		x = newX;
		box.setX(newX);
	}
	
	/**
	 * Change the y coordinate of sprite to the specified value
	 * Also update the boundingbox of the sprite
	 * @param newY The new y coordinate
	 */
	public void setY(float newY) {
		y = newY;
		box.setY(newY);
	}	
}