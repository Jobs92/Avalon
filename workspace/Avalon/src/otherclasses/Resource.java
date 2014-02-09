package otherclasses;

/**
 * @author Frederik
 * Manages the Resources of a single order.
 */
public class Resource {

	private int quality;
	private int amount;

	public Resource(int quality, int amount) {
		this.quality = quality;
		this.amount = amount;
	}

	public int getQuality() {
		return quality;
	}
	
	public void useResource(){
		amount--;
	}
	
	public int getAmount(){
		return amount;
	}
}
