package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class MarketingPanel extends AvalonPanel {
	private ArrayList<JCheckBox> campaignsCB = new ArrayList<JCheckBox>();
	private ArrayList<JButton> info = new ArrayList<JButton>();
	private JButton upgradeButton = new JButton("Upgrade");
	// private JButton startButton = new JButton("Start Campaigns");
	private ArrayList<Dictionary<String, String>> campaigns = new ArrayList<Dictionary<String, String>>();

	public MarketingPanel() {
		TitledBorder tb = new TitledBorder("Marketing");
		setBorder(tb);
		setLayout(new BorderLayout());
		setBackground(new Color(201, 148, 255));

		JPanel campaignPanel = new JPanel();
		campaignPanel.setBackground(getBackground());
		campaignPanel.setLayout(new GridLayout(3, 3));

		// init arraylists
		for (int i = 1; i <= 3; i++) {
			JCheckBox cb = new JCheckBox("Campaign #" + i);
			cb.setBackground(getBackground());
			campaignsCB.add(cb);

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
			campaignPanel.add(campaignsCB.get(i));
			campaignPanel.add(info.get(i));
		}

		upgradeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				makeUpgradePopup();
			}
		});

		// startButton.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// // TODO send start for selected marketing campaigns
		// startButton.setEnabled(false);
		// }
		// });

		add(upgradeButton, BorderLayout.SOUTH);
		add(campaignPanel, BorderLayout.NORTH);
		// add(startButton, BorderLayout.SOUTH);
	}

	protected void makeUpgradePopup() {
		int accepted = JOptionPane.showConfirmDialog(
				null,
				"Do you want to spy to upgrade the Research department for "
						+ GuiManager.sharedInstance().getDs()
								.getUpgradeCosts("marketing") + "?", "Upgrade",
				JOptionPane.YES_NO_OPTION);
		if (accepted == 0) {
			upgradeButton.setEnabled(false);
			// accepted
			GuiManager.sharedInstance().getApi().upgradeMarketing();
		}
	}

	private void makeInfoPopup(int index) {
		String infoString = campaigns.get(index).get("description")
				+ " Duration: " + campaigns.get(index).get("duration")
				+ ", price: " + campaigns.get(index).get("cost")
				+ ", levelupgrade: " + campaigns.get(index).get("level")
				+ ", successprobability: "
				+ campaigns.get(index).get("successprobability");
		JOptionPane.showMessageDialog(null, infoString, campaigns.get(index)
				.get("title") + index, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	protected void fill() {
		upgradeButton.setEnabled(true);
		setBorder(new TitledBorder("Marketing(Level: "
				+ GuiManager.sharedInstance().getDs().getLevel("marketing")
				+ ", Fixcosts: "
				+ GuiManager.sharedInstance().getDs()
						.getDepartmentFixcosts("marketing") + ")"));
		campaigns = GuiManager.sharedInstance().getDs().getMarketingCampaigns();
		for (int i = 0; i < campaignsCB.size(); i++) {
			campaignsCB.get(i).setText(campaigns.get(i).get("title"));
		}
	}

	@Override
	protected void send() {
		for (int i = 0; i < campaignsCB.size(); i++) {
			if (campaignsCB.get(i).isSelected()) {
				GuiManager.sharedInstance().getApi().startMarketingCampaign(i);
			}
		}
	}

}
