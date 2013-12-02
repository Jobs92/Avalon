package UnitTests;

import static org.junit.Assert.*;
import gameManager.GameManager;

import org.junit.Before;
import org.junit.Test;

import company.Company;

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

//	@Test
//	public void testAddCompany() {
//		gameManager.addPlayer(company3);
//		assertEquals(gameManager.getPlayer().size(), 1);
//	}
	
	@Test
	public void testNextRound() {
		gameManager.addPlayer(company1);
		gameManager.addPlayer(company2);
		gameManager.startGame();
		company1.setReady(true);
		company2.setReady(true);
		assertEquals(gameManager.getRound(), 2);
		company1.setReady(true);
		assertEquals(gameManager.getRound(), 2);
		company2.setReady(true);
		assertEquals(gameManager.getRound(), 3);
	}
	

}
