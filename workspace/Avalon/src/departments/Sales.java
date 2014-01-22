package departments;

import gameManager.GameManager;

import java.util.ArrayList;

import market.Market;
import otherclasses.SalesHistory;
import product.Product;
import utils.Message;
import company.Company;



public class Sales  extends Department{
	
	
	private ArrayList<otherclasses.SalesHistory> salesHistory;
	public Sales(Company company) {
		super(company);
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
		else { }
			
		
			company.getWarehouse().getSingleProduct(level).changeAmount(0-amount);
			company.changeMoney(amount*currentPrice);
			this.revenue+=amount*currentPrice;
			
			if (salesHistory.get(salesHistory.size()).getRound() == GameManager.sharedInstance().getRound() && level==salesHistory.get(salesHistory.size()).getLevel()){
				salesHistory.get(salesHistory.size()).updateAmount(availableAmount);
			}
			else {
					salesHistory.add(new SalesHistory(level, amount, GameManager.sharedInstance().getRound()));
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
	
	
	@Override
	public void simulate() {
	}

}
