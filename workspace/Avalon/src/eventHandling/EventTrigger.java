package eventHandling;

import java.util.ArrayList;
import market.Market;
import utils.Message;
import company.Company;

/**
 * @author Johannes
 * This class combines Events with a counter variable and simulates the events.
 *
 */
public class EventTrigger {
	private Event event;
	private int counter;

	
	/**
	 * @param e Event
	 * @param c counter variable
	 */
	public EventTrigger(Event e, int c){
		this.event = e;
		this.counter = c;

	}
	
		
	

	/**
	 * @return 
	 * This method performs group and market events (include reset events).
	 */
	public EventTrigger simulategGroupEvents() {
		String text = event.getText(); 				//extract event data;
		String type = event.getType();
		int value = event.getValue();
//		int oldBuyingPower;
		EventTrigger evt = null;

		System.out.println(event.getText());
		if (counter==0) {
			
			//MarketEvent
			if (type.equals("changeBuyingPower")) {
				Market.sharedInstance().changeBuyingPower(value);
	
				Event e=new Event("Die Kaufkraft ist wieder gestigen auf: ", "ReChangeBuyingPower", value);
				int rnd=(int) (Math.random() * (4 - 1) + 1);
				evt = new EventTrigger(e, rnd);
				
				Message m = new Message();
				m.setTitle(text);
				m.setType(Message.BROADCAST);
				m.setMessage(text);
				Market.sharedInstance().sendMessage(m);
				
			}	
			
			//Event to Reset BuyingPower
			else if (type.equals("ReChangeBuyingPower")) {	
				Message m = new Message();
				m.setTitle(text);
				m.setType(Message.BROADCAST);
				m.setMessage(text+Market.sharedInstance().getBuyingPower());
				Market.sharedInstance().sendMessage(m);
				
				Market.sharedInstance().changeBuyingPower(-1*value);
			}
			
			
			
			//Event that effects every Player
			else {
				ArrayList<company.Company> players = market.Market.sharedInstance().getCompanies();
				for (Company company : players) {
					
					
					if (type.equals("cost")) {
						company.changeMoney(-1*value);
					}
					else if (type.equals("earn")) {
						company.changeMoney(value);
					}		
				
					
					Message m = new Message();
					m.setTitle(text);
					m.setType(Message.GAME);
					m.setTargetPlayer(company.getId());
					m.setMessage(text);
					Market.sharedInstance().sendMessage(m);
					
				}
			}
			
			
		}
		else {
			counter--;
		}
		return evt;
		
	}
	/**
	 * @param company
	 * This method perform events for single user.
	 */
	public void simulategSingleEvents(Company company) {
		String text = event.getText(); 				//extract event data;
		String type = event.getType();
		int value = event.getValue();
		
		
		if (counter==0) {		
			if (type.equals("cost")) {
				company.changeMoney(-1*value);
			}
			else if (type.equals("earn")) {
				company.changeMoney(value);
			}
			else if (type.equals("imageUp")) {
				company.addPopularity(value);
			}
			else if (type.equals("imageDown")) {
				company.addPopularity(-1*value);
			}
			else if (type.equals("fakeSpy")) {
				int rndId;
				if (market.Market.sharedInstance().getCompanies().size()<2) {
					text=" ";
				}
				else {
				 do {
					rndId = (int) (Math.random()*market.Market.sharedInstance().getCompanies().size());
				} while (rndId == company.getId());
				String tgtName=market.Market.sharedInstance().getCompanyById(rndId).getName();	 
				
				text=tgtName+" "+text;	
				}
									
			}
			
			Message m = new Message();
			m.setTitle(text);
			m.setType(Message.GAME);
			m.setTargetPlayer(company.getId());
			m.setMessage(text);
			Market.sharedInstance().sendMessage(m);

		}
		else {
			counter--;
		}
		
	}
}
