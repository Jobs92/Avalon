package product;

import company.Company;

public class Product {

	private double attractivity;
	private int level;
	private int price;
	private int amount;
	private int quality;
	private String name;
	private Company company;

	public Product(int level, String name, Company company) {
		super();
		this.company = company;
		this.level = level;
		this.amount = 0;
		this.name = name;
	}

	public int sell(int amount) { 
		int dif = this.amount - amount;
		if (this.amount >= amount) {
			System.out.println("Produkt " + name +" wird " + amount + " mal gekauft.");
			company.getSales().sell(level, amount);
		} else {
			System.out.println("Produkt " + name +" wird " + this.amount + " mal gekauft.");
			company.getSales().sell(level, this.amount);
		}
		return ((-1)*dif);
	}

	public void addLevel(int level) {
		this.level += level;
	}

	public Company getCompany() {
		return company;
	}

	public double getAttractivity() {
		return attractivity;
	}

	public void setAttractivity(double attractivity2) {
		this.attractivity = attractivity2;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void changeAmount(int amount) {
		this.amount += amount;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public void addQuality(int quality) {
		this.quality += quality;
	}
	
	public String getName(){
		return name;
	}

	
}
