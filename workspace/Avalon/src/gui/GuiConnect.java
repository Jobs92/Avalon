package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import client.Connection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

@SuppressWarnings("serial")
public class GuiConnect extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */

	public GuiConnect() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 242, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIpAdresse = new JLabel("IP-Address:");
		lblIpAdresse.setBounds(10, 82, 99, 14);
		contentPane.add(lblIpAdresse);

		textField = new JTextField();
		textField.setBounds(103, 42, 112, 20);
		textField.setText("Enter name...");
		textField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				textField.setText("");
				textField.removeKeyListener(this);
				textField.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						connect();
					}
				});
			}
		});
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(103, 79, 112, 20);
		textField_1.setText("localhost");
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNickname = new JLabel("Companyname:");
		lblNickname.setBounds(10, 45, 99, 14);
		contentPane.add(lblNickname);

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connect();
			}
		});
		btnConnect.setBounds(10, 120, 205, 23);
		contentPane.add(btnConnect);

		JLabel lblWillkommen = new JLabel("Welcome to Avalon!");
		lblWillkommen.setBounds(10, 11, 500, 14);
		contentPane.add(lblWillkommen);
	}

	public void connect() {
		String nick = textField.getText();
		if (checkNick(nick) == false) {
			wrongNick("Bitte kein ',' im Spielername!");
		} else {

			try {
				Socket socket = new Socket(textField_1.getText(), 56557);
				Connection conn = new Connection(socket);

				if (conn.connect()) {
					// send chosen name
					GuiManager.sharedInstance().getApi().setName(nick);

					GuiManager.sharedInstance().successfullLogin();
				}

			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void wrongNick(String string) {
		JOptionPane.showMessageDialog(this, string, "Error",
				JOptionPane.ERROR_MESSAGE);

	}

	public String getNick() {
		return textField.getText();
	}

	private boolean checkNick(String nick) {
		if (nick.contains(",")) {
			return false;

		} else {
			return true;
		}

	}
}
