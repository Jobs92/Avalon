package departments;

import company.Company;

/**
 * @author Martin
 * Abstract superclass for all departments.
 */
public abstract class Department {
	protected double fixcost;
	protected Company company;

	public Department(Company company) {
		this.company = company;
	}

	/**
	 * Is called every round to simulate the department-specific behaviour.
	 */
	public abstract void simulate();

	public double getFixcost() {
		return fixcost;
	}

	public Company getCompany() {
		return company;
	}
	
	public void payFixcosts(){
		if (!company.changeMoney((-1)*fixcost)){
			//Insolvenz
			company.insolvency();
		}
	}

}
