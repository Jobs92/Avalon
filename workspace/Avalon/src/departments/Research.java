package departments;

import java.util.ArrayList;

import product.Product;
import campaigns.ExplicitCampaign;
import campaigns.ExplicitMarketingCampaign;
import campaigns.ResearchCampaign;
import campaigns.SpyingCampaign;

public class Research extends Department {
	private int level;
	private int reasearchedLevels;
	private ArrayList<ResearchCampaign> campaigns;
	private ArrayList<ExplicitCampaign> explicitCampaigns;

	public Research() {
		super();
		this.level = 1;
		init();
	}

	private void init() {
		reasearchedLevels = 0;
		explicitCampaigns = new ArrayList<ExplicitCampaign>();
		campaigns = new ArrayList<ResearchCampaign>();
		// load campaigns from file (?)
	}

	public void addCampaign(ResearchCampaign campaign) {
		campaigns.add(campaign);
	}

	public void startCampaign(ResearchCampaign campaign) {
		explicitCampaigns.add((ExplicitMarketingCampaign) campaign
				.startCampaign());
	}

	public void startCampaign(SpyingCampaign spyingCampaign, int target) {
		explicitCampaigns.add(spyingCampaign.startSpyingCampaign(target));
	}

	public void applyResearchResults() {
		Product newProduct = new Product(company.getHighestProduct().getLevel()
				+ this.reasearchedLevels);
		company.addProduct(newProduct);
		reasearchedLevels = 0;
	}

	public void addResearchedLevels(int level) {
		reasearchedLevels += level;
	}

	public void improveResearch() {
		for (ResearchCampaign campaign : campaigns) {
			campaign.updateProbability(1);
		}
		this.level += 1;
	}

	public int getCostForNextLevel() {
		int cost = level * level * 20000;
		return cost;
	}

	public int getLevel() {
		return level;
	}
}
