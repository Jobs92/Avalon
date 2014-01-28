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

/**
 * @author Frederik
 * Thread, that manages the connection to the server for a client.
 */
public class Connection extends Thread {
	private Socket socket;
	private PrintWriter out = null;
	private BufferedReader inTxt = null;
	private ObjectInputStream in_object;
	private boolean active = true;

	public Connection(Socket socket) {
		this.socket = socket;
	}

	/**
	 * @return
	 * Tries to initiate a PrintWriter, to send Strings, a BufferedReader, to receive Strings (chat) and 
	 * an ObjectInputStream, to receive the DataSnapshot Objects. The api for the client is created.
	 */
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

	/** (non-Javadoc)
	 * @see java.lang.Thread#run()
	 * Before the game is started, this method waits for incoming Strings. After the game has started, the
	 * method waits for DataSnapshot objects and informs the GuiManager.
	 */
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
						ClientMessageHandler.handleMessage(s);
					}

				}
			}catch (IOException | ClassNotFoundException e) {
				close();
				break;
			}
		}
	}

	/**
	 * @param txt
	 * The given text is buffered in the PrintWriter.
	 */
	public void send(String txt) {
		System.out.println("Client sendet: " + txt);
		out.println(txt);
	}
	
	/**
	 * All buffered Strings are send to the server.
	 */
	public void flush(){
		out.flush();
	}

	/**
	 * Closes the In- and Output Streams when the connection is closed.
	 */
	public void close() {
		active = false;
		try {
			if (out != null) out.close();
			if (inTxt != null) inTxt.close();
			if (in_object != null) in_object.close();
			if (socket != null) socket.close();
			System.out.println("Schlieﬂt Verbindung...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
