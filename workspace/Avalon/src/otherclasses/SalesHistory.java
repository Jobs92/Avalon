package otherclasses;

public class SalesHistory {
	
	private int level;
	private int amount;
	private int round;
	
	public SalesHistory(int level, int amount, int round){
		this.level=level;
		this.amount=amount;
		this.round=round;
	}

	public int getLevel() {
		return level;
	}

	public int getAmount() {
		return amount;
	}

	public int getRound() {
		return round;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void updateAmount(int amount) {
		this.amount += amount;
	}

	public void setRound(int round) {
		this.round = round;
	}
	

}
