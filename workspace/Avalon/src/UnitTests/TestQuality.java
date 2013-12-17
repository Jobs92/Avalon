package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import gameManager.GameManager;
import market.Market;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import otherclasses.Order;
import otherclasses.Supplier;
import company.Company;
import config.Config;

public class TestQuality {
	private Company company1;
	private Company company2;
	private Company company3;
	private GameManager gameManager;

	@Before
	public void createGameManager() {
		System.out.println(1);
		gameManager = GameManager.sharedInstance();
	}

	@Before
	public void createCompanies() {
		System.out.println(2);
		company1 = new Company();
		company2 = new Company();
		company3 = new Company();
	}
	
	@Before
	public void startGame(){
		System.out.println(3);
		gameManager.addPlayer(company1);
		gameManager.addPlayer(company2);
		gameManager.startGame();
		
		company1.changeMoney(1000000);
		int amount = 10000;
		Supplier s = Market.sharedInstance().getSupplier().get(0);
		for (int i = 0; i < amount; i++) {
			company1.getPurchase().addOrder(new Order(s, 1));
		}
		company1.setReady(true);
		company2.setReady(true);
	}



	@Test
	public void testQuality() {
		int amountRessources = company1.getWarehouse().getAmountRessources();
		System.out.println("Anzahl Ressourcen vor Prod.:"+ company1.getWarehouse().getAmountRessources());
		System.out.println("Soviel soll produziert werden: " + amountRessources);
		System.out.println("Highest Produkt: " + company1.getWarehouse().getHighestProduct().getLevel());
		company1.getProduction().produce(company1.getWarehouse().getHighestProduct().getLevel(), amountRessources);
		company1.setReady(true);
		company2.setReady(true);
		System.out.println("Restliche Ressourcen: " + company1.getWarehouse().getAmountRessources());
		System.out.println("Anzahl Produkte" + company1.getWarehouse().getHighestProduct().getAmount());
		System.out.println(company1.getWarehouse().getHighestProduct().getAmount()/(amountRessources*1.0));
		assertEquals(Config.getSupplierQuality()[0], company1.getWarehouse().getHighestProduct().getAmount()/(amountRessources*1.0), 90);
	}
	
	@After
	public void removeInstances(){
		GameManager.sharedInstance().deleteInstance();
		Market.sharedInstance().deleteInstance();
	}

}
