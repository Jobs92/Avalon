package company;

import departments.LegalDepartment;
import departments.Marketing;
import departments.Research;

public class Company {
	private String playername;
	private int popularity;
	private int money;
	private Marketing marketing;
	private Research research;
	private Storage storage;
	private LegalDepartment legaldepartment;
	private Purchase purchase;


// money functions	
public int getMoney() {
	return this.money;
}
public void addMoney(int value){
	this.money = this.money + value;
}
public void reduceMoney(int value){
	this.money = this.money - value;
}

// popularity functions
public int getPopularity() {
	return this.popularity;
}
public void addPopularity(int value){
	this.popularity = this.popularity + value;
}
public void reducePopularity(int value){
	this.popularity = this.popularity - value;
}

// simulation functions
 public void simActivities(){
	 
 }
 
 
public void initialize(){
	marketing = new Marketing();
	research = new Research();
	storage = new Storage();
	legaldepartment = new LegalDepartment();
	purchase = new Purchase();

	}

}

