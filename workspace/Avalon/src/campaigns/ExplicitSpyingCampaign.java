package campaigns;

public class ExplicitSpyingCampaign extends ExplicitResearchCampaign {
	private int spiedPlayer;

	public ExplicitSpyingCampaign(int spiedPlayer, SpyingCampaign campaign) {
		super(campaign);
		this.spiedPlayer = spiedPlayer;
	}

	public int getSpiedPlayer() {
		return spiedPlayer;
	}

	@Override
	protected void campaignFailed() {
		// TODO Auto-generated method stub

	}

}
