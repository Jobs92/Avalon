package lawsuits;

import market.Market;
import utils.Message;
import config.Config;
import departments.LegalDepartment;


/**
 * @author Frederik 
 * Simulates a lawsuit between two Legal Departments.
 */
public class Lawsuit {
	private LegalDepartment claimant;
	private LegalDepartment defendant;
	private double amount = 0;
	private double costs = 0;
	private int duration = 0;
	
	public final static int WAITING = 0;
	public final static int ACTIVE = 1;
	public final static int FINISHED = 2;
	
	
	private int state;
	
	
	/** 
	 * @param LegalDepartment c
	 * @param Legal Department d
	 * @param ArrayList<ExplicitSpyingCampaign> spyings
	 * @param double amount
	 */
	public Lawsuit(LegalDepartment c, LegalDepartment d, double amount){
		claimant = c;
		defendant = d;
		this.amount = amount;
		costs = amount*Config.getRelativeAmountCostsLawsuit();
		state = Lawsuit.WAITING;
	}
	
	public double getCosts(){
		return costs;
	}
	
	/**
	 * Starts simulating the lawsuit
	 */
	public void startLawsuit(){
		defendant.beSued(this);
		state = Lawsuit.ACTIVE;
	}
	
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Ends the lawsuit and informs the players.
	 */
	public void endLawsuit(){
		state = Lawsuit.FINISHED;
	}

	public int getState(){
		return state;
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

	/**
	 * Simulates the lawsuit. The process costs are subtracted from the companies money. Then the winner is detected. The Probability to win is 
	 * better, when the the level of the Legal Department is higher and when the duration of the lawsuit is higher. 
	 * If the claimant wins, the defendant has to pay the claimed amount and furthermore he has to refund the process costs of the claimant.
	 * If the defendant wins, he has to refund the process costs of the claimant.
	 * If the lawsuits ends, both companies are informed.
	 */
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
			endLawsuit();
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
			endLawsuit();
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
		}else{
			//No Winner this round
			Message m = new Message();
			m.setTitle("Gerichtsentscheidung vertagt.");
			m.setType(Message.GAME);
			m.setTargetPlayer(defendant.getCompany().getId());
			m.setMessage("Die Entscheidung des Gerichts im Verfahren gegen " + claimant.getCompany().getName() + "wurde vertagt.");
			Market.sharedInstance().sendMessage(m);
			
			m.setTargetPlayer(claimant.getCompany().getId());
			m.setMessage("Die Entscheidung des Gerichts im Verfahren gegen " + defendant.getCompany().getName() + "wurde vertagt.");
			Market.sharedInstance().sendMessage(m);
		}
		
	}

	/**
	 * 
	 * Informs winner and loser about the outcome  of the lawsuit.
	 * @param winner
	 * @param loser
	 */
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
