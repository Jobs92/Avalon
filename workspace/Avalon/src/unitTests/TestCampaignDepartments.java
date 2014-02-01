package unitTests;

import static org.junit.Assert.*;
import gameManager.GameManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import campaigns.ResearchCampaign;
import campaigns.SpyingCampaign;
import company.Company;
import config.Config;
import departments.Research;

public class TestCampaignDepartments {
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
	public void testUpgrade() {
		r.upgradeDepartment();
		assertEquals(2, r.getLevel());
	}

	@Test
	public void testDowngrade() {
		r.upgradeDepartment();
		r.downgrade();
		assertEquals(1, r.getLevel());
	}

	@Test
	public void testDowngradeWhenLevelIsOne() {
		r.downgrade();
		assertEquals(1, r.getLevel());
	}

	@Test
	public void testUpgradeWhenLevelIsMax() {
		for (int i = 0; i < 12; i++) {
			r.upgradeDepartment();
		}
		assertEquals(Config.getMaxLevelResearch(), r.getLevel());
	}

	@After
	public void shutDown() {

	}

}
