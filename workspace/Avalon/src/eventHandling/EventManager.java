package eventHandling;


import java.util.ArrayList;




import company.Company;
import config.Config;

/**
 * @author Johannes
 * This class manages the events
 *
 */
public class EventManager {
	
	private ArrayList<Event> groupEvents;
	private ArrayList<Event> singleEvents;
	private ArrayList<EventTrigger> triggedGroupEvents;
	private ArrayList<EventTrigger> triggedSingleEvents;
	private ArrayList<EventTrigger> delayedGroupEvents;
	//private ArrayList<EventTrigger> delayedSingleEvents; //NOT USED
	private static EventManager sharedInstance;
	
	double groupChance; //0.2
	double singleChance;//0.3

	public static EventManager sharedInstance() {
		if (EventManager.sharedInstance == null) {
			EventManager.sharedInstance = new EventManager();
		}
		return EventManager.sharedInstance;
	}
	
	private EventManager(){
		
	}
	
	/**
	 * This method extract the event data from config file and creates the events.
	 */
	public void createEvents(){
		groupChance=Config.getEventGroupChance(); //0.2
		singleChance=Config.getEventGroupChance();//0.3
		
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

	/**
	 * This method pick a random event.
	 */
	@SuppressWarnings("unchecked")
	public void simEvents() {
	
		
		ArrayList<company.Company> players = market.Market.sharedInstance().getCompanies();
		
		
		if (Math.random()<=groupChance) {   // pick random groupEvent
			int var=(int) (1+(groupEvents.size()-1)*Math.random());
			Event gEvent = groupEvents.get(var-1);
			EventTrigger GroupEventTrigger = new EventTrigger(gEvent, 0);
			triggedGroupEvents.add(GroupEventTrigger);			
		}
		
		else  {   // SingleEvent

		for (Company company : players) {
			if (Math.random()<=singleChance) {
				int var=(int) ((int) (Math.random()*singleEvents.size()));
					Event sEvent = singleEvents.get(var);
					EventTrigger SingleEventTrigger = new EventTrigger(sEvent, 0);
					triggedSingleEvents.add(SingleEventTrigger);
					for (EventTrigger evt : triggedSingleEvents) {
						evt.simulategSingleEvents(company);
					}
					triggedSingleEvents.clear();
					
					
			}
			else {} // Do noting!
		}
			
					
		}
		
		// Simulate GroupEvents
		for (EventTrigger evt : triggedGroupEvents) {
			EventTrigger delayedEvt = evt.simulategGroupEvents();
			if (delayedEvt != null) delayedGroupEvents.add(delayedEvt);
		}
		triggedGroupEvents.clear();
		triggedGroupEvents= (ArrayList<EventTrigger>) delayedGroupEvents.clone();
		delayedGroupEvents.clear();
	
	

		
	}
	
	public void addEventTrigger(EventTrigger e){
		triggedGroupEvents.add(e);
	}
	public void addDelayedEvent(EventTrigger e){
		delayedGroupEvents.add(e);
	}

	private void setGroupChance(double groupChance) {
		this.groupChance = groupChance;
	}

	private void setSingleChance(double singleChance) {
		this.singleChance = singleChance;
	}
	

}
