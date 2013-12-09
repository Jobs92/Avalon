package market;

import gameManager.GameManager;

import java.util.ArrayList;

import otherclasses.Supplier;
import utils.Message;
import company.Company;
import config.Config;

public class Market {
	private int marketSaturation;
	private int buyingPower;
	private ArrayList<Company> companies = new ArrayList<Company>();
	private static Market sharedInstance;
	private ArrayList<Supplier> supplier = new ArrayList<Supplier>();
	private int demand;
	private ArrayList<ConsumerGroups> consumerGroups;

	public static Market sharedInstance() {
		if (Market.sharedInstance == null) {
			Market.sharedInstance = new Market();
		}
		return Market.sharedInstance;
	}

	public Market() {
		demand = Config.getDemand();
		buyingPower = Config.getBuyingPower();
	}

	public void simulateMarket() {
		int demand = oscillate(calculateDemand());
		demand = (int) (demand * buyingPower / 100.0);
		
		
		
		
		// TODO: machen wir zusammen
		// auch zurückschicken kommt hier irgendwo rein
	}

	public int oscillate(int i) {
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

	public ArrayList<Company> getCompanies() {
		return companies;
	}

	public void addCompany(Company company) {
		companies.add(company);
	}

	public void addSupplier(Supplier s) {
		supplier.add(s);
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

}
