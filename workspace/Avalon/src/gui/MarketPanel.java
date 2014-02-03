package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Dictionary;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class MarketPanel extends AvalonPanel {
	JLabel label = new JLabel("Markt information");
	private ArrayList<Dictionary<String, String>> products;

	public MarketPanel() {
		setBorder(new TitledBorder("Markt"));
		JScrollPane s = new JScrollPane();
		label.add(s);
		add(label);
	}

	@Override
	protected void fill() {
		products = GuiManager.sharedInstance().getDs().getMarketProducts();
		String text;
		if (products.size() == 0) {
			text = "Keine Produkte auf dem Markt.";
		} else {
			text = "<html><ul>";
			for (Dictionary<String, String> p : products) {
				text += "<li>Produkt: " + p.get("name") + "<ul><li>Unternehmen: "
						+ p.get("company") + "</li><li>Level: "
						+ p.get("level") + "</li> <li>Preis: " + p.get("price")
						+ "</ul></li>";
			}
			text += "</ul></html>";
		}
		label.setText(text);
	}

	@Override
	protected void send() {
	}

	@Override
	protected void refreshBackground(Color bg) {
	}

}
