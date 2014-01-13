package campaigns;

import departments.Department;

public class SpyingCampaign extends ResearchCampaign {

	public SpyingCampaign(Department department, String title, double cost,
			int duration, int successProbability, int level, String description) {
		super(department, title, cost, duration, successProbability, level,
				description);
	}

	public ExplicitSpyingCampaign startSpyingCampaign(int target) {
		return new ExplicitSpyingCampaign(target, this);
	}

}
