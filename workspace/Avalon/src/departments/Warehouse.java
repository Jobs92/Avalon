package departments;

import java.util.ArrayList;

import company.Company;

public class Warehouse  extends Department{
	
	
	private ArrayList<product.Product> products;
	private int ressources;
	
	public Warehouse(Company company) {
		super(company);
		products = new ArrayList<product.Product>();
		ressources=0;
	}
	
	public int getRessources() {
		return ressources;
	}

	public void changeRessources(int ressources) {
		this.ressources += ressources;
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

}
