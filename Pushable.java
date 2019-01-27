
public interface Pushable {

	/**
	 * Push other object in the direction this moves
	 * @param other Object to be pushed
	 * @param delta Time passed since last frame (milliseconds)
	 */
	void push(Sprite other, int delta);
}
