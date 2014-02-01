package unitTests;

import static org.junit.Assert.*;

import gameManager.GameManager;
import market.Market;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import company.Company;
import config.Config;

public class TestFixcostProduction {
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
	public void testFixAndVarcosts() {
		double fixcosts = Config.getProductionFixcost();
		double varcosts = Config.getProductionVariableCosts();
		
		//FixCosts
		double money = company.getMoney();
		company.getProduction().simulate();
		assertEquals(money-fixcosts, company.getMoney(), 0);
		
		//Var Costs
		money = company.getMoney();
		company.getWarehouse().addResources(10, 100);
		company.getProduction().produce(1, 10);
		company.getProduction().simulate();
		assertEquals(money-fixcosts-10*varcosts, company.getMoney(), 0);
	}
	
	@Test
	public void testUpgradeAndDowngrade(){
		//Einfach Upgrade
		company.getProduction().upgrade();
		assertEquals(2, company.getProduction().getLevel());
		
		//Einfaches Downgrade
		company.getProduction().downgrade();
		assertEquals(1, company.getProduction().getLevel());
		
		//Downgrade auf 0
		company.getProduction().downgrade();
		assertEquals(1, company.getProduction().getLevel());
		
		//Testen max. Level
		for (int i = 0; i < 40; i++) {
			company.getProduction().upgrade();
		}
		assertEquals(Config.getMaxLevelProduction(), company.getProduction().getLevel());
		
	}
	
	@Test
	public void testAmountResources(){
		//Grenze: Resources
		company.changeMoney(9999999);
		company.getWarehouse().addResources(5, 100);
		company.getProduction().produce(1, 100);
		company.simulate();
		assertEquals(5, company.getWarehouse().getTotalAmountProducts());
	}
		
	@Test
	public void testCapacity(){
		//Grenze: Kapazität
		company.changeMoney(9999999);
		company.getWarehouse().addResources(5000000, 100);
		company.getProduction().produce(1, 5000000);
		company.simulate();
		assertEquals(Config.getProductionCapacity(), company.getWarehouse().getTotalAmountProducts());
	}
	
	@Test
	public void warehouseCount100Quality(){
	company.changeMoney(9999999.0);
	
	//Ohne Ausschuss
	company.getWarehouse().addResources(100, 100);
	company.getProduction().produce(1, 100);
	company.getProduction().simulate();
	
	assertEquals(100, company.getWarehouse().getTotalAmountProducts());
	}
	
	@Test
	public void warehouseCount90Quality(){
	company.changeMoney(9999999.0);
	
	//Mit Ausschuss
	company.getWarehouse().addResources(100000, 90);
	company.getProduction().produce(1, 100000);
	company.getProduction().simulate();
	assertEquals(90000, company.getWarehouse().getTotalAmountProducts(),300.0);
	}
	
	@After
	public void removeInstances(){
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	}
	

}
