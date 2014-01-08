package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	private Vector<String> suesData = new Vector<String>();

	public LawPanel() {
		TitledBorder tb = new TitledBorder("Law");
		setBorder(tb);
		setLayout(new BorderLayout());
		setBackground(new Color(0,179,0));

		initEnemyPanel();
		initSuesPanel();
		// add(suesPanel);
		// add(enemyPanel);
		// add(upgradeButton);
		add(suesPanel, BorderLayout.NORTH);
		add(enemyPanel, BorderLayout.CENTER);
		add(upgradeButton, BorderLayout.SOUTH);
	}

	private void initSuesPanel() {
		for (int i = 0; i < 5; i++) {
			suesData.add("Sue #" + (i + 1));
		}
		sues = new JList<String>(suesData);
		sues.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				int i = sues.getSelectedIndex();
				String sue = suesData.get(i);
				JButton abort = new JButton("Abort Sue");
				abort.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Abort sue

					}
				});

				JButton pay = new JButton("Pay");
				pay.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO pay sue

					}
				});
				Object[] options = { abort, pay };
				JOptionPane.showOptionDialog(null, sue, sue,
						JOptionPane.CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, options, null);
			}
		});
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
				String enemy = enemyData.get(i);
				JButton check = new JButton("Check Enemy");
				check.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Abort sue

					}
				});

				JButton sue = new JButton("Sue Enemy");
				sue.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO pay sue

					}
				});
				Object[] options = { check, sue };
				JOptionPane.showOptionDialog(null, enemy, enemy,
						JOptionPane.CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, options, null);
			}
		});
		enemyPanel.add(new JScrollPane(enemies));
	}

	@Override
	protected void fill() {
		// TODO Auto-generated method stub
		
	}

}
