package campaigns;

import product.Product;

public class ExplicitSpyingCampaign extends ExplicitCampaign {
	private int spiedPlayer;
	private Product product;

	public ExplicitSpyingCampaign(int spiedPlayer, Product product,
			SpyingCampaign campaign) {
		super(campaign);
		this.spiedPlayer = spiedPlayer;
		this.product = product;
	}

	public int getSpiedPlayer() {
		return spiedPlayer;
	}

	public Product getProduct() {
		return product;
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
