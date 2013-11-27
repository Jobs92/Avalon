package company;

import departments.LegalDepartment;
import departments.Marketing;
import departments.Purchase;
import departments.Research;
import departments.Warehouse;

public class Company {
	private String playername;
	private double popularity;
	private double money;
	private Marketing marketing;
	private Research research;
	private Warehouse warehouse;
	private LegalDepartment legaldepartment;
	private Purchase purchase;


// money functions	
public double getMoney() {
	return this.money;
}
public void addMoney(double value){
	this.money = this.money + value;
}
public void reduceMoney(double value){
	this.money = this.money - value;
}

// popularity functions
public double getPopularity() {
	return this.popularity;
}
public void addPopularity(double value){
	this.popularity = this.popularity + value;
}
public void reducePopularity(int value){
	this.popularity = this.popularity - value;
}

// simulation functions
 public void simActivities(){
	 legaldepartment.simulate();
	 marketing.simulate();
	 research.simulate();
	 sales.simulate();
 }
 
 
public void initialize(){
	marketing = new Marketing();
	research = new Research(1);
	warehouse = new Warehouse();
	legaldepartment = new LegalDepartment(this);
	purchase = new Purchase();

	}

}

