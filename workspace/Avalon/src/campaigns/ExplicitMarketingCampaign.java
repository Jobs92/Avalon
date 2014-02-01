package campaigns;

import utils.Message;
import company.Company;

public class ExplicitMarketingCampaign extends ExplicitCampaign {

	public ExplicitMarketingCampaign(MarketingCampaign campaign) {
		super(campaign);
	}

	@Override
	public void campaignFinishedSuccessfully() {
		Company c = campaign.getDepartment().getCompany();
		c.addPopularity(campaign.getLevel());

		// send message
		Message message = new Message();
		message.setTitle("Marketingkampagne erfolgreich durchgef�hrt.");
		message.setMessage("Marketingkampagne \"" + campaign.getTitle()
				+ "\" erfolgreich durchgef�hrt. Popularit�t um "
				+ campaign.getLevel() + " Level gestiegen.");
		message.setType(Message.GAME);
		message.setSourcePlayer(c.getId());
		message.setSourcePlayer(c.getId());
		c.addMessageToInbox(message);
	}

	@Override
	public void campaignFailed() {
		Company c = campaign.getDepartment().getCompany();

		// send message
		Message message = new Message();
		message.setTitle("Marketingkampagne nicht erfolgreich durchgef�hrt.");
		message.setMessage("Marketingkampagne \"" + campaign.getTitle()
				+ "\" wurde nicht erfolgreich abgeschlossen.");
		message.setType(Message.GAME);
		message.setSourcePlayer(c.getId());
		message.setSourcePlayer(c.getId());
		c.addMessageToInbox(message);
	}

}
