package departments;

import company.Company;

import product.Product;

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
	
	public void sell (int level, int amount){ 
		//HIER ÄNDERN JOOOBSS!!!!!!
		int target_amount=company.getWarehouse().getSingleProduct(level).getAmount();    //Helper
		int target_price=company.getWarehouse().getSingleProduct(level).getPrice();
		
		if (target_amount<amount) {
			 // TODO: Fehlermeldung: nicht genug produkte im lager
		}
		else {
			company.getWarehouse().getSingleProduct(level).setAmount(target_amount-amount);
			company.changeMoney(amount*target_price);
			this.revenue+=amount*target_price;
		}
	}
	
	@Override
	public void simulate() {
	}

}
