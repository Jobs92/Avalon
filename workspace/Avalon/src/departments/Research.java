package departments;

import java.util.ArrayList;

import product.Product;
import campaigns.Campaign;
import campaigns.ExplicitCampaign;
import campaigns.ExplicitMarketingCampaign;
import campaigns.ResearchCampaign;
import campaigns.SpyingCampaign;

public class Research extends Department {
	private int level;
	private int reasearchedLevels;
	private ArrayList<Campaign> campaigns;
	private ArrayList<ExplicitCampaign> explicitCampaigns;

	public Research(int level) {
		super();
		this.level = level;
		init();
	}

	private void init() {
		reasearchedLevels = 0;
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
	
	public void applyResearchResults(){
		Product newProduct = new Product(company.getHighestProduct().getLevel()+this.reasearchedLevels);
		company.addProduct(newProduct);
		reasearchedLevels=0;
	}
	
	public void addResearchedLevels(int level){
		reasearchedLevels+=level;
	}
	
	public void improveResearch(int level){
		this.level+=level;
	}

	public int getLevel() {
		return level;
	}
}
