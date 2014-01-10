package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class SalesPanel extends AvalonPanel {
	private JList<String> products;// = new JList<String>();
	private ArrayList<Dictionary<String, String>> productData;
	private Vector<String> data = new Vector<String>();
	private String[] names;

	public SalesPanel() {
		TitledBorder tb = new TitledBorder("Sales");
		setBorder(tb);
		setLayout(new BorderLayout());
		setBackground(new Color(255, 189, 122));

		for (int i = 0; i < 5; i++) {
			data.add("Product #" + (i + 1));
		}

		products = new JList<String>(data);
		products.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int i = products.getSelectedIndex();
				makePopup(i);
			}
		});

		products.setBackground(getBackground());
		add(new JScrollPane(products));
	}

	protected void makePopup(int i) {
		String value = JOptionPane.showInputDialog(null, "Set Price for "
				+ names[i], productData.get(i).get("price"));
		GuiManager
				.sharedInstance()
				.getApi()
				.setPrice(Integer.valueOf(productData.get(i).get("level")),
						Integer.valueOf(value));
	}

	@Override
	protected void fill() {
		productData = GuiManager.sharedInstance().getDs().getProducts();

		names = new String[productData.size()];
		for (int i = 0; i < names.length; i++) {
			names[i] = "Product (Level " + productData.get(i).get("level")
					+ " )";
		}
		products.setListData(names);
	}

	@Override
	protected void send() {
	}
}
