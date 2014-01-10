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
	private Market market = Market.sharedInstance();
	private EventManager eventManager = new EventManager();
	private static GameManager sharedInstance;
	private Config c = new Config();
	
	private GameManager(){
	}

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
		active = true;
		
		//Generate Supplier
		int amountSupplier = Math.min(Config.getSupplierTrust().length, Math.min(Config.getSupplierQuality().length, Config.getSupplierPrice().length));
		for (int i = 0; i < amountSupplier; i++) {
			Supplier s = new Supplier(Config.getSupplierPrice()[i], Config.getSupplierTrust()[i], Config.getSupplierQuality()[i]);
			market.addSupplier(s);
		}
		
	}

	private void nextRound() {
		market.informPlayers();
		round++;
		// Player decide further actions
		waitForPlayer();
	}
	
	private void simulate(){

		// Active Actions are simulated
		market.simulate();

		// Buying Behaviour at the market is simulated
		market.simulateMarket();

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
			if (!player.get(i).isReady()){
				//Not all player ready
				return;
			}
		}
		simulate();
	}

	private void endGame() {
		active = false;
	}

	private boolean checkWinner() {
		int neededAmount = Config.getAmountWin(); 
		ArrayList<Company> cs = market.getCompanies();
		for (Company c : cs) {
			if (c.getMoney() >= neededAmount) {
				winner = c;
				return true;
			}
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
	}
	
	public boolean getActive(){
		return this.active;
	}
	
	public Company getWinner(){
		return this.winner;
	}

	public ArrayList<Company> getPlayer() {
		return market.getCompanies();
	}
	
	public int getRound() {
		return this.round;
	}
	
	public void deleteInstance(){
		GameManager.sharedInstance = null;
	}
}
