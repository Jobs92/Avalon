package unitTests;


import eventHandling.EventManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class TestEvents {
	private EventManager evManager;

	@Before
	public void createEventManager() {
		evManager=new EventManager();
	}
	
	@Before
	public void init(){
		evManager.createEvents();
	}


	@Test
	public void testEvent() {
		evManager.simEvents();
//		assertEquals(5, company1.getWarehouse().getSingleProduct(1).getAmount());
//		assertEquals(0, company2.getWarehouse().getProducts().size());
//		assertEquals(Config.getCompanyStartMoney() - Config.getProductionFixcost() - Config.getProductionVariableCosts()*5, company1.getMoney(), 0);
	}
	
	@After
	public void removeInstances(){

	
	}
	

}
