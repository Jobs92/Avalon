package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class CompanyPanel extends AvalonPanel {

	private JLabel labelMoney = new JLabel("Money");
	private JLabel money = new JLabel("0", SwingConstants.RIGHT);

	private JLabel labelFixcosts = new JLabel("Fixcosts");
	private JLabel fixcosts = new JLabel("0", SwingConstants.RIGHT);

	private JLabel labelVarCosts = new JLabel("Variable Costs");
	private JLabel varCosts = new JLabel("0", SwingConstants.RIGHT);

	private JLabel labelProductsOnStock = new JLabel("Products on Stock");
	private JLabel productsOnStock = new JLabel("0", SwingConstants.RIGHT);

	private JLabel labelProductLevel = new JLabel("Highest Product Level");
	private JLabel productLevel = new JLabel("0", SwingConstants.RIGHT);

	public CompanyPanel() {
		TitledBorder tb = new TitledBorder("Company");
		setBorder(tb);
		setLayout(new GridLayout(5, 2));
		setBackground(new Color(255, 0, 0, 95));

		addLabels();
	}

	private void addLabels() {
		add(labelMoney);
		add(money);

		add(labelFixcosts);
		add(fixcosts);

		add(labelVarCosts);
		add(varCosts);

		add(labelProductLevel);
		add(productLevel);

		add(labelProductsOnStock);
		add(productsOnStock);
	}

	@Override
	protected void fill() {
		money.setText(String.valueOf(GuiManager.sharedInstance().getDs()
				.getMoney()));
		fixcosts.setText(String.valueOf(GuiManager.sharedInstance().getDs()
				.getFixCosts()));
		varCosts.setText(String.valueOf(GuiManager.sharedInstance().getDs()
				.getVarCosts()));
		productLevel.setText(String.valueOf(GuiManager.sharedInstance().getDs()
				.getHighestProductLevel()));
		productsOnStock.setText(String.valueOf(GuiManager.sharedInstance()
				.getDs().getProductsOnStock()));
	}
}
