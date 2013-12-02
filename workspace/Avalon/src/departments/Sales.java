package departments;

import product.Product;

public class Sales  extends Department{
	
	private int revenue;
	
	
	public int getRevenue() {
		return revenue;
	}
	


	public void setPrice(Product p, int price){
		p.setPrice(price);
	}
	
	public void sell (int level, int amount){ 
		int target_amount=company.getWarehouse().getSingleProduct(level).getAmount();    //Helper
		int target_price=company.getWarehouse().getSingleProduct(level).getPrice();
		
		if (target_amount<amount) {
			 // ToDo: Error Massaage oder so?
		}
		else {
			
			company.getWarehouse().getSingleProduct(level).setAmount(target_amount-amount);
			this.revenue+=amount*target_price;
		}
		
	}



	@Override
	public void simulate() {
		// TODO Auto-generated method stub
		
	}

}
