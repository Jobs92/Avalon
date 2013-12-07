package departments;

import company.Company;

public abstract class Department {
	protected double fixcost;
	protected Company company;

	public Department(Company company) {
		this.company = company;
	}

	public abstract void simulate();

	public double getFixcost() {
		return fixcost;
	}

	public Company getCompany() {
		return company;
	}

}
