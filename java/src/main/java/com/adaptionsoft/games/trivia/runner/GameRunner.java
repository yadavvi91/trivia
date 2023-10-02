
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

		boolean notAWinner;
		// 5
		// correct
		aGame.roll(5);
		notAWinner = aGame.wasCorrectlyAnswered();
		if (!notAWinner) return;

		// 2
		// correct
		aGame.roll(2);
		notAWinner = aGame.wasCorrectlyAnswered();
		if (!notAWinner) return;

		// 5
		// correct
		aGame.roll(5);
		notAWinner = aGame.wasCorrectlyAnswered();
		if (!notAWinner) return;

		// 1
		// correct
		aGame.roll(1);
		notAWinner = aGame.wasCorrectlyAnswered();
		if (!notAWinner) return;

		// 3
		// correct
		aGame.roll(3);
		notAWinner = aGame.wasCorrectlyAnswered();
		if (!notAWinner) return;

		// 4
		// correct
		aGame.roll(4);
		notAWinner = aGame.wrongAnswer();
		if (!notAWinner) return;

		// 1
		// correct
		aGame.roll(1);
		notAWinner = aGame.wasCorrectlyAnswered();
		if (!notAWinner) return;

		// 4
		// correct
		aGame.roll(4);
		notAWinner = aGame.wasCorrectlyAnswered();
		if (!notAWinner) return;

		// 3
		// correct
		aGame.roll(3);
		notAWinner = aGame.wasCorrectlyAnswered();
		if (!notAWinner) return;

		// 2
		// correct
		aGame.roll(2);
		notAWinner = aGame.wasCorrectlyAnswered();
		if (!notAWinner) return;

		// 3
		// correct
		aGame.roll(3);
		notAWinner = aGame.wasCorrectlyAnswered();
		if (!notAWinner) return;

		// 5
		// correct
		aGame.roll(5);
		notAWinner = aGame.wasCorrectlyAnswered();
		if (!notAWinner) return;

		// 2
		// correct
		aGame.roll(2);
		notAWinner = aGame.wasCorrectlyAnswered();
		if (!notAWinner) return;

		// 1
		// correct
		aGame.roll(1);
		notAWinner = aGame.wasCorrectlyAnswered();
		if (!notAWinner) return;

		// 2
		// correct
		aGame.roll(2);
		notAWinner = aGame.wasCorrectlyAnswered();
		if (!notAWinner) return;

		// 4
		// correct
		aGame.roll(4);
		notAWinner = aGame.wasCorrectlyAnswered();
		if (!notAWinner) return;
	}
}
