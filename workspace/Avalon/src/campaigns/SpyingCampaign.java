package campaigns;

import departments.Department;

public class SpyingCampaign extends ResearchCampaign {

	public SpyingCampaign(Department department, String title, double cost,
			int duration, int successProbability, int level, String description) {
		super(department, title, cost, duration, successProbability, level,
				description);
	}

	/**
	 * Start an explicit spying campaign to spy the opponent with the given id.
	 * 
	 * @param target
	 *            id
	 * @return explicit spying campaign
	 */
	public ExplicitSpyingCampaign startSpyingCampaign(int target) {
		return new ExplicitSpyingCampaign(target, this);
	}

}
