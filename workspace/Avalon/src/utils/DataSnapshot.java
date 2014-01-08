package utils;

import java.io.Serializable;

public class DataSnapshot implements Serializable{
	private double money;
	private double image;
	private double fixCosts;
	private double varCosts;
	private int highestProductLevel;
	private int productsOnStock;
	private String[] products;
	private String[] supplier;
	private int[] level; //Research#LegalDepartment#Marketing#spying#patent
	private String[] messages;
	private int round;
	

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getImage() {
		return image;
	}

	public void setImage(double image) {
		this.image = image;
	}

	public double getFixCosts() {
		return fixCosts;
	}

	public void setFixCosts(double fixCosts) {
		this.fixCosts = fixCosts;
	}

	public double getVarCosts() {
		return varCosts;
	}

	public void setVarCosts(double varCosts) {
		this.varCosts = varCosts;
	}

	public String[] getProducts() {
		return products;
	}

	public void setProducts(String[] products) {
		this.products = products;
	}

	public String[] getSupplier() {
		return supplier;
	}

	public void setSupplier(String[] supplier) {
		this.supplier = supplier;
	}

	public int[] getLevel() {
		return level;
	}

	public void setLevel(int[] level) {
		this.level = level;
	}

	public String[] getMessages() {
		return messages;
	}

	public void setMessages(String[] messages) {
		this.messages = messages;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getHighestProductLevel() {
		return highestProductLevel;
	}

	public void setHighestProductLevel(int highestProductLevel) {
		this.highestProductLevel = highestProductLevel;
	}

	public int getProductsOnStock() {
		return productsOnStock;
	}

	public void setProductsOnStock(int productsOnStock) {
		this.productsOnStock = productsOnStock;
	}
	
	
	
}
