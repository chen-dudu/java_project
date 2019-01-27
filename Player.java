import java.util.ArrayList;
import org.newdawn.slick.Input;
import utilities.BoundingBox;

/**
 * The player class in the game
 * handles everything related to player
 */
public class Player extends Sprite {

	/** starting x coordinate of player */
	public static final float PLAYER_START_X = 512;
	/** starting y coordinate of player */
	public static final float PLAYER_START_Y = 720;
	/** start x coordinate for live icon */
	public static final float LIVEX = 24;
	/** start y coordinate for live icon */
	public static final float LIVEY = 744;
	/** separation between live icons */
	public static final int SEP = 32;
	/** constant indicating object being solid or not */
	public static final boolean IS_SOLID = false;
	
	// some constants used in the class
	private final int LEFT = 1;
	private final int UP = 2;
	private final int RIGHT = 3;
	private final int DOWN = 4;
	
	// initial x coordinate of player
	private float initX;
	// initial y coordinate of player
	private float initY;
	// an arraylist containing player's lives
	private ArrayList<Life> lives = new ArrayList<>();
	// number of times player successfully reach destination
	private int success;
	
	/**
	 * Create a new player object
	 * @param imageSrc The path of image file
	 * @param numOfLive The number of lives player has
	 */
	public Player(String imageSrc, int numOfLive) {
		super(imageSrc, PLAYER_START_X, PLAYER_START_Y, IS_SOLID);
		success = 0;
		initX = getX();
		initY = getY();
		try {
			for(int i = 0; i < numOfLive; i++) {
				lives.add(new Life(LIVEX + i*SEP, LIVEY));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Input input, int delta) {}
	
	/**
	 * Update player object
	 * @param input The Slick input object
	 * @param delta Time passed since last frame (milliseconds)
	 * @param others An arraylist of game objects
	 */
	public void update(Input input, int delta, ArrayList<Sprite> others) {
		if(input.isKeyPressed(Input.KEY_UP) && getY() > getHeight()) {
			if(canMove(others, UP)) {
				setY(getY() - getHeight());
			}
		}
		if(input.isKeyPressed(Input.KEY_DOWN) && getY() + getHeight() < App.SCREEN_HEIGHT) {
			if(canMove(others, DOWN)) {
				setY(getY() + getHeight());
			}
		}
		if(input.isKeyPressed(Input.KEY_LEFT) && getX() > getWidth()) {
			if(canMove(others, LEFT)) {
				setX(getX() - getWidth());
			}
		}
		if(input.isKeyPressed(Input.KEY_RIGHT) && getX() + getWidth()  < App.SCREEN_WIDTH) {
			if(canMove(others, RIGHT)) {
				setX(getX() + getWidth());
			}
		}
		getBox().update(getX(), getY());
	}
	
	@Override
	public void render() {
		// render player and lives
		super.render();
		for(Life next : lives) {
			next.render();
		}
	}
	
	@Override
	public void contactSprite(Sprite other, int delta) {}
	
	/**
	 * Get the number of times player successfully reaches the destination
	 * @return The number of times player successfully reaches the destination
	 */
	public int getSuccess() { return success; }
	
	/**
	 * Decide if the player has successfully reached destination
	 * @param end The y coordinate of destination
	 * @return A flag indicating if player has successfully reached destination
	 */
	public boolean reachDestination(float end) {
		boolean output = (Float.compare(getY(), end) == 0);
		if(output) {
			success++;
		}
		return output;
	}
	
	/**
	 * Get the index of hole that should be filled in
	 * @return The index of hole that should be filled in
	 */
	public int holeBelongTo() {
		for(int i = 0; i < World.END_X.length; i++) {
			if(Math.abs(getX() - World.END_X[i]) < getWidth()) {
				return i;
			}
		}
		// hole not found
		return -1;
	}
	
	/** 
	 * Reset the position of the player 
	 * when hit by hitable object in the game
	 */
	public void reset() {
		setX(initX);
		setY(initY);
		getBox().update(getX(), getY());
	}
	
	/** 
	 * Reset the position and lives of the player 
	 * when the game proceeds to a new level
	 * @param numOfLives An integer indicating 
	 * the number of lives the player will have after resetting
	 */
	public void reset(int numOfLives) {
		reset();
		resetLive(numOfLives);
		success = 0;
	}
	
	/** 
	 * Reset the lives of player 
	 * @param numOfLives An integer indicating number of lives the player will have after resetting
	 */
	public void resetLive(int numOfLives) {
		int i;
		if(lives.size() < World.INIT_LIVE) {
			for(i = lives.size(); i < numOfLives; i++) {
				try {
					gainLive();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else if(lives.size() > World.INIT_LIVE) {
			for(i = lives.size(); i > numOfLives; i--) {
				loseLive();
			}
		}
	}
	
	/** 
	 * Get the number of lives player has
	 * @return The number of lives the player currently has 
	 */
	public int getLive() { return lives.size() + 1; }
	
	/** Decrease the player's life by one */
	public void loseLive() {
		try {
			lives.remove(lives.size() - 1);
		} catch (Exception e) {
			// no life to lose, game over
			System.exit(0);
		}
	}
	
	/** Increase the player's life by one */
	public void gainLive() {
		try {
			Life newLife;
			if(lives.isEmpty()) {
				newLife = new Life(LIVEX, LIVEY);
			}
			else {
				Life currLive = lives.get(lives.size() - 1);
				newLife = new Life(currLive.getX() + SEP, currLive.getY());
			}
			lives.add(newLife);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Check if the player is within the boundaries of the screen
	 * Taken from project 1 sample solution
	 * Wiritten by by Eleanor McMurtry, University of Melbourne
	 * @param x The x coordinate of player
	 * @param y The y coordinate of player
	 * @return True if the player is on the screen
	 */
	public boolean onScreen(float x, float y) {
		return !(x + getWidth() / 2 > App.SCREEN_WIDTH || x - getWidth() / 2 < 0
			 || y + getHeight() / 2 > App.SCREEN_HEIGHT || y - getHeight() / 2 < 0);
	}
	
	// decide if the player can make a move in the direction indicated by direction
	// 1 for left, 2 for up, 3 for right, 4 for down
	// return true if it's ok for player to move, and vice versa
	private boolean canMove(ArrayList<Sprite> others, int direction) {
		// create a new bounding box object for detection
		BoundingBox scout = null;
		switch(direction) {
		case LEFT:
			scout = new BoundingBox(getX() - getWidth(), getY(), getWidth(), getHeight(), false);
			break;
		case UP:
			scout = new BoundingBox(getX(), getY() - getHeight(), getWidth(), getHeight(), false);
			break;
		case RIGHT:
			scout = new BoundingBox(getX() + getWidth(), getY(), getWidth(), getHeight(), false);
			break;
		case DOWN:
			scout = new BoundingBox(getX(), getY() + getHeight(), getWidth(), getHeight(), false);
			break;
		}
		// check if making the move will result in a contact with solid object
		for(Sprite next : others) {
			if(next.getBox().getProperty() && scout.intersects(next.getBox())) {
				// will move into solid object, move request denied
				return false;
			}
		}
		// all good, move request approved
		return true;
	}
}
