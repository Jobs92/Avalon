package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Vector;
import javax.swing.JButton;
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
	private Vector<String> enemyData = new Vector<String>();
	private ArrayList<JCheckBox> campaignsCB = new ArrayList<JCheckBox>();
	private ArrayList<JButton> info = new ArrayList<JButton>();
	private JButton upgradeButton = new JButton("Upgrade");
	private ArrayList<Dictionary<String, String>> campaigns = new ArrayList<Dictionary<String, String>>();
	private JLabel labelPatentLevelNumber;
	private JButton releaseButton = new JButton("Release new Product");

	public ResearchPanel() {
		TitledBorder tb = new TitledBorder("Research");
		setBorder(tb);
		setBackground(new Color(122, 189, 255));

		enemyPanel.setBackground(getBackground());
		patentPanel.setBackground(getBackground());
		researchCampainPanel.setBackground(getBackground());

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

		releaseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				makeReleasePopup();
			}
		});

		JPanel p = new JPanel();
		p.setBackground(getBackground());
		p.add(upgradeButton);
		p.add(releaseButton);
		add(p, BorderLayout.SOUTH);

	}

	protected void makeReleasePopup() {
		String name = JOptionPane.showInputDialog(this,
				"Release a new Product. Choose name.");
		GuiManager.sharedInstance().getApi().release(name);
	}

	private void initResearchCampainPanel() {
		researchCampainPanel.setLayout(new GridLayout(3, 2));

		for (int i = 1; i <= 3; i++) {
			JCheckBox rbutton = new JCheckBox("Campaign " + i, false);
			rbutton.setBackground(getBackground());
			campaignsCB.add(rbutton);

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

		for (int i = 0; i < 3; i++) {
			researchCampainPanel.add(campaignsCB.get(i));
			researchCampainPanel.add(info.get(i));
		}
	}

	private void initPatentLevelPanel() {

		JLabel labelPatentLevel = new JLabel("Patentlevel : ");
		labelPatentLevelNumber = new JLabel("0");
		JButton orderPatent = new JButton("Patent");

		orderPatent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int accepted = JOptionPane.showConfirmDialog(null,
						"Do you want to patent for "
								+ GuiManager.sharedInstance().getDs()
										.getPatentCost() + "?", "Patent",
						JOptionPane.YES_NO_OPTION);
				if (accepted == 0) {
					// accepted
					GuiManager.sharedInstance().getApi().patent();
				}
			}
		});

		patentPanel.add(labelPatentLevel);
		patentPanel.add(labelPatentLevelNumber);
		patentPanel.add(orderPatent);
	}

	private void initEnemyPanel() {
		for (int i = 0; i < 3; i++) {
			enemyData.add("Enemy #" + (i + 1));
		}
		enemies = new JList<String>(enemyData);
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
		int accepted = JOptionPane.showConfirmDialog(null,
				"Do you want to spy " + enemyData.get(i) + " for "
						+ GuiManager.sharedInstance().getDs().getSpyCost()
						+ "?", "Spy", JOptionPane.YES_NO_OPTION);
		if (accepted == 0) {
			// accepted
			GuiManager.sharedInstance().getApi().spy(i);
		}
	}

	protected void makeInfoPopup(int index) {
		String infoString = campaigns.get(index).get("description")
				+ " Duration: " + campaigns.get(index).get("duration")
				+ ", price: " + campaigns.get(index).get("cost")
				+ ", levelupgrade: " + campaigns.get(index).get("level")
				+ ", successprobability: "
				+ campaigns.get(index).get("successprobability") + "%";
		JOptionPane.showMessageDialog(null, infoString, campaigns.get(index)
				.get("title") + index, JOptionPane.INFORMATION_MESSAGE);
	}

	protected void makeUpgradePopup() {
		int accepted = JOptionPane.showConfirmDialog(
				null,
				"Do you wantto upgrade the Research department for "
						+ GuiManager.sharedInstance().getDs()
								.getUpgradeCosts("research") + "?", "Upgrade",
				JOptionPane.YES_NO_OPTION);
		if (accepted == 0) {
			upgradeButton.setEnabled(false);
			// accepted
			GuiManager.sharedInstance().getApi().upgradeResearch();
		}
	}

	@Override
	protected void fill() {
		labelPatentLevelNumber.setText(String.valueOf(GuiManager
				.sharedInstance().getDs().getPatentLevel()));
		upgradeButton.setEnabled(true);
		setBorder(new TitledBorder("Research(Level: "
				+ GuiManager.sharedInstance().getDs()
						.getLevel("research") +", Fixcosts: "+GuiManager.sharedInstance().getDs().getDepartmentFixcosts("research")+")"));
		campaigns = GuiManager.sharedInstance().getDs().getResearchCampaigns();
		for (int i = 0; i < 3; i++) {
			campaignsCB.get(i).setText(campaigns.get(i).get("title"));
		}
		enemyData = GuiManager.sharedInstance().getDs().getEnemyNames();
		enemies.setListData(enemyData);
	}

	@Override
	protected void send() {
		for (int i = 0; i < campaignsCB.size(); i++) {
			if (campaignsCB.get(i).isSelected()) {
				GuiManager.sharedInstance().getApi().startResearchCampaign(i);
			}
		}
	}
}
