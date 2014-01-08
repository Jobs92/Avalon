package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import client.Connection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class GUI_Connect extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */

	public GUI_Connect() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 242, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIpAdresse = new JLabel("IP - Adresse:");
		lblIpAdresse.setBounds(10, 82, 99, 14);
		contentPane.add(lblIpAdresse);
		
		textField = new JTextField();
		textField.setBounds(103, 42, 112, 20);
		textField.setText("Namen eingeben...");
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(103, 79, 112, 20);
		textField_1.setText("91.89.60.182");
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNickname = new JLabel("Nickname:");
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
		
		JLabel lblWillkommen = new JLabel("Willkommen!");
		lblWillkommen.setBounds(89, 11, 79, 14);
		contentPane.add(lblWillkommen);
	}
	
	public void connect(){
		String nick = textField.getText();
		if (checkNick(nick) == false) {
			wrongNick("Bitte kein ',' im Spielername!");
		}else{
		
		
		try {
			Socket socket = new Socket(textField_1.getText() , 56557);
			Connection conn = new Connection(socket);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	public void wrongNick(String string) {
		JOptionPane.showMessageDialog(this,
			    string,
			    "Fehler",
			    JOptionPane.ERROR_MESSAGE);
		
	}

	public String getNick(){
		return textField.getText();
	}

	private boolean checkNick(String nick) {
		// TODO Auto-generated method stub
		if (nick.contains(",")) {
		return false;

		}else{
			return true;
		}
		
	}
}
