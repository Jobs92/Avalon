package UnitTests;

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
		company1 = new Company();
		company2 = new Company();
		company3 = new Company();
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
		company1.getWarehouse().addRessources(10, 1);
		company1.getProduction().produce(1, 5);
		company2.getProduction().produce(1, 5);
		company2.getWarehouse().addRessources(110, 1);
		company2.getProduction().produce(1, 110);
		company1.setReady(true);
		company2.setReady(true);
		assertEquals(5, company1.getWarehouse().getSingleProduct(1).getAmount());
		assertEquals(0, company2.getWarehouse().getProducts().size());
		assertEquals(Config.getCompanyStartMoney() - Config.getProductionFixcost() - Config.getProductionVariableCosts()*5, company1.getMoney(), 0);
	}
	
	@After
	public void removeInstances(){
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	}
	

}
