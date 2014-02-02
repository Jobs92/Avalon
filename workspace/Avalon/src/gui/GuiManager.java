package gui;

import java.awt.Color;

import javax.swing.JFrame;

import client.Api;
import utils.DataSnapshot;

public class GuiManager {
	private Color colors[] = {Color.red,Color.blue,Color.PINK,Color.green,Color.ORANGE,Color.magenta};
	private DataSnapshot ds;
	private Api api;
	AvalonFrame mainFrame;
	JFrame loginFrame;
	LobbyFrame lobbyFrame;
	private boolean gameStarted;
	private static GuiManager sharedInstance = null;

	public static GuiManager sharedInstance() {
		if (sharedInstance == null) {
			sharedInstance = new GuiManager();
		}
		return sharedInstance;
	}

	private GuiManager() {
		loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
	}

	public Api getApi() {
		return api;
	}
	
	public boolean gameStarted(){
		return gameStarted;
	}

	public Color[] getColors() {
		return colors;
	}

	public void setApi(Api api) {
		this.api = api;
	}

	public void update(DataSnapshot ds) {
		this.ds = ds;
		mainFrame.fill();
	}

	public void successfullLogin() {
		loginFrame.setVisible(false);
		lobbyFrame =new LobbyFrame();
//		mainFrame = new AvalonFrame();
		
		//for testing
//		api.startGame();
	}
	
	public void startGame(){
		gameStarted = true;
		lobbyFrame.setVisible(false);
		mainFrame = new AvalonFrame();
	}
	
	public void handleChat(String txt, int id){
		if(lobbyFrame != null){
			lobbyFrame.handleChat(txt, id);
		}
	}

	public static void main(String[] args) {
		GuiManager.sharedInstance();
	}

	public DataSnapshot getDs() {
		return ds;
	}
}
