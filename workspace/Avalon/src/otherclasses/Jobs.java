package otherclasses;
		

public class Jobs {
	private int level;
	private int amount;
	private boolean completed;
	
	
	public Jobs(int level, int amount){
		this.level=level;
		this.amount=amount;
		this.completed=false;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
