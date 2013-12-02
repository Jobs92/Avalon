package departments;

import otherclasses.Supplier;
import company.*;

public class Purchase extends Department {
	private Company company = null;

	public Purchase(Company c) {
		this.company = c;
	}

	public void simulate(int amount, Supplier supplier) {
		company.changeMoney((-1) * amount * supplier.getPrice());
	}

	@Override
	public void simulate() {
		// TODO Auto-generated method stub
		
	}

}
