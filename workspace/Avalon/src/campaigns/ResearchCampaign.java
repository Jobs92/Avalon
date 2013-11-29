package campaigns;

import departments.Department;

public class ResearchCampaign extends Campaign {
	public ResearchCampaign(Department department, String title, int cost,
			int duration, int successProbability, int level, String description) {
		super(department, title, cost, duration, successProbability, level, description);
	}

	@Override
	public ExplicitCampaign startCampaign() {
		ExplicitResearchCampaign c = new ExplicitResearchCampaign(this);
		return c;
	}	
}
