package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class ResearchPanel extends JPanel {

	public ResearchPanel() {
		TitledBorder tb = new TitledBorder("Research");
		setBorder(tb);
		setBackground(new Color(122, 189, 255));

		initEnemyPanel();
		initPatentLevelPanel();
		initresearchCampainPanel();
		
		add(researchCampainPanel, BorderLayout.NORTH);
		add(patentPanel, BorderLayout.CENTER);
		add(enemyPanel, BorderLayout.SOUTH);
		
	}
	
	private JPanel enemyPanel = new JPanel();
	private JPanel patentPanel = new JPanel();
	private JPanel researchCampainPanel = new JPanel();
	

	private JList<String> enemies;
	private Vector<String> enemyData = new Vector<String>();
	private ArrayList<JRadioButton> campaigns = new ArrayList<JRadioButton>();
	private ArrayList<JButton> info = new ArrayList<JButton>();

	
	private void initresearchCampainPanel() {
		// TODO Auto-generated method stub
		researchCampainPanel.setLayout(new GridLayout(3, 2));
		
		ButtonGroup bgroup = new ButtonGroup();
		for (int i = 1; i <= 3; i++) {
			JRadioButton rbutton = new JRadioButton("Campaign " + i, false);
			campaigns.add(rbutton);
			bgroup.add(rbutton);
			
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
			researchCampainPanel.add(campaigns.get(i));
			researchCampainPanel.add(info.get(i));
		}
	}

	private void initPatentLevelPanel() {
	
		JLabel labelPatentLevel = new JLabel("Patenlevel : ");
		JLabel labelPatentLevelNumber = new JLabel("0");
		JButton orderPatent = new JButton("Order");
		
		orderPatent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Popup
				
			}
		});
		
		patentPanel.add(labelPatentLevel);
		patentPanel.add(labelPatentLevelNumber);
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
				JButton check = new JButton("Spy Enemy");
				check.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Abort sue

					}
				});

				JButton spy = new JButton("Spy enemy");
				spy.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO pay sue

					}
				});
				Object[] options = { spy };
				JOptionPane.showOptionDialog(null, enemy, enemy,
						JOptionPane.CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, options, null);
			}
		});
		enemyPanel.add(new JScrollPane(enemies));
	}

	protected void makeInfoPopup(int index) {
		String infoString = "Index=" + String.valueOf(index);
		// TODO: Must be loaded
		JOptionPane.showMessageDialog(this, infoString);
	}
}
