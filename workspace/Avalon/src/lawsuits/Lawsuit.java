package lawsuits;
import java.util.ArrayList;

import market.Market;
import utils.Message;
import config.Config;
import campaigns.ExplicitSpyingCampaign;
import departments.LegalDepartment;

public class Lawsuit {
	private LegalDepartment claimant;
	private LegalDepartment defendant;
	private ArrayList<ExplicitSpyingCampaign> spyings;
	private double amount = 0;
	private double costs = 0;
	private int duration = 0;
	private boolean active;
	private boolean started;
	
	public Lawsuit(LegalDepartment c, LegalDepartment d, ArrayList<ExplicitSpyingCampaign> spyings, double amount){
		claimant = c;
		defendant = d;
		this.amount = amount;
		this.spyings = spyings;
		costs = amount*Config.getRelativeAmountCostsLawsuit();
	}
	
	public double getCosts(){
		return costs;
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
		Message m = new Message();
		m.setTitle("Gerichtsverfahren beendet");
		m.setType(Message.GAME);
		
		
		m.setTargetPlayer(claimant.getCompany().getId());
		m.setMessage("Das Gerichtsverfahren gegen " + defendant.getCompany().getName() + " ist nun beendet!");
		Market.sharedInstance().sendMessage(m);
		
		m.setTargetPlayer(defendant.getCompany().getId());
		m.setMessage("Das Gerichtsverfahren gegen " + claimant.getCompany().getName() + " ist nun beendet!");
		Market.sharedInstance().sendMessage(m);
		
		active = false;
	}

	public boolean isActive() {
		return active;
	}

	public boolean isStarted() {
		return started;
	}
	
	public int getDuration(){
		return duration;
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
		
		if (!claimant.getCompany().changeMoney(costs)){
			//Insolvenz
			claimant.getCompany().insolvency();
		}
		if (!defendant.getCompany().changeMoney(costs)){
			//Insolvenz
			defendant.getCompany().insolvency();
		}
		
		//Check Winner
		double quotient = (claimant.getLevel()*1.0)/defendant.getLevel();
		
		double weightLevel = Config.getWeightLevel(); 
		double weightRound = Config.getWeightRound();
		
		double paramWin = (weightLevel*quotient + weightRound*duration)/(weightLevel + weightRound);
		double paramLose = (weightLevel*(1/quotient) + weightRound*duration)/(weightLevel + weightRound);
		
		if (utils.Probability.propability((int) (Config.getProbWinLawsuit() * paramWin))){
			//Claimant wins lawsuit
			
			informPlayer(claimant, defendant);
			
			//Refund Process Costs
			double refundedCosts = duration * Config.getRelativeAmountCostsLawsuit() * amount;
			
			double totalCosts = amount + refundedCosts;
			
			//Pay amount
			if (defendant.getCompany().changeMoney((-1)*totalCosts)){
				claimant.getCompany().changeMoney(totalCosts);
			}else{
				//Insolvenz
				
				claimant.getCompany().changeMoney(defendant.getCompany().getMoney());
				defendant.getCompany().insolvency();
			}
			
			
		}else if (utils.Probability.propability((int) (Config.getProbWinLawsuit() * paramLose))){
			//Defendant wins lawsuit

			informPlayer(defendant, claimant);
			
			//Refund Process Costs
			double refundedCosts = duration * Config.getRelativeAmountCostsLawsuit() * amount;
			
			//Pay amount
			if (claimant.getCompany().changeMoney((-1)*refundedCosts)){
				defendant.getCompany().changeMoney(refundedCosts);
			}else{
				//Insolvenz
				defendant.getCompany().changeMoney(claimant.getCompany().getMoney());
				claimant.getCompany().insolvency();
			}
		}
		
	}

	private void informPlayer(LegalDepartment winner, LegalDepartment loser) {
		//Inform Winner
		Message m = new Message();
		m.setTitle("Gerichtsverfahren gewonnen");
		m.setType(Message.GAME);
		m.setTargetPlayer(winner.getCompany().getId());
		m.setMessage("Sie haben das Gerichtsverfahren gegen " + loser.getCompany().getName() + " gewonnen!");
		Market.sharedInstance().sendMessage(m);
		
		//Inform Loser
		m = new Message();
		m.setTitle("Gerichtsverfahren verloren");
		m.setType(Message.GAME);
		m.setTargetPlayer(loser.getCompany().getId());
		m.setMessage("Sie haben das Gerichtsverfahren gegen " + winner.getCompany().getName() + " verloren!");
		Market.sharedInstance().sendMessage(m);
	}
}
