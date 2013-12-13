package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import gameManager.GameManager;
import market.Market;

import org.junit.Before;
import org.junit.Test;

import otherclasses.Supplier;
import company.Company;
import config.Config;

public class TestProduction {
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
	}

}
