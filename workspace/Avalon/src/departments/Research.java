package departments;

import market.Market;
import company.Company;
import config.Config;
import product.Product;
import utils.Message;
import campaigns.ResearchCampaign;
import campaigns.SpyingCampaign;

public class Research extends CampaignDepartment {
	private int notAppliedLevels;
	private int patentLevel;
	private int researchLevel;
	private int spiedLevels;

	private SpyingCampaign spyingCampain;

	public Research(Company company) {
		super(company);
		this.notAppliedLevels = 0;
		this.patentLevel = 1;
		this.researchLevel = 1;
		this.spiedLevels = 0;
		updateFixcost();
	}

	/**
	 * Starts a specific spying campaign and adds it to the Research department.
	 * 
	 * @param spyingCampaign
	 *            template
	 * @param target
	 *            id
	 */
	public void startCampaign(SpyingCampaign spyingCampaign, int target) {
		System.out.println("Class: " +this.getClass()+". Spying Campaign gestartet. Target: "+ target);
		explicitCampaigns.add(spyingCampaign.startSpyingCampaign(target));
	}

	/**
	 * Apply the accumulated research levels to release a new product with the
	 * current level.
	 * 
	 * @param name
	 *            for new product.
	 */
	public void applyResearchResults(String name) {
		this.researchLevel += notAppliedLevels;

		Product newProduct = new Product(this.researchLevel + this.spiedLevels,
				name, company);
		company.getWarehouse().addProduct(newProduct);
		notAppliedLevels = 0;
	}

	public void addResearchedLevels(int level) {
		notAppliedLevels += level;
	}

	public void addSpiedLevels(int level) {
		spiedLevels += level;
	}

	public int getResearchLevel() {
		return researchLevel;
	}

	public int getNotAppliedLevels() {
		return notAppliedLevels;
	}

	@Override
	protected void loadCampaigns() {
		//load research campaigns
		for (int i = 0; i < 3; i++) {
			ResearchCampaign m = new ResearchCampaign(this,
					Config.getTitleResearch()[i], Config.getCostResearch()[i],
					Config.getDurationResearch()[i],
					Config.getSuccessprobabilityResearch()[i],
					Config.getLevelResearch()[i],
					Config.getDescriptionResearch()[i]);
			addCampaign(m);
		}
		//load spying campaigns
		spyingCampain = new SpyingCampaign(this, Config.getTitleSpying(),
				Config.getCostSpying(), Config.getDurationSpying(),
				Config.getSuccessprobabilitySpying(), Config.getLevelSpying(),
				Config.getDescriptionSpying());
	}

	@Override
	public int getCostForNextLevel() {
		int cost = level * level * Config.getCostsUpgradeResearch();
		return cost;
	}

	/**
	 * Patent the current research-level to save it. The level cannot fall under
	 * this level due to lost lawsuits.
	 */
	public void patentResearchLevel() {
		company.changeMoney(Config.getCostsPatent() * (researchLevel + 1));
		patentLevel = researchLevel + notAppliedLevels;
	}

	public int getPatentLevel() {
		return this.patentLevel;
	}

	public int getSpiedLevels() {
		return this.spiedLevels;
	}

	@Override
	protected boolean isMaxLevel() {
		int maxLevel = Config.getMaxLevelResearch();
		if (level == maxLevel) {
			Message m = new Message();
			m.setTitle("Level-Update-Error");
			m.setType(Message.GAME);
			m.setTargetPlayer(company.getId());
			m.setMessage("Sie haben das maximale Level dieses Breichs erreicht!");
			Market.sharedInstance().sendMessage(m);
			return true;
		}
		return false;
	}

	public SpyingCampaign getSpyingCampaign() {
		return spyingCampain;
	}

	@Override
	protected void updateFixcost() {
		fixcost = Config.getResearchFixcost() * level;
	}
}
