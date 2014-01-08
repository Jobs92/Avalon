package gui;

import javax.swing.JFrame;

import client.Api;
import utils.DataSnapshot;

public class GuiManager {

	private DataSnapshot ds;
	private Api api;
	AvalonFrame mainFrame;
	JFrame loginFrame;
	private static GuiManager sharedInstance = null;

	public static GuiManager sharedInstance() {
		if (sharedInstance == null) {
			sharedInstance = new GuiManager();
		}
		return sharedInstance;
	}

	private GuiManager() {
		loginFrame = new GUI_Connect();
		loginFrame.setVisible(true);
	}

	public Api getApi() {
		return api;
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
		mainFrame = new AvalonFrame();
	}

	public static void main(String[] args) {
		GuiManager.sharedInstance();
	}

	public DataSnapshot getDs() {
		return ds;
	}
}
