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
		Research r = (Research) campaign.getDepartment();
		r.addResearchedLevels(campaign.getLevel());
		Company c = campaign.getDepartment().getCompany();
		c.addPopularity(campaign.getLevel());
		Message message = new Message();
		message.setTitle("Forschungskampagne erfolgreich durchgeführt!");
		message.setMessage("Forschungskampagne \"" + campaign.getTitle()
				+ "\" erfolgreich durchgeführt.");
		message.setType(Message.GAME);
		message.setTargetPlayer(c.getId());
		c.addMessageToInbox(message);
	}

	@Override
	protected void campaignFailed() {
		Company c = campaign.getDepartment().getCompany();
		Message message = new Message();
		message.setTitle("Forschungskampagne fehlgeschlagen!");
		message.setMessage("Forschungskampagne \"" + campaign.getTitle()
				+ "\" hatte keine Ergebnisse.");
		message.setType(Message.GAME);
		message.setTargetPlayer(c.getId());
		c.addMessageToInbox(message);
	}

}
