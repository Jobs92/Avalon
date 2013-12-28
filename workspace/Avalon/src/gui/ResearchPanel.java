package gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ResearchPanel extends JPanel {

	public ResearchPanel() {
		TitledBorder tb = new TitledBorder("Research");
		setBorder(tb);
		setBackground(new Color(122, 189, 255));
	}

}
