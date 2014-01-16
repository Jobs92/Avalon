package eventHandling;


import java.util.ArrayList;

import market.Market;
import utils.Message;
import company.Company;
import config.Config;

public class EventManager {
	
	private ArrayList<Event> groupEvents;
	private ArrayList<Event> singleEvents;
	
	
	public void createEvents(){
		groupEvents=new ArrayList<Event>();
		singleEvents=new ArrayList<Event>();
		
		
		
		for (int i = 0; i < Config.getEventAmount(); i++) {
			if (Config.getEventAllPlayers()[i]==true) {   //new Groupevents
				groupEvents.add(new Event(Config.getEventText()[i], Config.getEventType()[i] , Config.getEventValue()[i]));
			}
			else if (Config.getEventAllPlayers()[i]==false) { //new Singleevents
				singleEvents.add(new Event(Config.getEventText()[i], Config.getEventType()[i] , Config.getEventValue()[i]));
			}
				
		}
		
//		//new Groupevents		
//		groupEvents.add(new Event("Steuererhöhung", "cost", 5000));
//		groupEvents.add(new Event("Umweltkatastrophe", "cost", 10000));
//		groupEvents.add(new Event("Ökozulage", "cost", 6000));
//		
//		//new Singleevents		
//		singleEvents.add(new Event("Produktionsfehler", "cost", 5000));
//		singleEvents.add(new Event("Wettbewerbsgewinn", "earn",10000));
//		singleEvents.add(new Event("Auszeichnung Smartphone", "imageUp", 3));
//		singleEvents.add(new Event("schlechte Testberichte", "imageDown", 3));


	}
//	public static void main (String args[]){
//		EventManager evManager=new EventManager();
//		new Config();
//		evManager.createEvents();
//		System.out.println("bla");
//		evManager.simEvents();
//	}

	public void simEvents() {
		double groupChance=0.1;
		double singleChance=0.9;
		
		ArrayList<company.Company> players = market.Market.sharedInstance().getCompanies();
		
		if (Math.random()<=groupChance) {   // GruppenEvent
			Event e = groupEvents.get((int) (1+(groupEvents.size()-1)*Math.random()));
//			System.out.println("GroupEvent "+e.getText());
			String text = e.getText(); 				//extract event data;
			String type = e.getType();
						int value = e.getValue();
			
			for (Company company : players) {
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
				
				
				
				
			}
			
		}
		else  {   // Singleevent

		for (Company company : players) {
			if (Math.random()<=singleChance) {
						
					Event e = singleEvents.get((int) (1+(singleEvents.size()-1)*Math.random()));
//					System.out.println("SingleEvent "+e.getText());
					String text = e.getText(); 				//extract event data;
					String type = e.getType();
					int value = e.getValue();
					
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
			else {} // Do noting!
		}
			
					
		}
		
	}

}
