package gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class MessagePanel extends JPanel {

	public MessagePanel() {
		TitledBorder tb = new TitledBorder("Message Inbox");
		setBorder(tb);
	}
}
