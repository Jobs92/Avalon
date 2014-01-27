package departments;

import java.util.ArrayList;

import otherclasses.Resources;
import product.Product;
import company.Company;
import config.Config;

public class Warehouse extends Department {

	private ArrayList<product.Product> products;
	private ArrayList<otherclasses.Resources> resources;

	public Warehouse(Company company) {
		super(company);
		products = new ArrayList<product.Product>();
		resources = new ArrayList<otherclasses.Resources>();
		initialize();
	}

	private void initialize() {
		// Create first product
		Product p = new Product(1, Config.getStartProductName(), company);
		products.add(p);
	}

	public int getAmountResources() {
		int amount = 0;
		for (Resources r : resources) {
			amount += r.getAmount();
		}
		return amount;
	}

	public Resources getResource() {
		Resources r = resources.get(resources.size() - 1);
		r.useResource();
		if (r.getAmount()== 0){
			resources.remove(r);
		}
		return r;
	}

	public void addResources(int amount, int quality) {
		resources.add(new Resources(quality, amount));
	}

	public ArrayList<product.Product> getProducts() {
		return products;
	}

	public product.Product getSingleProduct(int level) {
		for (int i = 0; i < products.size(); i++) {

			if (products.get(i).getLevel() == level) {
				return products.get(i);
			}

		}
		return null;

	}

	public void addProduct(product.Product product) {
		products.add(product);
	}

	public product.Product getHighestProduct() {
		return products.get(products.size() - 1);
	}

	@Override
	public void simulate() {
	}

	public int getTotalAmountProducts() {
		int sum = 0;
		for (Product p : products) {
			sum += p.getAmount();
		}
		return sum;
	}

}
