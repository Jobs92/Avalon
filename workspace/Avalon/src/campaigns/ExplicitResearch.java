package campaigns;


public class ExplicitResearch {
	int startRound;
	int endRound;
	ResearchCampaign researchCampaign;
	
	public ExplicitResearch(int startRound, int endRound,
			ResearchCampaign researchCampaign) {
		super();
		this.startRound = startRound;
		this.endRound = endRound;
		this.researchCampaign = researchCampaign;
	}
	public int getStartRound() {
		return startRound;
	}
	public void setStartRound(int startRound) {
		this.startRound = startRound;
	}
	public int getEndRound() {
		return endRound;
	}
	public void setEndRound(int endRound) {
		this.endRound = endRound;
	}
	
}
