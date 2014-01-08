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
	private ArrayList<ConsumerGroup> consumerGroups;

	public static Market sharedInstance() {
		if (Market.sharedInstance == null) {
			Market.sharedInstance = new Market();
		}
		return Market.sharedInstance;
	}

	public Market() {
		demand = Config.getDemand();
		buyingPower = Config.getBuyingPower();
		products = new ArrayList<Product>();
		// load ConsumerGroup
	}

	public void simulateMarket() {
		int demand = saisonalOscillate(calculateDemand());
		demand = (int) (demand * buyingPower / 100.0);
		oscillateConsumerGroup();
		for (ConsumerGroup cg : consumerGroups) {
			cg.simulate();
		}

		// auch zurückschicken kommt hier irgendwo rein
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
		return (int) ((((Math.random() - 0.5) * 10 * 2) + 100) / 100 * 40);
	}

	private int saisonalOscillate(int i) {
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

	private int calculateDemand() {
		demand = (int) (demand * (1 + Math.log((double) (GameManager
				.sharedInstance().getRound()) / Math.log(30.0))));
		return demand;
	}

	public void simulate() {
		for (Company c : companies) {
			c.simulate();
		}
	}

	public void addProduct(Product product) {
		products.add(product);
	}

	public int getDemand() {
		return demand;
	}

	public ArrayList<Company> getCompanies() {
		return companies;
	}
	
	public Company getCompanyById(int id){
		return companies.get(id);
	}

	public void addCompany(Company company) {
		companies.add(company);
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
	
	@SuppressWarnings("static-access")
	public void deleteInstance(){
		this.sharedInstance = null;
	}

	public Supplier getSupplierById(int supplierId) {
		return supplier.get(supplierId);
	}
	
	public void informPlayers() {
		for (Company c: companies) {
			c.informPlayer();
		}
	}

}
