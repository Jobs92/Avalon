package unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gameManager.GameManager;
import market.Market;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import campaigns.ExplicitMarketingCampaign;
import campaigns.MarketingCampaign;

import company.Company;

import config.Config;
import departments.Marketing;

public class TestMarketing {

	private Company c;
	Marketing m;
	MarketingCampaign mc;

	@Before
	public void prepareTest() {
		c = new Company();
		m = c.getMarketing();
		mc = new MarketingCampaign(m, "test", 100, 1, 100, 2,
				"test description");
	}

	@Test
	public void testStartCampaignFinishedSuccessfully() {
		m.startCampaign(mc);
		ExplicitMarketingCampaign emc = (ExplicitMarketingCampaign) m
				.getExplicitCampaigns().get(0);
		emc.campaignFinishedSuccessfully();
		assertEquals(Config.getCompanyStartPopularity() + 2, c.getPopularity());
	}

	@Test
	public void testStartCampaignFailed() {
		m.startCampaign(mc);
		ExplicitMarketingCampaign emc = (ExplicitMarketingCampaign) m
				.getExplicitCampaigns().get(0);
		emc.campaignFailed();
		assertNotNull(c.getMessagesFromInbox());
	}

	@After
	public void removeInstances() {
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	}

}
