package campaigns;

import departments.Department;

public class MarketingCampaign extends Campaign {

	public MarketingCampaign(Department department, String title, double d,
			int duration, int successProbability, int level, String description) {
		super(department, title, d, duration, successProbability, level,
				description);
	}

	@Override
	public ExplicitCampaign startCampaign() {
		ExplicitMarketingCampaign c = new ExplicitMarketingCampaign(this);
		return c;
	}
}
