package departments;

import java.util.ArrayList;
import otherclasses.Order;
import company.Company;

public class Purchase extends Department {
	private Company company = null;
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
		for (Order order : orders) {
			sum += order.getCost();
		}
		orders.clear();
		super.company.changeMoney((-1) * sum);
	}
}
