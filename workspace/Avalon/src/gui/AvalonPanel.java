package gui;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AvalonPanel extends JPanel {

	/**
	 * This method is called at the beginning of every round and on the start of
	 * the game to fill the GUI with data.
	 * 
	 */
	protected abstract void fill();

	/**
	 * Send data to server at the end of the round.
	 * 
	 */
	protected abstract void send();
}
