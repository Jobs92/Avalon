package server;

import java.io.*;
import java.net.*;

public class Server {
	private static ServerSocket server;

	public static void main(String args[]) {
		try {
			server = new ServerSocket(56557);
			System.out.println(server.getLocalPort());
			while (true) {
				Socket skt = server.accept();
				Connection conn = new Connection(skt);
				ServerMessageHandler.sharedInstance().addPlayer(conn);
				System.out.println("Neue Vervindung");
			}
		} catch (Exception e) {
			System.err.println(e);
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
