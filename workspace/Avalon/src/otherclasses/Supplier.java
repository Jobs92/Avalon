package otherclasses;

public class Supplier {
	private double price;
	private int trustiness;
	private int qualitiy;
	private String name;
	
	public Supplier(double price, int trustiness, int qualitiy, String name) {
		super();
		this.price = price;
		this.trustiness = trustiness;
		this.qualitiy = qualitiy;
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public int getTrustiness() {
		return trustiness;
	}
	public int getQuality() {
		return qualitiy;
	}
	
	public String getName(){
		return name;
	}
}
