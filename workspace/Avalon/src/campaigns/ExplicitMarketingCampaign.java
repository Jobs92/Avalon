package campaigns;

import utils.Message;
import company.Company;

public class ExplicitMarketingCampaign extends ExplicitCampaign {

	public ExplicitMarketingCampaign(MarketingCampaign campaign) {
		super(campaign);
	}

	@Override
	protected void campaignFinishedSuccessfully() {
		Company c = campaign.getDepartment().getCompany();
		c.addPopularity(campaign.getLevel());
		Message message = new Message();
		message.setTitle("Marketingkampagne erfolgreich durchgeführt!");
		message.setMessage("Marketingkampagne \"" + campaign.getTitle()
				+ "\" erfolgreich durchgeführt. Popularität um "
				+ campaign.getLevel() + " Level gestiegen.");
		message.setType(Message.GAME);
		message.setTargetPlayer(c.getId());
		c.addMessageToInbox(message);
	}

	@Override
	protected void campaignFailed() {
		Company c = campaign.getDepartment().getCompany();
		Message message = new Message();
		message.setTitle("Marketingkampagne fehlgeschlagen!");
		message.setMessage("Marketingkampagne \"" + campaign.getTitle()
				+ "\" wurde nicht erfolgreich abgeschlossen.");
		message.setType(Message.GAME);
		message.setTargetPlayer(c.getId());
		c.addMessageToInbox(message);
	}

}
