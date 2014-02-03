package gui;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import utils.DataSnapshot;

@SuppressWarnings("serial")
public class CompanyPanel extends AvalonPanel {

	private JLabel infoLabel = new JLabel();

	public CompanyPanel() {
		TitledBorder tb = new TitledBorder("Unternehmen");
		setBorder(tb);
		add(infoLabel);
	}

	@Override
	protected void fill() {
		DecimalFormat df = (DecimalFormat) NumberFormat
				.getInstance(Locale.GERMANY);
		DataSnapshot ds = GuiManager.sharedInstance().getDs();
		String infoString = "<html><ul><li>Kontostand: "
				+ df.format(ds.getMoney()) + "</li>";
		infoString += "<li>Gewinn: " + df.format(ds.getProfit()) + "</li>";
		infoString += "<li>Umsatz: " + df.format(ds.getRevenue()) + "</li>";
		infoString += "<li>Gesamtkosten: " + df.format(ds.getTotalCosts())
				+ "</li>";
		infoString += "<li>Verkaufte Smartphones: "
				+ df.format(ds.getSoldSmartphones()) + "</li>";
		infoString += "<li>Fixkosten: " + df.format(ds.getFixCosts()) + "</li>";
		infoString += "<li>Höchstes Produktlevel: "
				+ ds.getHighestProductLevel() + "</li>";
		infoString += "<li>Produkte im Lager: " + ds.getProductsOnStock()
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
