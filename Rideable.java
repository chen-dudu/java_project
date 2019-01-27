
public interface Rideable {

	/**
	 * Ride on the object other
	 * @param other The object being ridden on
	 * @param delta Time passed since last frame (milliseconds)
	 */
	void ride(Sprite other, int delta);
}
