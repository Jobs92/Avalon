package server;
import java.lang.*;
import java.io.*;
import java.net.*;

class Server {
	private static ServerSocket server;
   public static void main(String args[]) {
      try {
         server = new ServerSocket(56557);
         System.out.print(server.getLocalPort());
         while (true){
        	 Socket skt = server.accept();
        	 Connection conn = new Connection(skt);
        	 MessageHandler.sharedInstance().addPlayer(conn);
         }
      }
      catch(Exception e) {
         System.out.print("Whoops! It didn't work!\n");
      }
   }
   
   public static void close(){
       try {
		server.close();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }
}
