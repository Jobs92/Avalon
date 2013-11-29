package market;

import java.util.Arrays;

import company.Company;

public class Market {
	private int marketSaturation;
	private int buyingPower;
	private Company[] companies = new Company[0];
	
	public void simBuyingBehaviour(){
		//TODO: machen wir zusammen
		//auch zurückschicken kommt hier irgendwo rein
	}
	
	public void simActivities(){
		for (Company c : companies) {
			c.simActivities();
		}
	}

	public Company[] getCompanies() {
		return companies;
	}
	
	public void addCompany(Company c){
		System.out.println(companies.length);
		companies = Arrays.copyOf(companies, companies.length+1);
		companies[companies.length-1] = c;
		System.out.println(companies.length);
	}
	
}
