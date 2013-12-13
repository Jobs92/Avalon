package gui;

import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class CompanyPanel extends JPanel {

	public CompanyPanel() {
		TitledBorder tb = new TitledBorder("Company");
		setBorder(tb);
	}
}
