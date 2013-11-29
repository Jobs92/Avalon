package departments;

import java.util.ArrayList;
import campaigns.ExplicitMarketingCampaign;
import campaigns.MarketingCampaign;

public class Marketing extends Department {
	private int level;
	private ArrayList<MarketingCampaign> campaigns;
	private ArrayList<ExplicitMarketingCampaign> explicitCampaigns;

	public Marketing() {
		super();
		init();
	}

	private void init() {
		explicitCampaigns = new ArrayList<ExplicitMarketingCampaign>();
		campaigns = new ArrayList<MarketingCampaign>();
		level = 1;
		// load campaigns from file (?)
	}

	public void addCampaign(MarketingCampaign campaign) {
		campaigns.add(campaign);
	}

	public void simulate() {
		for (ExplicitMarketingCampaign campaign : explicitCampaigns) {
			campaign.simulate();
		}
	}

	public void startCampaign(MarketingCampaign campaign) {
		explicitCampaigns.add((ExplicitMarketingCampaign) campaign
				.startCampaign());
	}

	public void improveMarketing() {
		for (MarketingCampaign campaign : campaigns) {
			campaign.updateProbability(1);
		}
		this.level += 1;
	}

	public int getCostForNextLevel() {
		int cost = level * level * 10000;
		return cost;
	}

	public int getLevel() {
		return this.level;
	}
}
