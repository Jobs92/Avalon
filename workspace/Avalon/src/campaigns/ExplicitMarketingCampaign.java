package campaigns;

import company.Company;

public class ExplicitMarketingCampaign extends ExplicitCampaign {

	public ExplicitMarketingCampaign(MarketingCampaign campaign) {
		super(campaign);
	}

	@Override
	protected void campaignFinishedSuccessfully() {
		Company c = new Company();
		c.addPopularity(campaign.getLevel());
		String message = "Marketingkampagne \"" + campaign.getTitle()
				+ "\" erfolgreich durchgeführt. Popularität um "
				+ campaign.getLevel() + " Level gestiegen.";
		c.addMessageToInbox(message);
	}

	@Override
	protected void campaignFailed() {
		Company c = new Company();
		String message = "Marketingkampagne \"" + campaign.getTitle()
				+ "\" wurde nicht erfolgreich abgeschlossen.";
		c.addMessageToInbox(message);
	}

}
