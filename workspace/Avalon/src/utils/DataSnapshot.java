package utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Vector;

public class DataSnapshot implements Serializable{
	private static final long serialVersionUID = 1L;
	private double money;
	private double image;
	private double fixCosts;
	private double varCosts; // <-- ???
	private int highestProductLevel;
	private int productsOnStock;
	private int patentLevel;
	private double costsPatent;
	private ArrayList<Dictionary<String, String>> products = new ArrayList<Dictionary<String, String>>();
	private ArrayList<Dictionary<String, Double>> supplier = new ArrayList<Dictionary<String, Double>>();
	private Dictionary<String, Integer> levels = new Hashtable<String, Integer>(); //Research#LegalDepartment#Marketing#spying#patent
	private Dictionary<String, Double> upgradeCosts = new Hashtable<String, Double>();
	private ArrayList<Dictionary<String, String>> messages = new ArrayList<Dictionary<String, String>>();
	private ArrayList<Dictionary<String, String>> marketingcampaigns = new ArrayList<Dictionary<String, String>>();
	private int round;
	private Vector<String> enemyNames;
	
	public double getCostsPatent() {
		return costsPatent;
	}

	public void setCostsPatent(double costsPatent) {
		this.costsPatent = costsPatent;
	}

	public Vector<String> getEnemyNames() {
		return enemyNames;
	}

	public void addEnemyName(String enemyName) {
		this.enemyNames.add(enemyName);
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getPatentLevel() {
		return patentLevel;
	}

	public void setPatentLevel(int patentLevel) {
		this.patentLevel = patentLevel;
	}

	public void setLevels(Dictionary<String, Integer> levels) {
		this.levels = levels;
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

	public ArrayList<Dictionary<String, String>> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Dictionary<String, String>> products) {
		this.products = products;
	}
	
	public Dictionary<String, Double> getUpgradeCosts(){
		return upgradeCosts;
	}
	
	public double getUpgradeCosts(String key){
		return upgradeCosts.get(key);
	}
	
	public void addUpgradeCosts(String key, double value){
		upgradeCosts.put(key, value);
	}
	
	public void addMarktetingCampaign(int cost, int duration, int successProbability, int level, String description){
		Dictionary<String, String> d = new Hashtable<String, String>();
		d.put("cost", cost + "");
		d.put("duration", duration+ "");
		d.put("successProbability", successProbability+ "");
		d.put("description", description+ "");
		marketingcampaigns.add(d);
	}
	
	public ArrayList<Dictionary<String, String>> getMarketingCampaigns() {
		return marketingcampaigns;
		/**
		 * Für Martin:
		 * for (Dictionary<String, String> s : ds.getSupplier()) {
		 *		double trust = m.get("trust");
		 *		double quality = m.get("quality");
		 *		double price = m.get("price");
			}
		 */
	}

	public ArrayList<Dictionary<String, Double>> getSupplier() {
		return supplier;
		/**
		 * Für Martin:
		 * for (Dictionary<String, String> s : ds.getSupplier()) {
		 *		double trust = m.get("trust");
		 *		double quality = m.get("quality");
		 *		double price = m.get("price");
			}
		 */
	}

	public void addSupplier(double price, double trust, double quality) {
		Dictionary<String, Double> d = new Hashtable<String, Double>();
		d.put("trust", trust);
		d.put("price", price);
		d.put("quality", quality);
		supplier.add(d);
	}
	
	public int getLevel(String s){
		return levels.get(s);
	}

	public Dictionary<String, Integer> getLevels() {
		return levels;
	}

	public void addLevel(String s, int level) {
		levels.put(s, level);
	}

	public ArrayList<Dictionary<String, String>> getMessages() {
		return messages;
		/**
		 * Für Martin:
		 * Ich geb dir die Messages in einer Array List mit Dictionaries. du kannst dann so drauf zugreifen:
		 * for (Dictionary<String, String> m : ds.getMessages()) {
		 *		String titel = m.get("title");
		 *		String message = m.get("message");
			}
		 */
	}

	public void addMessage(String title, String message) {
		Dictionary<String, String> d = new Hashtable<String, String>();
		d.put("title", title);
		d.put("message", message);
		
		this.messages.add(d);
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
