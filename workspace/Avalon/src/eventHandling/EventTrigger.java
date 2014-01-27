package eventHandling;

import java.util.ArrayList;

import market.Market;
import utils.Message;
import company.Company;

public class EventTrigger {
	private Event event;
	private int counter;

	
	public EventTrigger(Event e, int c){
		this.event = e;
		this.counter = c;

	}

	public void simulategGroupEvents() {
		String text = event.getText(); 				//extract event data;
		String type = event.getType();
		int value = event.getValue();
		int oldBuyingPower;
		

		
		if (counter==0) {
			//MarketEvent
			if (type.equals("changeBuyingPower")) {
			oldBuyingPower=Market.sharedInstance().getBuyingPower();	
			Event e=new Event("Kaufkraft ist wieder auf "+oldBuyingPower+" gestiegen", "changeBuyingPower", oldBuyingPower);
			int rnd=(int) (Math.random() * (4 - 1) + 1);
			EventManager.sharedInstance().addEventTrigger(new EventTrigger(e, rnd));
			
			Message m = new Message();
			m.setTitle(text);
			m.setType(Message.BROADCAST);
			m.setMessage(text);
			Market.sharedInstance().sendMessage(m);
			
			Market.sharedInstance().changeBuyingPower(value);
				
			}
			//Event that effects every Player
			else {
				ArrayList<company.Company> players = market.Market.sharedInstance().getCompanies();
				for (Company company : players) {
					Message m = new Message();
					m.setTitle(text);
					m.setType(Message.GAME);
					m.setTargetPlayer(company.getId());
					m.setMessage(text);
					Market.sharedInstance().sendMessage(m);
					
					if (type.equals("cost")) {
						company.changeMoney(-1*value);
					}
					else if (type.equals("earn")) {
						company.changeMoney(value);
					}				
					
				}
			}
			
			
			
			
		}
		else {
			counter--;
		}
		
	}
	public void simulategSingleEvents(Company company) {
		String text = event.getText(); 				//extract event data;
		String type = event.getType();
		int value = event.getValue();
		
		
		if (counter==0) {
			
			
			Message m = new Message();
			m.setTitle(text);
			m.setType(Message.GAME);
			m.setTargetPlayer(company.getId());
			m.setMessage("Das Ereignis "+text+" ist eingetreten!");
			Market.sharedInstance().sendMessage(m);
			
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
		}
		else {
			counter--;
		}
		
	}
}
