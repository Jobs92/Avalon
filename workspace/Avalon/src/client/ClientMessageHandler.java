package client;

import gui.GuiManager;

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

	public static void handleMessage(String txt) {
		if (txt.startsWith("CHAT")){
			int id = Integer.parseInt(txt.substring(5,6));
			String s = txt.substring(6);
			GuiManager.sharedInstance().handleChat(s, id);
		}
		if (txt.startsWith("GAMESTARTED")){
			GuiManager.sharedInstance().startGame();
		}
		
	}
	
	public void addPlayer(Connection p){
		players.add(p);
	}

}
