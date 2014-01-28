package client;

/**
 * @author Frederik
 * Offers methods for the client for actions on the server. Sends needed Strings to the server.
 */
public class Api {
	private Connection connection;
	
	public Api(Connection connection){
		this.connection = connection;
	}
	
	/**
	 * @param txt
	 * Sends given chat message to the server.
	 */
	public void sendChat(String txt){
		String s = "CHAT " + txt;
		connection.send(s);
		connection.flush();
	}
	
	/**
	 * Starts the game on the server.
	 */
	public void startGame(){
		connection.send("STARTGAME ");
		connection.flush();
	}
	
	/**
	 * @param name
	 * Sends chosen name to the server.
	 */
	public void setName(String name){
		String s = "NAME " + name;
		connection.send(s);
		connection.flush();
	}

	/**
	 * @param x
	 * Sends a production job to the server at the end of the round.
	 */
	public void produce(int x){
		String s = "PRODUCE " + x;
		connection.send(s);
	}
	
	/**
	 * Informs the server, that the player is ready.
	 */
	public void ready(){
		String s = "READY ";
		connection.send(s);
		connection.flush();
	}
	
	/**
	 * Upgrades the marketing department on the server at the end of the round.
	 */
	public void upgradeMarketing(){
		String s = "UPGRADEMARKETING ";
		connection.send(s);
	}
	
	/**
	 * Downgrades the marketing department on the server at the end of the round.
	 */
	public void downMarketing(){
		String s = "DOWNGRADEMARKETING ";
		connection.send(s);
	}
	
	/**
	 * @param campaign
	 * Starts a given marketing campaign at the end of the round.
	 */
	public void startMarketingCampaign(int campaign){
		String s = "STARTMARKETINGCAMPAIGN " + campaign;
		connection.send(s);
	}
	
	/**
	 * @param supplier
	 * @param amount
	 * A order to buy the given amount of resources at the given supplier is created at the end of the round.
	 */
	public void buy(int supplier, int amount){
		String s = "BUY " + supplier + "#" + amount;
		connection.send(s);
	}
	
	/**
	 * Upgrades the production department at the end of the round.
	 */
	public void upgradeProduction(){
		String s = "UPGRADEPRODUCTION ";
		connection.send(s);
	}
	
	/**
	 * Downgrades the production department at the end of the round. 
	 */
	public void downgradeProduction(){
		String s = "DOWNGRADEPRODUCTION ";
		connection.send(s);
	}
	
	/**
	 * @param campaign
	 * Starts the given research campaign at the end of the round.
	 */
	public void startResearchCampaign(int campaign){
		String s = "STARTRESEARCHCAMPAIGN " + campaign;
		connection.send(s);
	}
	
	/**
	 * Upgrades the research department at the end of the round.
	 */
	public void upgradeResearch(){
		String s = "UPGRADERESEARCH ";
		connection.send(s);
	}
	
	/**
	 * Downgrades the research department at the end of the round.
	 */
	public void downgradeResearch(){
		String s = "DOWNGRADERESEARCH ";
		connection.send(s);
	}
	
	/**
	 * The current researched level is patented at the end of the round.
	 */
	public void patent(){
		String s = "PATENT ";
		connection.send(s);
	}
	
	/**
	 * @param name
	 * A new product is released with the given name at the end of the round.
	 */
	public void release(String name){
		String s = "RELEASE " + name;
		connection.send(s);
	}
	
	/**
	 * @param opponent
	 * A spying campaign at the given player is started at the end of the round.
	 */
	public void spy(int opponent){
		String s = "SPY " + opponent;
		connection.send(s);
	}
	
	/**
	 * @param level
	 * @param amount
	 * The price of the given product is set to the given amount at the end of the round.
	 */
	public void setPrice(int level, int amount){
		String s = "PRICE " + level + "#" + amount;
		connection.send(s);
	}
	
	/**
	 * @param opponent
	 * Checks, whether the given opponent has spied you.
	 */
	public void checkOpponent(int opponent){
		String s = "CHECK " + opponent;
		connection.send(s);
	}
	
	/**
	 * @param opponent
	 * The given opponent is sued at the end of the round.
	 */
	public void sueOpponent(int opponent){
		String s = "SUE " + opponent;
		connection.send(s);
	}
	
	/**
	 * As defendant, the claimed amount of the current lawsuit is paid at the end of the round.
	 */
	public void payAmount(){
		String s = "PAYAMOUNT ";
		connection.send(s);
	}
	
	/**
	 * Stops a current lawsuit as claimant at the end of the round.
	 */
	public void abandonLawsuit(){
		String s = "ABANDONLAWSUIT ";
		connection.send(s);
	}
	
	/**
	 * Upgrades the legal department at the end of the round.
	 */
	public void upgradeLegalDepartment(){
		String s = "UPGRADELEGALDEPARTMENT ";
		connection.send(s);
	}
	
	/**
	 * Downgrades the legal department at the end of the round.
	 */
	public void downgradeLegalDepartment(){
		String s = "DOWNGRADELEGALDEPARTMENT ";
		connection.send(s);
	}
}
