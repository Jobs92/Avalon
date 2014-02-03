package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class MarketingPanel extends AvalonPanel {
	private ArrayList<JCheckBox> campaignsCB = new ArrayList<JCheckBox>();
	private ArrayList<AvalonButton> info = new ArrayList<AvalonButton>();

	private AvalonButton upgradeButton = new AvalonButton("Upgrade");
	private AvalonButton downgradeButton = new AvalonButton("Downgrade");
	private JPanel southPanel = new JPanel();
	private ArrayList<Dictionary<String, String>> campaigns = new ArrayList<Dictionary<String, String>>();
	JPanel campaignPanel;

	public MarketingPanel() {
		TitledBorder tb = new TitledBorder("Marketing");
		setBorder(tb);
		setLayout(new BorderLayout());
		// setBackground(new Color(201, 148, 255));

		campaignPanel = new JPanel();
		campaignPanel.setBackground(getBackground());
		campaignPanel.setLayout(new GridLayout(3, 3));

		// init arraylists
		for (int i = 1; i <= 3; i++) {
			JCheckBox cb = new JCheckBox("Kampagne #" + i);
			cb.setBackground(getBackground());
			campaignsCB.add(cb);

			AvalonButton b = new AvalonButton("Info");
			b.setName(String.valueOf(i - 1));
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					AvalonButton b = (AvalonButton) e.getSource();
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

		downgradeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int accepted = JOptionPane.showConfirmDialog(null,
						"Willst du wirklich die Marketingabteilung downgraden?",
						"Downgrade", JOptionPane.YES_NO_OPTION);
				if (accepted == 0) {
					downgradeButton.setEnabled(false);
					// accepted
					GuiManager.sharedInstance().getApi().downMarketing();
				}
			}
		});

		upgradeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				makeUpgradePopup();
			}
		});
		southPanel.add(upgradeButton);
		southPanel.add(downgradeButton);
		southPanel.setBackground(getBackground());
		add(southPanel, BorderLayout.SOUTH);
		add(campaignPanel, BorderLayout.NORTH);
	}

//	protected void makeDowngradePopup() {
//		int accepted = JOptionPane.showConfirmDialog(null,
//				"Do you want to downgrade the Marketing department?",
//				"Upgrade", JOptionPane.YES_NO_OPTION);
//		if (accepted == 0) {
//			upgradeButton.setEnabled(false);
//			// accepted
//			GuiManager.sharedInstance().getApi().downMarketing();
//		}
//	}

	protected void makeUpgradePopup() {
		int accepted = JOptionPane.showConfirmDialog(
				null,
				"Willst du wirklich die Marketingabteilung für "
						+ GuiManager.sharedInstance().getDs()
								.getUpgradeCosts("marketing") + " upgraden?", "Upgrade",
				JOptionPane.YES_NO_OPTION);
		if (accepted == 0) {
			upgradeButton.setEnabled(false);
			// accepted
			GuiManager.sharedInstance().getApi().upgradeMarketing();
		}
	}

	private void makeInfoPopup(int index) {
		if (index > -1) {
			String infoString = campaigns.get(index).get("description")
					+ "\nLaufzeit: " + campaigns.get(index).get("duration")
					+ "\nPreis: " + campaigns.get(index).get("cost")
					+ "\nImageupgrade: " + campaigns.get(index).get("level")
					+ "\nErfolgswahrscheinlichkeit: "
					+ campaigns.get(index).get("successProbability");
			JOptionPane.showMessageDialog(null, infoString, campaigns
					.get(index).get("title") + index,
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	protected void fill() {
		upgradeButton.setEnabled(true);
		downgradeButton.setEnabled(true);
		setBorder(new TitledBorder("Marketing(Level: "
				+ GuiManager.sharedInstance().getDs().getLevel("marketing")
				+ ", Fixkosten: "
				+ GuiManager.sharedInstance().getDs()
						.getDepartmentFixcosts("marketing") + ")"));
		campaigns = GuiManager.sharedInstance().getDs().getMarketingCampaigns();
		for (int i = 0; i < campaignsCB.size(); i++) {
			campaignsCB.get(i).setText(campaigns.get(i).get("title"));
			campaignsCB.get(i).setSelected(false);
		}

		refresh();
		refreshBackground(getBackground());
	}

	@Override
	protected void send() {
		for (int i = 0; i < campaignsCB.size(); i++) {
			if (campaignsCB.get(i).isSelected()) {
				GuiManager.sharedInstance().getApi().startMarketingCampaign(i);
			}
		}
	}

	@Override
	protected void refreshBackground(Color bg) {
		southPanel.setBackground(bg);
		campaignPanel.setBackground(bg);
		for (JCheckBox cb : campaignsCB) {
			cb.setBackground(bg);
		}
	}

}
