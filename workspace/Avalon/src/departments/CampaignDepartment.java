package departments;

import java.util.ArrayList;

import campaigns.Campaign;
import campaigns.ExplicitCampaign;

public abstract class CampaignDepartment extends Department {

	protected int level;
	protected ArrayList<Campaign> campaigns;
	protected ArrayList<ExplicitCampaign> explicitCampaigns;

	public CampaignDepartment() {
		super();
		init();
	}

	private void init() {
		this.level = 1;
		explicitCampaigns = new ArrayList<ExplicitCampaign>();
		campaigns = new ArrayList<Campaign>();
		loadCampaigns();
	}

	protected abstract void loadCampaigns();

	public void simulate() {
		for (ExplicitCampaign campaign : explicitCampaigns) {
			campaign.simulate();
		}
	}

	public void addCampaign(Campaign campaign) {
		campaigns.add(campaign);
	}

	public void startCampaign(Campaign campaign) {
		explicitCampaigns.add((ExplicitCampaign) campaign.startCampaign());
	}

	public int getCountOfCurrentlyRunningCampaigns() {
		return explicitCampaigns.size();
	}

	public void improveDepartment() {
		for (Campaign campaign : campaigns) {
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

	public ArrayList<ExplicitCampaign> getExplicitCampaigns() {
		return explicitCampaigns;
	}

}
