package departments;

import java.util.ArrayList;
import campaigns.ExplicitCampaign;
import campaigns.ExplicitResearchCampaign;
import campaigns.ExplicitSpyingCampaign;
import company.Company;
import lawsuits.Lawsuit;

public class LegalDepartment extends Department{
	private int level = 1;
	private Company company = null;
	private ArrayList<Lawsuit> lawsuitsAsClaimant = new ArrayList<Lawsuit>();
	private ArrayList<Lawsuit> lawsuitsAsDefendant = new ArrayList<Lawsuit>();
	private ArrayList<ExplicitSpyingCampaign> foundSpyingCampaigns = new ArrayList<ExplicitSpyingCampaign>();
	
	public LegalDepartment(Company c){
		this.company = c;
	}
	
	public void checkOpponent(Company c){
		ArrayList<ExplicitCampaign> allCampaigns= c.getResearch().getExplicitCampaigns();
		for (int i = 0; i < allCampaigns.size(); i++) {
			ExplicitResearchCampaign campaign = (ExplicitResearchCampaign) allCampaigns.get(i);
			if (campaign.getClass() == ExplicitSpyingCampaign.class){
				foundSpyingCampaigns.add((ExplicitSpyingCampaign) campaign);
			}
				
		}
	}
	
	public int getLevel() {
		return level;
	}
	
	public void upgrade(){
		int amount = 0; //TODO aus Config Datei holen
		company.changeMoney(amount*-1);
		level++;
	}

	public void beSued(Lawsuit l){
		lawsuitsAsDefendant.add(l);
	}
	
	public void sueOpponent(Company c){
		LegalDepartment opponent = c.getLegaldepartment();
		if (this.isAvailable() && opponent.isAvailable()){
			Lawsuit l = new Lawsuit(this, opponent);
			lawsuitsAsClaimant.add(l);
		}
	}
	
	private boolean isAvailable() {
		// A legal Departmant has the capacity for only one lawsuit at the same time
		
		// Check lawsuits as Claimant
		for (int i = 0; i < lawsuitsAsClaimant.size(); i++) {
			if (lawsuitsAsClaimant.get(i).isActive()){
				return false;
			}
		}
		
		// Check lawsuits as Defendant
		for (int i = 0; i < lawsuitsAsDefendant.size(); i++) {
			if (lawsuitsAsDefendant.get(i).isActive()){
				return false;
			}
		}
		return true;
	}

	public void payAmount(){
		Lawsuit l = lawsuitsAsDefendant.get(lawsuitsAsDefendant.size()-1);
		if (l.isActive()){
			int amount = l.getAmount();
			company.changeMoney(amount);
			l.endLawsuit();
		}
	}
	
	public void abandonLawsuit(){
		Lawsuit l = lawsuitsAsClaimant.get(lawsuitsAsClaimant.size()-1);
		if (l.isActive()){
			l.endLawsuit();
		}
	}

	@Override
	public void simulate() {
		Lawsuit l;
		for (int i = 0; i < lawsuitsAsClaimant.size(); i++) {
			l = lawsuitsAsClaimant.get(i);
			if (l.isActive()){
				l.simulate();
			}
		}
	}	
}
