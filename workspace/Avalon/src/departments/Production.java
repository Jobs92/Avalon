package departments;

import java.util.ArrayList;

import market.Market;
import otherclasses.ProductionJobs;
import otherclasses.Ressources;
import company.Company;
import config.Config;
import utils.Message;

public class Production extends Department {
	private int fixcost = Config.getProductionFixcost();
	private int level;
	private int capacity;
	private ArrayList<otherclasses.ProductionJobs> allProductionJobs;
	
	public Production(Company company){
		super(company);
		allProductionJobs = new ArrayList<otherclasses.ProductionJobs>();
		level = 1;
		capacity=config.Config.getProductionCapacity();
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	@Override
	public void simulate() {
		super.payFixcosts();
		for (ProductionJobs job : allProductionJobs) {
			if (!job.isCompleted()){
				
				int defectiveGoods = 0;
				for (int i = 0; i < job.getAmount(); i++) {
					if (company.changeMoney((-1)*Config.getProductionVariableCosts())){
						Ressources r = company.getWarehouse().getRessource();
						if (utils.Probability.propability(r.getQuality())){
							company.getWarehouse().getSingleProduct(job.getLevel()).changeAmount(1);
						} else{
							defectiveGoods++;
						}
					}else{
						//Not enough money
						Message m = new Message();
						m.setTitle("Geld reicht nicht aus");
						m.setType(Message.GAME);
						m.setTargetPlayer(company.getId());
						m.setMessage("Sie haben nicht genügend Geld um alle Produktionsaufträge auszuführen!");
						Market.sharedInstance().sendMessage(m);
						
						break;
					}
					
				}
				job.setCompleted(true);
				if (defectiveGoods != 0) {
					
					//Inform User about defective goods
					Message m = new Message();
					m.setTitle("Ausschuss");
					m.setType(Message.GAME);
					m.setTargetPlayer(company.getId());
					m.setMessage("Bei der Ausführung Ihres Produktionsauftrags gab es einen Ausschuss von " + defectiveGoods + " Smartphones.");
					Market.sharedInstance().sendMessage(m);
				}
				
			}
		}
		
	}
	public void produce (int level, int amount){ 
		if (capacity>=amount + countAlreadyNeededRessources()) {
			if (countAlreadyNeededRessources()+amount <= company.getWarehouse().getAmountRessources()){
//				if (company.getWarehouse().getSingleProduct(level)==null) {
//					company.getWarehouse().addProduct(new Product(level, company));
//				}
				ProductionJobs job = new ProductionJobs(level, amount);
				allProductionJobs.add(job);
			}else{
				//Fehlermeldung: keine ressourcen
				Message m = new Message();
				m.setTitle("Nicht genügend Ressourcen");
				m.setType(Message.GAME);
				m.setTargetPlayer(company.getId());
				m.setMessage("Sie haben nicht genügend Ressourcen um " + amount + " weitere Smartphones zu produzieren!");
				Market.sharedInstance().sendMessage(m);
			}
		}
		else {
			 //Fehlermeldung: keine kapazitaet
			Message m = new Message();
			m.setTitle("Kapazität reicht nicht aus");
			m.setType(Message.GAME);
			m.setTargetPlayer(company.getId());
			m.setMessage("Ihre Kapazitäten reichen nicht aus um " + amount + " weitere Smartphones zu produzieren!");
			Market.sharedInstance().sendMessage(m);
			
		}
		
	}

	private int countAlreadyNeededRessources() {
		int amount = 0;
		for (ProductionJobs job : allProductionJobs) {
			if (!job.isCompleted()){
				amount+=job.getAmount();
			}
		}
		return amount;
	}

	public void upgrade(){
		if (this.level+1 <= Config.getMaxLevelProduction()){
			level++;
			if (super.company.changeMoney((-1)*Config.getCostsUpgradeProduction())){
				this.capacity=(int)(this.capacity*Config.getUpgradeProduction());
			}else{
				Message m = new Message();
				m.setTitle("Geld reicht nicht aus");
				m.setType(Message.GAME);
				m.setTargetPlayer(company.getId());
				m.setMessage("Sie haben nicht genügend Geld um die Produktion zu upgraden!");
				Market.sharedInstance().sendMessage(m);
			}
		}
	}		
}
