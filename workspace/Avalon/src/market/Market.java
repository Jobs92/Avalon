package market;

import gameManager.GameManager;

import java.util.ArrayList;

import otherclasses.Supplier;
import utils.Message;
import company.Company;

public class Market {
	private int marketSaturation;
	private int buyingPower;
	private ArrayList<Company> companies = new ArrayList<Company>();
	private static Market sharedInstance;
	private ArrayList<Supplier> supplier = new ArrayList<Supplier>();
	
	public static Market sharedInstance() {
		if (Market.sharedInstance == null) {
			Market.sharedInstance = new Market();
		}
		return Market.sharedInstance;
	}

	public void simBuyingBehaviour() {
		// TODO: machen wir zusammen
		// auch zurückschicken kommt hier irgendwo rein
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
	
	public void addSupplier(Supplier s){
		supplier.add(s);
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

}
