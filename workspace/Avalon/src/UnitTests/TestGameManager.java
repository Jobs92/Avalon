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
		gameManager.addPlayer(company1);
		assertEquals(gameManager.getPlayer().size(), 1);
	}

}
