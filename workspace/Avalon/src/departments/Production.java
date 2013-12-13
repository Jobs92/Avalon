package departments;

import java.util.ArrayList;

import company.Company;
import config.Config;
import product.Product;

public class Production extends Department {
	
	private int level;
	private int capacity;
	private ArrayList<otherclasses.Jobs> history;
	
	public Production(Company company){
		super(company);
		history = new ArrayList<otherclasses.Jobs>();
		level = 1;
		capacity=config.Config.getProductionCapacity();
		
	}
	
	
	
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void addLevel(int level) {
		this.level += level;
		this.upgrade();
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void addCapacity(int capacity) {
		this.capacity = capacity;
	}
	@Override
	public void simulate() {
		// TODO Auto-generated method stub
		
	}
	public void produce (int level, int amount){ 

		
		if (capacity<amount) {
			 // ToDo: Error Massaage oder so?
		}
		else {
			
			if (company.getWarehouse().getSingleProduct(level)==null) {
				company.getWarehouse().addProduct(new Product(level, company));
				company.getWarehouse().getSingleProduct(level).setAmount(amount);
			} else {
				company.getWarehouse().getSingleProduct(level).setAmount(company.getWarehouse().getSingleProduct(level).getAmount()+amount);
			}
			
			
		
		}
		
	}

	public void upgrade(){
		if (this.level+1 <= Config.getMaxLevelProduction()){
			level++;
			super.company.changeMoney((-1)*Config.getCostsUpgradeProduction());
			this.capacity=(int)(this.capacity*Config.getUpgradeProduction());
		}
	}



		
}
