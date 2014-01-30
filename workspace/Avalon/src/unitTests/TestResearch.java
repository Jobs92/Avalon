package unitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import gameManager.GameManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import campaigns.Campaign;
import company.Company;
import departments.Research;

public class TestResearch {
	Company c;
	Research r;
	GameManager gm = GameManager.sharedInstance();
	
	@Before
	public void startGame(){
		c = new Company("Testcompany");
		r = c.getResearch();
		gm.addPlayer(c);
		gm.startGame();
	}
	
	@Test
	public void testStartCampaign(){
		ArrayList<Campaign> campaigns = r.getCampaigns();
		r.startCampaign(campaigns.get(0));
		for (int i = 0; i < campaigns.get(0).getDuration(); i++) {
			c.setReady(true);
		}
	}
	
	@After
	public void shutDown(){
		gm.deleteInstance();
	}

}
