package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class ResearchPanel extends AvalonPanel {

	private JPanel enemyPanel = new JPanel();
	private JPanel patentPanel = new JPanel();
	private JPanel researchCampainPanel = new JPanel();

	private JList<String> enemies;
	private ArrayList<Dictionary<String, String>> enemyData = new ArrayList<Dictionary<String, String>>();
	private ArrayList<JCheckBox> campaignsCB = new ArrayList<JCheckBox>();
	private ArrayList<AvalonButton> info = new ArrayList<AvalonButton>();
	private AvalonButton upgradeButton = new AvalonButton("Upgrade");
	private AvalonButton downgradeButton = new AvalonButton("Downgrade");
	private ArrayList<Dictionary<String, String>> campaigns = new ArrayList<Dictionary<String, String>>();
	private JLabel labelPatentLevelNumber;
	private AvalonButton releaseButton = new AvalonButton("Neues Produkt");
	private JPanel southPanel;

	public ResearchPanel() {
		TitledBorder tb = new TitledBorder("Forschungsabteilung");
		setBorder(tb);
		// setBackground(new Color(122, 189, 255));

		enemyPanel.setBackground(getBackground());
		patentPanel.setBackground(getBackground());
		researchCampainPanel.setBackground(getBackground());
		researchCampainPanel.setBorder(new TitledBorder("Kampagnen"));

		initEnemyPanel();
		initPatentLevelPanel();
		initResearchCampainPanel();

		add(researchCampainPanel, BorderLayout.NORTH);
		add(patentPanel, BorderLayout.CENTER);
		add(enemyPanel, BorderLayout.CENTER);

		upgradeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				makeUpgradePopup();
			}
		});

		downgradeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int accepted = JOptionPane
						.showConfirmDialog(
								null,
								"Willst du wirklich die Forschungsabteilung downgraden?",
								"Downgrade", JOptionPane.YES_NO_OPTION);
				if (accepted == 0) {
					downgradeButton.setEnabled(false);
					// accepted
					GuiManager.sharedInstance().getApi().downgradeResearch();
				}
			}
		});

		releaseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				makeReleasePopup();
			}
		});

		southPanel = new JPanel();
		southPanel.setBackground(getBackground());
		southPanel.add(upgradeButton);
		southPanel.add(downgradeButton);
		southPanel.add(releaseButton);
		add(southPanel, BorderLayout.SOUTH);

	}

	protected void makeReleasePopup() {
		String name = JOptionPane.showInputDialog(this,
				"Neues Produkt ver�ffentlichen. Bitte Namen w�hlen.");
		if (!name.equalsIgnoreCase("")) {
			releaseButton.setEnabled(false);
			GuiManager.sharedInstance().getApi().release(name);
		}
	}

	private void initResearchCampainPanel() {
		researchCampainPanel.setLayout(new GridLayout(3, 2));

		for (int i = 1; i <= 3; i++) {
			JCheckBox rbutton = new JCheckBox("Kampagne " + i, false);
			rbutton.setBackground(getBackground());
			campaignsCB.add(rbutton);

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

		for (int i = 0; i < 3; i++) {
			researchCampainPanel.add(campaignsCB.get(i));
			researchCampainPanel.add(info.get(i));
		}
	}

	private void initPatentLevelPanel() {
		labelPatentLevelNumber = new JLabel("0");
		AvalonButton orderPatent = new AvalonButton("Patentieren");

		orderPatent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int accepted = JOptionPane.showConfirmDialog(null,
						"Willst du den Forschungsstand f�r "
								+ GuiManager.sharedInstance().getDs()
										.getPatentCost() + " patentieren?",
						"Patentieren", JOptionPane.YES_NO_OPTION);
				if (accepted == 0) {
					// accepted
					GuiManager.sharedInstance().getApi().patent();
				}
			}
		});
		patentPanel.add(labelPatentLevelNumber);
		patentPanel.add(orderPatent);
	}

	private void initEnemyPanel() {
		for (int i = 0; i < 3; i++) {
			// enemyData.add("Enemy #" + (i + 1));
		}
		enemies = new JList<String>();
		enemies.setBackground(getBackground());
		enemies.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				int i = enemies.getSelectedIndex();
				makeEnemyPopup(i);
			}
		});
		enemyPanel.add(new JScrollPane(enemies));
	}

	protected void makeEnemyPopup(int i) {
		if (i > -1) {
			int accepted = JOptionPane
					.showConfirmDialog(null, "Willst du "
							+ enemyData.get(i).get("name") + " f�r "
							+ GuiManager.sharedInstance().getDs().getSpyCost()
							+ " auspionieren?", "Spionieren",
							JOptionPane.YES_NO_OPTION);
			if (accepted == 0) {
				// accepted
				GuiManager.sharedInstance().getApi()
						.spy(Integer.parseInt(enemyData.get(i).get("id")));
			}
		}
	}

	protected void makeInfoPopup(int index) {
		if (index > -1) {
			String infoString = campaigns.get(index).get("description")
					+ "\nLaufzeit: " + campaigns.get(index).get("duration")
					+ "\nPreis: " + campaigns.get(index).get("cost")
					+ "\nLevelupgrade: " + campaigns.get(index).get("level")
					+ "\nErfolgswahrscheinlichkeit: "
					+ campaigns.get(index).get("successprobability") + "%";
			JOptionPane.showMessageDialog(null, infoString, campaigns
					.get(index).get("title") + index,
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void makeUpgradePopup() {
		int accepted = JOptionPane.showConfirmDialog(
				null,
				"Willst du wirklich die Forschungsabteilung f�r "
						+ GuiManager.sharedInstance().getDs()
								.getUpgradeCosts("research") + " upgraden?",
				"Upgrade", JOptionPane.YES_NO_OPTION);
		if (accepted == 0) {
			upgradeButton.setEnabled(false);
			// accepted
			GuiManager.sharedInstance().getApi().upgradeResearch();
		}
	}

	@Override
	protected void fill() {
		labelPatentLevelNumber.setText("<html><p>Patentlevel: "
				+ String.valueOf(GuiManager.sharedInstance().getDs()
						.getPatentLevel())
				+ "<br>Produktlevel: "
				+ String.valueOf(GuiManager.sharedInstance().getDs()
						.getResearchLevel())
				+ "<br>Forschungslevel: "
				+ String.valueOf(GuiManager.sharedInstance().getDs()
						.getResearchLevel()
						+ GuiManager.sharedInstance().getDs()
								.getNotAppliedLevels()) + "</p></html>");
		upgradeButton.setEnabled(true);
		downgradeButton.setEnabled(true);
		releaseButton.setEnabled(true);
		setBorder(new TitledBorder("Forschungsabteilung(Level: "
				+ GuiManager.sharedInstance().getDs().getLevel("research")
				+ ", Fixkosten: "
				+ GuiManager.sharedInstance().getDs()
						.getDepartmentFixcosts("research") + ")"));
		campaigns = GuiManager.sharedInstance().getDs().getResearchCampaigns();
		for (int i = 0; i < 3; i++) {
			campaignsCB.get(i).setText(campaigns.get(i).get("title"));
			campaignsCB.get(i).setSelected(false);
		}
		enemyData = GuiManager.sharedInstance().getDs().getEnemies();
		String[] enemyListData = new String[enemyData.size()];
		for (int i = 0; i < enemyData.size(); i++) {
			enemyListData[i] = enemyData.get(i).get("name");
		}
		enemies.setListData(enemyListData);

		refresh();
		refreshBackground(getBackground());
	}

	@Override
	protected void send() {
		for (int i = 0; i < campaignsCB.size(); i++) {
			if (campaignsCB.get(i).isSelected()) {
				GuiManager.sharedInstance().getApi().startResearchCampaign(i);
			}
		}
	}

	@Override
	protected void refreshBackground(Color bg) {
		enemies.setBackground(bg);
		for (JCheckBox cb : campaignsCB) {
			cb.setBackground(bg);
		}
		enemyPanel.setBackground(bg);
		patentPanel.setBackground(bg);
		researchCampainPanel.setBackground(bg);
		southPanel.setBackground(bg);
	}
}
