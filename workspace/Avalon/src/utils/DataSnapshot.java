package utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @author Frederik
 * Manages all Data sent from the server to a single client. 
 */
public class DataSnapshot implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Generell
	private int round;
	private Dictionary<String, Double> upgradeCosts = new Hashtable<String, Double>();
	private ArrayList<Dictionary<String, String>> messages = new ArrayList<Dictionary<String, String>>();
	private ArrayList<Dictionary<String, String>> supplier = new ArrayList<Dictionary<String, String>>();
	
	// Market
	private ArrayList<Dictionary<String, String>> enemyNames = new ArrayList<Dictionary<String, String>>();
	private ArrayList<Dictionary<String, String>> marketProducts = new ArrayList<Dictionary<String, String>>();
	
	//Company
	private double money;
	private double image;
	private double fixCosts;
	private int productsOnStock;
	private double varCosts; // <-- ???
	private Dictionary<String, String> departmentFixcosts 	= new Hashtable<String, String>();
	private Dictionary<String, Integer> levels 				= new Hashtable<String, Integer>(); // Research#LegalDepartment#Marketing#spying#patent

	// Research
	private ArrayList<Dictionary<String, String>> researchCampaigns = new ArrayList<Dictionary<String, String>>();
	private double spyCost;
	private int researchLevel;
	private int notAppliedLevels;

	// Sales
	private ArrayList<Dictionary<String, String>> products = new ArrayList<Dictionary<String, String>>();

	// LegalDepartment
	private Dictionary<String, String> lawsuit					 = new Hashtable<String, String>();
	private ArrayList<Dictionary<String, String>> checkedEnemies = new ArrayList<Dictionary<String, String>>();

	// Marketing
	private int patentLevel;
	private double patentCost;
	
	

	private ArrayList<Dictionary<String, String>> marketingCampaigns = new ArrayList<Dictionary<String, String>>();
	

	// Production
	private String resources;
	private int highestProductLevel;
	private String highestProductName;
	
	
	//Setter and Getter
	public void setLawsuit(String claimant, String defendant, int duration,
			double amount, double costs) {
		lawsuit.put("claimant", claimant);
		lawsuit.put("defendant", defendant);
		lawsuit.put("duration", duration + "");
		lawsuit.put("amount", amount + "");
		lawsuit.put("costs", costs + "");
	}

	public Dictionary<String, String> getLawsuit() {
		return lawsuit;
	}

	public String getDepartmentFixcosts(String key) {
		return departmentFixcosts.get(key);
	}
	
	public String getHighestProductName(){
		return highestProductName;
	}
	
	public void setHighestProductName(String s){
		highestProductName = s;
	}

	public void addDepartmentFixcost(String key, double value) {
		departmentFixcosts.put(key, value + "");
	}

	public void addCheckedEnemy(String name, double amount, int id) {
		Dictionary<String, String> d = new Hashtable<String, String>();
		d.put("name", name);
		d.put("amount", amount + "");
		d.put("id", id+"");
		checkedEnemies.add(d);
	}

	public ArrayList<Dictionary<String, String>> getCheckedEnemies() {
		return checkedEnemies;
	}

	public ArrayList<Dictionary<String, String>> getResearchCampaigns() {
		return researchCampaigns;
	}

	public double getPatentCost() {
		return patentCost;
	}

	public void setSpyCost(double cost) {
		this.spyCost = cost;
	}

	public double getSpyCost() {
		return spyCost;
	}

	public void setPatentCost(double patentCost) {
		this.patentCost = patentCost;
	}

	public ArrayList<Dictionary<String, String>> getEnemies() {
		return enemyNames;
	}

	public void addEnemyName(String enemyName, int id) {
		Dictionary<String, String> d = new Hashtable<String, String>();
		d.put("name", enemyName);
		d.put("id", id+"");
		this.enemyNames.add(d);
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

	public int getResearchLevel() {
		return researchLevel;
	}

	public void setResearchLevel(int level) {
		researchLevel = level;
	}

	public int getNotAppliedLevels() {
		return notAppliedLevels;
	}

	public void setNotAppliedLevels(int level) {
		this.notAppliedLevels = level;
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

	public void addProduct(String name, int level, int amount, double price) {
		Dictionary<String, String> d = new Hashtable<String, String>();
		d.put("name", name);
		d.put("level", level+"");
		d.put("amount", amount+"");
		d.put("price", price+"");
		products.add(d);
	}
	
	public ArrayList<Dictionary<String, String>> getMarketProducts() {
		return marketProducts;
	}
	
	public void addMarketProduct(String company, String name, int level, double price) {
		Dictionary<String, String> d = new Hashtable<String, String>();
		d.put("company", company);
		d.put("name", name);
		d.put("level", level+"");
		d.put("price", price+"");
		marketProducts.add(d);
	}

	public Dictionary<String, Double> getUpgradeCosts() {
		return upgradeCosts;
	}

	public double getUpgradeCosts(String key) {
		return upgradeCosts.get(key);
	}

	public void addUpgradeCosts(String key, double value) {
		upgradeCosts.put(key, value);
	}

	public void addMarketingCampaign(double cost, int duration,
			int successProbability, int level, String title, String description) {
		Dictionary<String, String> d = new Hashtable<String, String>();
		d.put("cost", cost + "");
		d.put("duration", duration + "");
		d.put("successProbability", successProbability + "");
		d.put("description", description + "");
		d.put("level", String.valueOf(level));
		d.put("title", title);
		marketingCampaigns.add(d);
	}

	public void addResearchCampaign(double cost, int duration,
			int successProbability, int level, String title, String description) {
		Dictionary<String, String> d = new Hashtable<String, String>();
		d.put("title", title);
		d.put("description", description);
		d.put("cost", cost + "");
		d.put("duration", duration + "");
		d.put("level", level + "");
		d.put("successprobability", successProbability + "");
		researchCampaigns.add(d);
	}

	public ArrayList<Dictionary<String, String>> getMarketingCampaigns() {
		return marketingCampaigns;
		/**
		 * Für Martin: for (Dictionary<String, String> s : ds.getSupplier()) {
		 * double trust = m.get("trust"); double quality = m.get("quality");
		 * double price = m.get("price"); }
		 */
	}

	public ArrayList<Dictionary<String, String>> getSupplier() {
		return supplier;
		/**
		 * Für Martin: for (Dictionary<String, String> s : ds.getSupplier()) {
		 * double trust = m.get("trust"); double quality = m.get("quality");
		 * double price = m.get("price"); }
		 */
	}

	public void addSupplier(String name, double price, double trust,
			double quality) {
		Dictionary<String, String> d = new Hashtable<String, String>();
		d.put("name", name);
		d.put("trust", String.valueOf(trust));
		d.put("price", String.valueOf(price));
		d.put("quality", String.valueOf(quality));
		supplier.add(d);
	}

	public int getLevel(String s) {
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
		 * Für Martin: Ich geb dir die Messages in einer Array List mit
		 * Dictionaries. du kannst dann so drauf zugreifen: for
		 * (Dictionary<String, String> m : ds.getMessages()) { String titel =
		 * m.get("title"); String message = m.get("message"); }
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

	public void setResources(int resources) {
		this.resources = String.valueOf(resources);
	}

	public String getResources() {
		return resources;
	}

}
