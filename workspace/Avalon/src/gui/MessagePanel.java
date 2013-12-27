package gui;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class MessagePanel extends JPanel {
	private JList<String> inbox;
	private Vector<String> data = new Vector<String>();
	private Vector<Message> extendedData = new Vector<Message>();

	public MessagePanel() {
		TitledBorder tb = new TitledBorder("Message Inbox");
		setBorder(tb);
		setLayout(new BorderLayout());

		// sample data
		for (int i = 0; i < 100; i++) {
			Message m = new Message(String.valueOf(i), String.valueOf(i * i));
			extendedData.add(m);
			data.add(m.getTitle());
		}

		inbox = new JList<String>(data);
		inbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		inbox.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
					return;
				int i = inbox.getSelectedIndex();
				Message m = extendedData.get(i);
				JOptionPane.showConfirmDialog(null, m.getMessage(),
						m.getTitle(), JOptionPane.OK_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		add(new JScrollPane(inbox), BorderLayout.CENTER);
	}
}
