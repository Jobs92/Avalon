package departments;

public class Production extends Department {
		
	private int level;
	private int capacity;
	
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void addLevel(int level) {
		this.level += level;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void addCapacity(int capacity) {
		this.capacity = capacity;
	}
	@Override
	public void simulate() {
		// TODO Auto-generated method stub
		
	}
	public void produve (int level, int amount){ 
		int targat_amount=company.getWarehouse().getSingleProduct(level).getAmount();    //Helper
		int target_price=company.getWarehouse().getSingleProduct(level).getPrice();
		
		
		
		if (capacity>amount) {
			 // ToDo: Error Massaage oder so?
		}
		else {
			
			company.getWarehouse().getSingleProduct(level).setAmount(targat_amount-amount);
			this.revenue+=amount*target_price;
		}
		
	
}
