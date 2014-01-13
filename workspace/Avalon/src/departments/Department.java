package departments;

import market.Market;
import utils.Message;
import company.Company;

public abstract class Department {
	protected double fixcost;
	protected Company company;

	public Department(Company company) {
		this.company = company;
	}

	public abstract void simulate();

	public double getFixcost() {
		return fixcost;
	}

	public Company getCompany() {
		return company;
	}
	
	public void payFixcosts(){
		if (!company.changeMoney((-1)*fixcost)){
			//Insolvenz
//			Message m = new Message();
//			m.setTitle("Zahlungsunf�hig");
//			m.setType(Message.GAME);
//			m.setTargetPlayer(company.getId());
//			m.setMessage("Sie k�nnen ihre Kosten nicht mehr bezahlen!");
//			Market.sharedInstance().sendMessage(m);
		}
	}

}
