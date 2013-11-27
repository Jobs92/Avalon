package campaigns;

import product.Product;


public class ExplicitResearchCampaign extends ExplicitCampaign {
	Product product;
	
	public ExplicitResearchCampaign(ResearchCampaign campaign, Product product) {
		super(campaign);
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	protected void campaignFinished() {
		product.addLevel(campaign.getLevel());
	}

	@Override
	protected void campaignFinishedSuccessfully() {
		product.addLevel(campaign.getLevel());
	}

	@Override
	protected void campaignFailed() {
		// TODO Auto-generated method stub
		
	}
	
	

}
