package departments;

import gameManager.GameManager;

import java.util.ArrayList;

import market.Market;
import otherclasses.SalesHistory;
import product.Product;
import utils.Message;
import company.Company;
import config.Config;



public class Sales  extends Department{
	
	
	private ArrayList<otherclasses.SalesHistory> salesHistory;
	public Sales(Company company) {
		super(company);
		updateFixcost();
		salesHistory=new ArrayList<otherclasses.SalesHistory>();
		this.revenue=0;
	}
	
	private int revenue;
	
	
	public int getRevenue() {
		return revenue;
	}

	public void setPrice(int level, int price){
		if (price>0){
			Product p = company.getWarehouse().getSingleProduct(level);
			p.setPrice(price);
			if (!Market.sharedInstance().productAlreadyOnMarket(p)){
				Market.sharedInstance().addProduct(p);
			}
		}
	}
	
	public int sell (int level, int amount){ 


		int availableAmount=company.getWarehouse().getSingleProduct(level).getAmount();    //Helper
		int currentPrice=company.getWarehouse().getSingleProduct(level).getPrice();		
		int div=0;
		
		if (availableAmount<amount) {
			
			 div=amount-availableAmount;
			 amount=availableAmount;   // Change amount!
			 //Fehlermeldung: nicht genug produkte im lager
				Message m = new Message();
				m.setTitle("Lager leer!");
				m.setType(Message.GAME);
				m.setTargetPlayer(company.getId());
				m.setMessage("Sie haben nicht gen�gend Produkte im Lager!");
				Market.sharedInstance().sendMessage(m);
		}
		else { 
			
		
			company.getWarehouse().getSingleProduct(level).changeAmount(0-amount);
			company.changeMoney(amount*currentPrice);
			this.revenue+=amount*currentPrice;
			
			for (SalesHistory sH : salesHistory) {
				if (sH.getRound() == GameManager.sharedInstance().getRound() && level==sH.getLevel()){
					sH.updateAmount(availableAmount);
					return div;
				}
			}
			salesHistory.add(new SalesHistory(level, amount, GameManager.sharedInstance().getRound()));	
				
				
				
//				if ((salesHistory.get(salesHistory.size()-1).getRound() == GameManager.sharedInstance().getRound()) && (level==salesHistory.get(salesHistory.size()-1).getLevel())) {
//					salesHistory.get(salesHistory.size()-1).updateAmount(availableAmount);	
//				}
//				
//			}
//			else {
//					salesHistory.add(new SalesHistory(level, amount, GameManager.sharedInstance().getRound()));
//			}
		
		}
		
		
       return div;
	}
	
	public int getTotalAmount(int round){
		int totalAmount=0;
		for (otherclasses.SalesHistory list : salesHistory) {
			if (list.getRound()==round) {
				totalAmount +=list.getAmount();
			}
		}
		
		
		return totalAmount;
		
	}
	
	public void sendQuarterlyReport(){
		if (getAmountSoldProductsCurrentRound() != 0){
			String message = "Verkaufte Smartphones in diesem Quartal: \n";
			for (SalesHistory sH : salesHistory) {
				message += sH.getAmount() + "x " + company.getWarehouse().getSingleProduct(sH.getLevel()).getName() + "\n";
			}
			
			Message m = new Message();
			m.setTitle("Sales Quartalsbericht");
			m.setType(Message.GAME);
			m.setTargetPlayer(company.getId());
			m.setMessage(message);
			Market.sharedInstance().sendMessage(m);
		}
	}
	
	public int getAmountSoldProductsCurrentRound(){
		if (salesHistory.isEmpty()==false ){
			if (salesHistory.get(salesHistory.size()-1).getRound() == GameManager.sharedInstance().getRound()){
				return salesHistory.get(salesHistory.size()-1).getAmount();
			}
		}
		return 0;
	}
	
	protected void updateFixcost() {
		fixcost = Config.getSalesFixcost();	
	}
	
	@Override
	public void simulate() {
		payFixcosts();
	}

}
