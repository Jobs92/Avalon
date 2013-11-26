package GameManager;

import company.Company;

public class GameManager {
	private int round = 0;
	private boolean active = false;
	private Company winner;
//	private Market market = new Market()
//	private EventManager eventManager = new EventManager()
	
	public void startGame(){
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
//		market.simBuyingBehaviour();
//		eventManager.simEvents();
		if (!checkWinner()){
			round++;
		}else{
			endGame();
		}
	}

	private void endGame() {
		// TODO Auto-generated method stub
		active = false;
	}

	private boolean checkWinner() {
		int neededAmount = 0; //TODO: get correct value from configuration
//		Company[] cs = market.getCompanies();
//		for (Company c : cs) {
//			if (c.getBalance >= neededAmount){
//				winner = c;
//				return true;
//			}
//		}
		return false;
	}

	private void waitForPlayer() {
		// TODO Auto-generated method stub
		
	}
}
