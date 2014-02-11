package departments;

import java.util.ArrayList;

import utils.Message;
import company.Company;
import campaigns.Campaign;
import campaigns.ExplicitCampaign;

/**
 * @author Martin
 * Abstract superclass for departments which are able to start campaigns (Marketing and Research).
 */
/**
 * @author Martin
 * 
 */
public abstract class CampaignDepartment extends Department implements
		Upgradable {

	protected int level;
	protected ArrayList<Campaign> campaigns;
	protected ArrayList<ExplicitCampaign> explicitCampaigns;

	public CampaignDepartment(Company company) {
		super(company);
		init();
	}

	private void init() {
		this.level = 1;
		explicitCampaigns = new ArrayList<ExplicitCampaign>();
		campaigns = new ArrayList<Campaign>();
		loadCampaigns();
	}

	/**
	 * Load campaigns from Config into departments.
	 */
	protected abstract void loadCampaigns();

	public void simulate() {
		payFixcosts();
		//simulate campaigns
		for (ExplicitCampaign campaign : explicitCampaigns) {
			if (campaign.isActive()) {
				campaign.simulate();
			}
		}
	}

	/**
	 * Add campaign templates.
	 * 
	 * @param abstract campaign
	 */
	public void addCampaign(Campaign campaign) {
		campaigns.add(campaign);
	}

	/**
	 * Starts the given campaign. Adds explicit campaign to the department.
	 * 
	 * @param campaign
	 */
	public void startCampaign(Campaign campaign) {
		//check money
		if (!company.changeMoney(-1 * campaign.getCost())) {
			//not enough money
			Message message = new Message();
			message.setTitle("Nicht genügend Geld.");
			message.setMessage("Kampagne \""
					+ campaign.getTitle()
					+ "\" konnte nicht gestartet werden, da zu wenig Geld vorhanden ist.");
			campaign.getDepartment().getCompany().addMessageToInbox(message);
		} else {
			//enough money
			explicitCampaigns.add((ExplicitCampaign) campaign.startCampaign());
		}
	}

	public int getCountOfCurrentlyRunningCampaigns() {
		return explicitCampaigns.size();
	}

	@Override
	public boolean upgrade() {
		//check if upgrade is possible
		if (isMaxLevel()) {
			//not possible because department is already max level
			return false;
		} else {
			//is possible, check money
			if (company.changeMoney((-1) * getCostForNextLevel())) {
				//enough money, rise probabilities of all campaigns
				for (Campaign campaign : campaigns) {
					campaign.updateProbability(1);
				}
				this.level += 1;
				updateFixcost();
				return true;
			} else {
				//not enough money
				return false;
			}
		}
	}

	@Override
	public boolean downgrade() {
		if (level > 1) {
			level--;
			updateFixcost();
			return true;
		}
		return false;
	}

	protected abstract void updateFixcost();

	protected abstract boolean isMaxLevel();

	public abstract int getCostForNextLevel();

	public int getLevel() {
		return level;
	}

	/**
	 * @return All currently running explicit campaigns.
	 */
	public ArrayList<ExplicitCampaign> getExplicitCampaigns() {
		return explicitCampaigns;
	}

	/**
	 * @return All abstract campaigns.
	 */
	public ArrayList<Campaign> getCampaigns() {
		return campaigns;
	}

	/**
	 * @param campaign
	 *            id
	 * @return campaign with given id
	 */
	public Campaign getCampaignByID(int id) {
		return campaigns.get(id);
	}

}
