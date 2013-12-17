package server;

import gameManager.GameManager;

import java.net.*;
import java.io.*;

import company.Company;

public class Connection extends Thread {

	private boolean active;
	private Socket socket;
	private Company company;
	private PrintWriter out;
	private BufferedReader in;

	public Connection(Socket socket) {
		this.socket = socket;
		initialize();
	}

	private void initialize() {
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			close();
			e.printStackTrace();
		}

		createCompany();
		active = true;
		start();
	}

	private void createCompany() {
		company = new Company();
		GameManager.sharedInstance().addPlayer(company);
	}
	
	public Company getCompany(){
		return company;
	}

	public void run() {
		// send("CONNECTED ");
		// System.out.print("Run gestartet...Server");
		String txt;
		while (active) {
			try {
				if ((txt = in.readLine()) != null) {
					System.out.println("Server bekommt: " + txt);
					ServerMessageHandler.sharedInstance().handleMessage(txt,
							this);
				}

			} catch (IOException e) {
				close();
				// handler.removePlayer(this);
				// handler.spreadPlayer();
			}
		}
	}

	public void send(String txt) {
		System.out.println("Server sendet: " + txt);
		out.println(txt);
	}

	public void close() {
		active = false;

		// close Socket
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}