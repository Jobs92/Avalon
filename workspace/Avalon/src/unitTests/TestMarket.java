package unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import gameManager.GameManager;
import market.ConsumerGroup;
import market.Market;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import config.Config;



public class TestMarket {
	private GameManager gameManager;
	private int demand;
	private int oscillate;
	private int[] demandExcel={1186,1274,1401,1582};
	private int[] demandOscillateExcel={1008,1210,1401,1898};

	@Before
	public void prepareTest() {
		demand = 1;
		gameManager=GameManager.sharedInstance();
		gameManager.startGame();
	}


	
	@Test
	public void testMarketDemand() {
		for (int i = 0; i < 4; i++) {
			demand=Market.sharedInstance().calculateDemand();
			assertEquals(demand, demandExcel[i]);
			gameManager.nextRound();
		}
		
		
	}
	
	@Test
	public void testMarketDemandOscillate() {
		for (int i = 0; i < 4; i++) {
			demand=1000;
			demand=Market.sharedInstance().calculateDemand();
			oscillate=Market.sharedInstance().saisonalOscillate(demand);
			assertEquals(oscillate, demandOscillateExcel[i]);
			gameManager.nextRound();
		}
		
		
	}
	public void testConsumerGroups() {
		int amountConsumerGroups = Math.min(
				Config.getConsumerGroupImage().length,
				Math.min(Config.getConsumerGroupPrice().length,Math.min(Config.getConsumerGroupSize().length,
						Config.getConsumerGroupQuality().length)));
		for (int i = 0; i < amountConsumerGroups; i++) {
			ConsumerGroup cg = new ConsumerGroup(Config.getConsumerGroupImage()[i],
												Config.getConsumerGroupPrice()[i], 
												Config.getConsumerGroupQuality()[i],  
												Config.getConsumerGroupSize()[i]);
			Market.sharedInstance().addConsumerGroup(cg);
		}
		
		
		
	}
	

	@After
	public void removeInstances(){
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	}
	

}
