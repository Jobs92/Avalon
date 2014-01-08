package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
public class MarketingPanel extends AvalonPanel {
	private ArrayList<JCheckBox> campaigns = new ArrayList<JCheckBox>();
	private ArrayList<JButton> info = new ArrayList<JButton>();
	private JButton upgradeButton = new JButton("Upgrade Marketing");
	private JButton startButton = new JButton("Start Campaigns");

	public MarketingPanel() {
		TitledBorder tb = new TitledBorder("Marketing");
		setBorder(tb);
		setLayout(new BorderLayout());
		setBackground(new Color(201, 148, 255));

		JPanel supplierPanel = new JPanel();
		supplierPanel.setLayout(new GridLayout(3, 3));

		// init arraylists
		for (int i = 1; i <= 3; i++) {
			campaigns.add(new JCheckBox("Campaign " + i));

			JButton b = new JButton("Info");
			b.setName(String.valueOf(i - 1));
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

		upgradeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO send upgrade
				JOptionPane.showConfirmDialog(null, "upgrade marketing");
			}
		});

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO send start for selected marketing campaigns
				startButton.setEnabled(false);
			}
		});

		add(supplierPanel, BorderLayout.NORTH);
		add(startButton, BorderLayout.SOUTH);
	}

	protected void makeInfoPopup(int index) {
		String infoString = "Index=" + String.valueOf(index);
		// TODO: Must be loaded
		JOptionPane.showMessageDialog(this, infoString);
	}

	@Override
	protected void fill() {
		// TODO Auto-generated method stub
		
	}

}
