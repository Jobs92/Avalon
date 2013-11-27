package campaigns;

import gameManager.GameManager;

public abstract class ExplicitCampaign {

	private int endRound;
	protected Campaign campaign;

	public ExplicitCampaign(Campaign campaign) {
		this.campaign = campaign;
		this.endRound = GameManager.sharedInstance().getRound()
				+ campaign.getDuration();
	}

	public void simulate() {
		if (GameManager.sharedInstance().getRound() == endRound) {
			if (isSuccessfull()) {
				campaignFinishedSuccessfully();
			} else {
				campaignFailed();
			}
		}
	}

	private boolean isSuccessfull() {
		int random = (int) Math.random() * 100;
		return random < campaign.getSuccessProbability();
	}

	protected abstract void campaignFinishedSuccessfully();

	protected abstract void campaignFailed();

}
