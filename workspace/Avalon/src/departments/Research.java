package departments;

import java.util.ArrayList;

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

	private ArrayList<SpyingCampaign> spyingCampains;

	public Research(Company company) {
		super(company);
		this.notAppliedLevels = 0;
		this.patentLevel = 1;
		this.researchLevel = 1;
		this.spiedLevels = 0;
		this.fixcost = Config.getResearchFixcost();
	}

	public void startCampaign(SpyingCampaign spyingCampaign, int target) {
		explicitCampaigns.add(spyingCampaign.startSpyingCampaign(target));
	}

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

	@Override
	protected void loadCampaigns() {
		for (int i = 0; i < 3; i++) {
			ResearchCampaign m = new ResearchCampaign(this,
					Config.getTitleResearch()[i],
					Config.getCostResearch()[i],
					Config.getDurationResearch()[i],
					Config.getSuccessprobabilityResearch()[i], 
					Config.getLevelResearch()[i],
					Config.getDescriptionResearch()[i]);
			addCampaign(m);
		}
		SpyingCampaign s = new SpyingCampaign(this, Config.getTitleSpying(),
				Config.getCostSpying(), Config.getDurationSpying(),
				Config.getSuccessprobabilitySpying(), Config.getLevelSpying(),
				Config.getDescriptionSpying());
		addCampaign(s);
	}

	@Override
	public int getCostForNextLevel() {
		int cost = level * level * Config.getCostsUpgradeResearch();
		return cost;
	}

	public void patentResearchLevel() {
		company.changeMoney(Config.getCostsPatent() * researchLevel);
		patentLevel = researchLevel;
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

	public SpyingCampaign getSpyingCampaignByID(int id) {
		return spyingCampains.get(id);
	}
}
