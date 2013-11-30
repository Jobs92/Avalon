package otherclasses;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class CreateConfig {

	public static void main(String[] args) {
		Properties prop = new Properties();
		 
    	try {
    		//set the properties value
    		prop.setProperty("amountWin", "10000");
    		prop.setProperty("weightLevel", "1");
    		prop.setProperty("weightRound", "1");
    		prop.setProperty("probWinLawsuit", "0.3");
    		prop.setProperty("supplierPrice", "50.0;75.0;100.0");
    		prop.setProperty("supplierTrust", "80;90;95");
    		prop.setProperty("supplierQuality", "70;80;90");
 
    		//save properties to project root folder
    		prop.store(new FileOutputStream("config.properties"), null);
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }

	}

}
