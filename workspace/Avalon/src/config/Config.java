package config;

public class Config {
	private int maxLevelResearch;
	private double[] supplierTrust = new double[3];
	private double[] supplierQuality = new double[3];
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

	public double[] getSupplierTrust() {
		return supplierTrust;
	}

	public double[] getSupplierQuality() {
		return supplierQuality;
	}

	public int getMaxLevelResearch() {
		return maxLevelResearch;
	}
	
	

}
