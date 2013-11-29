package company;

import java.util.ArrayList;

import product.Product;
import departments.Department;
import departments.LegalDepartment;
import departments.Marketing;
import departments.Purchase;
import departments.Research;
import departments.Sales;
import departments.Warehouse;

public class Company {
	private String playername;
	private int popularity;
	private double money;
	private ArrayList<String> inbox;
	private ArrayList<Product> products;

	private ArrayList<Department> departments;

	// private Marketing marketing;
	// private Research research;
	// private Warehouse warehouse;
	// private LegalDepartment legaldepartment;
	// private Purchase purchase;
	// private Sales sales;

	public Company() {
		super();
		money = 0;
		popularity = 0;
		departments = new ArrayList<Department>();
		departments.add(new Sales());
		departments.add(new Marketing());
		departments.add(new Research());
		departments.add(new Warehouse());
		departments.add(new LegalDepartment(this));
		departments.add(new Purchase(this));
		inbox = new ArrayList<String>();
		products = new ArrayList<Product>();
	}

	public String getPlayername() {
		return playername;
	}

	public Sales getSales() {
		return (Sales) departments.get(0);
	}

	public Marketing getMarketing() {
		return (Marketing) departments.get(1);
	}

	public Research getResearch() {
		return (Research) departments.get(2);
	}

	public Warehouse getWarehouse() {
		return (Warehouse) departments.get(3);
	}

	public LegalDepartment getLegaldepartment() {
		return (LegalDepartment) departments.get(4);
	}

	public Purchase getPurchase() {
		return (Purchase) departments.get(5);
	}

	// money functions
	public double getMoney() {
		return this.money;
	}

	public void changeMoney(double value) {
		this.money = this.money + value;
	}

	// popularity functions
	public int getPopularity() {
		return this.popularity;
	}

	public void addPopularity(int value) {
		this.popularity = this.popularity + value;
	}

	public void reducePopularity(int value) {
		this.popularity = this.popularity - value;
	}

	// simulation functions
	public void simActivities() {
		for (Department d : departments) {
			d.simulate();
		}
	}

	public void addMessageToInbox(String message) {
		inbox.add(message);
	}

	public String[] getMessagesFromInox() {
		String[] result = new String[inbox.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = inbox.get(i);
		}
		return result;
	}
	
	public void addProduct(Product product){
		products.add(product);
	}
	
	public Product getHighestProduct(){
		return products.get(products.size()-1);
	}
}
