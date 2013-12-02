package departments;

import gameManager.GameManager;

public class Marketing extends CampaignDepartment {

	public Marketing() {
		super();
		loadCampaigns();
	}

	@Override
	protected void loadCampaigns() {
		// TODO
	}

	@Override
	public int getCostForNextLevel() {
		int cost = level * level * GameManager.sharedInstance().getConfig().getCostsUpgradeMarketing();
		return cost;
	}

}
