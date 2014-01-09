package departments;

import java.util.ArrayList;

import otherclasses.Ressources;
import product.Product;
import company.Company;

public class Warehouse  extends Department{
	
	
	private ArrayList<product.Product> products;
	private ArrayList<otherclasses.Ressources> ressources;
	
	public Warehouse(Company company) {
		super(company);
		products = new ArrayList<product.Product>();
		ressources = new ArrayList<otherclasses.Ressources>();
		initialize();
	}
	
	private void initialize() {
		//Create first product 
		Product p = new Product(1, company);
		products.add(p);
	}

	public int getAmountRessources() {
		return ressources.size();
	}
	
	public Ressources getRessource(){
		Ressources r = ressources.get(ressources.size()-1);
		ressources.remove(r);
		return r;
	}

	public void addRessources(int amount, int quality) {
		for (int i = 0; i < amount; i++) {
			ressources.add(new Ressources(quality));
		}
	}

	public ArrayList<product.Product> getProducts() {
		return products;
	}
	
	public product.Product getSingleProduct(int level){
		for (int i = 0; i < products.size(); i++) {
			
			if (products.get(i).getLevel()==level) {
				return products.get(i);
			}
			
		}
		return null;
		
	}

	public void addProduct(product.Product product){
		products.add(product);
	}
	
	public product.Product getHighestProduct(){
		return products.get(products.size()-1);
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
