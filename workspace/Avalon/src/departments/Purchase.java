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
			if (company.getMoney() >= order.getCost()){ //Not enough money
				if (utils.Probability.propability(order.getTrust())){
					company.getWarehouse().addRessources(order.getAmount(), order.getQuality());
					company.changeMoney((-1)*order.getCost());
					orders.remove(order);
				}else{
					//TODO: Meldung: Supplier hats nicht geschafft
				}

			}
		}	
	}
}
