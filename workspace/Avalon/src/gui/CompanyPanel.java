package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class CompanyPanel extends JPanel {

	private JLabel labelMoney = new JLabel("Money");
	private JLabel money = new JLabel("0");

	private JLabel labelFixcosts = new JLabel("Fixcosts");
	private JLabel fixcosts = new JLabel("0");

	private JLabel labelVarCosts = new JLabel("Variable Costs");
	private JLabel varCosts = new JLabel("0");

	private JLabel labelProductsOnStock = new JLabel("Products on Stock");
	private JLabel productsOnStock = new JLabel("0");

	private JLabel labelProductLevel = new JLabel("Highest Product Level");
	private JLabel productLevel = new JLabel("0");

	public CompanyPanel() {
		TitledBorder tb = new TitledBorder("Company");
		setBorder(tb);
		setLayout(new GridLayout(5, 2));

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
}
