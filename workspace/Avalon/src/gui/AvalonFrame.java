package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class AvalonFrame extends JFrame {
	private JPanel gamePanel = new JPanel();
	private MessagePanel messagePanel = new MessagePanel();

	private CompanyPanel companyPanel = new CompanyPanel();
	private SalesPanel salesPanel = new SalesPanel();
	private PurchasePanel purchasePanel = new PurchasePanel();
	private MarketingPanel marketingPanel = new MarketingPanel();
	private ResearchPanel researchPanel = new ResearchPanel();
	private LawPanel lawPanel = new LawPanel();
	private ArrayList<JPanel> panels = new ArrayList<JPanel>();

	public AvalonFrame() {
		setVisible(true);
		setBounds(200, 200, 1000, 800);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);

		gamePanel.setLayout(new GridLayout(2, 3));
		initGamePanel();
		add(gamePanel, BorderLayout.CENTER);
		
		messagePanel.setPreferredSize(new Dimension(300, 100));
		add(messagePanel, BorderLayout.EAST);
	}

	private void initGamePanel() {
		panels.add(companyPanel);
		panels.add(salesPanel);
		panels.add(purchasePanel);
		panels.add(researchPanel);
		panels.add(marketingPanel);
		panels.add(lawPanel);
		for (JPanel panel : panels) {
			gamePanel.add(panel);
		}
	}

	private static JButton simplify(JButton button) {
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		button.setBorder(compound);
		return button;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		AvalonFrame f = new AvalonFrame();
	}
}
