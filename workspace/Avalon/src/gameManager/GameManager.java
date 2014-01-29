package gameManager;

import java.util.ArrayList;

import otherclasses.Supplier;
import company.Company;
import config.Config;
import eventHandling.EventManager;
import market.Market;

/**
 * @author Frederik
 * Ensures the turn-based process of the game. Starts and ends the game.
 */
public class GameManager {
	private int round = 0;
	private boolean active;
	private Company winner;
	private Market market = Market.sharedInstance();
	private EventManager eventManager = EventManager.sharedInstance();
	private static GameManager sharedInstance;

	private GameManager() {
		Config.loadConfig();
	}

	/**
	 * @return
	 * returns the single GameManager object (Singleton)
	 */
	public static GameManager sharedInstance() {
		if (GameManager.sharedInstance == null) {
			GameManager.sharedInstance = new GameManager();
		}
		return GameManager.sharedInstance;
	}

	/**
	 * Starts the game and the first round.
	 */
	public void startGame() {
		initializeGame();
		nextRound();
	}

	/**
	 * Create supplier objects and events.
	 */
	private void initializeGame() {
		active = true;

		// Generate Supplier
		int amountSupplier = Math.min(
				Config.getSupplierTrust().length,
				Math.min(Config.getSupplierQuality().length,
						Config.getSupplierPrice().length));
		for (int i = 0; i < amountSupplier; i++) {
			Supplier s = new Supplier(Config.getSupplierPrice()[i],
					Config.getSupplierTrust()[i],
					Config.getSupplierQuality()[i], Config.getSupplierName()[i]);
			market.addSupplier(s);
		}
		// Generate Business Events
		eventManager.createEvents();

	}

	/**
	 * Starts the next round and informs the payers. Waits until all players are ready.
	 */
	private void nextRound() {
		market.informPlayers();
		round++;
		// Player decide further actions
		waitForPlayer();
	}

	/**
	 * Simulates a round by simulating the market, all company activities and the random events.
	 * Checks if there is a winner.
	 */
	private void simulate() {

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

	/**
	 * When a player is ready, it is checked whether all players are ready. If they are, the next round
	 * is started. If not, the game is still waiting.
	 */
	public void informReady() {
		ArrayList<Company> player = market.getCompanies();
		for (int i = 0; i < player.size(); i++) {
			if (player.get(i).isActive() && !player.get(i).isReady()) {
				// Not all player ready
				return;
			}
		}
		simulate();
	}

	private void endGame() {
		active = false;
	}

	/**
	 * @return
	 * If there is a winner, it it returned true, else false.
	 */
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

	/**
	 * Sets the boolean "ready" for all players to false.
	 */
	private void waitForPlayer() {
		ArrayList<Company> player = market.getCompanies();
		for (int i = 0; i < player.size(); i++) {
			player.get(i).setReady(false);
		}
	}

	public int addPlayer(Company c) {
		return market.addCompany(c);
	}

	public boolean getActive() {
		return this.active;
	}

	public Company getWinner() {
		return this.winner;
	}

	public ArrayList<Company> getPlayer() {
		return market.getCompanies();
	}

	public int getRound() {
		return this.round;
	}

	public void deleteInstance() {
		GameManager.sharedInstance = null;
	}
}
