package UnitTests;

import static org.junit.Assert.*;
import gameManager.GameManager;

import org.junit.Before;
import org.junit.Test;

import company.Company;

public class TestGameManager {
	private Company company1;
	private Company company2;
	private GameManager gameManager;

	@Before
	public void createGameManager() {
		gameManager = new GameManager();
	}

	@Before
	public void createCompanies() {
		company1 = new Company();
		company2 = new Company();
	}

	@Test
	public void testAddCompany() {
		System.out.println("bla");
		gameManager.addPlayer(company1);
		System.out.println("fas");
		assertEquals(gameManager.getPlayer().size(), 1);
	}
	
<<<<<<< HEAD
=======
//	@Test
//	public void testNextRound() {
//		gameManager.addPlayer(company1);
//		gameManager.addPlayer(company2);
//		gameManager.startGame();
//		System.out.println(gameManager.getRound());
//		assertEquals(gameManager.getRound(), 0);
//	}
	
>>>>>>> dfbf42e809bc470cf3d2bd66cd45d2bf0d89eaa9

}
