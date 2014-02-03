package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class LawPanel extends AvalonPanel {
	private JPanel enemyPanel = new JPanel();
	private JPanel suesPanel = new JPanel();
	private AvalonButton upgradeButton = new AvalonButton("Upgrade");
	private AvalonButton downgradeButton = new AvalonButton("Downgrade");
	private JPanel southPanel = new JPanel();

	private JList<String> enemies = new JList<String>();
	private JList<String> sues = new JList<String>();
	private ArrayList<Dictionary<String, String>> enemyData = new ArrayList<Dictionary<String, String>>();
	private Dictionary<String, String> suesData;

	public LawPanel() {
		TitledBorder tb = new TitledBorder("Rechtsabteilung");
		setBorder(tb);
		setLayout(new BorderLayout());
		// setBackground(new Color(0, 179, 0));

		initEnemyPanel();
		initSuesPanel();
		suesPanel.setBackground(getBackground());
		suesPanel.setBorder(new TitledBorder("Laufende Verfahren"));
		enemyPanel.setBackground(getBackground());
		enemyPanel.setBorder(new TitledBorder("Konkurrenten"));
		add(suesPanel, BorderLayout.NORTH);
		add(enemyPanel, BorderLayout.CENTER);

		downgradeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int accepted = JOptionPane.showConfirmDialog(null,
						"Willst du wirklich die Rechtsabteilung downgraden?",
						"Downgrade", JOptionPane.YES_NO_OPTION);
				if (accepted == 0) {
					downgradeButton.setEnabled(false);
					// accepted
					GuiManager.sharedInstance().getApi()
							.downgradeLegalDepartment();
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
	}

	protected void makeUpgradePopup() {
		int accepted = JOptionPane.showConfirmDialog(
				null,
				"Willst du wirklich die Rechtsabteilung für "
						+ GuiManager.sharedInstance().getDs()
								.getUpgradeCosts("legalDepartment") + " upgraden?",
				"Upgrade", JOptionPane.YES_NO_OPTION);
		if (accepted == 0) {
			upgradeButton.setEnabled(false);
			// accepted
			GuiManager.sharedInstance().getApi().upgradeLegalDepartment();
		}
	}

	private void initSuesPanel() {
		Vector<String> enemyNames = new Vector<String>();
		for (int i = 0; i < 5; i++) {
			enemyNames.add("Sue #" + (i + 1) + " vs me");
		}
		sues = new JList<String>(enemyNames);
		sues.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				int i = sues.getSelectedIndex();
				makeSuePopup(i);
			}
		});

		sues.setBackground(getBackground());
		suesPanel.add(new JScrollPane(sues));
	}

	private void initEnemyPanel() {

		String[] listData = new String[3];
		for (int i = 0; i < listData.length; i++) {
			listData[i] = "Konkurrent #" + (i + 1);
		}
		enemies = new JList<String>(listData);
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

		enemies.setBackground(getBackground());
		enemyPanel.add(new JScrollPane(enemies));
	}

	private void makeEnemyPopup(final int index) {
		if (index > -1) {
			String infoString = "Aktion auswählen.";

			boolean sueable = !enemyData.get(index).get("amount")
					.equalsIgnoreCase("0.0");

			final AvalonButton sue = new AvalonButton("Konkurrent auf "
					+ enemyData.get(index).get("amount")+"verklagen");
			sue.setEnabled(sueable);
			sue.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					GuiManager
							.sharedInstance()
							.getApi()
							.sueOpponent(
									Integer.parseInt(enemyData.get(index).get(
											"id")));
					System.out.println("enemy sued");
					// close popup
					Window win = SwingUtilities.getWindowAncestor(sue);
					win.setVisible(false);
				}
			});

			final AvalonButton check = new AvalonButton("Konkurrent überprüfen");
			check.setEnabled(!sueable);

			check.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					GuiManager
							.sharedInstance()
							.getApi()
							.checkOpponent(
									Integer.parseInt(enemyData.get(index).get(
											"id")));
					System.out.println("enemy checked");
					// close popup
					Window win = SwingUtilities.getWindowAncestor(check);
					win.setVisible(false);
				}
			});
			Object[] options = { sue, check };
			JOptionPane.showOptionDialog(null, infoString,
					enemies.getSelectedValue(),
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.YES_NO_CANCEL_OPTION, null, options, null);
		}
	}

	private void makeSuePopup(int index) {
		if (index > -1) {
			String infoString = " Duration: " + suesData.get("duration")
					+ ", price: " + suesData.get("cost") + ", amount: "
					+ suesData.get("amount");
			boolean isClaimant = Boolean.parseBoolean(suesData
					.get("isClaimant"));

			final AvalonButton abort = new AvalonButton("Klage abbrechen");
			abort.setEnabled(isClaimant);
			abort.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					GuiManager.sharedInstance().getApi().abandonLawsuit();
					System.out.println("sue aborted");
					// close popup
					Window win = SwingUtilities.getWindowAncestor(abort);
					win.setVisible(false);
				}
			});

			final AvalonButton pay = new AvalonButton("Bezahlen");
			pay.setEnabled(!isClaimant);
			pay.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					GuiManager.sharedInstance().getApi().payAmount();
					System.out.println("sue payed");
					// close popup
					Window win = SwingUtilities.getWindowAncestor(pay);
					win.setVisible(false);
				}
			});
			Object[] options = { abort, pay };
			JOptionPane.showOptionDialog(this, infoString,
					sues.getSelectedValue(), JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.YES_NO_CANCEL_OPTION, null, options, null);
		}
	}

	@Override
	protected void fill() {
		upgradeButton.setEnabled(true);
		downgradeButton.setEnabled(true);
		setBorder(new TitledBorder("Law(Level: "
				+ GuiManager.sharedInstance().getDs()
						.getLevel("legalDepartment")
				+ ", Fixkosten: "
				+ GuiManager.sharedInstance().getDs()
						.getDepartmentFixcosts("legalDepartment") + ")"));
		enemyData = GuiManager.sharedInstance().getDs().getCheckedEnemies();
		suesData = GuiManager.sharedInstance().getDs().getLawsuit();

		String[] suesListData = new String[suesData.size()];
		if (suesData.size() > 0) {
			suesListData[0] = suesData.get("claimant") + " vs "
					+ suesData.get("defendant");
		} else {
			suesListData = new String[1];
			suesListData[0] = "Keine laufenden Verfahren";
		}
		sues.setListData(suesListData);

		String[] enemyListData = new String[enemyData.size()];
		for (int i = 0; i < enemyListData.length; i++) {
			String s = enemyData.get(i).get("name");
			if (enemyData.get(i).get("amount").equalsIgnoreCase("0.0")) {
				s += "(not yet checked)";
			} else {
				s += "(amount: " + enemyData.get(i).get("amount") + ")";
			}
			enemyListData[i] = s;
		}
		enemies.setListData(enemyListData);

		refresh();
		refreshBackground(getBackground());
	}

	@Override
	protected void send() {
	}

	@Override
	protected void refreshBackground(Color bg) {
		enemies.setBackground(bg);
		enemyPanel.setBackground(bg);
		suesPanel.setBackground(bg);
		sues.setBackground(bg);
		southPanel.setBackground(bg);
	}

}
