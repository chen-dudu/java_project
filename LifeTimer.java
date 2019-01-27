
/**
 * Timer class for extra life object
 * handles all time-related updating, reseting
 * relevant to extra life object
 */
public class LifeTimer {

	// duration of time between each move extra life object makes
	private int interval;
	// duration of time extra life disappears
	private int disappear;
	// duration of time extra life appears
	private int appear;
	// time count for extra life's life cycle
	private int ticktack;
	// time count for extra life's move
	private int moveTick;
	// a variable indicating if a new cycle begins
	private boolean newCycle;
	
	/**
	 * Create a new life timer object
	 * @param appear Duration of time extra life appears
	 * @param disappear Duration of time extra life disappears
	 * @param interval Duration of time between each move extra life object makes
	 */
	public LifeTimer(int appear, int disappear, int interval) {
		this.appear = appear;
		this.disappear = disappear;
		this.interval = interval;
		ticktack = disappear;
		moveTick = interval;
		newCycle = false;
	}
	
	/**
	 * Update the status of extra life object
	 * @param visible The current status of extra life object
	 * @param delta Time passed since last frame (milliseconds)
	 * @return New status of extra life object
	 */
	public boolean update(boolean visible, int delta) {
		if((ticktack -= delta) <= 0) {
			// count reaches zero, time to change status
			if(visible) {
				ticktack = disappear;
				// disappear again, indicating a new cycle begins
				newCycle = true;
			}
			else {
				ticktack = appear;
			}
			return !visible;
		}
		return visible;
	}
	
	/**
	 * Update the moving status of extra life object
	 * @param moveNow The current moving status of extra life object
	 * @param delta Time passed since last frame (milliseconds)
	 * @return New moving status of extra life object
	 */
	public boolean moveUpdata(boolean moveNow, int delta) {
		if((moveTick -= delta) <= 0) {
			// count reaches zero, time to change status
			moveTick = interval;
			return !moveNow;
		}
		return moveNow;
	}
	
	/**
	 * Reset both life cycle count and moving count
	 * @param disappear Duration of time extra life disappears
	 */
	public void reset(int disappear) {
		this.disappear = disappear;
		ticktack = disappear;
		moveTick = interval;
	}
	
	/**
	 * Get the new cycle flag indicating if a new cycle should begin
	 * @return A flag indicating if a new cycle should begin
	 */
	public boolean newCycle() { return newCycle; }
	
	/** Reset the new cycle flag */
	public void resetCycle() { newCycle = false; }
}
