package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Vector;

import javax.swing.JButton;
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
	private JButton upgradeButton = new JButton("Upgrade");

	private JList<String> enemies;
	private JList<String> sues;
	private Vector<String> enemyData = new Vector<String>();
	private ArrayList<Dictionary<String, String>> suesData = new ArrayList<Dictionary<String, String>>();

	public LawPanel() {
		TitledBorder tb = new TitledBorder("Law");
		setBorder(tb);
		setLayout(new BorderLayout());
		setBackground(new Color(0, 179, 0));

		initEnemyPanel();
		initSuesPanel();
		// add(suesPanel);
		// add(enemyPanel);
		// add(upgradeButton);
		suesPanel.setBackground(getBackground());
		enemyPanel.setBackground(getBackground());
		add(suesPanel, BorderLayout.NORTH);
		add(enemyPanel, BorderLayout.CENTER);
		upgradeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				makeUpgradePopup();
			}
		});
		add(upgradeButton, BorderLayout.SOUTH);
	}

	protected void makeUpgradePopup() {
		int accepted = JOptionPane.showConfirmDialog(null,
				"Do you want to spy to upgrade the Law department for "
						+ GuiManager.sharedInstance().getDs()
								.getUpgradeCosts("legalDepartment") + "?", "Upgrade",
				JOptionPane.YES_NO_OPTION);
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
		for (int i = 0; i < 3; i++) {
			enemyData.add("Enemy #" + (i + 1));
		}
		enemies = new JList<String>(enemyData);
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

	private void makeEnemyPopup(int index) {
		String infoString = "Choose action.";

		final JButton sue = new JButton("Sue Enemy");
		sue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: REALLY??? no parameters?
				GuiManager.sharedInstance().getApi().abandonLawsuit();
				System.out.println("enemy sued");
				// close popup
				Window win = SwingUtilities.getWindowAncestor(sue);
				win.setVisible(false);
			}
		});

		final JButton check = new JButton("Check Enemy");
		check.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = enemies.getSelectedIndex();
				GuiManager.sharedInstance().getApi().checkOpponent(i);
				System.out.println("enemy checked");
				// close popup
				Window win = SwingUtilities.getWindowAncestor(check);
				win.setVisible(false);
			}
		});
		Object[] options = { sue, check };
		JOptionPane.showOptionDialog(null, infoString,
				enemies.getSelectedValue(), JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.YES_NO_CANCEL_OPTION, null, options, null);
	}

	private void makeSuePopup(int index) {
		String infoString = " Duration: " + suesData.get(index).get("duration")
				+ ", price: " + suesData.get(index).get("cost") + ", amount: "
				+ suesData.get(index).get("amount");

		final JButton abort = new JButton("Abort Sue");
		abort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Abort sue
				System.out.println("sue aborted");
				// close popup
				Window win = SwingUtilities.getWindowAncestor(abort);
				win.setVisible(false);
			}
		});

		final JButton pay = new JButton("Pay");
		pay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO pay sue
				System.out.println("sue payed");
				// close popup
				Window win = SwingUtilities.getWindowAncestor(pay);
				win.setVisible(false);
			}
		});
		Object[] options = { abort, pay };
		JOptionPane.showOptionDialog(null, infoString, sues.getSelectedValue(),
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.YES_NO_CANCEL_OPTION, null, options, null);
	}

	@Override
	protected void fill() {
		upgradeButton.setEnabled(true);
		setBorder(new TitledBorder("Law("
				+ GuiManager.sharedInstance().getDs().getLevel("legalDepartment") + ")"));
		enemyData = GuiManager.sharedInstance().getDs().getEnemyNames();
		suesData = GuiManager.sharedInstance().getDs().getSues();

		String[] listData = new String[suesData.size()];
		for (int i = 0; i < listData.length; i++) {
			listData[i] = suesData.get(i).get("claimant") + " vs "
					+ suesData.get(i).get("defendant");
		}
		sues.setListData(listData);

		enemies.setListData(enemyData);
	}

	@Override
	protected void send() {
	}

}
