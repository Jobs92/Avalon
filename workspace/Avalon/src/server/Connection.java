package server;

import gameManager.GameManager;

import java.net.*;
import java.io.*;

import utils.DataSnapshot;
import company.Company;

/**
 * @author Frederik
 * Manages the connection to a single client. As thread, this object is waiting for incoming Strings. For this a BufferedReader is used.
 * To inform the client, a ObjectOutputStream is used.
 */
public class Connection extends Thread {

	private boolean active;
	private Socket socket;
	private Company company;
	private PrintWriter out;
	private BufferedReader in;
	private ObjectOutputStream out_object;

	public Connection(Socket socket) {
		this.socket = socket;
		initialize();
	}

	/**
	 * Creates a PrintWriter to send Strings, an ObjectOutputStream to send DataSnapshot objects
	 * and a BufferedReader to receive Strings.
	 */
	private void initialize() {
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out_object = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			close();
			e.printStackTrace();
		}

		createCompany();
		active = true;
		start();
	}

	/**
	 * Creates a company object for this connection with the ID assigned from the GameManager.
	 */
	private void createCompany() {
		company = new Company(this);
		int id = GameManager.sharedInstance().addPlayer(company);
		company.setId(id);
	}
	
	public Company getCompany(){
		return company;
	}

	/** 
	 * Waits for incoming Strings from the Client.
	 */
	public void run() {
		String txt;
		while (active) {
			try {
				if ((txt = in.readLine()) != null) {
					System.out.println("Server bekommt von "+company.getName()+" : " + txt);
					ServerMessageHandler.sharedInstance().handleMessage(txt,
							this);
				}

			} catch (IOException e) {
				close();
			}
		}
	}

	public void send(String txt) {
		System.out.println("Server sendet: " + txt);
		out.println(txt);
	}
	
	public void sendSnapshot(DataSnapshot sd){
		try {
			System.out.println("sendet snapshot");
			out_object.writeObject(sd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes Streams and Socket. Informs the Company, that the Connection is closed.
	 */
	public void close() {
		active = false;
		System.out.println(company.getName() + " hat das Spiel verlassen.");
		company.setActive(false);
		try {
			if (in != null){
				in.close();
			}
			if (out != null){
				out.close();
			}
			if (out_object != null){
				out_object.close();
			}
			if (socket != null){
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}