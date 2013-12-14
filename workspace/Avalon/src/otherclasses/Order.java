package otherclasses;

public class Order {
	private int amount;
	private Supplier supplier;
	private boolean active = true;

	public Order(Supplier supplier, int amount) {
		super();
		this.amount = amount;
		this.supplier = supplier;
	}

	public double getCost() {
		return amount * supplier.getPrice();
	}
	
	public int getAmount(){
		return amount;
	}
	
	public int getQuality(){
		return supplier.getQuality();
	}
	
	public int getTrust(){
		return supplier.getTrustiness();
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
