
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.ConsoleUI;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.UI;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		UI ui = new ConsoleUI(System.out);
		Game aGame = new Game();
		
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		// 5
		// correct
		aGame.roll(5);
		aGame.wasCorrectlyAnswered();

		// 2
		// correct
		aGame.roll(2);
		aGame.wasCorrectlyAnswered();

		// 5
		// correct
		aGame.roll(5);
		aGame.wasCorrectlyAnswered();

		// 1
		// correct
		aGame.roll(1);
		aGame.wasCorrectlyAnswered();

		// 3
		// correct
		aGame.roll(3);
		aGame.wasCorrectlyAnswered();

		// 4
		// correct
		aGame.roll(4);
		aGame.wrongAnswer();

		// 1
		// correct
		aGame.roll(1);
		aGame.wasCorrectlyAnswered();

		// 4
		// correct
		aGame.roll(4);
		aGame.wasCorrectlyAnswered();

		// 3
		// correct
		aGame.roll(3);
		aGame.wasCorrectlyAnswered();

		// 2
		// correct
		aGame.roll(2);
		aGame.wasCorrectlyAnswered();

		// 3
		// correct
		aGame.roll(3);
		aGame.wasCorrectlyAnswered();

		// 5
		// correct
		aGame.roll(5);
		aGame.wasCorrectlyAnswered();

		// 2
		// correct
		aGame.roll(2);
		aGame.wasCorrectlyAnswered();

		// 1
		// correct
		aGame.roll(1);
		aGame.wasCorrectlyAnswered();

		// 2
		// correct
		aGame.roll(2);
		// aGame.wasCorrectlyAnswered();

		// 4
		// correct
		aGame.roll(4);
		aGame.wasCorrectlyAnswered();
	}
}
