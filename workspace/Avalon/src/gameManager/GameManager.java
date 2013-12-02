package gameManager;

import java.util.ArrayList;

import otherclasses.Supplier;
import company.Company;
import config.Config;
import eventHandling.EventManager;
import market.Market;

public class GameManager {
	private int round = 0;
	private boolean active;
	private Company winner;
	private Market market;
	private EventManager eventManager;
	private static GameManager sharedInstance;
	private Config config;

	public static GameManager sharedInstance() {
		if (GameManager.sharedInstance == null) {
			GameManager.sharedInstance = new GameManager();
		}
		return GameManager.sharedInstance;
	}

	public void startGame() {
		initializeGame();
		nextRound();
	}

	private void initializeGame() {
		// TODO Auto-generated method stub
		config = new Config();
		market = new Market();
		eventManager = new EventManager();
		active = true;
		
		//Generate Supplier
		int amountSupplier = Math.min(config.getSupplierTrust().length, Math.min(config.getSupplierQuality().length, config.getSupplierPrice().length));
		for (int i = 0; i < amountSupplier; i++) {
			Supplier s = new Supplier(config.getSupplierPrice()[i], config.getSupplierTrust()[i], config.getSupplierQuality()[i]);
			market.addSupplier(s);
		}
		
	}

	private void nextRound() {
		round++;
		// Player decide further actions
		waitForPlayer();
	}
	
	private void simulate(){
		// Active Actions are simulated
		market.simulate();

		// Buying Behaviour at the market is simulated
		market.simBuyingBehaviour();

		// Events are simulated
		eventManager.simEvents();

		// Check for winner
		if (!checkWinner()) {
			// no winner --> next round is started
			nextRound();
		} else {
			// winner --> games is finished
			endGame();
		}
	}
	
	public void informReady(){
		ArrayList<Company> player = market.getCompanies();
		for (int i = 0; i < player.size(); i++) {
			if (!player.get(i).isReady());
			return;
		}
		simulate();
	}

	private void endGame() {
		// TODO Auto-generated method stub
		active = false;
	}
	
	
	public Config getConfig(){
		return config;
	}

	private boolean checkWinner() {
		int neededAmount = 0; // TODO: get correct value from configuration
		ArrayList<Company> cs = market.getCompanies();
		for (Company c : cs) {
			if (c.getMoney() >= neededAmount) {
			}
			winner = c;
			return true;
		}
		return false;
	}

	private void waitForPlayer() {
		ArrayList<Company> player = market.getCompanies();
		for (int i = 0; i < player.size(); i++) {
			player.get(i).setReady(false);
		}

	}

	public void addPlayer(Company c) {
		market.addCompany(c);
		c.setMarket(market);
	}

	public ArrayList<Company> getPlayer() {
		System.out.println(market);
		System.out.println(market.getCompanies());
		return market.getCompanies();
	}
	
	public int getRound() {
		return this.round;
	}
}
