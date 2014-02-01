package gui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import utils.DataSnapshot;

@SuppressWarnings("serial")
public class CompanyPanel extends AvalonPanel {

	private JLabel infoLabel = new JLabel();

	public CompanyPanel() {
		TitledBorder tb = new TitledBorder("Company");
		setBorder(tb);
		// setLayout(new GridLayout(5, 2));
		// setBackground(new Color(255, 0, 0, 95));
		add(infoLabel);
	}

	@Override
	protected void fill() {
		DataSnapshot ds = GuiManager.sharedInstance().getDs();
		String infoString = "<html><ul><li>Money: " + ds.getMoney() + "</li>";
		infoString += "<li>Profit: " + ds.getProfit() + "</li>";
		infoString += "<li>Revenue: " + ds.getRevenue() + "</li>";
		infoString += "<li>Total Costs: " + ds.getTotalCosts() + "</li>";
		infoString += "<li>Sold Smartphones: " + ds.getSoldSmartphones()
				+ "</li>";
		infoString += "<li>Fixcosts: " + ds.getFixCosts() + "</li>";
		infoString += "<li>Highest Product Level: "
				+ ds.getHighestProductLevel() + "</li>";
		infoString += "<li>Products on Stock: " + ds.getProductsOnStock()
				+ "</li>";
		infoString += "</ul></html>";
		infoLabel.setText(infoString);
		refresh();
	}

	@Override
	protected void send() {
	}

	@Override
	protected void refreshBackground(Color bg) {
	}
}
