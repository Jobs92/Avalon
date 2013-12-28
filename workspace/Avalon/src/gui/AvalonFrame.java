package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

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
	private JButton nextRoundButton = new JButton("Next Round");

	public AvalonFrame() {
		setVisible(true);
		setBounds(200, 200, 1000, 800);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		setTitle("Avalon - Wirtschaftssimulation");

		gamePanel.setLayout(new GridLayout(2, 3));
		initGamePanel();
		add(gamePanel, BorderLayout.CENTER);

		messagePanel.setPreferredSize(new Dimension(300, 100));
		add(messagePanel, BorderLayout.EAST);

		add(nextRoundButton, BorderLayout.SOUTH);
		
		JLabel headerLabel = new JLabel("AVALON");
		headerLabel.setFont(new Font(null, Font.PLAIN, 20));
		add(headerLabel, BorderLayout.NORTH);
	}

	private void initGamePanel() {
		//add panels
		JPanel t = new JPanel();
		t.setBorder(new TitledBorder("outer"));
//		t.setLayout(new BorderLayout());
//		t.setLayout(new BoxLayout(t, BoxLayout.Y_AXIS));
//		t.setLayout(new GridLayout(2, 1));
		t.setLayout(new FlowLayout());
		
		t.add(new JButton("iejaoi"));
		t.add(salesPanel);
		t.add(marketingPanel);
		
//		t.add(new JButton("iejaoi"));
//		t.add(new JButton("aaaa"));
		panels.add(companyPanel);
		panels.add(t);
//		panels.add(salesPanel);
		panels.add(purchasePanel);
		panels.add(researchPanel);
		panels.add(marketingPanel);
		panels.add(lawPanel);
		for (JPanel panel : panels) {
			gamePanel.add(panel);
		}
		
		//configure button
		nextRoundButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// send confirm
				JOptionPane.showConfirmDialog(null, "Next Round");
			}
		});
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		AvalonFrame f = new AvalonFrame();
	}
}
