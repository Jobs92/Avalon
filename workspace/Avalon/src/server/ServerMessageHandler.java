package server;

import gameManager.GameManager;

import java.util.ArrayList;

import company.Company;

import otherclasses.Order;
import campaigns.MarketingCampaign;
import campaigns.ResearchCampaign;
import campaigns.SpyingCampaign;
import market.Market;

/**
 * @author Frederik
 * Interprets and handles the incoming strings.
 */
public class ServerMessageHandler {

	private ArrayList<Connection> players = new ArrayList<Connection>();
	private static ServerMessageHandler sharedInstance;

	public static ServerMessageHandler sharedInstance() {
		if (ServerMessageHandler.sharedInstance == null) {
			ServerMessageHandler.sharedInstance = new ServerMessageHandler();
		}
		return ServerMessageHandler.sharedInstance;
	}

	private ServerMessageHandler() {
	}

	
	/**
	 * Interprets an given String and executes the wanted action.
	 * @param txt
	 * @param sender
	 */
	public void handleMessage(String txt, Connection sender) {
		System.out.println("ServerHandler handlet: " + txt);
		if (txt.startsWith("NAME")){
			setName(sender, txt.substring("NAME ".length()));
		}
		if(txt.startsWith("CHAT")){
		 String s = "CHAT " + sender.getCompany().getId() + sender.getCompany().getName() + ": " + txt.substring("CHAT ".length());
		 sendChat(s);
		}
		if (txt.startsWith("READY")){
			isReady(sender);
		}
		if (txt.startsWith("STARTGAME")){
			broadcast("GAMESTARTED");
			startGame();
		}
		if (txt.startsWith("UPGRADEMARKETING")){
			upgradeMarketing(sender);
		}
		if (txt.startsWith("DOWNGRADEMARKETING")){
			downgradeMarketing(sender);
		}
		if (txt.startsWith("STARTMARKETINGCAMPAIGN ")){
			int campaign = Integer.parseInt(txt.substring("STARTMARKETINGCAMPAIGN ".length()));
			startMarketingCampaign(sender, campaign);
		}
		if (txt.startsWith("BUY ")){
			String[] split = txt.substring("BUY ".length()).split("#");
			int supplierId = Integer.parseInt(split[0]);
			int amount = Integer.parseInt(split[1]);
			buyRessources(sender, supplierId, amount);
		}
		if (txt.startsWith("UPGRADEPRODUCTION")){
			upgradeProduction(sender);
		}
		if (txt.startsWith("DOWNGRADEPRODUCTION")){
			downgradeProduction(sender);
		}
		if (txt.startsWith("PRODUCE ")){
			int amount = Integer.parseInt(txt.substring("PRODUCE ".length()));
			produce(sender, amount);
		}
		if (txt.startsWith("STARTRESEARCHCAMPAIGN")){
			int campaign = Integer.parseInt(txt.substring("STARTRESEARCHCAMPAIGN ".length()));
			startResearchCampaign(sender, campaign);
		}
		if (txt.startsWith("UPGRADERESEARCH")){
			upgradeResearch(sender);
		}
		if (txt.startsWith("DOWNGRADERESEARCH")){
			downgradeResearch(sender);
		}
		if (txt.startsWith("PATENT")){
			patent(sender);
		}
		if (txt.startsWith("RELEASE")){
			String name = txt.substring("RELEASE ".length());
			release(sender, name);
		}
		if (txt.startsWith("SPY ")){
			int opponent = Integer.parseInt(txt.substring("SPY ".length()));
			spy(sender, opponent);
		}
		if (txt.startsWith("PRICE ")){
			String[] split = txt.substring("PRICE ".length()).split("#");
			int level = Integer.parseInt(split[0]);
			int price = Integer.parseInt(split[1]);
			setPrice(sender, level, price);
		}
		if (txt.startsWith("CHECK ")){
			int opponent = Integer.parseInt(txt.substring("CHECK ".length()));
			check(sender, opponent);
		}
		if (txt.startsWith("SUE ")){
			int opponent = Integer.parseInt(txt.substring("SUE ".length()));
			sue(sender, opponent);
		}
		if (txt.startsWith("PAYAMOUNT ")){
			payAmount(sender);
		}
		if (txt.startsWith("ABANDONLAWSUIT")){
			abandonLawsuit(sender);
		}
		if (txt.startsWith("DOWNGRADELEGALDEPARTMENT")){
			downgradeLegalDepartment(sender);
		}
		if (txt.startsWith("UPGRADELEGALDEPARTMENT")){
			upgradeLegalDepartment(sender);
		}
	}
	
