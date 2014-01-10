package utils;

public class Incident {
	private String title;
	private String description;
	private int target; // if everyone: -1
	private int probability;
	private IncidentType type;

	public Incident(String title, String description, int target,
			int probability) {
		super();
		this.title = title;
		this.description = description;
		this.target = target;
		this.probability = probability;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getProbability() {
		return probability;
	}

	public void setProbability(int probability) {
		this.probability = probability;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
