import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * The factory class in the game
 * handles creation of all game objects
 */
public class Factory {

	// an arraylist containing all objects in the game
	private ArrayList<Sprite> elements = new ArrayList<>();
	private String fileName;
	
	/**
	 * Create a new factory object
	 * @param fileName The file name from which factory will read
	 * information to create game objects
	 */
	public Factory(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * Create all game objects
	 * @return An array list containing all objects in the game
	 */
	public ArrayList<Sprite> getElements() {	
		try (BufferedReader br =
			new BufferedReader(new FileReader(fileName))) {
			String text;
			while ((text = br.readLine()) != null) {
				String[] words = text.split(",");
				
				float x = Float.parseFloat(words[1]);
				float y = Float.parseFloat(words[2]);
				boolean moveRight = false;
				if(words.length == 4) {
					moveRight = Boolean.parseBoolean(words[3]);
				}
				
				switch(words[0]) {
				case "water":
					elements.add(new Water(x, y));
					break;
				case "grass":
					elements.add(new Grass(x, y));
					break;
				case "tree":
					elements.add(new Tree(x, y));
					break;
				case "bus":
					elements.add(new Bus(x, y,moveRight));
					break;
				case "bulldozer":
					elements.add(new Bulldozer(x, y, moveRight));
					break;
				case "bike":
					elements.add(new Bike(x, y, moveRight));
					break;
				case "racecar":
					elements.add(new Racecar(x, y, moveRight));
					break;
				case "log":
					elements.add(new Log(x, y, moveRight));
					break;
				case "longLog":
					elements.add(new LongLog(x, y, moveRight));
					break;
				case "turtle":
					elements.add(new Turtle(x,  y, moveRight));
					break;
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.exit(0);
		}
		return elements;
	 }
	
	/**
	 * Update the name of file the class will read information from to create game objects
	 * @param newFileName New name of the file to read information from
	 */
	public void update(String newFileName) { fileName = newFileName; }
}
