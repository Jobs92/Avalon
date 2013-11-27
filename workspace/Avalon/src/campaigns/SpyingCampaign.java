package campaigns;

import departments.Department;

public class SpyingCampaign extends ResearchCampaign {

	public SpyingCampaign(Department department, String title, int cost,
			int duration, int successProbability, int level, String description) {
		super(department, title, cost, duration, successProbability, level,
				description);
	}

}
