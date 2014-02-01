package unitTests;

import static org.junit.Assert.*;

import gameManager.GameManager;
import market.Market;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import company.Company;
import config.Config;

public class TestJobsProduction {
	private Company company1;
	private Company company2;
	private Company company3;
	private GameManager gameManager;

	@Before
	public void createGameManager() {
		gameManager = GameManager.sharedInstance();
	}

	@Before
	public void createCompanies() {
		company1 = new Company("1");
		company2 = new Company("2");
		company3 = new Company("3");
	}
	
	@Before
	public void startGame(){
		gameManager.addPlayer(company1);
		gameManager.addPlayer(company2);
		gameManager.startGame();
	}

//	@Test
//	public void testAddCompany() {
//		gameManager.addPlayer(company3);
//		assertEquals(gameManager.getPlayer().size(), 1);
//	}
	
	@Test
	public void testNextRound() {
		company1.getWarehouse().addResources(100,100);
		company1.getProduction().produce(1, 5);
		company2.getProduction().produce(1, 5);
		company2.getWarehouse().addResources(110, 100);
		company2.getProduction().produce(1, 110);
		company1.getProduction().simulate();
		company2.getProduction().simulate();
		assertEquals(5, company1.getWarehouse().getSingleProduct(1).getAmount());
		assertEquals(1, company2.getWarehouse().getProducts().size());
		assertEquals(Config.getCompanyStartMoney() - Config.getProductionFixcost() - Config.getProductionVariableCosts()*5, company1.getMoney(), 0);
	}
	
	@After
	public void removeInstances(){
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	}
	

}
