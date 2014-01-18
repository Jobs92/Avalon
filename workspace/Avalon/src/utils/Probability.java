package utils;

public class Probability {

	/**
	 * Check if unsure event happened with the given probability.
	 * 
	 * @param probability
	 *            , range: 0-100.
	 * @return
	 */
	public static boolean propability(int p) {
		int random = (int) (Math.random() * 100);
		if (random < p) {
			return true;
		}
		return false;
	}

}
