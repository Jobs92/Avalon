package departments;

import company.Company;

import config.Config;
import product.Product;
import campaigns.SpyingCampaign;

public class Research extends CampaignDepartment {
	private int notAppliedLevels;
	private int patentLevel;
	private int researchLevel;
	private int spiedLevels;

	public Research(Company company) {
		super(company);
		this.notAppliedLevels = 0;
		this.patentLevel = 1;
		this.researchLevel = 1;
		this.spiedLevels = 0;
		this.fixcost = Config.getResearchFixcost();
	}

	public void startCampaign(SpyingCampaign spyingCampaign, int target) {
		explicitCampaigns.add(spyingCampaign.startSpyingCampaign(target));
	}

	public void applyResearchResults() {
		this.researchLevel += notAppliedLevels;

		Product newProduct = new Product(this.researchLevel + this.spiedLevels, company);
		company.getWarehouse().addProduct(newProduct);
		notAppliedLevels = 0;
	}

	public void addResearchedLevels(int level) {
		notAppliedLevels += level;
	}

	public void addSpiedLevels(int level) {
		spiedLevels += level;
	}

	@Override
	protected void loadCampaigns() {
		// TODO
	}

	@Override
	public int getCostForNextLevel() {
		int cost = level * level * Config.getCostsUpgradeResearch();
		return cost;
	}

	public void patentResearchLevel() {
		patentLevel = researchLevel;
	}

	public int getPatentLevel() {
		return this.patentLevel;
	}

	public int getSpiedLevels() {
		return this.spiedLevels;
	}
}
