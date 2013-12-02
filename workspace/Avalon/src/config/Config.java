package config;

public class Config {
	private int maxLevelResearch;
	private int[] supplierTrust = new int[3];
	private int[] supplierQuality = new int[3];
	private double[] supplierPrice = new double[3];

	private double companyStartMoney;
	private int companyStartPopularity;

	private static int costsUpgradeResearch;
	private int costsUpgradeMarketing;
	private static int campaignDepartmentUpgradeCost;
	private static int researchFixcost;

	public double[] getSupplierPrice() {
		return supplierPrice;
	}

	public double getCompanyStartMoney() {
		return companyStartMoney;
	}

	public int getCompanyStartPopularity() {
		return companyStartPopularity;
	}

	public int[] getSupplierTrust() {
		return supplierTrust;
	}

	public int[] getSupplierQuality() {
		return supplierQuality;
	}

	public int getMaxLevelResearch() {
		return maxLevelResearch;
	}

	public static int getCostsUpgradeResearch() {
		return costsUpgradeResearch;
	}

	public static int getResearchFixcost() {
		return researchFixcost;
	}

	public static int getCampaignDepartmentUpgradeCost() {
		return campaignDepartmentUpgradeCost;
	}

	public int getCostsUpgradeMarketing() {
		return costsUpgradeMarketing;
	}

}
