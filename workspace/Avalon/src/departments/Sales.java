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
	
	public void sale (Product p, int amount){
		
	}

}
