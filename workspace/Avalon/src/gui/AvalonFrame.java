package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.Api;

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
	private ProductionPanel productionPanel = new ProductionPanel();
	private ArrayList<AvalonPanel> panels = new ArrayList<AvalonPanel>();
	private JButton nextRoundButton = new JButton("Next Round");
	private JLabel roundLabel = new JLabel("Round");

	public AvalonFrame() {
		setVisible(true);
		setBounds(
				0,
				0,
				java.awt.Toolkit.getDefaultToolkit().getScreenSize().width,
				java.awt.Toolkit.getDefaultToolkit().getScreenSize().height - 50);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		setTitle("Avalon - Wirtschaftssimulation");

		gamePanel.setLayout(new GridLayout(2, 4));
		initGamePanel();
		add(gamePanel, BorderLayout.CENTER);

		add(nextRoundButton, BorderLayout.SOUTH);

		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new BorderLayout());
		JLabel headerLabel = new JLabel("AVALON");
		headerLabel.setFont(new Font(null, Font.PLAIN, 20));
		headerPanel.add(headerLabel, BorderLayout.WEST);
		headerPanel.add(roundLabel, BorderLayout.EAST);
		add(headerPanel, BorderLayout.NORTH);
	}

	private void initGamePanel() {
		// add panels
		panels.add(companyPanel);
		panels.add(productionPanel);
		panels.add(salesPanel);
		panels.add(purchasePanel);
		panels.add(researchPanel);
		panels.add(marketingPanel);
		panels.add(lawPanel);
		panels.add(messagePanel);
		for (JPanel panel : panels) {
			gamePanel.add(panel);
		}

		// configure button
		nextRoundButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// send confirm
				int accepted = JOptionPane
						.showConfirmDialog(
								null,
								"Do you really want to finish this round and go on with the next round?",
								"Next Round", JOptionPane.YES_NO_OPTION);
				if (accepted == 0) {
					send();
				}
			}
		});
	}

	protected void send() {
		Api api = GuiManager.sharedInstance().getApi();
		for (AvalonPanel p : panels) {
			p.send();
		}
		// production
		api.ready();
	}

	public void fill() {
		// update round
		roundLabel.setText("Round #"
				+ GuiManager.sharedInstance().getDs().getRound());
		for (AvalonPanel p : panels) {
			p.fill();
		}
		revalidate();
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		AvalonFrame f = new AvalonFrame();
	}
}
