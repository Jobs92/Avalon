package departments;

import company.Company;

import config.Config;

public class Marketing extends CampaignDepartment {

	public Marketing(Company company) {
		super(company);
		fixcost = Config.getMarketingFixcost();
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

	@Override
	protected boolean isMaxLevel() {
		int maxLevel = Config.getMaxLevelMarketing();
		if (level==maxLevel) {
			return true;
		}
		return false;
	}

}
