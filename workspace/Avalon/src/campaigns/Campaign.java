package campaigns;

import departments.Department;

public abstract class Campaign {
	public static final int MARKETING = 0;
	public static final int REASEARCH = 1;
	public static final int SPYING = 2;
	
	private String description;
	private String title;
	private int successProbability;
	private int cost;
	private int duration;
	private int level;
	private boolean active;
	private Department department;

	public String getDescription() {
		return description;
	}

	public String getTitle() {
		return title;
	}

	public int getCost() {
		return cost;
	}

	public int getDuration() {
		return duration;
	}

	public int getLevel() {
		return level;
	}

	public boolean isActive() {
		return active;
	}

	public Department getDepartment() {
		return department;
	}

	public int getSuccessProbability() {
		return successProbability;
	}

	public Campaign(Department department, String title, int cost,
			int duration, int successProbability, int level, String description) {
		super();
		this.department = department;
		this.successProbability = successProbability;
		this.title = title;
		this.description = description;
		this.cost = cost;
		this.duration = duration;
		this.level = level;
		this.active = true;
	}
	
	public abstract ExplicitCampaign startCampaign();
}





