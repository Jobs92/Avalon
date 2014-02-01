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
		message.setTitle("Marketingkampagne erfolgreich durchgeführt.");
		message.setMessage("Marketingkampagne \"" + campaign.getTitle()
				+ "\" erfolgreich durchgeführt. Popularität um "
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
		message.setTitle("Marketingkampagne nicht erfolgreich durchgeführt.");
		message.setMessage("Marketingkampagne \"" + campaign.getTitle()
				+ "\" wurde nicht erfolgreich abgeschlossen.");
		message.setType(Message.GAME);
		message.setSourcePlayer(c.getId());
		message.setSourcePlayer(c.getId());
		c.addMessageToInbox(message);
	}

}
