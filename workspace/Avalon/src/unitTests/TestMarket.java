package unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import gameManager.GameManager;
import market.Market;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class TestMarket {
	private GameManager gameManager;
	private int demand;

	@Before
	public void prepareTest() {
		demand = 1;
		gameManager=GameManager.sharedInstance();
		gameManager.startGame();
	}


	
	@Test
	public void testMarket() {
		demand=Market.sharedInstance().calculateDemand();
		assertEquals(demand, 1000);
		
	}
	

	@After
	public void removeInstances(){
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	}
	

}
