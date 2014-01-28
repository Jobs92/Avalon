package eventHandling;


import java.util.ArrayList;


import company.Company;
import config.Config;

public class EventManager {
	
	private ArrayList<Event> groupEvents;
	private ArrayList<Event> singleEvents;
	private ArrayList<EventTrigger> triggedGroupEvents;
	private ArrayList<EventTrigger> triggedSingleEvents;
	private ArrayList<EventTrigger> delayedGroupEvents; 
	//private ArrayList<EventTrigger> delayedSingleEvents; //NOT USED
	private static EventManager sharedInstance;

	public static EventManager sharedInstance() {
		if (EventManager.sharedInstance == null) {
			EventManager.sharedInstance = new EventManager();
		}
		return EventManager.sharedInstance;
	}
	
	private EventManager(){
		
	}
	
	public void createEvents(){
		groupEvents=new ArrayList<Event>();
		singleEvents=new ArrayList<Event>();
		triggedGroupEvents=new ArrayList<EventTrigger>();
		delayedGroupEvents=new ArrayList<EventTrigger>();
		triggedSingleEvents=new ArrayList<EventTrigger>();
		
		
		
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
		
		double groupChance=0.2;
		double singleChance=0.3;
		
		ArrayList<company.Company> players = market.Market.sharedInstance().getCompanies();
		
		if (Math.random()<=groupChance) {   // GruppenEvent
			int var=(int) (1+(groupEvents.size()-1)*Math.random());
			Event gEvent = groupEvents.get(var-1);
			EventTrigger GroupEventTrigger = new EventTrigger(gEvent, 0);
			triggedGroupEvents.add(GroupEventTrigger);
			for (EventTrigger evt : triggedGroupEvents) {
				evt.simulategGroupEvents();
			}
			

			
		}
		else  {   // SingleEvent

		for (Company company : players) {
			if (Math.random()<=singleChance) {
				int var=(int) (1+(groupEvents.size()-1)*Math.random());
					Event sEvent = singleEvents.get(var-1);
					EventTrigger SingleEventTrigger = new EventTrigger(sEvent, 0);
					triggedSingleEvents.add(SingleEventTrigger);
					for (EventTrigger evt : triggedSingleEvents) {
						evt.simulategSingleEvents(company);
					}
				
					
					
			}
			else {} // Do noting!
		}
			
					
		}
		
	triggedGroupEvents=delayedGroupEvents;
	delayedGroupEvents.clear();
		
	}
	
	public void addEventTrigger(EventTrigger e){
		triggedGroupEvents.add(e);
	}
	public void addDelayedEvent(EventTrigger e){
		delayedGroupEvents.add(e);
	}
	

}
