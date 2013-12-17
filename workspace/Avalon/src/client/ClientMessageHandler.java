package client;

import java.util.ArrayList;

public class ClientMessageHandler {

	private ArrayList<Connection> players = new ArrayList<Connection>();
	private static ClientMessageHandler sharedInstance;

	public static ClientMessageHandler sharedInstance(){
		if (ClientMessageHandler.sharedInstance == null) {
			ClientMessageHandler.sharedInstance = new ClientMessageHandler();
		}
		return ClientMessageHandler.sharedInstance;
	}

	public void handleMessage(String txt, Connection sender) {
		//TODO: interprete Strings
		//Fredi kann nicht debuggen
		System.out.println("Client Handler handlet: " + txt);
		
	}
	
	public void addPlayer(Connection p){
		players.add(p);
	}

}
