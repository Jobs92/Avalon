package departments;

import java.util.ArrayList;

import campaigns.ExplicitCampaign;
import campaigns.ExplicitResearchCampaign;
import campaigns.ExplicitSpyingCampaign;
import company.Company;
import config.Config;
import lawsuits.Lawsuit;

public class LegalDepartment extends Department {
	private int level = 1;
	private ArrayList<Lawsuit> lawsuitsAsClaimant = new ArrayList<Lawsuit>();
	private ArrayList<Lawsuit> lawsuitsAsDefendant = new ArrayList<Lawsuit>();
	private ArrayList<ExplicitSpyingCampaign> foundSpyingCampaigns = new ArrayList<ExplicitSpyingCampaign>();
	private ArrayList<ExplicitSpyingCampaign> checkedCampaigns = new ArrayList<ExplicitSpyingCampaign>(); //Remember already checked Campaigns

	public LegalDepartment(Company company) {
		super(company);
	}

	public void checkOpponent(Company c) {
		ArrayList<ExplicitCampaign> allCampaigns = c.getResearch().getExplicitCampaigns();
		int amountSpyingCampaigns = 0;
		//Check all Campaigns
		for (int i = 0; i < allCampaigns.size(); i++) {
			ExplicitResearchCampaign campaign = (ExplicitResearchCampaign) allCampaigns.get(i);
			if (!checkedCampaigns.contains(campaign) && company.changeMoney((-1)*Config.getCostsCheckCampaign())){
				if (campaign.getClass() == ExplicitSpyingCampaign.class && ((ExplicitSpyingCampaign) campaign).getSpiedPlayer() == company.getId()) {
					foundSpyingCampaigns.add((ExplicitSpyingCampaign) campaign);
					amountSpyingCampaigns++;
				}
			}else{
				//TODO: Message Geld reicht nicht aus
			}
		}
		
		//Create Lawsuit, if amount spying campaigns > 0
		if (amountSpyingCampaigns != 0){
			double sum = Config.getCostsFoundSpyingCampaign() * amountSpyingCampaigns;
			Lawsuit l = new Lawsuit(this, company.getLegaldepartment(), sum);
			lawsuitsAsClaimant.add(l);
		}
		
	}

	public int getLevel() {
		return level;
	}

	public void upgrade() {
		int amount = 0; // TODO aus Config Datei holen
		company.changeMoney(amount * -1);
		level++;
	}

	public void beSued(Lawsuit l) {
		lawsuitsAsDefendant.add(l);
	}

	public void sueOpponent(Company c) {
		LegalDepartment opponent = c.getLegaldepartment();
		if (this.isAvailable() && opponent.isAvailable()) {
			for (Lawsuit l : lawsuitsAsClaimant) {
				if (!l.isActive() && !l.isStarted()){
					l.startLawsuit();
					break;
				}
			}
		}
	}

	private boolean isAvailable() {
		// A legal Departmant has the capacity for only one lawsuit at the same
		// time

		// Check lawsuits as Claimant
		for (int i = 0; i < lawsuitsAsClaimant.size(); i++) {
			if (lawsuitsAsClaimant.get(i).isActive()) {
				return false;
			}
		}

		// Check lawsuits as Defendant
		for (int i = 0; i < lawsuitsAsDefendant.size(); i++) {
			if (lawsuitsAsDefendant.get(i).isActive()) {
				return false;
			}
		}
		return true;
	}

	public void payAmount() {
		Lawsuit l = lawsuitsAsDefendant.get(lawsuitsAsDefendant.size() - 1);
		if (l.isActive()) {
			double amount = l.getAmount();
			if (company.changeMoney((-1)*amount)){
				l.getClaimant().getCompany().changeMoney(amount);
			}else{
				//TODO: Message payAmount not possible
			}
			
			l.endLawsuit();
		}
	}

	public void abandonLawsuit() {
		Lawsuit l = lawsuitsAsClaimant.get(lawsuitsAsClaimant.size() - 1);
		if (l.isActive()) {
			l.endLawsuit();
		}
	}

	@Override
	public void simulate() {
		Lawsuit l;
		for (int i = 0; i < lawsuitsAsClaimant.size(); i++) {
			l = lawsuitsAsClaimant.get(i);
			if (l.isActive()) {
				l.simulate();
			}
		}
	}
}
