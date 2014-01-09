package server;

import gameManager.GameManager;

import java.util.ArrayList;

import otherclasses.Order;
import campaigns.MarketingCampaign;
import campaigns.ResearchCampaign;
import campaigns.SpyingCampaign;
import market.Market;

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

	public void handleMessage(String txt, Connection sender) {
		System.out.println("ServerHandler handlet: " + txt);
		if (txt.startsWith("READY")){
			isReady(sender);
		}
		if (txt.startsWith("UPGRADEMARKETING")){
			upgradeMarketing(sender);
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
		if (txt.startsWith("PATENT")){
			patent(sender);
		}
		if (txt.startsWith("RELEASE")){
			release(sender);
		}
		if (txt.startsWith("SPY ")){
			String[] split = txt.substring("SPY ".length()).split("#");
			int id = Integer.parseInt(split[0]);
			int opponent = Integer.parseInt(split[1]);
			spy(sender, opponent, id);
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
		if (txt.startsWith("UPGRADELEGALDEPARTMENT")){
			upgradeLegalDepartment(sender);
		}
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

	private void spy(Connection sender, int opponent, int id) {
		SpyingCampaign spyingCampaign = sender.getCompany().getResearch().getSpyingCampaignByID(id);
		sender.getCompany().getResearch().startCampaign(spyingCampaign, opponent);;
	}

	private void release(Connection sender) {
		sender.getCompany().getResearch().applyResearchResults();
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
