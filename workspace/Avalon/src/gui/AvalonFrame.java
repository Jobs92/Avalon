package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	private MarketPanel marketPanel = new MarketPanel();
	private ProductionPanel productionPanel = new ProductionPanel();

	private ArrayList<AvalonPanel> panels = new ArrayList<AvalonPanel>();
	// private JButton nextRoundButton = new JButton("Next Round");
	private JButton nextRoundButton = new AvalonButton("Next Round");
	private JLabel roundLabel = new JLabel("Round");
	private JButton refreshButton = new AvalonButton("Refresh");
	private JPanel headerPanel = new JPanel();
	private JPanel eastPanel = new JPanel();
	private JLabel headerLabel = new JLabel();

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
		setBackground(new Color(52, 152, 219));
		setTitle("Avalon - Wirtschaftssimulation");

		gamePanel.setLayout(new GridLayout(2, 4));
		initGamePanel();
		add(gamePanel, BorderLayout.CENTER);

		add(nextRoundButton, BorderLayout.SOUTH);

		headerPanel.setLayout(new BorderLayout());
		headerLabel = new JLabel("AVALON");
		headerLabel.setFont(new Font(null, Font.PLAIN, 20));
		headerPanel.add(headerLabel, BorderLayout.WEST);
		headerPanel.add(roundLabel, BorderLayout.CENTER);
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (AvalonPanel p : panels) {
					p.fill();
				}
			}
		});
		headerPanel.add(refreshButton, BorderLayout.EAST);
		add(headerPanel, BorderLayout.NORTH);
		eastPanel.setLayout(new GridLayout(1, 1));
		eastPanel.add(messagePanel);
		add(eastPanel, BorderLayout.EAST);
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
		panels.add(marketPanel);
		panels.add(messagePanel);
		headerPanel.setBackground(new Color(52, 152, 219));
		int j = 0;
		messagePanel.setBackground(new Color(92, 192, 255));
		messagePanel.setPreferredSize(new Dimension(200, 0));
		eastPanel.setBackground(new Color(92, 192, 255));
		for (int i = 0; i < panels.size() - 1; i++) {
			AvalonPanel panel = panels.get(i);
			j++;
			if (j == 4) {
				j++;
			}
			Color c;
			if (j % 2 == 0) {
				c = new Color(92, 192, 255);
			} else {
				c = new Color(81, 168, 225);
			}
			c = Color.WHITE;
			panel.setBackground(c);
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
					nextRoundButton.setEnabled(false);
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
		String header = GuiManager.sharedInstance().getDs().getCompanyName()
				+ "s AVALON";
		headerLabel.setText(header);
		// update round
		int round = GuiManager.sharedInstance().getDs().getRound() + 1;
		int a = round % 4;
		String s = "Round #" + round + ", Season: ";
		switch (a) {
		case 0:
			s += "Spring";
			break;
		case 1:
			s += "Summer";
			break;
		case 2:
			s += "Autumn";
			break;
		case 3:
			s += "Winter";
			break;

		default:
			break;
		}
		int year = 2007 + round/4;
		s+=" "+year;
		nextRoundButton.setEnabled(true);
		roundLabel.setText(s);
		for (AvalonPanel p : panels) {
			p.fill();
		}
		if (round > 1) {
			JOptionPane.showMessageDialog(this, "You are entering round #"
					+ (GuiManager.sharedInstance().getDs().getRound() + 1));
		}
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		AvalonFrame f = new AvalonFrame();
	}
}
