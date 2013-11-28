package departments;

import java.util.ArrayList;

import campaigns.Campaign;
import campaigns.ExplicitMarketingCampaign;
import campaigns.MarketingCampaign;

public class Marketing extends Department {
	private ArrayList<Campaign> campaigns;
	private ArrayList<ExplicitMarketingCampaign> explicitCampaigns;

	public Marketing() {
		super();
		init();
	}

	private void init() {
		explicitCampaigns = new ArrayList<ExplicitMarketingCampaign>();
		campaigns = new ArrayList<Campaign>();
		// load campaigns from file (?)
	}

	public void addCampaign(Campaign campaign) {
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
}
