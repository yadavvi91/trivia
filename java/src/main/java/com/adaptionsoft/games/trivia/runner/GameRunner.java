
package com.adaptionsoft.games.trivia.runner;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.ConsoleUI;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.UI;


public class GameRunner {

	public static void main(String[] args) throws IOException {
		UI ui = new ConsoleUI(System.out);
		Game aGame = new Game(ui);

		// String line2 = readLine();

		File file = new File("src/main/resources/input.txt");
		// BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file));
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		String[] playerNames = line.split(" ");
		for (String playerName : playerNames) {
			aGame.add(playerName);
		}

		while ((line = reader.readLine()) != null) {
			int roll = Integer.parseInt(line.trim());
			aGame.roll(roll);
			String answer = reader.readLine().trim();
			if (answer.equalsIgnoreCase("correct")) {
				boolean notAWinner = aGame.wasCorrectlyAnswered();
				if (!notAWinner) break;
			} else if (answer.equalsIgnoreCase("incorrect")) {
				boolean notAWinner = aGame.wrongAnswer();
				if (!notAWinner) break;
			}
		}
	}

	private static String readLine() throws IOException {
		String currentPlayerName = Files.readString(Path.of("src/main/resources/input.txt"), StandardCharsets.UTF_8);
		String[] lines = currentPlayerName.split("\n");
		String[] players = lines[0].split(" ");
		System.out.println(Arrays.asList(players));
		return null;
	}
}
