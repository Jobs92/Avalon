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

public class TestGameManager {
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
		assertEquals(2, GameManager.sharedInstance().getPlayer().size());
		company1.setReady(true);
		company2.setReady(true);
		assertEquals(2, gameManager.getRound());
		company1.setReady(true);
		assertEquals(2, gameManager.getRound());
		company2.setReady(true);
		assertEquals(3, gameManager.getRound());
		company1.changeMoney(100000);
		company1.setReady(true);
		company2.setReady(true);
		assertEquals(false, GameManager.sharedInstance().getActive());
		assertEquals(company1, GameManager.sharedInstance().getWinner());
	}
	
	@After
	public void removeInstances(){
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	}
	

}
