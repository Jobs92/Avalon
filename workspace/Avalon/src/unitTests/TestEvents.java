package unitTests;


import static org.junit.Assert.*;

import java.util.ArrayList;

import eventHandling.Event;
import eventHandling.EventManager;
import eventHandling.EventTrigger;
import gameManager.GameManager;
import market.Market;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import company.Company;



public class TestEvents {
	private EventManager evManager;
	private ArrayList<EventTrigger> triggedGroupEvents;
	private ArrayList<EventTrigger> delayedGroupEvents;
	private Company comp;


		
	
	
	@Before
	public void init(){
		GameManager.sharedInstance();
		evManager=EventManager.sharedInstance();
		evManager.createEvents();
		triggedGroupEvents=new ArrayList<EventTrigger>();
		delayedGroupEvents=new ArrayList<EventTrigger>();
		comp=new Company();
		GameManager.sharedInstance().addPlayer(comp);
	
	}

	@Test
	public void testEvent() {
		Event e = evManager.getGroupEvents(1);
		assertEquals(5000, e.getValue());
		assertEquals("cost", e.getType());


	}
	@Test
	public void testEventTrigger() {
		
		for (int i = 0; i < evManager.getGroupEvents().size(); i++) {
			triggedGroupEvents.add(new EventTrigger(evManager.getGroupEvents(i), 0));
			
		}
		assertEquals(4, triggedGroupEvents.size());
		for (EventTrigger evt : triggedGroupEvents) {
			EventTrigger delayedEvt = evt.simulategGroupEvents();
			if (delayedEvt != null) delayedGroupEvents.add(delayedEvt);
		}
		assertEquals(1, delayedGroupEvents.size());
		
		//assertEquals((199900.0-21000.0), comp.getMoney());
			
		
		

	}
	
	@After
	public void removeInstances(){
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	
	}
	

}
