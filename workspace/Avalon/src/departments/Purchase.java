package departments;

import java.util.ArrayList;
import otherclasses.Order;
import company.Company;

public class Purchase extends Department {
	int amount;
	private ArrayList<Order> orders;

	public Purchase(Company company) {
		super(company);
		orders = new ArrayList<Order>();
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	@Override
	public void simulate() {
		int sum = 0;
		ArrayList<Order> delayedOrders = new ArrayList<Order>();
		for (Order order : orders) {
			if (super.company.getMoney() >= order.getCost()) { // Not enough
																// money
				if (utils.Probability.propability(order.getTrust())) {
					super.company.getWarehouse().addRessources(
							order.getAmount(), order.getQuality());
					super.company.changeMoney((-1) * order.getCost());
					order.setActive(false);
				} else {
					delayedOrders.add(order);
					// TODO: Meldung: Supplier hats nicht geschafft
				}

			}
		}
		orders = delayedOrders;
	}

}
