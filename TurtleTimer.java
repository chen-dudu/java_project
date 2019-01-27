
/**
 * Timer class for turtle object
 * handles all time-related updating relevant to turtle object
 */
public class TurtleTimer {

	// duration of time extra life disappears
	private int disappear;
	//duration of time extra life appears
	private int appear;
	// time count
	private int ticktack;
	
	/**
	 * Create a new turtle timer object
	 * @param appear Duration of time turtle appears
	 * @param disappear Duration of time turtle disappears
	 */
	public TurtleTimer(int appear, int disappear) {
		this.disappear = disappear;
		this.appear = appear;
		ticktack = appear;
	}
	
	/**
	 * Update the status of turtle object
	 * @param visible The current status of turtle object
	 * @param delta Time passed since last frame (milliseconds)
	 * @return New status of turtle object
	 */
	public boolean update(boolean visible, int delta) {
		if((ticktack -= delta) <= 0) {
			// count reaches zero, time to change status
			ticktack = visible ? disappear : appear;
			return !visible;
		}
		return visible;
	}
}
