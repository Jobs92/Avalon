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
	


	public void setPrice(Product p, int price){
		p.setPrice(price);
	}
	
	public void sell (Product product, int amount){ 
		//HIER ÄNDERN JOOOBSS!!!!!!
		int target_amount=company.getWarehouse().getSingleProduct(product).getAmount();    //Helper
		int target_price=company.getWarehouse().getSingleProduct(product).getPrice();
		
		if (target_amount<amount) {
			 // ToDo: Error Massaage oder so?
		}
		else {
			
			company.getWarehouse().getSingleProduct(product).setAmount(target_amount-amount);
			this.revenue+=amount*target_price;
		}
		
	}



	@Override
	public void simulate() {
		// TODO Auto-generated method stub
		
	}

}
