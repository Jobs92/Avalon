package company;

import gameManager.GameManager;

import java.util.ArrayList;

import server.Connection;
import config.Config;
import utils.Message;
import utils.SnapshotData;
import departments.Department;
import departments.LegalDepartment;
import departments.Marketing;
import departments.Production;
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
	private Connection connection;

	private ArrayList<Department> departments;
	private int id;

	// private ArrayList<product.Product> products;

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
		if (ready) {
			GameManager.sharedInstance().informReady();
		}
	}

	// private Marketing marketing;
	// private Research research;
	// private Warehouse warehouse;
	// private LegalDepartment legaldepartment;
	// private Purchase purchase;
	// private Sales sales;
	
	public Company(){
		// For Unit tests withot Client/Server Architecture
		money = Config.getCompanyStartMoney();
		popularity = Config.getCompanyStartPopularity();
		departments = new ArrayList<Department>();
		departments.add(new Sales(this));
		departments.add(new Marketing(this));
		departments.add(new Research(this));
		departments.add(new Warehouse(this));
		departments.add(new LegalDepartment(this));
		departments.add(new Purchase(this));
		departments.add(new Production(this));
		inbox = new ArrayList<Message>();
	}
	public Company(Connection connection) {
		super();
		this.connection = connection;
		money = Config.getCompanyStartMoney();
		popularity = Config.getCompanyStartPopularity();
		departments = new ArrayList<Department>();
		departments.add(new Sales(this));
		departments.add(new Marketing(this));
		departments.add(new Research(this));
		departments.add(new Warehouse(this));
		departments.add(new LegalDepartment(this));
		departments.add(new Purchase(this));
		departments.add(new Production(this));
		inbox = new ArrayList<Message>();
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

	public Production getProduction() {
		return (Production) departments.get(6);
	}

	// money functions
	public double getMoney() {
		return this.money;
	}

	public boolean changeMoney(double value) {
		System.out.println("bezahlt wird: "+ money);
		if (value > this.money) {
			return false;
		} else {
			this.money = this.money + value;
			return true;
		}
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
	
	public void informPlayer(){
		SnapshotData snapshot = new SnapshotData();
		snapshot.setMoney(this.money);
		connection.sendSnapshot(snapshot);
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
