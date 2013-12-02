package otherclasses;

public class Supplier {
	private double price;
	private double trustiness;
	private double qualitiy;
	
	public Supplier(double price, double trustiness, double qualitiy) {
		super();
		this.price = price;
		this.trustiness = trustiness;
		this.qualitiy = qualitiy;
	}
	public double getPrice() {
		return price;
	}
	public double getTrustiness() {
		return trustiness;
	}
	public double getQualitiy() {
		return qualitiy;
	}	
}
