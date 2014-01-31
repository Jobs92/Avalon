package departments;

import java.util.ArrayList;
import utils.Message;
import campaigns.ExplicitCampaign;
import campaigns.ExplicitResearchCampaign;
import campaigns.ExplicitSpyingCampaign;
import company.Company;
import config.Config;
import lawsuits.Lawsuit;
import market.Market;

/**
 * @author Frederik
 * Manages the Legal Department of the company.
 */
public class LegalDepartment extends Department {
	private int level = 1;
	private ArrayList<Lawsuit> lawsuitsAsClaimant = new ArrayList<Lawsuit>();
	private ArrayList<Lawsuit> lawsuitsAsDefendant = new ArrayList<Lawsuit>();
	private ArrayList<ExplicitResearchCampaign> checkedCampaigns = new ArrayList<ExplicitResearchCampaign>(); //Remember already checked Campaigns

	public LegalDepartment(Company company) {
		super(company);
		updateFixcost();
	}

	/**
	 * @param c
	 * Checks, whether the the given opponent has spied your company. If he has spied, a lawsuit object
	 * is created, which can be started now.
	 */
	public void checkOpponent(Company c) {
		
		//Player has do sue opponent, before he checks him again
		if (!alreadyChecked(c)){
			ArrayList<ExplicitCampaign> allCampaigns = c.getResearch().getExplicitCampaigns();
			ArrayList<ExplicitSpyingCampaign> foundSpyingCampaigns = new ArrayList<ExplicitSpyingCampaign>();
			//Check all Campaigns
			for (int i = 0; i < allCampaigns.size(); i++) {
				ExplicitResearchCampaign campaign = (ExplicitResearchCampaign) allCampaigns.get(i);
				
				//Check: Campaign already checked
				if (!checkedCampaigns.contains(campaign)){
					//Pay amount for checking
					if (company.changeMoney((-1)*Config.getCostsCheckCampaign())){
						//Check whether campaign is spying campaign
						if (campaign.getClass() == ExplicitSpyingCampaign.class && ((ExplicitSpyingCampaign) campaign).getSpiedPlayer() == company.getId()) {
							foundSpyingCampaigns.add((ExplicitSpyingCampaign) campaign);
						}
						checkedCampaigns.add(campaign);
					}else{
						//Not enough money
						Message m = new Message();
						m.setTitle("Geld reicht nicht aus");
						m.setType(Message.GAME);
						m.setTargetPlayer(company.getId());
						m.setMessage("Sie haben nicht genügend Geld um bei " + c.getName() + " weiter nach Spionagekampagnen zu suchen!");
						Market.sharedInstance().sendMessage(m);
						break;
					}
				}
			}
			
			//Create Lawsuit, if amount spying campaigns > 0
			if (foundSpyingCampaigns.size() != 0){
				double sum = Config.getCostsFoundSpyingCampaign() * foundSpyingCampaigns.size();
				Lawsuit l = new Lawsuit(this, c.getLegaldepartment(), sum);
				lawsuitsAsClaimant.add(l);
				
				Message m = new Message();
				m.setTitle("Spionagekampagnen gefunden!");
				m.setType(Message.GAME);
				m.setTargetPlayer(company.getId());
				m.setMessage(c.getName() + " hat Sie ausspioniert! Sie können " + c.getName() + " auf " + l.getAmount() + "$ verklagen!");
				Market.sharedInstance().sendMessage(m);
			}else{
				Message m = new Message();
				m.setTitle("Keine Spionagekampagnen gefunden!");
				m.setType(Message.GAME);
				m.setTargetPlayer(company.getId());
				m.setMessage(c.getName() + " hat Sie nicht ausspioniert!");
				Market.sharedInstance().sendMessage(m);
			}
			
		}else{
			Message m = new Message();
			m.setTitle("Aktion nicht möglich");
			m.setType(Message.GAME);
			m.setTargetPlayer(company.getId());
			m.setMessage("Sie müssen " + c.getName() + " erst verklagen, bevor Sie erneut nach Spionagekampagnen suchen wollen!");
			Market.sharedInstance().sendMessage(m);
		}
	}
	
	public boolean alreadyChecked(Company c){
		for (Lawsuit l : lawsuitsAsClaimant) {
			if (c.getLegaldepartment() == l.getDefendant() && l.getState() == Lawsuit.WAITING) return true;
		}
		return false;
	}
	
	/**
	 * @param c
	 * @return sum
	 * If the company can start a lawsuit against the given opponent, the amount of this lawsuit is returned.
	 */
	public double getAmountForEnemy(Company c){
		for (Lawsuit l : lawsuitsAsClaimant) {
			if (l.getState() != Lawsuit.FINISHED){
				return l.getAmount();
			}
		}
		return 0;
	}
	
	/**
	 * @return
	 * Returns the lawsuit, the company is currently involved.
	 */
	public Lawsuit getCurrentLawsuit(){
		
		// Check lawsuits as Claimant
		for (int i = 0; i < lawsuitsAsClaimant.size(); i++) {
			if (lawsuitsAsClaimant.get(i).getState() == Lawsuit.ACTIVE) {
				return lawsuitsAsClaimant.get(i);
			}
		}

		// Check lawsuits as Defendant
		for (int i = 0; i < lawsuitsAsDefendant.size(); i++) {
			if (lawsuitsAsDefendant.get(i).getState() == Lawsuit.ACTIVE) {
				return lawsuitsAsDefendant.get(i);
			}
		}
		return null;
	}

