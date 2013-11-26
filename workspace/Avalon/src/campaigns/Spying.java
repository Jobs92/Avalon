package campaigns;

import departments.Research;

public class Spying extends ResearchCampaign {
	int spiedPlayer;

	public Spying(Research research) {
		super(research);
		// TODO Auto-generated constructor stub
	}

	public Spying(Research research, int spiedPlayer) {
		super(research);
		this.spiedPlayer = spiedPlayer;
	}

	public int getSpiedPlayer() {
		return spiedPlayer;
	}

	public void setSpiedPlayer(int spiedPlayer) {
		this.spiedPlayer = spiedPlayer;
	}

}
