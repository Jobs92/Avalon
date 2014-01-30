package unitTests;


import static org.junit.Assert.*;
import eventHandling.Event;
import eventHandling.EventManager;
import eventHandling.EventTrigger;
import gameManager.GameManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class TestEvents {
	private EventManager evManager;
	private EventTrigger evTrigger;

	@Before
	public void createEventManager() {

		
		
	}
	
	@Before
	public void init(){
		GameManager.sharedInstance();
		evManager=EventManager.sharedInstance();
		evManager.createEvents();
	}


	@Test
	public void testEvent() {
		Event e = evManager.getGroupEvents(1);
		assertEquals(5000, e.getValue());
//		evTrigger = new EventTrigger(e, 0);
//		evTrigger.simulategGroupEvents();
//		evManager.setGroupChance(1);
//		evManager.setSingleChance(1);
		
		
//		assertEquals(5, company1.getWarehouse().getSingleProduct(1).getAmount());
//		assertEquals(0, company2.getWarehouse().getProducts().size());
//		assertEquals(Config.getCompanyStartMoney() - Config.getProductionFixcost() - Config.getProductionVariableCosts()*5, company1.getMoney(), 0);
	}
	
	@After
	public void removeInstances(){

	
	}
	

}
