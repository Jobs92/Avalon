package gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class CompanyPanel extends JPanel {

	public CompanyPanel() {
		TitledBorder tb = new TitledBorder("Company");
		setBorder(tb);
	}
}
