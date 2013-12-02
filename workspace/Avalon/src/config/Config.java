package config;

public class Config {
	private static  int maxLevelResearch;
	private static int[] supplierTrust = new int[3];
	private static  int[] supplierQuality = new int[3];
	private static double[] supplierPrice = new double[3];

	private static double companyStartMoney;
	private static int companyStartPopularity;

	private static int costsUpgradeResearch;
	private static int costsUpgradeMarketing;
<<<<<<< HEAD
=======
	
>>>>>>> dfbf42e809bc470cf3d2bd66cd45d2bf0d89eaa9
	private static int productionCapacity;

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
	
=======

>>>>>>> dfbf42e809bc470cf3d2bd66cd45d2bf0d89eaa9
	public static int getCostsUpgradeMarketing() {
		return costsUpgradeMarketing;
	}

	public static int getProductionCapacity() {
		return productionCapacity;
	}

	public static void setProductionCapacity(int productionCapacity) {
		Config.productionCapacity = productionCapacity;
	}

}
