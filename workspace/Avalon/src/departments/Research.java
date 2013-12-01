package departments;

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
		Product newProduct = new Product(company.getWarehouse().getHighestProduct().getLevel()
				+ this.reasearchedLevels);
		company.getWarehouse().addProduct(newProduct);
		reasearchedLevels = 0;
	}

	public void addResearchedLevels(int level) {
		reasearchedLevels += level;
	}

	@Override
	protected void loadCampaigns() {
		// TODO Auto-generated method stub
		
	}
}
