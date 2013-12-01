package departments;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Marketing extends CampaignDepartment {

	public Marketing() {
		super();
		loadCampaigns();
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
