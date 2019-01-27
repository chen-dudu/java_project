import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The world class in the game
 * handles creating, update and rendering for game objects
 */
public class World {
	
	/** prefix of the file name */
	public static final String PREFIX = "assets/levels/";
	/** suffix of the file name */
	public static final String SUFFIX = ".lvl";
	
	/** x coordinate of holes */
	public static final float[] END_X = new float[] {120, 312, 504, 696, 888};
	/** y coordinate of holes */
	public static final float END_Y = 48;
	/** number of lives player has at the beginning of each level */
	public static final int INIT_LIVE = 3;
	
	// an arraylist containing all game objects
	private ArrayList<Sprite> elements;
	
	// an arraylist containing all log objects
	private ArrayList<Sprite> logs = new ArrayList<>();
	
	// an arraylist containing objects used to fill in the holes
	private ArrayList<Sprite> filling = new ArrayList<>();
	
	private Player player;
	private ExtraLife extraLife;
	private Factory factory;
	
	// level player is currently at
	private int atLevel;
	
	// random number generator
	private Random random = new Random();
	
	// a random number generated from the generator
	// indicating which log to be used for extra life
	private int randNum;
	
	/**
	 * Create a new world object
	 * @throws SlickException
	 */
	public World() throws SlickException {
		atLevel = 1;
		factory = new Factory(PREFIX + atLevel + SUFFIX);
		elements = factory.getElements();
		
		for(Sprite next : elements) {
			if(next.getClass().getSimpleName().equals("Log") || 
			   next.getClass().getSimpleName().equals("LongLog")) {
				logs.add(next);
			}
		}
		// choose a log randomly for extra life object
		randNum = random.nextInt(logs.size());
		extraLife = new ExtraLife(logs.get(randNum).getX(), 
				                  logs.get(randNum).getY());
		player = new Player(Frog.PATH, INIT_LIVE);
	}
	
	/**
	 * Update all game objects
	 * @param input The Slick input object
	 * @param delta Time passed since last frame (milliseconds)
	 * @throws SlickException
	 */
	public void update(Input input, int delta) throws SlickException {
		
		player.update(input, delta, elements);
		extraLife.update(delta, logs.get(randNum));
		
		if(extraLife.newCycle()) {
			// choose a new log for a new cycle
			randNum = random.nextInt(logs.size());
			extraLife.reset();
		}
		
		if(extraLife.getBox().intersects(player.getBox())) {
			// extra life has contact with player
			extraLife.contactSprite(player, delta);
			// choose a new log for a new cycle
			randNum = random.nextInt(logs.size());
		}
		
		// flags indicating if player has contact with water/ride object
		boolean onWater = false, onRide = false;
		Sprite water = null, ride = null;
		
		for(Sprite next : elements) {
			next.update(input, delta);
			next.getBox().update(next.getX(), next.getY());
			
			if (next.getBox().intersects(player.getBox())) {
				if(next.getClass().getSimpleName().equals("Water")) {
					onWater = true;
					water = next;
				}
				else if(next.getClass().getSimpleName().equals("Log") || 
				        next.getClass().getSimpleName().equals("LongLog")) {
					onRide = true;
					ride = next;
				}
				else if(next.getClass().getSimpleName().equals("Turtle")) {
					Turtle turtle = (Turtle)next;
					if(turtle.getStatus()) {
						// turtle is on the surface of water
						// can give support to player
						onRide = true;
						ride = turtle;
					}
				}
				else {
					next.contactSprite(player, delta);
				}
			}
		}
		
		// check if player has contact with frogs in the holes
		for(Sprite next : filling) {
			if(next.getBox().intersects(player.getBox())) {
				next.contactSprite(player, delta);
			}
		}

		if(onWater && onRide) {
			// player on the logs/turtle, safe
			ride.contactSprite(player, delta);
		} else if(onWater && !onRide) {
			// player on the water, lose a life
			water.contactSprite(player, delta);
		}
		
		// player has reached one of the holes
		if(player.reachDestination(END_Y)) {
			filling.add(new Frog(END_X[player.holeBelongTo()], END_Y));
			player.reset();
		}
		
		// all five holes has been filled, move on to next level
		if(player.getSuccess() == END_X.length) {
			atLevel++;
			player.reset(INIT_LIVE);
			factory.update(PREFIX + atLevel + SUFFIX);
			elements.clear();
			elements = factory.getElements();
			logs.clear();
			for(Sprite next : elements) {
				if(next.getClass().getSimpleName().equals("Log") || 
				   next.getClass().getSimpleName().equals("LongLog")) {
					logs.add(next);
				}
			}
			// choose a new log for a new cycle
			randNum = random.nextInt(logs.size());
			extraLife.reset();
			filling.clear();
		}
		
		// no live left, game over
		if(player.getLive() == 0) {
			System.exit(0);
		}
	}
	
	/**
	 * Render all game objects
	 * @param g The Slick graphics object
	 */
	public void render(Graphics g) {
		for(Sprite next : elements) {
			next.render();
		}
		for(Sprite next : filling) {
			next.render();
		}
		extraLife.render();
		player.render();
	}
}