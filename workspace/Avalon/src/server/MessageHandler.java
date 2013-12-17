package server;

import java.util.ArrayList;

import market.Market;

public class MessageHandler {

	private ArrayList<Connection> players = new ArrayList<Connection>();
	private static MessageHandler sharedInstance;

	public static MessageHandler sharedInstance(){
		if (MessageHandler.sharedInstance == null) {
			MessageHandler.sharedInstance = new MessageHandler();
		}
		return MessageHandler.sharedInstance;
	}

	public void handleMessage(String txt, Connection sender) {
		//TODO: interprete Strings
		
	}
	
	public void addPlayer(Connection p){
		players.add(p);
	}

}
