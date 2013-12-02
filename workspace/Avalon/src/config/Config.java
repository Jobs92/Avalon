package config;

public class Config {
	private int maxLevelResearch;
	private int[] supplierTrust = new int[3];
	private int[] supplierQuality = new int[3];
	
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
