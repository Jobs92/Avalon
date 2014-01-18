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
	private BufferedReader inTxt = null;
	private ObjectInputStream in_object;
	private boolean active = true;

	public Connection(Socket socket) {
		this.socket = socket;
	}

	public boolean connect() {
		try {
			out = new PrintWriter(socket.getOutputStream());
			inTxt = new BufferedReader(new InputStreamReader(
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
		DataSnapshot ds;
		while (active) {
			try {
				if (GuiManager.sharedInstance().gameStarted()){
					if ((ds = (DataSnapshot) in_object.readObject()) != null) {
						GuiManager.sharedInstance().update(ds);
					}
				}else{
					String s;
					if ((s = inTxt.readLine()) != null) {
						System.out.println("hat String " + s);
						ClientMessageHandler.handleMessage(s);
					}

				}
			}catch (IOException | ClassNotFoundException e) {
				close();
				break;
			}
		}
	}

	public void send(String txt) {
		System.out.println("Client sendet: " + txt);
		out.println(txt);
	}
	
	public void flush(){
		out.flush();
	}

	public void close() {
		active = false;
		// more todo?
		try {
			out.close();
			inTxt.close();
			socket.close();
			System.out.println("Schlieﬂt Verbindung...");
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
