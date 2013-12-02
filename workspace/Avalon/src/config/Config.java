package config;

public class Config {
	private static  int maxLevelResearch;
	private static int[] supplierTrust = new int[3];
	private static  int[] supplierQuality = new int[3];
	private static double[] supplierPrice = new double[3];

	private static double companyStartMoney;
	private static int companyStartPopularity;

	private static int costsUpgradeResearch;
<<<<<<< HEAD
	private int costsUpgradeMarketing;
	private static int campaignDepartmentUpgradeCost;
	private static int researchFixcost;
=======
	private static int costsUpgradeMarketing;
	
	private static int productionCapacity;
>>>>>>> a1ddb2ed7e031b4df232eaf3bb0a3eb10e0660dc

	public static double[] getSupplierPrice() {
		return supplierPrice;
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

	public static int getMaxLevelResearch() {
		return maxLevelResearch;
	}

	public static int getCostsUpgradeResearch() {
		return costsUpgradeResearch;
	}

<<<<<<< HEAD
	public static int getResearchFixcost() {
		return researchFixcost;
	}

	public static int getCampaignDepartmentUpgradeCost() {
		return campaignDepartmentUpgradeCost;
	}

	public int getCostsUpgradeMarketing() {
=======
	public static int getCostsUpgradeMarketing() {
>>>>>>> a1ddb2ed7e031b4df232eaf3bb0a3eb10e0660dc
		return costsUpgradeMarketing;
	}

	public static int getProductionCapacity() {
		return productionCapacity;
	}

	public static void setProductionCapacity(int productionCapacity) {
		Config.productionCapacity = productionCapacity;
	}

}
