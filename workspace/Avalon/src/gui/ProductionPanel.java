package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
	private JLabel resourceLabel = new JLabel("Available Resources: 0");
	private JTextField amountTF = new JTextField("0");
	private JButton upgradeButton = new JButton("Upgrade");

	// private JList<String> products;// = new JList<String>();
	// private Vector<String> data = new Vector<String>();

	public ProductionPanel() {
		TitledBorder tb = new TitledBorder("Production");
		setBorder(tb);
		setLayout(new GridLayout(2, 2));
		setBackground(Color.ORANGE);

		add(productLabel);
		amountTF.setPreferredSize(new Dimension(100, 30));
		add(amountTF);

		add(resourceLabel);
		upgradeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				makeUpgradePopup();
			}
		});
		add(upgradeButton);
	}

	protected void makeUpgradePopup() {
		int accepted = JOptionPane.showConfirmDialog(
				null,
				"Do you want to spy to upgrade the Production department for "
						+ GuiManager.sharedInstance().getDs()
								.getUpgradeCosts("production") + "?",
				"Upgrade", JOptionPane.YES_NO_OPTION);
		if (accepted == 0) {
			upgradeButton.setEnabled(false);
			// accepted
			GuiManager.sharedInstance().getApi().upgradeProduction();
		}
	}

	@Override
	protected void fill() {
		setBorder(new TitledBorder("Production (Level: "
				+ GuiManager.sharedInstance().getDs().getLevel("production")
				+ ", Fixcosts: "
				+ GuiManager.sharedInstance().getDs()
						.getDepartmentFixcosts("production") + ")"));
		productLabel.setText("Product (Level "
				+ GuiManager.sharedInstance().getDs().getHighestProductLevel()
				+ ")");
		resourceLabel.setText("Available Resources: "
				+ GuiManager.sharedInstance().getDs().getResources());
		refresh();
	}

	@Override
	protected void send() {
		String value = amountTF.getText();
		if (!value.equalsIgnoreCase("")) {
			GuiManager.sharedInstance().getApi()
					.produce(Integer.valueOf(value));
		}
	}

}
