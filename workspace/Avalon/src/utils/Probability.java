package utils;

public class Probability {
	public static boolean propability(int p){
		int random = (int) (Math.random()*100);
		if (random<p){
			return true;
		}
		return false;
	}

}
