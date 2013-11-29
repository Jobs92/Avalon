package UnitTests;

import static org.junit.Assert.*;
import gameManager.GameManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import campaigns.MarketingCampaign;
import company.Company;
import departments.Marketing;

public class TestMarketing {
	Marketing marketing;
	Company company;
	GameManager gameManager;
	MarketingCampaign campaign;

	@Before
	public void setUp() throws Exception {
		company = new Company();
		gameManager = new GameManager();
		marketing = new Marketing();
		campaign = new MarketingCampaign(marketing, "TestTitle", 10000, 1, 95,
				3, "Description");
		marketing.addCampaign(campaign);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testImproveMarketing() {
		marketing.improveDepartment();
		// does not work due to GameManager faults
		assertEquals(96, campaign.getSuccessProbability());
	}

	// @Test
	// public void testStartCampaign() {
	// marketing.startCampaign(new MarketingCampaign(marketing, "TestTitle",
	// 10000, 1, 95, 3, "Description"));
	// marketing.simulate();
	// // does not work due to GameManager faults
	// assertEquals(3, company.getPopularity());
	// }

}
