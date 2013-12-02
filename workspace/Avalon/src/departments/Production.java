package departments;

import java.util.ArrayList;

import product.Product;

public class Production extends Department {
	
		//toDo:Fixkosten
	private int level;
	private int capacity;
<<<<<<< HEAD
=======
	private int capacity_upgrade; //ToDo
>>>>>>> dfbf42e809bc470cf3d2bd66cd45d2bf0d89eaa9
	private ArrayList<otherclasses.Jobs> history;
	
	public Production(){
		history = new ArrayList<otherclasses.Jobs>();
		level = 1;
<<<<<<< HEAD
		capacity=1;
=======
		capacity=config.Config.getProductionCapacity();
		
>>>>>>> dfbf42e809bc470cf3d2bd66cd45d2bf0d89eaa9
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
				company.getWarehouse().addProduct(new Product(level));
				company.getWarehouse().getSingleProduct(level).setAmount(amount);
			} else {
				company.getWarehouse().getSingleProduct(level).setAmount(company.getWarehouse().getSingleProduct(level).getAmount()+amount);
			}
			
			
		
		}
		
	}




	public int getCapacity_upgrade() {
		return capacity_upgrade;
	}
	public void upgrade(){
		this.capacity=this.capacity*capacity_upgrade;
	}



		
	
}
