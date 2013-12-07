package departments;

import otherclasses.Supplier;

import company.Company;

public class Purchase extends Department {
	private Company company = null;
	int amount;
	Supplier supplier; //only one supplier?

	// ArrayList<Supplier> supplier;

	public Purchase(Company company) {
		super(company);
		// supplier = new ArrayList<Supplier>();
	}

	// public void simulate(int amount, Supplier supplier) {
	// company.changeMoney((-1) * amount * supplier.getPrice());
	// }

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public void simulate() {
		company.changeMoney((-1) * amount * supplier.getPrice());
	}

}
