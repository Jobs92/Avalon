package departments;

import config.Config;

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
		int cost = level * level * Config.getCostsUpgradeMarketing();
		return cost;
	}

}
