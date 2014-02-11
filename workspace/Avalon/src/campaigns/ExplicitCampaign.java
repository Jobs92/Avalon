package campaigns;

import utils.Probability;
import gameManager.GameManager;

/**
 * @author Martin Abstract superclass for all explicit campaigns.
 */
public abstract class ExplicitCampaign {

	private int endRound;
	protected Campaign campaign;
	protected boolean active = true;

	public ExplicitCampaign(Campaign campaign) {
		this.campaign = campaign;
		//calc endRound
		this.endRound = GameManager.sharedInstance().getRound()
				+ campaign.getDuration() - 1;
		campaign.getDepartment().getCompany()
				.changeMoney(-1 * campaign.getCost());
	}

	/**
	 * Checks if the campaign is finished, if so it calculates whether it was
	 * successful or not.
	 */
	public void simulate() {
		//check endround
		if (GameManager.sharedInstance().getRound() == endRound) {
			//campaign over, test if successfull
			active = false;
			if (Probability.propability(campaign.getSuccessProbability())) {
				//success
				campaignFinishedSuccessfully();
			} else {
				//failed
				campaignFailed();
			}
		}
	}

	/**
	 * Success-handler.
	 */
	public abstract void campaignFinishedSuccessfully();

	/**
	 * Failure-handler.
	 */
	public abstract void campaignFailed();

	public boolean isActive() {
		return active;
	}

}
