package campaigns;

public class ExplicitMarketingCampaign extends ExplicitCampaign {

	public ExplicitMarketingCampaign(MarketingCampaign campaign) {
		super(campaign);
	}

	@Override
	protected void campaignFinishedSuccessfully() {
		campaign.getDepartment().getCompany()
				.addPopularity(campaign.getLevel());
	}

	@Override
	protected void campaignFailed() {
		// TODO Auto-generated method stub

	}

}
