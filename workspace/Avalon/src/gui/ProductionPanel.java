package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class ProductionPanel extends AvalonPanel {
	private JList<String> products;// = new JList<String>();
	private Vector<String> data = new Vector<String>();

	public ProductionPanel() {
		TitledBorder tb = new TitledBorder("Production");
		setBorder(tb);
		setLayout(new BorderLayout());
		setBackground(Color.ORANGE);

		for (int i = 0; i < 5; i++) {
			data.add("Product #" + (i + 1));
		}
		products = new JList<String>(data);
		products.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int i = products.getSelectedIndex();
				String s = data.get(i);
				// TODO: must be loaded
				String initialValue = "null";
				String value = JOptionPane.showInputDialog(null,
						"Set Production Amount for " + s, initialValue);
				System.out.println(value);
				// TODO: value is new price
			}
		});

		add(new JScrollPane(products));
	}

	@Override
	protected void fill() {
		String[] productNames = GuiManager.sharedInstance().getDs().getProducts();
		products.setListData(productNames);
	}

}
