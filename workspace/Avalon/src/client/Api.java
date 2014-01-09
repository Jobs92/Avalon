package client;

public class Api {
	private Connection connection;
	
	public Api(Connection connection){
		this.connection = connection;
	}

	public void produce(int x){
		String s = "PRODUCE " + x;
		connection.send(s);
	}
	
	public void ready(){
		String s = "READY ";
		connection.send(s);
	}
	
	public void upgradeMarketing(){
		String s = "UPGRADEMARKETING ";
		connection.send(s);
	}
	
	public void startMarketingCampaign(int campaign){
		String s = "STARTMARKETINGCAMPAIGN " + campaign;
		connection.send(s);
	}
	
	public void buy(int supplier, int amount){
		String s = "BUY " + supplier + "#" + amount;
		connection.send(s);
	}
	
	public void upgradeProduction(){
		String s = "UPGRADEPRODUCTION ";
		connection.send(s);
	}
	
	public void startResearchCampaign(int campaign){
		String s = "STARTRESEARCHCAMPAIGN " + campaign;
		connection.send(s);
	}
	
	public void upgradeResearch(){
		String s = "UPGRADERESEARCH ";
		connection.send(s);
	}
	
	public void patent(){
		String s = "PATENT ";
		connection.send(s);
	}
	
	public void release(){
		String s = "RELEASE ";
		connection.send(s);
	}
	
	public void spy(int campaign, int opponent){
		String s = "SPY " + campaign + "#" + opponent;
		connection.send(s);
	}
	
	public void setPrice(int level, int amount){
		String s = "PRICE " + level + "#" + amount;
		connection.send(s);
	}
	
	public void checkOpponent(int opponent){
		String s = "CHECK " + opponent;
		connection.send(s);
	}
	
	public void sueOpponent(int opponent){
		String s = "SUE " + opponent;
		connection.send(s);
	}
	
	public void payAmount(){
		String s = "PAYAMOUNT ";
		connection.send(s);
	}
	
	public void abandonLawsuit(){
		String s = "ABANDONLAWSUIT ";
		connection.send(s);
	}
	
	public void upgradeLegalDepartment(){
		String s = "UPGRADELEGALDEPARTMENT ";
		connection.send(s);
	}
}