	public int getLevel() {
		return level;
	}

	/**
	 * Upgrades the legal department if it is possible.
	 */
	public void upgrade() {
		if (level < Config.getMaxLevelLegalDepartment()){
			int amount = Config.getCostsUpgradeLegalDeparment();
			if (company.changeMoney(amount * -1)) {
				level++;	
				updateFixcost();
			}else{
				Message m = new Message();
				m.setTitle("Geld reicht nicht aus");
				m.setType(Message.GAME);
				m.setTargetPlayer(company.getId());
				m.setMessage("Sie haben nicht genügend Geld um Ihre Rechtsabteilung zu upgraden!");
				Market.sharedInstance().sendMessage(m);
			}	
		}
	}
	
	/**
	 * Downgrades the legal department.
	 */
	public void downgrade(){
		if (level >1){
			level--;
			updateFixcost();
		}
	}
	
	public void updateFixcost(){
		fixcost = Config.getLegalDepartmentFixcost() * level;
	}

	/**
	 * @param l
	 * Informs the company, that it is sued by an opponent.
	 */
	public void beSued(Lawsuit l) {
		lawsuitsAsDefendant.add(l);
		
		Message m = new Message();
		m.setTitle("Sie wurden verklagt!");
		m.setType(Message.GAME);
		m.setTargetPlayer(company.getId());
		m.setMessage(l.getClaimant().getCompany().getName() + " hat Sie auf " + l.getAmount() + "$ verklagt!");
		Market.sharedInstance().sendMessage(m);
	}

	/**
	 * @param c
	 * The given opponent is suid if possible.
	 */
	public void sueOpponent(Company c) {
		LegalDepartment opponent = c.getLegaldepartment();
		if (this.isAvailable()) {
			if (opponent.isAvailable()){
				for (Lawsuit l : lawsuitsAsClaimant) {
					if (l.getState() == Lawsuit.WAITING){
						l.startLawsuit();
						break;
					}
				}
			}else{
				Message m = new Message();
				m.setTitle("Klage nicht möglich");
				m.setType(Message.GAME);
				m.setTargetPlayer(company.getId());
				m.setMessage(opponent.getCompany().getName() + " ist zur Zeit an einem anderen Gerichtsverfahren beteiligt und kann nicht verklagt werden!");
				Market.sharedInstance().sendMessage(m);
			}
		}else{
			Message m = new Message();
			m.setTitle("Klage nicht möglich");
			m.setType(Message.GAME);
			m.setTargetPlayer(company.getId());
			m.setMessage("Sie können " + opponent.getCompany().getName() + "  zur Zeit nicht verklagen, da Sie schon an einem anderen Gerichtsverfahren beteiligt sind!");
			Market.sharedInstance().sendMessage(m);
		}
	}

	/**
	 * @return
	 * Checks, whether the legal department is available for a lawsuit.
	 */
	private boolean isAvailable() {
		// A legal Departmant has the capacity for only one lawsuit at the same
		// time

		if (this.getCurrentLawsuit() == null){
			return true;
		}
		return false;
	}

	/**
	 * As defendant, the claimed amount is paid.
	 */
	public void payAmount() {
		Lawsuit l = lawsuitsAsDefendant.get(lawsuitsAsDefendant.size() - 1);
		if (l.getState() == Lawsuit.ACTIVE) {
			double amount = l.getAmount();
			if (company.changeMoney((-1)*amount)){
				l.getClaimant().getCompany().changeMoney(amount);
				
				l.endLawsuit();
				
				Message m = new Message();
				m.setTitle("Gerichtsverfahren beendet!");
				m.setType(Message.GAME);
				m.setTargetPlayer(l.getClaimant().getCompany().getId());
				m.setMessage(company.getName() + " zahlt Ihnen die geforderte Summe von " + l.getAmount() + "$.");
				Market.sharedInstance().sendMessage(m);
				
			}else{
				
				Message m = new Message();
				m.setTitle("Geld reicht nicht aus");
				m.setType(Message.GAME);
				m.setTargetPlayer(company.getId());
				m.setMessage("Sie haben nicht genügend Geld um die aktuelle Klage zu bezahlen!");
				Market.sharedInstance().sendMessage(m);
			}
			
		}
	}

	/**
	 * As Claimant, the lawsuit is stopped.
	 */
	public void abandonLawsuit() {
		if (lawsuitsAsClaimant.size() >0){
			Lawsuit l = lawsuitsAsClaimant.get(lawsuitsAsClaimant.size() - 1);
			if (l.getState() == Lawsuit.ACTIVE) {
				l.endLawsuit();
				
				Message m = new Message();
				m.setTitle("Klage zurückgezogen!");
				m.setType(Message.GAME);
				m.setTargetPlayer(l.getDefendant().getCompany().getId());
				m.setMessage(company.getName() + " zieht die Klage gegen Sie zurück.");
				Market.sharedInstance().sendMessage(m);
			}
		}
	}

	@Override
	public void simulate() {
		super.payFixcosts();
		Lawsuit l;
		for (int i = 0; i < lawsuitsAsClaimant.size(); i++) {
			l = lawsuitsAsClaimant.get(i);
			if (l.getState() == Lawsuit.ACTIVE) {
				l.simulate();
			}
		}
	}
}
