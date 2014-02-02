package market;

import gameManager.GameManager;

import java.util.ArrayList;

import otherclasses.Supplier;
import product.Product;
import utils.Message;
import company.Company;
import config.Config;

public class Market {
	private ArrayList<Product> products;
	private int buyingPower;
	private ArrayList<Company> companies = new ArrayList<Company>();
	private static Market sharedInstance;
	private ArrayList<Supplier> supplier = new ArrayList<Supplier>();
	private int demand;
	private ArrayList<ConsumerGroup> consumerGroups = new ArrayList<ConsumerGroup>();

	public static Market sharedInstance() {
		if (Market.sharedInstance == null) {
			Market.sharedInstance = new Market();
		}
		return Market.sharedInstance;
	}

	private Market() {
		demand = Config.getDemand();
		buyingPower = Config.getBuyingPower();
		products = new ArrayList<Product>();
	}
	
	public void addConsumerGroup(ConsumerGroup cg){
		consumerGroups.add(cg);
	}
	
	public String getNameForId(int id){
		for (Company c : companies) {
			if (c.getId() == id){
				return c.getName();
			}
		}
		return null;
	}

	public void simulateMarket() {

		// For Testing
//		if (true) {
//			return;
//		}

		demand = saisonalOscillate(calculateDemand());
		demand = (int) (demand * buyingPower / 100.0);
		System.out.println("Die Nachfrage liegt diese Runde bei " + demand);
		oscillateConsumerGroup();
		for (ConsumerGroup cg : consumerGroups) {
			cg.simulate();
		}
		// TODO: push image for bestseller
	}

	private void oscillateConsumerGroup() {
		int sum = 100;
		for (int i = 0; i < consumerGroups.size() - 1; i++) {
			ConsumerGroup cg = consumerGroups.get(i);
			int p = oscillate(cg.getPercentage(),
					Config.getConsumerGroupOscillation());
			sum -= p;
			cg.setPercentage(p);
		}
		ConsumerGroup cg = consumerGroups.get(consumerGroups.size() - 1);
		cg.setPercentage(sum);
	}

	private int oscillate(int i, int range) {
		return (int) ((((Math.random() - 0.5) * range * 2) + 100) / 100 * i);
	}

	public int saisonalOscillate(int i) {
		int round = GameManager.sharedInstance().getRound();
		switch (round % 4) {
		case 0:
			i *= 1.2;
			break;
		case 1:
			i *= 0.85;
			break;
		case 2:
			i *= 0.95;
			break;
		case 3:
			i *= 1;
			break;

		default:
			break;
		}
		return i;
	}

	public int calculateDemand() {
		demand = (int) (Config.getDemand() * (1+7/(1 + Math.exp(-0.4 * (GameManager.sharedInstance().getRound() - 10)))));
		return demand;
	}

	public void simulate() {
		for (Company c : companies) {
			if (c.isActive()){
				c.simulate();
			}
		}
	}

	public void addProduct(Product product) {
		products.add(product);
	}

	public boolean productAlreadyOnMarket(Product p){
		for (Product product : products) {
			if (product == p){
				return true;
			}
		}
		return false;
	}
	
	public int getDemand() {
		return demand;
	}

	public ArrayList<Company> getCompanies() {
		return companies;
	}

	public Company getCompanyById(int id) {
		return companies.get(id);
	}

	public int addCompany(Company company) {
		//Index = id
		int id = companies.size();
		companies.add(company);
		return id;
	}

	public void addSupplier(Supplier s) {
		supplier.add(s);
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public ArrayList<Supplier> getSupplier() {
		return supplier;
	}
	
	public void handleInsolvency(Company c){
		
		//Remove Products
		ArrayList<Product> productsToRemove = new ArrayList<Product>();
		for (Product p : products) {
			if (p.getCompany() == c){
				productsToRemove.add(p);
			}
		}
		
		for (Product product : productsToRemove) {
			products.remove(product);
		}
		
		//Inform all Player
		Message m = new Message();
		m.setTitle(c.getName() + " ist insolvent!");
		m.setType(Message.BROADCAST);
		m.setMessage("Der Spiegel berichtet: " + c.getName() + " musste Insolvenz anmelden.");
		Market.sharedInstance().sendMessage(m);
	}

	public void sendMessage(String title, String message, int target,
			int source, int type) {
		Message m = new Message(title, message, target, source, type);
		if (type == Message.BROADCAST) {
			for (Company c : companies) {
				c.addMessageToInbox(m);
			}
		} else {
			companies.get(target).addMessageToInbox(m);
		}
	}

	public void sendMessage(Message message) {
		if (message.getType() == Message.BROADCAST) {
			for (Company c : companies) {
				c.addMessageToInbox(message);
			}
		} else {
			companies.get(message.getTargetPlayer()).addMessageToInbox(message);
		}
	}

	public void deleteInstance() {
		sharedInstance = null;
	}

	public Supplier getSupplierById(int supplierId) {
		return supplier.get(supplierId);
	}

	public void informPlayers() {
		for (Company c : companies) {
			c.handleNextRound();
		}
	}

	public int getBuyingPower() {
		return buyingPower;
	}

	public void changeBuyingPower(int buyingPower) {
		this.buyingPower += buyingPower;
	}

	public void setDemand(int demand) {
		this.demand = demand;
	}

}
