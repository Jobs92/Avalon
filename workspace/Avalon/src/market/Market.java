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
<<<<<<< HEAD
<<<<<<< HEAD
	private ArrayList<Supplier> supplier;

=======
=======
>>>>>>> 8ca160347dc7c7da0de2f24ee95d9df66b758852
	private ArrayList<Supplier> supplier = new ArrayList<Supplier>();
	
>>>>>>> a1ddb2ed7e031b4df232eaf3bb0a3eb10e0660dc
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

<<<<<<< HEAD
	public void sendMessage(Message message) {
		if (message.getType() == Message.BROADCAST) {
			for (Company c : companies) {
				c.addMessageToInbox(message);
			}
		} else {
			companies.get(message.getTargetPlayer()).addMessageToInbox(message);
		}
	}
=======

>>>>>>> 8ca160347dc7c7da0de2f24ee95d9df66b758852
}
