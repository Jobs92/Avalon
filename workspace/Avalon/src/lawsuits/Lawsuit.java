package lawsuits;
import java.util.ArrayList;

import config.Config;
import campaigns.ExplicitSpyingCampaign;
import departments.LegalDepartment;

public class Lawsuit {
	private LegalDepartment claimant;
	private LegalDepartment defendant;
	private ArrayList<ExplicitSpyingCampaign> spyings;
	private double amount = 0;
	private int duration = 0;
	private boolean active;
	private boolean started;
	
	public Lawsuit(LegalDepartment c, LegalDepartment d, ArrayList<ExplicitSpyingCampaign> spyings, double amount){
		claimant = c;
		defendant = d;
		this.amount = amount;
		this.spyings = spyings;
	}
	
	public void startLawsuit(){
		defendant.beSued(this);
		active = true;
		started = true;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void endLawsuit(){
		active = false;
	}

	public boolean isActive() {
		return active;
	}

	public boolean isStarted() {
		return started;
	}
	
	public LegalDepartment getClaimant(){
		return claimant;
	}
	
	public LegalDepartment getDefendant(){
		return defendant;
	}

	public void simulate(){
		duration++;
		
		//Process Costs
		double costs = amount*Config.getRelativeAmountCostsLawsuit();
		if (!claimant.getCompany().changeMoney(costs)){
			//Insolvenz
		}
		if (!defendant.getCompany().changeMoney(costs)){
			//Insolvenz
		}
		
		//Check Winner
		double quotient = (claimant.getLevel()*1.0)/defendant.getLevel();
		
		double weightLevel = Config.getWeightLevel(); 
		double weightRound = Config.getWeightRound();
		
		double paramWin = (weightLevel*quotient + weightRound*duration)/(weightLevel + weightRound);
		double paramLose = (weightLevel*(1/quotient) + weightRound*duration)/(weightLevel + weightRound);
		
		if (utils.Probability.propability((int) (Config.getProbWinLawsuit() * paramWin))){
			//Claimant wins lawsuit
			//TODO: Message to inform Players
			
			//Refund Process Costs
			double refundedCosts = duration * Config.getRelativeAmountCostsLawsuit() * amount;
			
			double totalCosts = amount + refundedCosts;
			
			//Pay amount
			if (defendant.getCompany().changeMoney((-1)*totalCosts)){
				claimant.getCompany().changeMoney(totalCosts);
			}else{
				//Insolvenz
				claimant.getCompany().changeMoney(defendant.getCompany().getMoney());
			}
			
			
		}else if (utils.Probability.propability((int) (Config.getProbWinLawsuit() * paramLose))){
			//Defendant wins lawsuit
			//TODO: Message to inform Players
			
			//Refund Process Costs
			double refundedCosts = duration * Config.getRelativeAmountCostsLawsuit() * amount;
			
			//Pay amount
			if (defendant.getCompany().changeMoney((-1)*refundedCosts)){
				claimant.getCompany().changeMoney(refundedCosts);
			}else{
				//Insolvenz
				claimant.getCompany().changeMoney(defendant.getCompany().getMoney());
			}
		}
		
	}
}
