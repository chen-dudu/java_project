import org.newdawn.slick.Input;

/**
 * Life class in the game
 */
public class Life extends Sprite {

	/** path of icon */
	public static final String PATH = "assets/lives.png";
	/** constant indicating object being solid or not */
	public static final boolean IS_SOLID = false;
	
	/**
	 * Create a new life object at the specified position
	 * @param x The x coordinate of the object
	 * @param y The y coordinate of the object
	 */
	public Life(float x, float y) {
		super(PATH, x, y, IS_SOLID);
	}
	
	@Override
	public void update(Input input, int delta) {}
	
	@Override
	public void contactSprite(Sprite other, int delta) {}
}
