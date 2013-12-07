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
	private Market market = new Market();
	private EventManager eventManager = new EventManager();
	private static GameManager sharedInstance;
	
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
			if (!player.get(i).isReady()){
				return;
			}
		}
		simulate();
	}

	private void endGame() {
		// TODO Auto-generated method stub
		active = false;
	}

	private boolean checkWinner() {
		//zum testen
		return false;
//		int neededAmount = 0; // TODO: get correct value from configuration
//		ArrayList<Company> cs = market.getCompanies();
//		for (Company c : cs) {
//			if (c.getMoney() >= neededAmount) {
//			}
//			winner = c;
//			return true;
//		}
//		return false;
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

	public ArrayList<Company> getPlayer() {
		return market.getCompanies();
	}
	
	public int getRound() {
		return this.round;
	}
}
