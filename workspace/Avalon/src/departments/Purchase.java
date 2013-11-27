package departments;
import otherclasses.Supplier;
import company.*;

public class Purchase {
	
	
	private void newPurchase(int amount, Supplier supplier){
		company.reduceMoney =  amount * Supplier.getPrice();
	}
	
	public Purchase (Company company) {
		
	}
}
