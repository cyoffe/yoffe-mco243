package yoffe.deadlock;

public class Fork {
	
	private int number;
	private boolean inUse;


	public Fork(int number) {
		this.number = number;
		this.inUse = false;
	}
	
	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}
	
	public boolean isInUse(){
		return inUse;
	}

	@Override
	public String toString() {
		return "Fork [number=" + number + "]";
	}
	
}
