package campaigns;

import utils.Message;

import company.Company;

import departments.Research;

public class ExplicitResearchCampaign extends ExplicitCampaign {

	public ExplicitResearchCampaign(ResearchCampaign campaign) {
		super(campaign);
	}
	
	@Override
	public void campaignFinishedSuccessfully() {
		Company c = campaign.getDepartment().getCompany();
		Research r = (Research) campaign.getDepartment();
		//save research results
		r.addResearchedLevels(campaign.getLevel());

		// send message
		Message message = new Message();
		message.setTitle("Forschungskampagne erfolgreich durchgef�hrt.");
		message.setMessage("Forschungskampagne \""
				+ campaign.getTitle()
				+ "\" erfolgreich durchgef�hrt. Das Level wurde erh�ht.");
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
		message.setTitle("Forschungskampagne nicht erfolgreich durchgef�hrt.");
		message.setMessage("Forschungskampagne \""
				+ campaign.getTitle()
				+ "\" hatte keine Ergebnisse.");
		message.setType(Message.GAME);
		message.setSourcePlayer(c.getId());
		message.setSourcePlayer(c.getId());
		c.addMessageToInbox(message);
	}
}
