package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Dictionary;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class MessagePanel extends AvalonPanel {
	private JList<String> inbox;
	private String[] titles;
	private String[] messages;

	// private Vector<String> titles = new Vector<String>();
	// private Vector<Message> messages = new Vector<Message>();

	public MessagePanel() {
		TitledBorder tb = new TitledBorder("Message Inbox");
		setBorder(tb);
		setLayout(new BorderLayout());

		// sample data
		titles = new String[19];
		messages = new String[19];
		for (int i = 0; i < titles.length; i++) {
			titles[i] = "Message #" + i;
			messages[i] = "Quadrat von " + i + "=" + i * i;
		}

		inbox = new JList<String>();
		inbox.setListData(titles);
		// inbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		inbox.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
					return;
				int i = inbox.getSelectedIndex();
				System.out.println("index: message: " + i);
				if (i > -1) {
					JOptionPane.showMessageDialog(null, messages[i], titles[i],
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		add(new JScrollPane(inbox), BorderLayout.CENTER);
	}

	@Override
	protected void fill() {
		ArrayList<Dictionary<String, String>> input = GuiManager
				.sharedInstance().getDs().getMessages();
		titles = new String[input.size()];
		messages = new String[input.size()];
		for (int i = 0; i < input.size(); i++) {
			titles[i] = input.get(i).get("title");
			messages[i] = input.get(i).get("message");
		}
		inbox.setListData(titles);
		refresh();
	}

	@Override
	protected void send() {
	}
}
