package gui;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AvalonPanel extends JPanel {
	protected abstract void fill();
	protected abstract void send();
}
