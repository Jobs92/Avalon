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
		if (products.size() != 0){
			double averagePopularity = calculateAveragePopularity();
			double averageLevel = calculateAverageLevel();
			double averagePrice = calculateAveragePrice();
			double sum = 0.0;
			// calculate attractivity for each product for this consumer group
			for (Product product : products) {
				double i = product.getCompany().getPopularity() / averagePopularity;
				double l = product.getLevel() / averageLevel;
				double p = product.getPrice() / averagePrice;
	
				double attractivity = (i * popularity + l * level + p * price) / 100;
				attractivity *= attractivity;
				sum += attractivity;
				product.setAttractivity(attractivity);
			}
			// calculate demand for this consumer group
			int demand = Market.sharedInstance().getDemand();
			demand = (int) (demand * percentage / 100.0);
	
			// sort products by attractivity
			sort(products);
	
			// calculate relative attractivity for each product
			int rest = 0;
			for (Product product : products) {
				double relativeAttractivity = product.getAttractivity() / sum;
				product.setAttractivity(relativeAttractivity);
				int amount = (int) (relativeAttractivity * demand + rest);
				rest = product.sell(amount);
				System.out.println("Produkt " + product.getName() +" wird " + amount + " mal gekauft.");
				if (rest < 0) {
					rest = 0;
				}
			}
		}
	}

	private void sort(ArrayList<Product> input) {
		Product temp = null;
		for (int i = 1; i < input.size(); i++) {
			for (int j = 0; j < input.size() - i; j++) {
				if (input.get(j).getAttractivity() > input.get(i)
						.getAttractivity()) {
					temp = input.get(j);
					input.set(j, input.get(j + 1));
					input.set(j + 1, temp);
				}
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
