package unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import gameManager.GameManager;
import lawsuits.Lawsuit;
import market.Market;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import campaigns.ExplicitSpyingCampaign;
import campaigns.SpyingCampaign;
import otherclasses.Supplier;
import company.Company;
import config.Config;

public class TestLegalDepartment {
	private Company company1;
	private Company company2;
	private GameManager gameManager;

	@Before
	public void prepareTest() {
		gameManager = GameManager.sharedInstance();
		company1 = new Company("TestCompany1");
		company2 = new Company("TestCompany2");
		
		gameManager.addPlayer(company1);
		gameManager.addPlayer(company2);
	}

	
	@Test
	public void testCheckOpponent() {
		company1.getLegaldepartment().checkOpponent(company2);
		assertFalse(company1.getLegaldepartment().alreadyChecked(company2));
		
		SpyingCampaign spyingCampaign = company1.getResearch().getSpyingCampaign();
		company1.getResearch().startCampaign(spyingCampaign, company2.getId());
		company1.getResearch().simulate();
		company1.getResearch().simulate();
		
		company2.getLegaldepartment().checkOpponent(company1);
		assertTrue(company2.getLegaldepartment().alreadyChecked(company1));
	}
	
	@Test
	public void testSueOpponent() {
		SpyingCampaign spyingCampaign = company1.getResearch().getSpyingCampaign();
		company1.getResearch().startCampaign(spyingCampaign, company2.getId());
		company1.getResearch().simulate();
		company1.getResearch().simulate();
		
		company2.getLegaldepartment().checkOpponent(company1);
		assertTrue(company2.getLegaldepartment().alreadyChecked(company1));
		
		company2.getLegaldepartment().sueOpponent(company1);
		
		Lawsuit l;
		assertTrue((l = company1.getLegaldepartment().getCurrentLawsuit()) != null && l.getDefendant() == company1.getLegaldepartment());
		assertTrue((l = company2.getLegaldepartment().getCurrentLawsuit()) != null && l.getClaimant() == company2.getLegaldepartment());
	}
	
	@Test
	public void testPayAmount() {
		SpyingCampaign spyingCampaign = company1.getResearch().getSpyingCampaign();
		company1.getResearch().startCampaign(spyingCampaign, company2.getId());
		company1.getResearch().simulate();
		company1.getResearch().simulate();
		
		company2.getLegaldepartment().checkOpponent(company1);
		assertTrue(company2.getLegaldepartment().alreadyChecked(company1));
		
		company2.getLegaldepartment().sueOpponent(company1);
		
		company1.getLegaldepartment().payAmount();
		assertTrue(company1.getMoney() == company2.getMoney() - 2*Config.getCostsFoundSpyingCampaign());
		assertTrue(company1.getLegaldepartment().getCurrentLawsuit() == null);
		assertTrue(company2.getLegaldepartment().getCurrentLawsuit() == null);
	}
	
	@Test
	public void testAbandonLawsuit() {
		SpyingCampaign spyingCampaign = company1.getResearch().getSpyingCampaign();
		company1.getResearch().startCampaign(spyingCampaign, company2.getId());
		company1.getResearch().simulate();
		company1.getResearch().simulate();
		
		company2.getLegaldepartment().checkOpponent(company1);
		assertTrue(company2.getLegaldepartment().alreadyChecked(company1));
		
		company2.getLegaldepartment().sueOpponent(company1);
		
		//Wrong player aborts lawsuit
		company1.getLegaldepartment().abandonLawsuit();
		assertTrue(company1.getLegaldepartment().getCurrentLawsuit() != null);
		assertTrue(company2.getLegaldepartment().getCurrentLawsuit() != null);
		
		//Claimant aborts lawsuit
		company2.getLegaldepartment().abandonLawsuit();
		assertTrue(company1.getMoney() == company2.getMoney());
		assertTrue(company1.getLegaldepartment().getCurrentLawsuit() == null);
		assertTrue(company2.getLegaldepartment().getCurrentLawsuit() == null);
	}
	
	

	@After
	public void removeInstances(){
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	}
	

}
