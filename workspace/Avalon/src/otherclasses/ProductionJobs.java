package otherclasses;
		

public class ProductionJobs {
	private int level;
	private int amount;
	private boolean completed;
	
	public ProductionJobs (int level, int amount){
		this.level = level;
		this.amount = amount;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
