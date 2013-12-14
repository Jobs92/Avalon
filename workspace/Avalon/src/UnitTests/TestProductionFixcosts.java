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

public class TestProductionFixcosts {
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


	@SuppressWarnings("deprecation")
	@Test
	public void testNextRound() {
		company1.setReady(true);
		company2.setReady(true);
		assertEquals(Config.getCompanyStartMoney() - Config.getProductionFixcost(), company1.getMoney(), 0);
		assertEquals(Config.getCompanyStartMoney() - Config.getProductionFixcost(), company2.getMoney(), 0);
		company1.getProduction().upgrade();
		assertEquals(2, company1.getProduction().getLevel());
		assertEquals(1, company2.getProduction().getLevel());
		assertEquals(Config.getCompanyStartMoney() - Config.getProductionFixcost() - Config.getCostsUpgradeProduction(), company1.getMoney(), 0);
		for (int i = 2; i < 20; i++) {
			company2.getProduction().upgrade();
			if (i<=10){
				assertEquals(i, company2.getProduction().getLevel());
			}else{
				assertEquals(10, company2.getProduction().getLevel());
			}
		}
		
	}
	
	@After
	public void removeInstances(){
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	}

}
