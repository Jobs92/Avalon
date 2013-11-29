package campaigns;

import departments.Research;

public class ExplicitSpyingCampaign extends ExplicitResearchCampaign {
	private int spiedPlayer;
	private boolean isSued;

	public ExplicitSpyingCampaign(int spiedPlayer, SpyingCampaign campaign) {
		super(campaign);
		this.isSued = false;
		this.spiedPlayer = spiedPlayer;
	}

	public boolean isSued() {
		return isSued;
	}

	public void setSued(boolean isSued) {
		this.isSued = isSued;
	}

	public int getSpiedPlayer() {
		return spiedPlayer;
	}

	@Override
	protected void campaignFinishedSuccessfully() {
		Research r = (Research) campaign.getDepartment();
		r.addResearchedLevels(campaign.getLevel());
		String message = "Spionagekampagne \""
				+ campaign.getTitle()
				+ "\" erfolgreich durchgeführt. Gewonnene Erkenntnisse werden verarbeitet.";
		campaign.getDepartment().getCompany().addMessageToInbox(message);
	}

	@Override
	protected void campaignFailed() {
		String message = "Spionagekampagne \""
				+ campaign.getTitle()
				+ "\" ist aufgeflogen.";
		campaign.getDepartment().getCompany().addMessageToInbox(message);
		
		//more to do
	}

}
