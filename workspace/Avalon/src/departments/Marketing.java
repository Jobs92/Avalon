package departments;

import campaigns.MarketingCampaign;
import market.Market;
import utils.Message;
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
		for (int i = 0; i < 3; i++) {
			MarketingCampaign m = new MarketingCampaign(this,
					Config.getTitleMarketing()[i],
					Config.getCostMarketing()[i],
					Config.getDurationMarketing()[i],
					Config.getSuccessprobabilityMarketing()[i],
					Config.getLevelMarketing()[i],
					Config.getDescriptionMarketing()[i]);
			addCampaign(m);
		}
	}

	@Override
	public int getCostForNextLevel() {
		int cost = level * level * Config.getCostsUpgradeMarketing();
		return cost;
	}

	@Override
	protected boolean isMaxLevel() {
		int maxLevel = Config.getMaxLevelMarketing();
		if (level == maxLevel) {
			Message m = new Message();
			m.setTitle("Level-Update-Error");
			m.setType(Message.GAME);
			m.setTargetPlayer(company.getId());
			m.setMessage("Sie haben das maximale Level dieses Breichs erreicht!");
			Market.sharedInstance().sendMessage(m);

		}
		return false;
	}

}
