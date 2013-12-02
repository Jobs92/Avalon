package departments;

import java.util.ArrayList;

import otherclasses.Supplier;
import company.*;

public class Purchase extends Department {
	private Company company = null;

	public Purchase(Company c) {
		this.company = c;
		ArrayList<Supplier> supplier = Market.sharedInstance().getSupplier;
	}

	public void simulate(int amount, Supplier supplier) {
		company.changeMoney((-1) * amount * supplier.getPrice());
	}

	@Override
	public void simulate() {
		// TODO Auto-generated method stub
		
	}

}
