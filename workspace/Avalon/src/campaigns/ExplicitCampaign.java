package campaigns;

import otherclasses.Probability;
import gameManager.GameManager;

public abstract class ExplicitCampaign {

	private int endRound;
	protected Campaign campaign;
	protected boolean active;

	public ExplicitCampaign(Campaign campaign) {
		this.campaign = campaign;
		this.endRound = GameManager.sharedInstance().getRound()
				+ campaign.getDuration();
	}

	public void simulate() {
		if (GameManager.sharedInstance().getRound() == endRound) {
			if (Probability.propability(campaign.getSuccessProbability())) {
				campaignFinishedSuccessfully();
			} else {
				campaignFailed();
			}
		}
	}

	protected abstract void campaignFinishedSuccessfully();

	protected abstract void campaignFailed();

}
