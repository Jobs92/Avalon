package campaigns;

import departments.Department;

public class ResearchCampaign extends Campaign {
	public ResearchCampaign(Department department, String title, double d,
			int duration, int successProbability, int level, String description) {
		super(department, title, d, duration, successProbability, level, description);
	}

	@Override
	public ExplicitCampaign startCampaign() {
		ExplicitResearchCampaign c = new ExplicitResearchCampaign(this);
		return c;
	}	
}
