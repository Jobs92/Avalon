package departments;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import product.Product;
import campaigns.SpyingCampaign;

public class Research extends CampaignDepartment {
	private int reasearchedLevels;

	public Research() {
		super();
		this.reasearchedLevels = 0;
	}

	public void startCampaign(SpyingCampaign spyingCampaign, int target) {
		explicitCampaigns.add(spyingCampaign.startSpyingCampaign(target));
	}

	public void applyResearchResults() {
		Product newProduct = new Product(company.getWarehouse().getHighestProduct().getLevel()
				+ this.reasearchedLevels);
		company.getWarehouse().addProduct(newProduct);
		reasearchedLevels = 0;
	}

	public void addResearchedLevels(int level) {
		reasearchedLevels += level;
	}

	@Override
	protected void loadCampaigns() {
		Properties prop = new Properties();
		try {
			// load a properties file
			prop.load(new FileInputStream("config.properties"));
			System.out.println(prop.getProperty("weightRound"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
