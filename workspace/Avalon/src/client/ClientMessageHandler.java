package client;

import gui.GuiManager;
/**
 * @author Frederik
 * Handles incoming Strings for the client.
 */
public class ClientMessageHandler {

	private static ClientMessageHandler sharedInstance;

	public static ClientMessageHandler sharedInstance(){
		if (ClientMessageHandler.sharedInstance == null) {
			ClientMessageHandler.sharedInstance = new ClientMessageHandler();
		}
		return ClientMessageHandler.sharedInstance;
	}

	/**
	 * @param txt
	 * Handles a given text.
	 */
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

}
