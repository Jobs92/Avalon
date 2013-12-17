package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class MarketingPanel extends JPanel {
	private ArrayList<JCheckBox> campaigns = new ArrayList<JCheckBox>();
	private ArrayList<JButton> info = new ArrayList<JButton>();

	public MarketingPanel() {
		TitledBorder tb = new TitledBorder("Marketing");
		setBorder(tb);
		setLayout(new BorderLayout());

		JPanel supplierPanel = new JPanel();
		supplierPanel.setLayout(new GridLayout(3, 3));

		// init arraylists
		for (int i = 1; i <= 3; i++) {
			campaigns.add(new JCheckBox("Campaign " + i));

			JButton b = new JButton("Info");
			b.setName(String.valueOf(i));
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JButton b = (JButton) e.getSource();
					makeInfoPopup(Integer.valueOf(b.getName()));
				}
			});
			info.add(b);
		}

		// fill ui
		for (int i = 0; i < 3; i++) {
			supplierPanel.add(campaigns.get(i));
			supplierPanel.add(info.get(i));
		}

		add(supplierPanel, BorderLayout.NORTH);
	}

	protected void makeInfoPopup(int index) {
		String infoString = "Index=" + String.valueOf(index);
		// TODO: Must be loaded
		JOptionPane.showMessageDialog(this, infoString);
	}

}
