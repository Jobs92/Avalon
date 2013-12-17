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
			if (utils.Probability.propability(order.getTrust())) {
				if (super.company.changeMoney((-1) * order.getCost())){
					super.company.getWarehouse().addRessources(order.getAmount(), order.getQuality());
					order.setActive(false);
				}
			}else {
				delayedOrders.add(order);
				// TODO: Meldung: Supplier hats nicht geschafft
			}

		}
		orders = delayedOrders;
	}

}
