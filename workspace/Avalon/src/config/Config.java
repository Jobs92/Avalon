package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

	// general
	private static int amountWin;
	private static double companyStartMoney;
	private static int companyStartPopularity;

	// Legal Department
	private static double weightRound;
	private static double weightLevel;
	private static int probWinLawsuit;
	private static int costsUpgradeLegalDeparment;
	private static int legalDepartmentFixcost;
	private static double costsCheckCampaign;
	private static double costsFoundSpyingCampaign;
	private static int relativeAmountCostsLawsuit;

	// Research
	private static int maxLevelResearch;
	private static int researchFixcost;
	private static int costsUpgradeResearch;
	private static double costsPatent;
	private static double costSpy;

	// Research Campaigns
	private static String[] titleResearch;
	private static String[] descriptionResearch;
	private static double[] costResearch;
	private static int[] levelResearch;
	private static int[] successprobabilityResearch;
	private static int[] durationResearch;
	
	//Spying Campaign
	private static String titleSpying;
	private static String descriptionSpying;
	private static double costSpying;
	private static int levelSpying;
	private static int successprobabilitySpying;
	private static int durationSpying;

	// Marketing
	private static int costsUpgradeMarketing;

	// Marketing Campaigns
	private static String[] titleMarketing;
	private static String[] descriptionMarketing;
	private static double[] costMarketing;
	private static int[] levelMarketing;
	private static int[] successprobabilityMarketing;
	private static int[] durationMarketing;

	// Market
	private static int demand;
	private static int buyingPower;
	private static int consumerGroupOscillation;

	// Supplier
	private static int[] supplierTrust = new int[3];
	private static int[] supplierQuality = new int[3];
	private static double[] supplierPrice = new double[3];
	private static String[] supplierName = new String[3];

	// Production
	private static int productionCapacity;
	private static int maxLevelProduction;
	private static int productionFixcost;
	private static int costsUpgradeProduction;
	private static double upgradeProduction;
	private static double productionVariableCosts;
	private static int maxLevelMarketing;
	private static double marketingFixcost;

	// Warehouse
	private static String startProductName;
	
	// Events
	private static String[] eventText;
	private static String[] eventType;
	private static int[] eventValue;
	private static boolean[] eventAllPlayers;
	private static int eventAmount;

	private Properties prop;

	public Config() {
		loadConfig();
	}

	private void loadConfig() {
		prop = new Properties();
		try {
			// load a properties file
			prop.load(new FileInputStream("config.properties"));

			// General
			amountWin = Integer.parseInt(prop.getProperty("amountWin"));
			companyStartMoney = Integer.parseInt(prop
					.getProperty("companyStartMoney"));
			companyStartPopularity = Integer.parseInt(prop
					.getProperty("companyStartPopularity"));

			// Legal Department
			weightLevel = Double.parseDouble(prop.getProperty("weightLevel"));
			weightRound = Double.parseDouble(prop.getProperty("weightRound"));
			probWinLawsuit = Integer.parseInt(prop
					.getProperty("probWinLawsuit"));
			legalDepartmentFixcost = Integer.parseInt(prop
					.getProperty("legalDepartmentFixcost"));
			costsUpgradeLegalDeparment = Integer.parseInt(prop
					.getProperty("costsUpgradeLegalDepartment"));

			// Supplier
			String[] trust = prop.getProperty("supplierTrust").split(";");
			for (int i = 0; i < trust.length; i++) {
				supplierTrust[i] = Integer.parseInt(trust[i]);
			}

			String[] quality = prop.getProperty("supplierQuality").split(";");
			for (int i = 0; i < trust.length; i++) {
				supplierQuality[i] = Integer.parseInt(quality[i]);
			}

			String[] price = prop.getProperty("supplierPrice").split(";");
			for (int i = 0; i < trust.length; i++) {
				supplierPrice[i] = Double.parseDouble(price[i]);
			}

			String[] name = prop.getProperty("supplierName").split(";");
			for (int i = 0; i < trust.length; i++) {
				supplierName[i] = name[i];
			}

			// Production
			productionCapacity = Integer.parseInt(prop
					.getProperty("productionCapacity"));
			productionFixcost = Integer.parseInt(prop
					.getProperty("productionFixcost"));
			maxLevelProduction = Integer.parseInt(prop
					.getProperty("maxLevelProduction"));
			costsUpgradeProduction = Integer.parseInt(prop
					.getProperty("costsUpgradeProduction"));
			upgradeProduction = Double.parseDouble(prop
					.getProperty("upgradeProduction"));
			productionVariableCosts = Double.parseDouble(prop
					.getProperty("productionVariableCosts"));

			// Warehouse
			startProductName = prop.getProperty("startProductName");

			// Research
			titleResearch = prop.getProperty("titleResearch").split(";");
			descriptionResearch = prop.getProperty("descriptionResearch")
					.split(";");
			String[] tmpCost = prop.getProperty("costResearch").split(";");
			costResearch = new double[tmpCost.length];
			for (int i = 0; i < tmpCost.length; i++) {
				costResearch[i] = Double.parseDouble(tmpCost[i]);
			}
			String[] tmpDuration = prop.getProperty("durationResearch").split(
					";");
			durationResearch = new int[tmpDuration.length];
			for (int i = 0; i < tmpCost.length; i++) {

				durationResearch[i] = Integer.parseInt(tmpDuration[i]);
			}
			String[] tmpLevel = prop.getProperty("levelResearch").split(";");
			levelResearch = new int[tmpLevel.length];
			for (int i = 0; i < tmpCost.length; i++) {
				levelResearch[i] = Integer.parseInt(tmpLevel[i]);
			}
			String[] probLevel = prop.getProperty("successprobabilityResearch")
					.split(";");
			successprobabilityResearch = new int[probLevel.length];
			for (int i = 0; i < tmpCost.length; i++) {
				successprobabilityResearch[i] = Integer.parseInt(probLevel[i]);
			}
			//Spying campaign
			titleSpying = prop.getProperty("titleSpying");
			descriptionSpying = prop.getProperty("descriptionSpying");
			durationSpying = Integer.parseInt(prop.getProperty("durationSpying"));
			costSpying = Double.parseDouble(prop.getProperty("costSpying"));
			successprobabilitySpying = Integer.parseInt(prop.getProperty("successprobabilitySpying"));
			levelSpying = Integer.parseInt(prop.getProperty("levelSpying"));

			// Marketing
			titleMarketing = prop.getProperty("titleMarketing").split(";");
			descriptionMarketing = prop.getProperty("descriptionMarketing")
					.split(";");
			String[] tmpMarketingCost = prop.getProperty("costMarketing")
					.split(";");
			costMarketing = new double[tmpMarketingCost.length];
			for (int i = 0; i < tmpMarketingCost.length; i++) {
				costMarketing[i] = Double.parseDouble(tmpMarketingCost[i]);
			}
			String[] tmpMarketingDuration = prop.getProperty(
					"durationMarketing").split(";");
			durationMarketing = new int[tmpMarketingDuration.length];
			for (int i = 0; i < tmpMarketingCost.length; i++) {

				durationMarketing[i] = Integer.parseInt(tmpMarketingDuration[i]);
			}
			String[] tmpMarketingLevel = prop.getProperty("levelMarketing")
					.split(";");
			levelMarketing = new int[tmpMarketingLevel.length];
			for (int i = 0; i < tmpMarketingCost.length; i++) {
				levelMarketing[i] = Integer.parseInt(tmpMarketingLevel[i]);
			}
			String[] probMarketingLevel = prop.getProperty(
					"successprobabilityMarketing").split(";");
			successprobabilityMarketing = new int[probMarketingLevel.length];
			for (int i = 0; i < tmpMarketingCost.length; i++) {
				successprobabilityMarketing[i] = Integer
						.parseInt(probMarketingLevel[i]);
			}
			
			// Events
			eventText = prop.getProperty("eventText").split(";");
			eventType = prop.getProperty("eventType").split(";");
			eventAmount = Integer.parseInt(prop.getProperty("eventAmount"));
			String[] tmpEventValue = prop.getProperty("eventValue").split(";");
			eventValue = new int[tmpEventValue.length];			
			for (int i = 0; i < tmpEventValue.length; i++) {
				eventValue[i] = Integer.parseInt(tmpEventValue[i]);
			}
			
			String[] tmpEventAllPlayers = prop.getProperty("eventAllPlayers").split(";");
			eventAllPlayers = new boolean[tmpEventValue.length];			
			for (int i = 0; i < tmpEventAllPlayers.length; i++) {
				if (tmpEventAllPlayers[i]=="true") {
					eventAllPlayers[i]=true;
				}
				else if (tmpEventAllPlayers[i]=="false"){
					eventAllPlayers[i]=false;
				}
				else{
					// Error Handling??
				}
			}
		

		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	public static String[] getTitleMarketing() {
		return titleMarketing;
	}

	public static String[] getDescriptionMarketing() {
		return descriptionMarketing;
	}

	public static double[] getCostMarketing() {
		return costMarketing;
	}

	public static int[] getLevelMarketing() {
		return levelMarketing;
	}

	public static int[] getSuccessprobabilityMarketing() {
		return successprobabilityMarketing;
	}

	public static String getTitleSpying() {
		return titleSpying;
	}

	public static String getDescriptionSpying() {
		return descriptionSpying;
	}

	public static double getCostSpying() {
		return costSpying;
	}

	public static int getLevelSpying() {
		return levelSpying;
	}

	public static int getSuccessprobabilitySpying() {
		return successprobabilitySpying;
	}

	public static int getDurationSpying() {
		return durationSpying;
	}

	public static int[] getDurationMarketing() {
		return durationMarketing;
	}

	public static double getCostSpy() {
		return costSpy;
	}

	public static String[] getTitleResearch() {
		return titleResearch;
	}

	public static String[] getDescriptionResearch() {
		return descriptionResearch;
	}

	public static double[] getCostResearch() {
		return costResearch;
	}

	public static int[] getLevelResearch() {
		return levelResearch;
	}

	public static int[] getSuccessprobabilityResearch() {
		return successprobabilityResearch;
	}

	public static int[] getDurationResearch() {
		return durationResearch;
	}

	public Properties getProp() {
		return prop;
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

	public static int getResearchFixcost() {
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

	public static int getDemand() {
		return demand;
	}

	public static int getBuyingPower() {
		return buyingPower;
	}

	public static int getMaxLevelResearch() {
		return maxLevelResearch;
	}

	public static int getCostsUpgradeResearch() {
		return costsUpgradeResearch;
	}

	public static double getCostsPatent() {
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

	public static String getStartProductName() {
		return startProductName;
	}

	public static String[] getEventText() {
		return eventText;
	}

	public static String[] getEventType() {
		return eventType;
	}

	public static int[] getEventValue() {
		return eventValue;
	}

	public static boolean[] getEventAllPlayers() {
		return eventAllPlayers;
	}

	public static int getEventAmount() {
		return eventAmount;
	}

}
