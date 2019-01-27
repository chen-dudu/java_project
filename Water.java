import org.newdawn.slick.Input;

/**
 * The water class in the game
 * handles contact for all water objects
 */
public class Water extends Sprite implements Hitable {
	
	/** path of icon */
	public static final String PATH = "assets/water.png";
	/** constant indicating object being solid or not */
	public static final boolean IS_SOLID = false;
	
	/**
	 * Creates a new water object at the specified position
	 * @param x The x coordinate of the object
	 * @param y The y coordinate of the object
	 */
	public Water(float x, float y) {
		super(PATH, x, y, IS_SOLID);
	}
	
	@Override
	public void update(Input input, int delta) {}
	
	@Override
	public void contactSprite(Sprite other, int delta) {
		// has contact with player
		hit(other);
	}
	
	@Override
	public void hit(Sprite other) {
		Player player = (Player)(other);
		player.reset();
		player.loseLive();
	}
}
