package unitTests;

import static org.junit.Assert.assertEquals;
import gameManager.GameManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import company.Company;

import config.Config;
import departments.Marketing;
import departments.Research;

public class TestCampaignDepartments {
	Company c;
	Company c2;
	Research r;
	Marketing m;
	GameManager gm = GameManager.sharedInstance();

	@Before
	public void startGame() {
		c = new Company("Testcompany");
		c2 = new Company("Testcompany2");
		r = c.getResearch();
		m = c.getMarketing();
		gm.addPlayer(c);
		gm.addPlayer(c2);
	}

	@Test
	public void testUpgrade() {
		r.upgradeDepartment();
		assertEquals(2, r.getLevel());

		m.upgradeDepartment();
		assertEquals(2, m.getLevel());
	}

	@Test
	public void testDowngrade() {
		r.upgradeDepartment();
		r.downgrade();
		assertEquals(1, r.getLevel());

		m.upgradeDepartment();
		m.downgrade();
		assertEquals(1, m.getLevel());
	}

	@Test
	public void testDowngradeWhenLevelIsOne() {
		r.downgrade();
		assertEquals(1, r.getLevel());
	}

	@Test
	public void testUpgradeWhenLevelIsMax() {
		c.changeMoney(1000000000);
		for (int i = 0; i < 11; i++) {
			m.upgradeDepartment();
			r.upgradeDepartment();
		}
		assertEquals(Config.getMaxLevelResearch(), r.getLevel());
		assertEquals(Config.getMaxLevelMarketing(), m.getLevel());
	}

	@After
	public void shutDown() {

	}

}
