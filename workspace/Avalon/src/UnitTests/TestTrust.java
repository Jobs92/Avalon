package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import gameManager.GameManager;
import market.Market;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import otherclasses.Order;
import otherclasses.Supplier;
import company.Company;
import config.Config;

public class TestTrust {
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
		company1.changeMoney(4000000);
		Supplier s = Market.sharedInstance().getSupplier().get(1);
		int amount = 10000;
		for (int i = 0; i < amount; i++) {
			company1.getPurchase().addOrder(new Order(s, 1));
		}
		company1.setReady(true);
		company2.setReady(true);
		System.out.println(company1.getWarehouse().getAmountRessources());
		assertEquals(90, company1.getWarehouse().getAmountRessources()/(amount*1.0), 90);	
	}
	
	@After
	public void removeInstances(){
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	}

}
