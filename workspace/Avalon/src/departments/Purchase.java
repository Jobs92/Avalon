package departments;

import java.util.ArrayList;

import market.Market;
import otherclasses.Order;
import utils.Message;
import company.Company;
import config.Config;

/**
 * @author Jonas Class of  the purchase department
 */
public class Purchase extends Department {
	int amount;
	private ArrayList<Order> orders;

	public Purchase(Company company) {
		super(company);
		orders = new ArrayList<Order>();
		fixcost = Config.getProductionFixcost();
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	/**
	 * Simulate all buyings of one company
	 */
	@Override
	public void simulate() {
		super.payFixcosts();
		ArrayList<Order> delayedOrders = new ArrayList<Order>();
		for (Order order : orders) {
			if (utils.Probability.propability(order.getTrust())) {
				if (super.company.changeMoney((-1) * order.getCost())) {
					super.company.getWarehouse().addResources(
							order.getAmount(), order.getQuality());
					order.setActive(false);
				} else {
					Message m = new Message();
					m.setTitle("Geld reicht nicht aus");
					m.setType(Message.GAME);
					m.setTargetPlayer(company.getId());
					m.setMessage("Sie haben nicht gen�gend Geld um bei "
							+ order.getSupplier().getName()
							+ " weitere Ressourcen zu kaufen!");
					Market.sharedInstance().sendMessage(m);
				}
			} else {
				delayedOrders.add(order);

				// Mesage to User
				Message m = new Message();
				m.setTitle("Lieferung versp�tet sich");
				m.setType(Message.GAME);
				m.setTargetPlayer(company.getId());
				m.setMessage("Die Lieferung von "
						+ order.getSupplier().getName()
						+ " versp�tet sich leider!");
				Market.sharedInstance().sendMessage(m);
			}

		}
		orders = delayedOrders;
	}

}
