package market;

import java.util.ArrayList;

import company.Company;

public class Market {
	private int marketSaturation;
	private int buyingPower;
	private ArrayList<Company> companies;

	public void simBuyingBehaviour() {
		// TODO: machen wir zusammen
		// auch zurückschicken kommt hier irgendwo rein
	}

	public void simulate() {
		for (Company c : companies) {
			c.simulate();
		}
	}

	public ArrayList<Company> getCompanies() {
		return companies;
	}

	public void addCompany(Company company) {
		companies.add(company);
	}

}
