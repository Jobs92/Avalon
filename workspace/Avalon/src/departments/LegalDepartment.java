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

public class LegalDepartment extends Department {
	private int level = 1;
	private ArrayList<Lawsuit> lawsuitsAsClaimant = new ArrayList<Lawsuit>();
	private ArrayList<Lawsuit> lawsuitsAsDefendant = new ArrayList<Lawsuit>();
	private ArrayList<ExplicitSpyingCampaign> checkedCampaigns = new ArrayList<ExplicitSpyingCampaign>(); //Remember already checked Campaigns

	public LegalDepartment(Company company) {
		super(company);
	}

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
				Lawsuit l = new Lawsuit(this, company.getLegaldepartment(), foundSpyingCampaigns, sum);
				lawsuitsAsClaimant.add(l);
				
				Message m = new Message();
				m.setTitle("Spionagekampagnen gefunden!");
				m.setType(Message.GAME);
				m.setTargetPlayer(company.getId());
				m.setMessage(c.getName() + " hat Sie ausspioniert!");
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
	
	private boolean alreadyChecked(Company c){
		for (Lawsuit l : lawsuitsAsClaimant) {
			if (c.getLegaldepartment() == l.getDefendant() && !l.isActive() && !l.isStarted()) return true;
		}
		return false;
	}

	public int getLevel() {
		return level;
	}

	public void upgrade() {
		int amount = Config.getCostsUpgradeLegalDeparment();
		if (company.changeMoney(amount * -1)) {
			level++;	
		}else{
			Message m = new Message();
			m.setTitle("Geld reicht nicht aus");
			m.setType(Message.GAME);
			m.setTargetPlayer(company.getId());
			m.setMessage("Sie haben nicht genügend Geld um Ihre Rechtsabteilung zu upgraden!");
			Market.sharedInstance().sendMessage(m);
		}	
	}

	public void beSued(Lawsuit l) {
		lawsuitsAsDefendant.add(l);
		
		Message m = new Message();
		m.setTitle("Sie wurden verklagt!");
		m.setType(Message.GAME);
		m.setTargetPlayer(company.getId());
		m.setMessage(l.getClaimant().getCompany().getName() + " hat Sie verklagt!");
		Market.sharedInstance().sendMessage(m);
	}

	public void sueOpponent(Company c) {
		LegalDepartment opponent = c.getLegaldepartment();
		if (this.isAvailable()) {
			if (opponent.isAvailable()){
				for (Lawsuit l : lawsuitsAsClaimant) {
					if (!l.isActive() && !l.isStarted()){
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
				
				Message m = new Message();
				m.setTitle("Geld reicht nicht aus");
				m.setType(Message.GAME);
				m.setTargetPlayer(company.getId());
				m.setMessage("Sie haben nicht genügend Geld um die aktuelle Klage zu bezahlen!");
				Market.sharedInstance().sendMessage(m);
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
		super.payFixcosts();
		Lawsuit l;
		for (int i = 0; i < lawsuitsAsClaimant.size(); i++) {
			l = lawsuitsAsClaimant.get(i);
			if (l.isActive()) {
				l.simulate();
			}
		}
	}
}
