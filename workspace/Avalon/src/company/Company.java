package company;

import gameManager.GameManager;

import java.util.ArrayList;

import market.Market;
import utils.Message;
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
	private ArrayList<Message> inbox;
	private boolean ready;
	// private ArrayList<product.Product> products;

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
		if (ready){
			GameManager.sharedInstance().informReady();
		}
	}

	private ArrayList<Department> departments;
	private int id;

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
		inbox = new ArrayList<Message>();
		// products = new ArrayList<product.Product>();
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
	public void simulate() {
		for (Department d : departments) {
			d.simulate();
		}
	}

	public void addMessageToInbox(Message message) {
		inbox.add(message);
	}

	public Message[] getMessagesFromInbox() {
		Message[] result = new Message[inbox.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = inbox.get(i);
		}
		return result;
	}

	public int getId() {
		return this.id;
	}

	// public void addProduct(product.Product product){
	// products.add(product); //ToDo!!!
	// }
	//
	// public product.Product getHighestProduct(){
	// return products.get(products.size()-1); //Todo!!!
	// }
	// public void saleProduct(){
	//
	//
	// }

}
