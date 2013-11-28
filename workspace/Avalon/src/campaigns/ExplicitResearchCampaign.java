package campaigns;

public class ExplicitResearchCampaign extends ExplicitCampaign {
	
	public ExplicitResearchCampaign(ResearchCampaign campaign) {
		super(campaign);
	}

	@Override
	protected void campaignFinishedSuccessfully() {
		campaign.getDepartment().getCompany().getProduct().addLevel(campaign.getLevel());
	}

	@Override
	protected void campaignFailed() {
		// TODO Auto-generated method stub
		
	}
	
	

}
