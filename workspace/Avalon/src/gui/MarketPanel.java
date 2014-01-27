package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Dictionary;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class MarketPanel extends AvalonPanel {
	JLabel label = new JLabel("Market information");
	private ArrayList<Dictionary<String, String>> products;

	public MarketPanel() {
		setBorder(new TitledBorder("Market"));
		add(label);
	}

	@Override
	protected void fill() {
		products = GuiManager.sharedInstance().getDs().getMarketProducts();

		String text = "<html><ul>";
		for (Dictionary<String, String> p : products) {
			text += "<li>Product: " + p.get("name") + " (" + p.get("company")
					+ "( Level " + p.get("level") + ", price " + p.get("price")
					+ ")</li>";
		}
		text += "</ul></html>";
		label.setText(text);
	}

	@Override
	protected void send() {
	}

	@Override
	protected void refreshBackground(Color bg) {
	}

}
