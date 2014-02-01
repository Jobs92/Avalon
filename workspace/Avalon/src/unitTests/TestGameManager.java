package unitTests;

import static org.junit.Assert.*;

import gameManager.GameManager;
import market.Market;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import company.Company;

public class TestGameManager {
	private Company company1;
	private Company company2;
	private GameManager gameManager;

	@Before
	public void createGameManager() {
		gameManager = GameManager.sharedInstance();
	}

	@Before
	public void createCompanies() {
		company1 = new Company("1");
		company2 = new Company("2");
		gameManager.addPlayer(company1);
		gameManager.addPlayer(company2);
	}

	@Test
	public void testStartGame() {
		assertEquals(false, GameManager.sharedInstance().getActive());
		gameManager.startGame();
		assertEquals(true, GameManager.sharedInstance().getActive());
	}

	@Test
	public void testNextRound() {
		gameManager.startGame();
		assertEquals(2, GameManager.sharedInstance().getPlayer().size());
		company1.setReady(true);
		company2.setReady(true);
		assertEquals(2, gameManager.getRound());
		company1.setReady(true);
		assertEquals(2, gameManager.getRound());
		company2.setReady(true);
		assertEquals(3, gameManager.getRound());
	}

	@Test
	public void testCheckWinner() {
		gameManager.startGame();
		company1.changeMoney(999999999);
		company1.setReady(true);
		company2.setReady(true);
		assertEquals(false, GameManager.sharedInstance().getActive());
		assertEquals(company1, GameManager.sharedInstance().getWinner());
	}

	@After
	public void removeInstances() {
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	}

}
