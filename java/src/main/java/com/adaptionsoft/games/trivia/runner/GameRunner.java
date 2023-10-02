
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		Game aGame = new Game();
		
		aGame.add("Chet", System.out);
		aGame.add("Pat", System.out);
		aGame.add("Sue", System.out);
		
		Random rand = new Random();
	
		do {
			
			aGame.roll(rand.nextInt(5) + 1, System.out);
			
			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer(System.out);
			} else {
				notAWinner = aGame.wasCorrectlyAnswered(System.out);
			}
			
			
			
		} while (notAWinner);
		
	}
}
