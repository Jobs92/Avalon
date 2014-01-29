package unitTests;

import static org.junit.Assert.*;
import gameManager.GameManager;
import market.Market;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import product.Product;
import company.Company;

public class TestWarehouse {
	
	private Company company;
	private GameManager gameManager;

	@Before
	public void prepareTest() {
		gameManager = GameManager.sharedInstance();
		company = new Company();
	}

	
	@Before
	public void startGame(){
		gameManager.addPlayer(company);
	}

	@Test
	public void test() {
		// Add resource
		company.getWarehouse().addResources(100, 90);
		assertEquals(company.getWarehouse().getAmountResources(), 100);
		
		// Delete 10 resources
		for (int i = 0; i < 10; i++) {
			company.getWarehouse().getResource();
		}
		assertEquals(company.getWarehouse().getAmountResources(), 90);
		
		//Product add
		Product product = new Product(10, "test", company);
		company.getWarehouse().addProduct(product);
		assertEquals(company.getWarehouse().getHighestProduct(), product);
		
		//Product delete
		Product product2 = new Product(11, "test", company);
		company.getWarehouse().addProduct(product2);
		assertEquals(company.getWarehouse().getSingleProduct(11), product2);
		
		// Highest product
		assertEquals(company.getWarehouse().getHighestProduct().getLevel(), 11);
		
	}
	
	@After
	public void removeInstances(){
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	}

}
