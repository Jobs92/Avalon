package campaigns;

import departments.Research;

public class ExplicitResearchCampaign extends ExplicitCampaign {

	public ExplicitResearchCampaign(ResearchCampaign campaign) {
		super(campaign);
	}

	@Override
	protected void campaignFinishedSuccessfully() {
		Research r = (Research) campaign.getDepartment();
		r.addResearchedLevels(campaign.getLevel());
		String message = "Forschungskampagne \""
				+ campaign.getTitle()
				+ "\" erfolgreich durchgeführt. Das Level wurde erhöht.";
		campaign.getDepartment().getCompany().addMessageToInbox(message);
	}

	@Override
	protected void campaignFailed() {
		String message = "Forschungskampagne \""
				+ campaign.getTitle()
				+ "\" hatte keine Ergebnisse.";
		campaign.getDepartment().getCompany().addMessageToInbox(message);
	}

}
