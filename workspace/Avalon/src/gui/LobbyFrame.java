package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class LobbyFrame extends JFrame {

	private JPanel contentPane;
	private JTextPane textPane;
	private JTextPane chatInput;
	private javax.swing.text.Style style;
	private StyledDocument doc;
	private JScrollPane scrollPane;

	// private AvalonButton btnSpielStarten;

	/**
	 * Create the frame.
	 */
	public LobbyFrame() {
		setTitle("Avalon - Unternehmenssimulation - Lobby");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		AvalonButton btnSpielStarten = new AvalonButton("Spiel starten");
		btnSpielStarten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendStartGame();
			}
		});
		btnSpielStarten.setBounds(10, 360, 360, 30);
		contentPane.add(btnSpielStarten);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 305, 360, 50);
		contentPane.add(scrollPane_1);

		chatInput = new JTextPane();

		int condition = JComponent.WHEN_FOCUSED;
		InputMap iMap = chatInput.getInputMap(condition);
		ActionMap aMap = chatInput.getActionMap();

		String enter = "enter";
		iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
		aMap.put(enter, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				send();
			}
		});

		scrollPane_1.setViewportView(chatInput);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 360, 290);
		contentPane.add(scrollPane);

		textPane = new JTextPane();

		// Style anfang:
		doc = textPane.getStyledDocument();

		style = textPane.addStyle("I'm a Style", null);

		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);

	}

	public void send() {
		if (chatInput.getText().equals("")) {
		} else {
			GuiManager.sharedInstance().getApi().sendChat(chatInput.getText());
			chatInput.setText("");
		}
	}

	public void handleChat(String txt, int id) {
		Color col = GuiManager.sharedInstance().getColors()[id];
		StyleConstants.setForeground(style, col);
		try {
			doc.insertString(doc.getLength(), txt + "\n", style);
			textPane.select(doc.getLength(), doc.getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public void sendStartGame() {
		GuiManager.sharedInstance().getApi().startGame();
	}
}
