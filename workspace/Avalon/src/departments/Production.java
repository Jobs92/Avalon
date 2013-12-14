package departments;

import java.util.ArrayList;

import otherclasses.ProductionJobs;
import company.Company;
import config.Config;
import product.Product;

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
		super.company.changeMoney((-1)*fixcost);
		for (ProductionJobs job : allProductionJobs) {
			if (!job.isCompleted()){
				int amount = company.getWarehouse().getSingleProduct(job.getLevel()).getAmount()+job.getAmount();
				company.getWarehouse().getSingleProduct(job.getLevel()).setAmount(amount);
				company.getWarehouse().changeRessources((-1)*amount);
				company.changeMoney((-1)*Config.getProductionVariableCosts()*amount);
				job.setCompleted(true);
			}
		}
		
	}
	public void produce (int level, int amount){ 
		if (capacity>=amount) {
			if (countAlreadyNeededRessources()+amount <= company.getWarehouse().getRessources()){
				if (company.getWarehouse().getSingleProduct(level)==null) {
					company.getWarehouse().addProduct(new Product(level, company));
				}
				ProductionJobs job = new ProductionJobs(level, amount);
				allProductionJobs.add(job);
			}else{
				//TODO:Fehlermeldung: keine ressourcen
			}
		}
		else {
			 // TODO:Fehlermeldung: keine kapazitaet
			
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
			super.company.changeMoney((-1)*Config.getCostsUpgradeProduction());
			this.capacity=(int)(this.capacity*Config.getUpgradeProduction());
		}
	}		
}
