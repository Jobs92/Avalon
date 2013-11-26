package market;

import company.Company;

public class Market {
	private int marketSaturation;
	private int buyingPower;
	private Company[] companies;
	
	public void simBuyingBehaviour(){
		//TODO: machen wir zusammen
	}
	
	public void simActivities(){
		for (Company c : companies) {
			c.simActivities();
		}
	}
	
}
