package server;

import java.io.*;
import java.net.*;

/**
 * @author Frederik
 *
 */
public class Server {
	private static ServerSocket server;

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			server = new ServerSocket(56557);
			System.out.println("Server started on Port "+server.getLocalPort());
			while (true) {
				Socket skt = server.accept();
				Connection conn = new Connection(skt);
				ServerMessageHandler.sharedInstance().addPlayer(conn);
				System.out.println("New Connection");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close() {
		try {
			server.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
