package departments;

import gameManager.GameManager;
import product.Product;
import campaigns.SpyingCampaign;

public class Research extends CampaignDepartment {
	private int reasearchedLevels;

	public Research() {
		super();
		this.reasearchedLevels = 0;
	}

	public void startCampaign(SpyingCampaign spyingCampaign, int target) {
		explicitCampaigns.add(spyingCampaign.startSpyingCampaign(target));
	}

	public void applyResearchResults() {
		Product newProduct = new Product(company.getWarehouse()
				.getHighestProduct().getLevel()
				+ this.reasearchedLevels);
		company.getWarehouse().addProduct(newProduct);
		reasearchedLevels = 0;
	}

	public void addResearchedLevels(int level) {
		reasearchedLevels += level;
	}

	@Override
	protected void loadCampaigns() {
		// TODO
	}

	@Override
	public int getCostForNextLevel() {
		int cost = level * level * GameManager.sharedInstance().getConfig().getCostsUpgradeResearch();
		return cost;
	}
}
