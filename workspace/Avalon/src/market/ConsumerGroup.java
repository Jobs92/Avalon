package market;

public class ConsumerGroup {
	private int popularity;
	private int price;
	private int level;
	private int percentage;
	
	public ConsumerGroup(int popularity, int price, int level, int percentage){
		this.popularity = popularity;
		this.price 		= price;
		this.level		= level;
		this.percentage	= percentage;
	}
	
	
	public int getPopularity() {
		return popularity;
	}


	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public int getPercentage() {
		return percentage;
	}


	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}


	public void simulate(){
		
	}
	
}
