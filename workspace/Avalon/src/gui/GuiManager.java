package gui;

import javax.swing.JFrame;

import utils.DataSnapshot;

public class GuiManager {

	private DataSnapshot ds;
	private static GuiManager sharedInstance = null;

	public static GuiManager sharedInstance() {
		if (sharedInstance == null) {
			sharedInstance = new GuiManager();
		}
		return sharedInstance;
	}

	private GuiManager() {
		JFrame frame = new GUI_Connect();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		GuiManager.sharedInstance();
	}

	public DataSnapshot getDs() {
		return ds;
	}
}
