package departments;

import java.util.ArrayList;

import company.Company;

import campaigns.Campaign;
import campaigns.ExplicitCampaign;

public abstract class CampaignDepartment extends Department {

	protected int level;
	protected ArrayList<Campaign> campaigns;
	protected ArrayList<ExplicitCampaign> explicitCampaigns;

	public CampaignDepartment(Company company) {
		super(company);
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

	public boolean upgradeDepartment() {
		if (isMaxLevel()) {
			return false;
		} else {
			if (company.changeMoney((-1) * getCostForNextLevel())) {
				for (Campaign campaign : campaigns) {
					campaign.updateProbability(1);
				}
				this.level += 1;
				return true;
			} else {
				return false;
			}
		}
	}

	protected abstract boolean isMaxLevel();

	public abstract int getCostForNextLevel();

	public int getLevel() {
		return level;
	}

	public ArrayList<ExplicitCampaign> getExplicitCampaigns() {
		return explicitCampaigns;
	}
	
	public ArrayList<Campaign> getCampaigns() {
		return campaigns;
	}

	public Campaign getCampaignByID(int id){
		return campaigns.get(id);
	}

}
