package otherclasses;

public class Supplier {
	private double price;
	private int trustiness;
	private int qualitiy;
	
	public Supplier(double price, int trustiness, int qualitiy) {
		super();
		this.price = price;
		this.trustiness = trustiness;
		this.qualitiy = qualitiy;
	}
	public double getPrice() {
		return price;
	}
	public int getTrustiness() {
		return trustiness;
	}
	public int getQualitiy() {
		return qualitiy;
	}	
}
