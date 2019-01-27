import org.newdawn.slick.Input;

/**
 * The grass class in the game
 */
public class Grass extends Sprite{
	
	/** path of icon */
	public static final String PATH = "assets/grass.png";
	/** constant indicating object being solid or not */
	public static final boolean IS_SOLID = false;
	
	/**
	 * Creates a new grass object at the specified position
	 * @param x The x coordinate of the object
	 * @param y The y coordinate of the object
	 */
	public Grass(float x, float y) {
		super(PATH, x, y, IS_SOLID);
	}
	
	@Override
	public void update(Input input, int delta) {}
	
	@Override
	public void contactSprite(Sprite other, int delta) {}
}
