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
	
	public void sale (int level, int amount){ 
		int targat_amount=company.getWarehouse().getSingleProduct(level).getAmount();    //Helper
		int target_price=company.getWarehouse().getSingleProduct(level).getPrice();
		
		
		
		if (targat_amount<amount) {
			 // ToDo: Error Massaage oder so?
		}
		else {
			
			company.getWarehouse().getSingleProduct(level).setAmount(targat_amount-amount);
			this.revenue+=amount*target_price;
		}
		
	}



	@Override
	public void simulate() {
		// TODO Auto-generated method stub
		
	}

}
