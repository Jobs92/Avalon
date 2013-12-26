package departments;

import company.Company;



public class Sales  extends Department{
	
	public Sales(Company company) {
		super(company);
	}
	
	private int revenue;
	
	
	public int getRevenue() {
		return revenue;
	}

	public void setPrice(int level, int price){
		if (price>0){
			company.getWarehouse().getSingleProduct(level).setPrice(price);
		}
	}
	
	public int sell (int level, int amount){ 


		int availableAmount=company.getWarehouse().getSingleProduct(level).getAmount();    //Helper
		int currentPrice=company.getWarehouse().getSingleProduct(level).getPrice();		
		int div=0;
		
		if (availableAmount<amount) {
			
			 div=amount-availableAmount;
			 amount=availableAmount;   // Change amount!
			 // TODO: Fehlermeldung: nicht genug produkte im lager
		}
		else { }
			
		
			company.getWarehouse().getSingleProduct(level).changeAmount(0-amount);
			company.changeMoney(amount*currentPrice);
			this.revenue+=amount*currentPrice;
		
		
		
       return div;
	}
	
	@Override
	public void simulate() {
	}

}