	private void downgradeMarketing(Connection sender) {
		sender.getCompany().getMarketing().downgrade();
	}

	private void downgradeProduction(Connection sender) {
		sender.getCompany().getProduction().downgrade();
	}

	private void downgradeResearch(Connection sender) {
		sender.getCompany().getResearch().downgrade();
	}

	private void downgradeLegalDepartment(Connection sender) {
		sender.getCompany().getLegaldepartment().downgrade();
	}

	private void broadcast(String s){
		for (Company c : Market.sharedInstance().getCompanies()) {
			c.getConnection().send(s);
		}
	}
	
	private void sendChat(String s){
		broadcast(s);
	}

	private void setName(Connection sender, String name) {
		sender.getCompany().setName(name);
	}
	
	private void startGame(){
		GameManager.sharedInstance().startGame();
	}

	private void upgradeLegalDepartment(Connection sender) {
		sender.getCompany().getLegaldepartment().upgrade();
	}

	private void abandonLawsuit(Connection sender) {
		sender.getCompany().getLegaldepartment().abandonLawsuit();
	}

	private void payAmount(Connection sender) {
		sender.getCompany().getLegaldepartment().payAmount();
	}

	private void sue(Connection sender, int opponent) {
		sender.getCompany().getLegaldepartment().sueOpponent(Market.sharedInstance().getCompanyById(opponent));
	}

	private void check(Connection sender, int opponent) {
		sender.getCompany().getLegaldepartment().checkOpponent(Market.sharedInstance().getCompanyById(opponent));
	}

	private void setPrice(Connection sender, int level, int price) {
		sender.getCompany().getSales().setPrice(level, price);
	}

	private void spy(Connection sender, int opponent) {
		SpyingCampaign spyingCampaign = sender.getCompany().getResearch().getSpyingCampaignByID(0);
		sender.getCompany().getResearch().startCampaign(spyingCampaign, opponent);;
	}

	private void release(Connection sender, String name) {
		sender.getCompany().getResearch().applyResearchResults(name);
	}

	private void patent(Connection sender) {
		sender.getCompany().getResearch().patentResearchLevel();
	}

	private void upgradeResearch(Connection sender) {
		sender.getCompany().getResearch().upgradeDepartment();
	}
	
	private void startResearchCampaign(Connection sender, int id) {
		ResearchCampaign campaign = (ResearchCampaign)sender.getCompany().getResearch().getCampaignByID(id);
		sender.getCompany().getResearch().startCampaign(campaign);
	}

	private void produce(Connection sender, int amount) {
		sender.getCompany().getProduction().produce(sender.getCompany().getWarehouse().getHighestProduct().getLevel(), amount);	
	}

	private void upgradeProduction(Connection sender) {
		sender.getCompany().getProduction().upgrade();
	}

	private void buyRessources(Connection sender, int supplierId, int amount) {
		Order order = new Order(Market.sharedInstance().getSupplierById(supplierId), amount);
		sender.getCompany().getPurchase().addOrder(order);
	}

	private void startMarketingCampaign(Connection sender, int id) {
		MarketingCampaign campaign = (MarketingCampaign)sender.getCompany().getMarketing().getCampaignByID(id);
		sender.getCompany().getMarketing().startCampaign(campaign);
	}

	private void upgradeMarketing(Connection sender) {
		sender.getCompany().getMarketing().upgradeDepartment();
	}

	private void isReady(Connection sender) {
		sender.getCompany().setReady(true);	
	}

	public void addPlayer(Connection p) {
		players.add(p);
	}

}
