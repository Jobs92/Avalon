package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class PurchasePanel extends JPanel {
	private ArrayList<JLabel> supplierLabels = new ArrayList<JLabel>();
	private ArrayList<JButton> info = new ArrayList<JButton>();
	private ArrayList<JTextField> amount = new ArrayList<JTextField>();
	private JLabel sumLabel = new JLabel("Sum: ");

	public PurchasePanel() {
		TitledBorder tb = new TitledBorder("Purchase");
		setBorder(tb);
		setLayout(new BorderLayout());

		JPanel supplierPanel = new JPanel();
		supplierPanel.setLayout(new GridLayout(3, 3));

		// init arraylists
		for (int i = 1; i <= 3; i++) {
			supplierLabels.add(new JLabel("Supplier " + i));

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
			
			JTextField t = new JTextField();
			t.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
					updateSum();
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}
			});
			amount.add(t);
		}

		// fill ui
		for (int i = 0; i < 3; i++) {
			supplierPanel.add(supplierLabels.get(i));
			supplierPanel.add(info.get(i));
			supplierPanel.add(amount.get(i));
		}

		add(supplierPanel, BorderLayout.NORTH);
		add(sumLabel, BorderLayout.CENTER);
	}

	protected void makeInfoPopup(int index) {
		String infoString = "Index="+String.valueOf(index);
		//TODO: Must be loaded		
		JOptionPane.showMessageDialog(this, infoString);
	}

	private void updateSum() {
		int sum = 0;
		for (JTextField t : amount) {
			String s = t.getText();
			if (!s.equalsIgnoreCase("")) {
				sum += Integer.valueOf(t.getText());
			}
		}
		sumLabel.setText("Sum: " + sum);
	}

}
