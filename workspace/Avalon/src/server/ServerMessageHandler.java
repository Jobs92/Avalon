package server;

import java.util.ArrayList;

import campaigns.ExplicitCampaign;
import campaigns.ExplicitSpyingCampaign;
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
		if (txt.startsWith("UPGRADERESEARCH")){
			upgradeResearch(sender);
		}
		if (txt.startsWith("PATENT ")){
			int level = Integer.parseInt(txt.substring("PATENT ".length()));
			patent(sender, level);
		}
		if (txt.startsWith("RELEASE ")){
			int level = Integer.parseInt(txt.substring("RELEASE ".length()));
			release(sender, level);
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

	private void spy(Connection sender, int opponent) {
		//auf martin warten
//		ExplicitSpyingCampaign sc = new ExplicitSpyingCampaign(Market.sharedInstance().getCompanyById(opponent), campaign)
//		sender.getCompany().getResearch().startCampaign(spyingCampaign, target);
	}

	private void release(Connection sender, int level) {
//		sender.getCompany().getResearch().
		
	}

	private void patent(Connection sender, int level) {
		// TODO Auto-generated method stub
		
	}

	private void upgradeResearch(Connection sender) {
		// TODO Auto-generated method stub
		
	}

	private void produce(Connection sender, int amount) {
		// TODO Auto-generated method stub
		
	}

	private void upgradeProduction(Connection sender) {
		// TODO Auto-generated method stub
		
	}

	private void buyRessources(Connection sender, int supplierId, int amount) {
		// TODO Auto-generated method stub
		
	}

	private void startMarketingCampaign(Connection sender, int campaign) {
		// TODO Auto-generated method stub
		
	}

	private void upgradeMarketing(Connection sender) {
		// TODO Auto-generated method stub
		
	}

	private void isReady(Connection sender) {
		// TODO Auto-generated method stub
		
	}

	public void addPlayer(Connection p) {
		players.add(p);
	}

}
