import org.newdawn.slick.Input;

/**
 * The tree class in the game
 */
public class Tree extends Sprite {
	
	/** path of icon */
	public static final String PATH = "assets/tree.png";
	/** constant indicating object being solid or not */
	public static final boolean IS_SOLID = true;
	
	/**
	 * Creates a new tree object at the specified position
	 * @param x The x coordinate of the object
	 * @param y The y coordinate of the object
	 */
	public Tree(float x, float y) {
		super(PATH, x, y, IS_SOLID);
	}
	
	@Override
	public void update(Input input, int delta) {}
	
	@Override
	public void contactSprite(Sprite other, int delta) {}
}
