package departments;

import gameManager.GameManager;

import java.util.ArrayList;

import market.Market;
import otherclasses.SalesHistory;
import product.Product;
import utils.Message;
import company.Company;
import config.Config;

public class Sales extends Department {

	private ArrayList<otherclasses.SalesHistory> salesHistory;

	public Sales(Company company) {
		super(company);
		updateFixcost();
		salesHistory = new ArrayList<otherclasses.SalesHistory>();
		this.revenue = 0;
	}

	private int revenue;

	public int getRevenue() {
		return revenue;
	}

	public void setPrice(int level, int price) {
		if (price > 0) {
			if (price <= Config.getMaxPrice()) {
				Product p = company.getWarehouse().getSingleProduct(level);
				p.setPrice(price);
				if (!Market.sharedInstance().productAlreadyOnMarket(p)) {
					Market.sharedInstance().addProduct(p);
				}
			} else {
				Message m = new Message();
				m.setTitle("Preis zu hoch!");
				m.setType(Message.GAME);
				m.setTargetPlayer(company.getId());
				m.setMessage("Sales-Experten raten von einem Preis über "
						+ Config.getMaxPrice() + " ab!");
				Market.sharedInstance().sendMessage(m);
			}
		}
	}

	public int sell(int level, int amount) {

		int availableAmount = company.getWarehouse().getSingleProduct(level)
				.getAmount(); // Helper
		int currentPrice = company.getWarehouse().getSingleProduct(level)
				.getPrice();
		int div = 0;

		if (availableAmount < amount) {

			div = amount - availableAmount;
			amount = availableAmount; // Change amount!
			// Fehlermeldung: nicht genug produkte im lager
			Message m = new Message();
			m.setTitle("Lager leer!");
			m.setType(Message.GAME);
			m.setTargetPlayer(company.getId());
			m.setMessage("Sie haben nicht genügend Produkte im Lager!");
			Market.sharedInstance().sendMessage(m);

			return div;
		} else {

			company.getWarehouse().getSingleProduct(level)
					.changeAmount(0 - amount);
			company.changeMoney(amount * currentPrice);
			this.revenue += amount * currentPrice;

			for (SalesHistory sH : salesHistory) {
				if (sH.getRound() == GameManager.sharedInstance().getRound()
						&& level == sH.getLevel()) {
					sH.updateAmount(amount);
					return div;
				}
			}
			salesHistory.add(new SalesHistory(level, amount, GameManager
					.sharedInstance().getRound()));

		}

		return div;
	}

	public int getTotalAmount(int round) {
		int totalAmount = 0;
		for (otherclasses.SalesHistory list : salesHistory) {
			if (list.getRound() == round) {
				totalAmount += list.getAmount();
			}
		}
		return totalAmount;
	}

	public void sendQuarterlyReport() {
		if (GameManager.sharedInstance().getRound() > 0) {
			String message = "Verkaufte Smartphones in diesem Quartal: \n";
			if (getAmountSoldProductsCurrentRound() != 0) {
				for (SalesHistory sH : salesHistory) {
					if (sH.getRound() == GameManager.sharedInstance()
							.getRound() && sH.getAmount() != 0) {
						message += sH.getAmount()
								+ "x "
								+ company.getWarehouse()
										.getSingleProduct(sH.getLevel())
										.getName() + "\n";
					}
				}
			} else {
				message += "Es wurden keine Produkte verkauft.";
			}
			Message m = new Message();
			m.setTitle("Sales Quartalsbericht");
			m.setType(Message.GAME);
			m.setTargetPlayer(company.getId());
			m.setMessage(message);
			Market.sharedInstance().sendMessage(m);
		}
	}

	public int getAmountSoldProductsCurrentRound() {
		int sum = 0;
		for (SalesHistory sH : salesHistory) {
			if (sH.getRound() == GameManager.sharedInstance().getRound()) {
				sum += sH.getAmount();
			}
		}
		return sum;
	}

	protected void updateFixcost() {
		fixcost = Config.getSalesFixcost();
	}

	@Override
	public void simulate() {
		payFixcosts();
	}

}
