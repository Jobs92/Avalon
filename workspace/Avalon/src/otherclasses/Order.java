package otherclasses;

public class Order {
	private int amount;
	private double price;

	public Order(Supplier supplier, int amount) {
		super();
		this.amount = amount;
		this.price = supplier.getPrice();
	}

	public double getCost() {
		return amount * price;
	}
}
