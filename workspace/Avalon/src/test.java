import gameManager.GameManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class test {

	public static void main(String[] args) {
		System.out.println("bla");
		GameManager g = new GameManager();
		g.startGame();
		System.out.println(g.getRound());
		System.out.println("bla");
	}
}
