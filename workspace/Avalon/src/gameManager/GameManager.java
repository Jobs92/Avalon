package gameManager;

import company.Company;
import eventHandling.EventManager;
import market.Market;

public class GameManager {
	private int round = 0;
	private boolean active = false;
	private Company winner;
	private Market market = new Market();
	private EventManager eventManager = new EventManager();
	private static GameManager sharedInstance;

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
		active = true;
	}

	private void nextRound() {
		round++;
		waitForPlayer();
		market.simActivities();
		market.simBuyingBehaviour();
		eventManager.simEvents();
		if (!checkWinner()) {
			round++;
		} else {
			endGame();
		}
	}

	private void endGame() {
		// TODO Auto-generated method stub
		active = false;
	}

	private boolean checkWinner() {
		int neededAmount = 0; // TODO: get correct value from configuration
		Company[] cs = market.getCompanies();
		for (Company c : cs) {
			if (c.getMoney() >= neededAmount) {
			}
			winner = c;
			return true;
		}
		return false;
	}

	private void waitForPlayer() {
		// TODO Auto-generated method stub

	}

	public void addPlayer(Company c) {
		market.addCompany(c);
	}

	public Company[] getPlayer() {
		System.out.println(market);
		System.out.println(market.getCompanies());
		return market.getCompanies();
	}

	public int getRound() {
		return this.round;
	}
}
