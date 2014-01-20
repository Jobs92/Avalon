package campaigns;

import utils.Probability;
import gameManager.GameManager;

/**
 * @author Martin Abstract superclass for all explicit campaigns.
 */
public abstract class ExplicitCampaign {

	private int endRound;
	protected Campaign campaign;
	protected boolean active;

	public ExplicitCampaign(Campaign campaign) {
		this.campaign = campaign;
		this.endRound = GameManager.sharedInstance().getRound()
				+ campaign.getDuration() - 1;
	}

	/**
	 * Checks if the campaign is finished, if so it calculates whether it was
	 * successfull or not.
	 */
	public void simulate() {
		if (GameManager.sharedInstance().getRound() == endRound) {
			if (Probability.propability(campaign.getSuccessProbability())) {
				campaignFinishedSuccessfully();
			} else {
				campaignFailed();
			}
		}
	}

	/**
	 * Success-handler.
	 */
	protected abstract void campaignFinishedSuccessfully();

	/**
	 * Failure-handler.
	 */
	protected abstract void campaignFailed();

}
