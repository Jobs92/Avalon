import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class test {

	public static void main(String[] args) {
		Properties prop = new Properties();
		 
    	try {
               //load a properties file
    		prop.load(new FileInputStream("config.properties"));
    		System.out.println(prop.getProperty("weightRound"));

	} catch (IOException ex) {
		ex.printStackTrace();
    }

}
}
