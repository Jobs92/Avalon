package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import gameManager.GameManager;

public class Config {

	//general
	private static int amountWin;
	private static double companyStartMoney;
	private static int companyStartPopularity;
	
	//Legal Department
	private static double weightRound;
	private static double weightLevel;
	private static int probWinLawsuit;
	private static int costsUpgradeLegalDeparment;
	private static int legalDepartmentFixcost;
	private static double costsCheckCampaign;
	private static double costsFoundSpyingCampaign;
	private static int relativeAmountCostsLawsuit;

	//Research
	private static  int maxLevelResearch;
	private static int researchFixcost;
	private static int costsUpgradeResearch;
	private static double costsPatent;
	
	//Marketing
	private static int costsUpgradeMarketing;
	
	//Market
	private static int demand;
	private static int buyingPower;
	private static int consumerGroupOscillation;
	
	//Supplier
	private static int[] supplierTrust = new int[3];
	private static  int[] supplierQuality = new int[3];
	private static double[] supplierPrice = new double[3];
	private static String[] supplierName = new String [3];

	//Production
	private static int productionCapacity;
	private static int maxLevelProduction;
	private static int productionFixcost;
	private static int costsUpgradeProduction;
	private static double upgradeProduction;
	private static double productionVariableCosts;
	private static int maxLevelMarketing;
	private static double marketingFixcost;
	
	//Warehouse
	private static String startProductName;
	
	private Properties prop;
	
	public Config(){
		loadConfig();
	}
	
	private void loadConfig() {
		prop = new Properties();
    	try {
               //load a properties file
    		prop.load(new FileInputStream("config.properties"));
    		
    		//General
    		this.amountWin = Integer.parseInt(prop.getProperty("amountWin"));
    		this.companyStartMoney = Integer.parseInt(prop.getProperty("companyStartMoney"));
    		this.companyStartPopularity = Integer.parseInt(prop.getProperty("companyStartPopularity"));
    		
    		//Legal Department
    		this.weightLevel = Double.parseDouble(prop.getProperty("weightLevel"));
    		this.weightRound = Double.parseDouble(prop.getProperty("weightRound"));
    		this.probWinLawsuit = Integer.parseInt(prop.getProperty("probWinLawsuit"));
    		this.legalDepartmentFixcost = Integer.parseInt(prop.getProperty("legalDepartmentFixcost"));
    		this.costsUpgradeLegalDeparment = Integer.parseInt(prop.getProperty("costsUpgradeLegalDepartment"));
    		
    		//Supplier
    		String[] trust = prop.getProperty("supplierTrust").split(";");
    		for (int i = 0; i < trust.length; i++) {
				this.supplierTrust[i] = Integer.parseInt(trust[i]);
			}
    		
    		String[] quality = prop.getProperty("supplierQuality").split(";");
    		for (int i = 0; i < trust.length; i++) {
				this.supplierQuality[i] = Integer.parseInt(quality[i]);
			}
    		
    		String[] price = prop.getProperty("supplierPrice").split(";");
    		for (int i = 0; i < trust.length; i++) {
				this.supplierPrice[i] = Double.parseDouble(price[i]);
			}
    		
    		String[] name = prop.getProperty("supplierName").split(";");
    		for (int i = 0; i < trust.length; i++) {
				this.supplierName[i] = name[i];
			}
    		
    		//Production
    		this.productionCapacity = Integer.parseInt(prop.getProperty("productionCapacity"));
    		this.productionFixcost = Integer.parseInt(prop.getProperty("productionFixcost"));
    		this.maxLevelProduction = Integer.parseInt(prop.getProperty("maxLevelProduction"));
    		this.costsUpgradeProduction = Integer.parseInt(prop.getProperty("costsUpgradeProduction"));
    		this.upgradeProduction = Double.parseDouble(prop.getProperty("upgradeProduction"));
    		this.productionVariableCosts = Double.parseDouble(prop.getProperty("productionVariableCosts"));
    		
    		//Warehouse
    		this.startProductName = prop.getProperty("startProductName");
    		
    		

    	} catch (IOException ex) {
		System.err.println(ex);
    	}	
	}
	
	
	public static double getProductionVariableCosts() {
		return productionVariableCosts;
	}

	public static double getUpgradeProduction() {
		return upgradeProduction;
	}

	public static int getCostsUpgradeProduction() {
		return costsUpgradeProduction;
	}

	public static int getMaxLevelProduction() {
		return maxLevelProduction;
	}

	public static int getProductionFixcost() {
		return productionFixcost;
	}

	public static int getAmountWin() {
		return amountWin;
	}

	public static double getWeightRound() {
		return weightRound;
	}

	public static double getWeightLevel() {
		return weightLevel;
	}

	public static int getProbWinLawsuit() {
		return probWinLawsuit;
	}

	public static int getCostsUpgradeLegalDeparment() {
		return costsUpgradeLegalDeparment;
	}

	public static int getLegalDepartmentFixcost() {
		return legalDepartmentFixcost;
	}
	
	public static double getCostsCheckCampaign() {
		return costsCheckCampaign;
	}
	
	public static double getCostsFoundSpyingCampaign() {
		return costsFoundSpyingCampaign;
	}

	public static int getRelativeAmountCostsLawsuit() {
		return relativeAmountCostsLawsuit;
	}

	public static int getConsumerGroupOscillation() {
		return consumerGroupOscillation;
	}

	public static int getResearchFixcost(){
		return researchFixcost;
	}

	public static double[] getSupplierPrice() {
		return supplierPrice;
	}
	
	public static String[] getSupplierName() {
		return supplierName;
	}

	public static double getCompanyStartMoney() {
		return companyStartMoney;
	}

	public static int getCompanyStartPopularity() {
		return companyStartPopularity;
	}

	public static int[] getSupplierTrust() {
		return supplierTrust;
	}

	public static int[] getSupplierQuality() {
		return supplierQuality;
	}
	
	public static int getDemand(){
		return demand;
	}
	
	public static int getBuyingPower(){
		return buyingPower;
	}

	public static int getMaxLevelResearch() {
		return maxLevelResearch;
	}

	public static int getCostsUpgradeResearch() {
		return costsUpgradeResearch;
	}
	
	public static double getCostsPatent(){
		return costsPatent;
	}

	public static int getCostsUpgradeMarketing() {
		return costsUpgradeMarketing;
	}

	public static int getProductionCapacity() {
		return productionCapacity;
	}

	public static void setProductionCapacity(int productionCapacity) {
		Config.productionCapacity = productionCapacity;
	}

	public static int getMaxLevelMarketing() {
		return maxLevelMarketing;
	}

	public static double getMarketingFixcost() {
		return marketingFixcost;
	}
	
	public static String getStartProductName(){
		return startProductName;
	}

}
