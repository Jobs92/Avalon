package campaigns;

import departments.Department;

public abstract class Campaign {
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
}
