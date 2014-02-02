package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ProductionPanel extends AvalonPanel {
	private JLabel productLabel = new JLabel("Product (Level 1)");
	private JLabel resourceLabel = new JLabel("Available Resources: 0");
	private JTextField amountTF = new JTextField("0");
	private AvalonButton upgradeButton = new AvalonButton("Upgrade");
	private AvalonButton downgradeButton = new AvalonButton("Downgrade");
	private JPanel southPanel = new JPanel();

	public ProductionPanel() {
		TitledBorder tb = new TitledBorder("Production");
		setBorder(tb);
		setLayout(new GridLayout(2, 2));

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

		downgradeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int accepted = JOptionPane.showConfirmDialog(null,
						"Do you want to downgrade the Production department?",
						"Downgrade", JOptionPane.YES_NO_OPTION);
				if (accepted == 0) {
					upgradeButton.setEnabled(false);
					// accepted
					GuiManager.sharedInstance().getApi().downgradeProduction();
				}
			}
		});
		southPanel.add(upgradeButton);
		southPanel.add(downgradeButton);
		southPanel.setBackground(getBackground());
		add(southPanel);
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
		upgradeButton.setEnabled(true);
		downgradeButton.setEnabled(true);
		setBorder(new TitledBorder("Production (Level: "
				+ GuiManager.sharedInstance().getDs().getLevel("production")
				+ ", Fixcosts: "
				+ GuiManager.sharedInstance().getDs()
						.getDepartmentFixcosts("production") + ")"));
		productLabel.setText(GuiManager.sharedInstance().getDs()
				.getHighestProductName()
				+ " (Level "
				+ GuiManager.sharedInstance().getDs().getHighestProductLevel()
				+ ")");
		resourceLabel.setText("Available Resources: "
				+ GuiManager.sharedInstance().getDs().getResources()
				+ "\n Variable Costs: "
				+ GuiManager.sharedInstance().getDs()
						.getVariableCostsProduction());
		amountTF.setText("0");
		refresh();
		refreshBackground(getBackground());
	}

	@Override
	protected void send() {
		String value = amountTF.getText();
		if (!value.equalsIgnoreCase("") && Integer.parseInt(value) > 0) {
			GuiManager.sharedInstance().getApi()
					.produce(Integer.valueOf(value));
		}
	}

	@Override
	protected void refreshBackground(Color bg) {
		southPanel.setBackground(bg);
	}

}
