package unitTests;

import static org.junit.Assert.*;
import gameManager.GameManager;
import market.Market;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;








import campaigns.Campaign;
import campaigns.ExplicitCampaign;
import campaigns.ExplicitMarketingCampaign;
import campaigns.MarketingCampaign;
import company.Company;
import config.Config;
import departments.Marketing;

public class TestMarketing {

	
	private Company company;
	private GameManager gameManager;

	@Before
	public void prepareTest() {
		gameManager = GameManager.sharedInstance();
		company = new Company();
	}

	
	@Before
	public void startGame(){
		gameManager.addPlayer(company);
	}

	@Test
	public void test() {
		// Check + update fixcosts
		assertEquals(Config.getMarketingFixcost(), company.getMarketing().getFixcost(), 0);
	
	
		//Create new campain, check if campain is successfull
		company.getMarketing().addCampaign(new MarketingCampaign(company.getMarketing(), "TestTitle", 10000, 1, 95,
						3, "Description"));

		 company.getMarketing().startCampaign(new MarketingCampaign(company.getMarketing(), "TestTitle",
		10000, 1, 100, 3, "Description"));
		 company.getMarketing().simulate();
		 assertEquals(Config.getCompanyStartPopularity() + 3, company.getPopularity());


		company.getMarketing().simulate();
		assertEquals(Config.getMarketingFixcost() * company.getMarketing().getLevel(), company.getMarketing().getFixcost(), 0);
		
		//Check costs for next level
		assertEquals(Config.getCostsUpgradeMarketing(), company.getMarketing().getCostForNextLevel() );
		
		
	}
	
	@After
	public void removeInstances(){
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	}

}
