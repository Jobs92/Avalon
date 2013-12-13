package market;

import java.util.ArrayList;
import product.Product;

public class ConsumerGroup {
	private int popularity;
	private int price;
	private int level;
	private int percentage;
	private ArrayList<Product> products;

	public ConsumerGroup(int popularity, int price, int level, int percentage) {
		this.popularity = popularity;
		this.price = price;
		this.level = level;
		this.percentage = percentage;
	}

	public void simulate() {
		products = Market.sharedInstance().getProducts();
		double averagePopularity = calculateAveragePopularity();
		double averageLevel = calculateAverageLevel();
		double averagePrice = calculateAveragePrice();
		double sum = 0.0;
		for (Product product : products) {
			double i = product.getCompany().getPopularity() / averagePopularity;
			double l = product.getLevel() / averageLevel;
			double p = product.getPrice() / averagePrice;

			double attractivity = (i * popularity + l * level + p * price) / 100;
			attractivity *= attractivity;
			sum += attractivity;
			product.setAttractivity(attractivity);
		}
		int demand = Market.sharedInstance().getDemand();
		demand = (int) (demand * percentage / 100.0);

		for (Product product : products) {
			double a = product.getAttractivity() / sum;
			product.setAttractivity(a);
			int amount = (int)(a * demand);
			int rest = product.sell(amount);
			//TODO hier gehts weiter mit überschüssigen produkten (frediproblem)
			if (res) {
				
			}
		}
	}

	public double calculateAveragePopularity() {
		int sum = 0;
		for (Product product : products) {
			sum += product.getCompany().getPopularity();
		}
		return sum / products.size();
	}

	public double calculateAverageLevel() {
		int sum = 0;
		for (Product product : products) {
			sum += product.getLevel();
		}
		return sum / products.size();
	}

	public double calculateAveragePrice() {
		int sum = 0;
		for (Product product : products) {
			sum += product.getPrice();
		}
		return sum / products.size();
	}

	public double calculateAttractivity() {
		return 0;
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

}
