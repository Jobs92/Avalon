package unitTests;

import static org.junit.Assert.*;
import gameManager.GameManager;
import market.Market;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import product.Product;
import company.Company;
import config.Config;

public class TestSales {

	
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
		//Pay fixcosts
		company.getSales().payFixcosts();
		assertEquals(company.getMoney(), Config.getCompanyStartMoney() - Config.getSalesFixcost(), 0);
		
		//Create products and get amount 
		
		company.getWarehouse().addResources(100,100);
		Product product = new Product(1, "test", company);
		company.getWarehouse().addProduct(product);
		company.getProduction().produce(1, 5);

		company.getProduction().simulate();
		
	

		company.getSales().setPrice(1, 1000);
		company.getSales().sell(1, 2); // Problem with history
		company.getSales().simulate();
		assertEquals(2000, company.getSales().getRevenue());
		
		
	}
	
	@After
	public void removeInstances(){
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	}

}
