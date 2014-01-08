package client;

import gui.GuiManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import utils.DataSnapshot;

public class Connection extends Thread {
	private Socket socket;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private ObjectInputStream in_object;
	private boolean active = true;

	public Connection(Socket socket) {
		this.socket = socket;
	}

	public boolean connect() {
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			in_object = new ObjectInputStream(socket.getInputStream());
			
			//Create Api
			Api api = new Api(this);
			GuiManager.sharedInstance().setApi(api);
		} catch (IOException e) {
			close();
			e.printStackTrace();
			return false;
		}

		active = true;
		start();
		return true;
	}

	// Receive Messages from the server
	public void run() {
		String txt;
		DataSnapshot ds;
		while (active) {
			try {
				if ((ds = (DataSnapshot) in_object.readObject()) != null) {
					GuiManager.sharedInstance().update(ds);
					System.out.println("Client bekommt money: " + ds.getMoney());
//					ClientMessageHandler.sharedInstance().handleMessage(txt,
//							this);
				}
			} catch (IOException | ClassNotFoundException e) {
				close();
			}
		}
	}

	public void send(String txt) {
		System.out.println("Client sendet: " + txt);
		out.println(txt);
	}

	public void close() {
		active = false;
		// more todo?
		try {
			out.close();
			in.close();
			close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 56557);
			Connection conn = new Connection(socket);
			BufferedReader userIn = new BufferedReader(new InputStreamReader(
					System.in));
			while (true) {
				System.out.println("wartet auf String");
				String s = userIn.readLine();
				conn.send(s);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
