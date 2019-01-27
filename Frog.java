import org.newdawn.slick.Input;

/**
 * The frog class in the game
 */
public class Frog extends Sprite implements Hitable {

	/** path of icon */
	public static final String PATH = "assets/frog.png";
	/** constant indicating object being solid or not */
	public static final boolean IS_SOLID = false;

	/**
	 * Create a new frog object in the specified position
	 * @param x The x coordinate of the object
	 * @param y The y coordinate of the object
	 */
	public Frog(float x, float y) {
		super(PATH, x, y, IS_SOLID);
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
	
	@Override
	public void update(Input input, int delta) {}
}
