package campaigns;

import utils.Message;

import company.Company;

import departments.Research;

public class ExplicitResearchCampaign extends ExplicitCampaign {

	public ExplicitResearchCampaign(ResearchCampaign campaign) {
		super(campaign);
	}
	
	@Override
	protected void campaignFinishedSuccessfully() {
		Company c = campaign.getDepartment().getCompany();
		Research r = (Research) campaign.getDepartment();
		r.addResearchedLevels(campaign.getLevel());

		// send message
		Message message = new Message();
		message.setTitle("Forschungskampagne erfolgreich durchgeführt.");
		message.setMessage("Forschungskampagne \""
				+ campaign.getTitle()
				+ "\" erfolgreich durchgeführt. Das Level wurde erhöht.");
		message.setType(Message.GAME);
		message.setSourcePlayer(c.getId());
		message.setSourcePlayer(c.getId());
		c.addMessageToInbox(message);
	}

	@Override
	protected void campaignFailed() {
		Company c = campaign.getDepartment().getCompany();

		// send message
		Message message = new Message();
		message.setTitle("Forschungskampagne nicht erfolgreich durchgeführt.");
		message.setMessage("Forschungskampagne \""
				+ campaign.getTitle()
				+ "\" hatte keine Ergebnisse.");
		message.setType(Message.GAME);
		message.setSourcePlayer(c.getId());
		message.setSourcePlayer(c.getId());
		c.addMessageToInbox(message);
	}
}
