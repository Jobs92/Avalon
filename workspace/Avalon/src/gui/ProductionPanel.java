package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ProductionPanel extends AvalonPanel {
	private JLabel productLabel = new JLabel("Product (Level 1)");
	private JTextField amountTF = new JTextField("0");
	private JButton upgradeButton = new JButton("Upgrade");

	// private JList<String> products;// = new JList<String>();
	// private Vector<String> data = new Vector<String>();

	public ProductionPanel() {
		TitledBorder tb = new TitledBorder("Production");
		setBorder(tb);
		setLayout(new FlowLayout());
		setBackground(Color.ORANGE);

		add(productLabel);
		amountTF.setPreferredSize(new Dimension(100, 30));
		add(amountTF);
		
		upgradeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				makeUpgradePopup();
			}
		});
		add(upgradeButton);

		// for (int i = 0; i < 5; i++) {
		// data.add("Product #" + (i + 1));
		// }
		// products = new JList<String>(data);
		// products.addListSelectionListener(new ListSelectionListener() {
		//
		// @Override
		// public void valueChanged(ListSelectionEvent e) {
		// int i = products.getSelectedIndex();
		// String s = data.get(i);
		// String value = JOptionPane.showInputDialog(null,
		// "Set Production Amount for " + s, 0);
		// System.out.println(value);
		//
		// GuiManager.sharedInstance().getApi().produce(Integer.valueOf(value));
		// }
		// });
		//
		// products.setBackground(getBackground());
		// add(new JScrollPane(products));
	}
	
	protected void makeUpgradePopup() {
		int accepted = JOptionPane.showConfirmDialog(null,
				"Do you want to spy to upgrade the Production department for "
						+ GuiManager.sharedInstance().getDs()
								.getUpgradeCosts("production") + "?", "Upgrade",
				JOptionPane.YES_NO_OPTION);
		if (accepted == 0) {
			upgradeButton.setEnabled(false);
			// accepted
			GuiManager.sharedInstance().getApi().upgradeProduction();
		}
	}

	@Override
	protected void fill() {
		setBorder(new TitledBorder("Production (Level: "
				+ GuiManager.sharedInstance().getDs()
						.getLevel("production") +", Fixcosts: "+GuiManager.sharedInstance().getDs().getDepartmentFixcosts("production")+")"));
		productLabel.setText("Product (Level "
				+ GuiManager.sharedInstance().getDs().getHighestProductLevel()
				+ ")");
		// ArrayList<Dictionary<String, String>> productNames =
		// GuiManager.sharedInstance().getDs().getProducts();
		// products.setListData(productNames);
	}

	@Override
	protected void send() {
		GuiManager.sharedInstance().getApi()
				.produce(Integer.valueOf(amountTF.getText()));
	}

}
