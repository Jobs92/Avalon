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

public class TestSupplier {
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
	
	@SuppressWarnings("deprecation")
	@Test //supplier
	public void testSupplier(){
		ArrayList<Supplier> sup = Market.sharedInstance().getSupplier();
		assertEquals(Config.getSupplierPrice()[0], sup.get(0).getPrice(), 0);
		assertEquals(Config.getSupplierQuality()[0], sup.get(0).getQuality(), 0);
		assertEquals(Config.getSupplierTrust()[0], sup.get(0).getTrustiness(), 0);
		assertEquals(Config.getSupplierPrice()[1], sup.get(1).getPrice(), 0);
		assertEquals(Config.getSupplierQuality()[1], sup.get(1).getQuality(), 0);
		assertEquals(Config.getSupplierTrust()[1], sup.get(1).getTrustiness(), 0);
		assertEquals(Config.getSupplierPrice()[2], sup.get(2).getPrice(), 0);
		assertEquals(Config.getSupplierQuality()[2], sup.get(2).getQuality(), 0);
		assertEquals(Config.getSupplierTrust()[2], sup.get(2).getTrustiness(), 0);
	

	}
	
	@After
	public void removeInstances(){
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	}
	

}
