package lawsuits;
import campaigns.ExplicitSpyingCampaign;
import departments.LegalDepartment;

public class Lawsuit {
	private LegalDepartment claimant;
	private LegalDepartment defendant;
	private ExplicitSpyingCampaign spying;
	private int amount = 0;
	private int duration = 0;
	private boolean active;
	
	public Lawsuit(LegalDepartment c, LegalDepartment d){
		claimant = c;
		defendant = d;
		defendant.beSued(this);
		active = true;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void endLawsuit(){
		active = false;
	}

	public boolean isActive() {
		return active;
	}
	
	public void simulate(){
		int dif = claimant.getLevel() - defendant.getLevel();
		//TODO: lawsuit simulieren
		
		double weightLevel = 1; //TODO: load from config
		double weightRound = 1; //TODO: load from config
		
		double param = (weightLevel*dif + weightRound*duration)/2.0;
	}
}
