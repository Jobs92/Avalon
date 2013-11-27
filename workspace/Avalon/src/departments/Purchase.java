package departments;
import otherclasses.Supplier;
import company.*;

public class Purchase {
	private Company company = null;
	
	public Purchase (Company c){
		this.company = c;
	}
	
	private void simulate(int amount, Supplier supplier){
		 company.reduceMoney(amount * supplier.getPrice());
	}
	
	
	
}
