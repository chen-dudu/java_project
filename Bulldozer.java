/**
 * The bulldozer class in the game
 * handles contacting for bulldozers
 */
public class Bulldozer extends Vehicle implements Pushable {
	
	/** path of icon */
	public static final String PATH = "assets/bulldozer.png";
	/** rate at which bulldozer moves, pixels per ms*/
	public static final float RATE = 0.05f;
	/** constant indicating object being solid or not */
	public static final boolean IS_SOLID = true;
	
	/**
	 * Creates a new bulldozer object at the specified position
	 * @param x The x coordinate of the object
	 * @param y The y coordinate of the object
	 * @param moveRight The moving direction of object
	 */
	public Bulldozer(float x, float y, boolean moveRight) {
		super(PATH, x, y, RATE, moveRight, IS_SOLID);
	}
	
	@Override
	public void contactSprite(Sprite other, int delta) {
		// has contact with player
		push(other, delta);
	}
	
	@Override
	public void push(Sprite other, int delta) {
		Player player = (Player)(other);
		if(player.onScreen(player.getX(), player.getY())) {
			if(getDirection()) {
				if (player.getX() < App.SCREEN_WIDTH - player.getWidth()/2) {
					player.setX(player.getX() + RATE*delta);
				}
			}
			else {
				if (player.getX() > player.getWidth()/2) {
					player.setX(player.getX() - RATE*delta);
				}
			}
		}
		// player is pushed out of the screen
		else {
			player.reset();
			player.loseLive();
		}
		// update boundingbox
		player.getBox().update(player.getX(), player.getY());
	}
}
