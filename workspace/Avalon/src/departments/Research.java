package departments;

import java.util.ArrayList;

import campaigns.Campaign;
import campaigns.ExplicitCampaign;
import campaigns.ExplicitMarketingCampaign;
import campaigns.ResearchCampaign;
import campaigns.SpyingCampaign;

public class Research extends Department {
	int level;
	private ArrayList<Campaign> campaigns;
	private ArrayList<ExplicitCampaign> explicitCampaigns;

	public Research(int level) {
		super();
		this.level = level;
		init();
	}

	private void init() {
		explicitCampaigns = new ArrayList<ExplicitCampaign>();
		campaigns = new ArrayList<Campaign>();
		// load campaigns from file (?)
	}
	
	public void addCampaign(Campaign campaign){
		campaigns.add(campaign);
	}

	public void startCampaign(ResearchCampaign campaign) {
		explicitCampaigns.add((ExplicitMarketingCampaign) campaign
				.startCampaign());
	}

	public void startCampaign(SpyingCampaign spyingCampaign, int target) {
		explicitCampaigns.add(spyingCampaign.startSpyingCampaign(target));
	}
}
