package config;

public class Config {
	private int maxLevelResearch;
	private int[] supplierTrust = new int[3];
	private int[] supplierQuality = new int[3];
	private double[] supplierPrice = new double[3];

	private double companyStartMoney;
	private int companyStartPopularity;
	
	
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
	
	

}
