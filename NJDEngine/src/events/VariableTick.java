package events;

public abstract class VariableTick {

	protected int tickTiming = 1000;
	
	public abstract void tick();
	
	public int getTickTiming() {
		return tickTiming;
	}

}
