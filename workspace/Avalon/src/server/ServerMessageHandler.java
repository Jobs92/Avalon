package server;

import java.util.ArrayList;

import market.Market;

public class ServerMessageHandler {

	private ArrayList<Connection> players = new ArrayList<Connection>();
	private static ServerMessageHandler sharedInstance;

	public static ServerMessageHandler sharedInstance(){
		if (ServerMessageHandler.sharedInstance == null) {
			ServerMessageHandler.sharedInstance = new ServerMessageHandler();
		}
		return ServerMessageHandler.sharedInstance;
	}

	public void handleMessage(String txt, Connection sender) {
		//TODO: interprete Strings
		System.out.println("ServerHandler handlet: " + txt);
	}
	
	public void addPlayer(Connection p){
		players.add(p);
	}

}
