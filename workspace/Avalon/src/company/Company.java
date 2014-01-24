package company;

import gameManager.GameManager;

import java.util.ArrayList;

import campaigns.Campaign;
import otherclasses.Supplier;
import product.Product;
import lawsuits.Lawsuit;
import market.Market;
import server.Connection;
import config.Config;
import utils.Message;
import utils.DataSnapshot;
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
	private String name;
	private boolean active;

	private ArrayList<Department> departments;
	private int id;

	// private ArrayList<product.Product> products;

	public boolean isReady() {
		return ready;
	}

	public void setId(int id) {
		this.id = id;
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

	public Company() {
		// For Unit tests withot Client/Server Architecture
		GameManager.sharedInstance();
		active = true;
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
	
	public boolean isActive(){
		return active;
	}
	
	public void setActive(boolean b){
		active = b;
	}

	public Company(Connection connection) {
		super();
		active = true;
		GameManager.sharedInstance();
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
	
	public void insolvency(){
		active = false;
		
		//Inform Playder
		Message m = new Message();
		m.setTitle("Insolvenz");
		m.setType(Message.GAME);
		m.setTargetPlayer(id);
		m.setMessage("Sie haben nicht gen�gend liquide Mittel um ihre Verbindlichkeiten zu begleichen. Sie sind insolvent.");
		Market.sharedInstance().sendMessage(m);
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
		System.out.println("bezahlt wird: " + money);
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

	public void informPlayer() {
		DataSnapshot snapshot = new DataSnapshot();

		// Fill DataSnapshot object with data
		snapshot.setMoney(this.money);
		snapshot.setImage(this.popularity);
		snapshot.setFixCosts(calcFixcosts());
		snapshot.setHighestProductLevel(getWarehouse().getHighestProduct()
				.getLevel());
		snapshot.setProductsOnStock(getWarehouse().getTotalAmountProducts());
		snapshot.setRound(GameManager.sharedInstance().getRound());
		snapshot.setPatentLevel(this.getResearch().getPatentLevel());
		snapshot.setPatentCost(Config.getCostsPatent() * GameManager.sharedInstance().getRound());
		snapshot.setSpyCost(Config.getCostSpy());
		snapshot.setNotAppliedLevels(this.getResearch().getNotAppliedLevels());
		snapshot.setResearchLevel(getResearch().getResearchLevel());
		snapshot.setHighestProductName(getWarehouse().getHighestProduct().getName());

		// Department Fixcosts
		snapshot.addDepartmentFixcost("marketing", this.getMarketing()
				.getFixcost());
		snapshot.addDepartmentFixcost("legalDepartment", this
				.getLegaldepartment().getFixcost());
		snapshot.addDepartmentFixcost("research", this.getResearch()
				.getFixcost());
		snapshot.addDepartmentFixcost("production", this.getProduction()
				.getFixcost());
		snapshot.addDepartmentFixcost("sales", this.getSales().getFixcost());
		snapshot.addDepartmentFixcost("purchase", this.getPurchase()
				.getFixcost());

		// Messages
		for (Message m : this.getMessagesFromInbox()) {
			snapshot.addMessage(m.getTitle(), m.getMessage());
		}
		clearInbox();

		// Levels
		snapshot.addLevel("production", this.getProduction().getLevel());
		snapshot.addLevel("marketing", this.getMarketing().getLevel());
		snapshot.addLevel("legalDepartment", this.getLegaldepartment()
				.getLevel());
		snapshot.addLevel("research", this.getResearch().getLevel());

		// Upgrades
		snapshot.addUpgradeCosts("production",
				Config.getCostsUpgradeProduction());
		snapshot.addUpgradeCosts("marketing", Config.getCostsUpgradeMarketing());
		snapshot.addUpgradeCosts("legalDepartment",
				Config.getCostsUpgradeLegalDeparment());
		snapshot.addUpgradeCosts("research", Config.getCostsUpgradeResearch());

		// Supplier
		for (Supplier s : Market.sharedInstance().getSupplier()) {
			snapshot.addSupplier(s.getName(), s.getPrice(), s.getTrustiness(),
					s.getQuality());
		}

		// Names
		for (Company c : Market.sharedInstance().getCompanies()) {
			if (c != this && c.isActive()) {
				// Enemy Names
				snapshot.addEnemyName(c.getName(), c.getId());
				
				//Checked Enemies
				double amount = this.getLegaldepartment().getAmountForEnemy(c);
				snapshot.addCheckedEnemy(c.getName(), amount, c.getId());
			}
	
		}

		// LegalDepartment
		Lawsuit l;
		if ((l = this.getLegaldepartment().getCurrentLawsuit()) != null) {
			snapshot.setLawsuit(l.getClaimant().getCompany().getName(), l
					.getDefendant().getCompany().getName(), l.getDuration(),
					l.getAmount(), l.getCosts());
		}

		// MarketingCampaigns
		for (Campaign c : this.getMarketing().getCampaigns()) {
			snapshot.addMarketingCampaign(c.getCost(), c.getDuration(),
					c.getSuccessProbability(), c.getLevel(), c.getTitle(),
					c.getDescription());
		}

		// ResearchCampaigns
		for (Campaign c : this.getResearch().getCampaigns()) {
			snapshot.addResearchCampaign(c.getCost(), c.getDuration(),
					c.getSuccessProbability(), c.getLevel(), c.getTitle(),
					c.getDescription());
		}
		
		//Products
		for (Product p : this.getWarehouse().getProducts()) {
			if (p.getAmount() > 0){
				snapshot.addProduct(p.getName(), p.getLevel(), p.getAmount(), p.getPrice());
			}
		}
		
		//Market Products
		for (Product p : Market.sharedInstance().getProducts()) {
			if (p.getAmount() > 0){
				snapshot.addMarketProduct(p.getCompany().getName(), p.getName(), p.getLevel(), p.getPrice());
			}
		}

		// Production
		snapshot.setResources(getWarehouse().getAmountResources());

		connection.sendSnapshot(snapshot);
	}

	private void clearInbox() {
		inbox = new ArrayList<Message>();

	}

	private double calcFixcosts() {
		double sum = 0;
		for (Department d : departments) {
			sum += d.getFixcost();
		}
		return sum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Connection getConnection() {
		return connection;
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
