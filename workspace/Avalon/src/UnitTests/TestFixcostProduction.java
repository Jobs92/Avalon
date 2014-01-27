package unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import gameManager.GameManager;
import market.Market;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import otherclasses.Supplier;
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
	public void testFixcost() {
		double money = company.getMoney();
		double costs = Config.getProductionFixcost();
		company.getProduction().simulate();
		assertEquals(money-costs, company.getMoney(), 0);
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
	public void testCapacity(){
		//Grenze: Resources
		int amount = company.getWarehouse().getTotalAmountProducts();
		company.getWarehouse().addResources(5, 100);
		company.getProduction().produce(1, 100);
		company.simulate();
		assertEquals(5, company.getWarehouse().getTotalAmountProducts()-amount);
		
		//Grenze: Kapazität
		amount = company.getWarehouse().getTotalAmountProducts();
		company.getWarehouse().addResources(5000000, 100);
		company.getProduction().produce(1, 5000000);
		company.simulate();
		assertEquals(Config.getProductionCapacity(), company.getWarehouse().getTotalAmountProducts()-amount);
	}
	
	@Test
	public void warehouseCount(){
	company.changeMoney(9999999.0);
	
	//Ohne Ausschuss
	company.getWarehouse().addResources(100, 100);
	company.getProduction().produce(1, 100);
	company.getProduction().simulate();
	assertEquals(100, company.getWarehouse().getTotalAmountProducts());
	
	//Mit Ausschuss
	int amount = company.getWarehouse().getTotalAmountProducts();
	company.getWarehouse().addResources(100000, 90);
	company.getProduction().produce(1, 100000);
	company.getProduction().simulate();
	assertEquals(90000, company.getWarehouse().getTotalAmountProducts()-amount,200.0);
	}
	

	
		
//		company1.getProduction().upgrade();
//		assertEquals(2, company1.getProduction().getLevel());
//		assertEquals(1, company2.getProduction().getLevel());
//		assertEquals(Config.getCompanyStartMoney() - Config.getProductionFixcost() - Config.getCostsUpgradeProduction(), company1.getMoney(), 0);
//		for (int i = 2; i < 20; i++) {
//			company2.getProduction().upgrade();
//			if (i<=10){
//				assertEquals(i, company2.getProduction().getLevel());
//			}else{
//				assertEquals(10, company2.getProduction().getLevel());
//			}
//		}
	
	@After
	public void removeInstances(){
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	}
	

}
