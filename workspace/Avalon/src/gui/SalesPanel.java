package gui;

import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class SalesPanel extends JPanel {
	private JTable table;
	private Container container;

	public SalesPanel() {
		TitledBorder tb = new TitledBorder("Sales");
		setBorder(tb);

		// create table
		// table = new JTable();
		// container = new Container();
		//
		// container.add(new JScrollPane(table));

		String[][] data = new String[][] { { "a", "b", "c", "d" },
				{ "e", "f", "g", "h" }, { "i", "j", "k", "l" } };

		// Die Column-Titles
		String[] title = new String[] { "A", "B", "C", "D" };

		// Das JTable initialisieren
		JTable table = new JTable(data, title);

		add(table);
	}
}
