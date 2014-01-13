package campaigns;

import departments.Department;

public abstract class Campaign {
	public static final int MARKETING = 0;
	public static final int REASEARCH = 1;
	public static final int SPYING = 2;

	protected String description;
	protected String title;
	protected int successProbability;
	protected double cost;
	protected int duration;
	protected int level;
	protected Department department;

	public String getDescription() {
		return description;
	}

	public String getTitle() {
		return title;
	}

	public double getCost() {
		return cost;
	}

	public int getDuration() {
		return duration;
	}

	public int getLevel() {
		return level;
	}

	public Department getDepartment() {
		return department;
	}

	public int getSuccessProbability() {
		return successProbability;
	}

	public void updateProbability(int level) {
		successProbability = successProbability + level;
		// TODO: also update levels???
	}

	public Campaign(Department department, String title, double cost,
			int duration, int successProbability, int level, String description) {
		super();
		this.department = department;
		this.successProbability = successProbability;
		this.title = title;
		this.description = description;
		this.cost = cost;
		this.duration = duration;
		this.level = level;
	}

	public abstract ExplicitCampaign startCampaign();
}
