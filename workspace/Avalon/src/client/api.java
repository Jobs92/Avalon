package client;

public class api {
	private Connection connection;
	
	public api(Connection connection){
		this.connection = connection;
	}
	
	public void produce(int x){
		String s = "PRODUCE " + x;
		connection.send(s);
	}
}
