package unitTests;

import static org.junit.Assert.*;
import gameManager.GameManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import campaigns.ExplicitResearchCampaign;
import campaigns.ExplicitSpyingCampaign;
import campaigns.ResearchCampaign;
import campaigns.SpyingCampaign;
import company.Company;
import departments.Research;

public class TestResearch {
	Company c;
	Company c2;
	Research r;
	ResearchCampaign rc;
	SpyingCampaign sc;
	GameManager gm = GameManager.sharedInstance();

	@Before
	public void startGame() {
		c = new Company("Testcompany");
		c2 = new Company("Testcompany2");
		gm.addPlayer(c);
		gm.addPlayer(c2);

		r = c.getResearch();
		rc = new ResearchCampaign(r, "test", 100, 1, 100, 2, "test description");
		sc = new SpyingCampaign(r, "testspy", 100, 1, 100, 3, "testdescription");
	}

	@Test
	public void testStartCampaignFinishedSuccessfully() {
		r.startCampaign(rc);
		ExplicitResearchCampaign erc = (ExplicitResearchCampaign) r
				.getExplicitCampaigns().get(0);
		erc.campaignFinishedSuccessfully();
		assertEquals(2, r.getNotAppliedLevels());
	}

	@Test
	public void testStartCampaignFailed() {
		r.startCampaign(rc);
		ExplicitResearchCampaign erc = (ExplicitResearchCampaign) r
				.getExplicitCampaigns().get(0);
		erc.campaignFailed();
		assertEquals(0, r.getNotAppliedLevels());
	}

	@Test
	public void testApplyResearchResults() {
		r.startCampaign(rc);
		ExplicitResearchCampaign erc = (ExplicitResearchCampaign) r
				.getExplicitCampaigns().get(0);
		erc.campaignFinishedSuccessfully();
		r.applyResearchResults("testname");
		assertEquals(3, r.getResearchLevel());
		assertEquals(0, r.getNotAppliedLevels());
		assertNotNull(c.getWarehouse().getProducts().get(0));
	}

	@Test
	public void testPatentResearchLevel() {
		r.startCampaign(rc);
		ExplicitResearchCampaign erc = (ExplicitResearchCampaign) r
				.getExplicitCampaigns().get(0);
		erc.campaignFinishedSuccessfully();
		r.patentResearchLevel();
		assertEquals(3, r.getPatentLevel());
	}

	@Test
	public void testStartSpyingCampaignFinishedSuccessfully() {
		r.startCampaign(sc, 0);
		ExplicitSpyingCampaign erc = (ExplicitSpyingCampaign) r
				.getExplicitCampaigns().get(0);
		erc.campaignFinishedSuccessfully();
		assertEquals(3, r.getSpiedLevels());
	}

	@Test
	public void testStartSpyingCampaignFailed() {
		r.startCampaign(sc, 1);
		ExplicitSpyingCampaign erc = (ExplicitSpyingCampaign) r
				.getExplicitCampaigns().get(0);
		erc.campaignFailed();
		assertEquals(0, r.getSpiedLevels());
	}

	@After
	public void shutDown() {
		gm.deleteInstance();
	}

}
