
package com.adaptionsoft.games.trivia.runner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	private static boolean notAWinner;
	private static final List<TestFile> testFiles = Arrays.asList(
			TestFile.of("src/main/resources/input.txt", "src/main/resources/output.txt"),
			TestFile.of("src/main/resources/input2.txt", "src/main/resources/output2.txt"),
			TestFile.of("src/main/resources/input3.txt", "src/main/resources/output3.txt")
	);

	public static List<Input> provideParameters() throws IOException {
		List<Input> arguments = new ArrayList<>();
		for (TestFile testFile : testFiles) {
			File file = new File(testFile.input);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			List<String> playerNames = List.of(reader.readLine().split(" "));

			List<Parameter> parameters = new ArrayList<>();
			String line;
			while ((line = reader.readLine()) != null) {
				String[] params = line.trim().split(" ");
				int roll = Integer.parseInt(params[0]);
				boolean correct = params[1].equals("correct") ? true : params[1].equals("incorrect") ? false : false;
				parameters.add(Parameter.of(roll, correct));
			}

			String expectedOutput = Files.readString(Path.of(testFile.output), StandardCharsets.UTF_8);
			arguments.add(Input.of(playerNames, parameters, expectedOutput));
		}

		return arguments;
	}

	public static void main(String[] args) throws IOException {
		Game aGame = new Game();

		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		Random rand = new Random();

		do {

			aGame.roll(rand.nextInt(5) + 1);

			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}



		} while (notAWinner);

		runListOfGames(provideParameters());
	}

	private static void runListOfGames(List<Input> inputs) throws IOException {
		for (Input input : inputs) {
			Game game = new Game();
			List<String> playerNames = input.playerNames;
			List<Parameter> parameters = input.parameters;
			String expectedOutput = input.expectedOutput;

			for (String playerName : playerNames) {
				game.add(playerName);
			}

			for (Parameter parameter : parameters) {
				game.roll(parameter.getRoll());
				if (parameter.isCorrect()) {
					boolean notAWinner = game.wasCorrectlyAnswered();
					if (!notAWinner) break;
				} else {
					boolean notAWinner = game.wrongAnswer();
					if (!notAWinner) break;
				}
			}
		}
	}

	static class Input {
		List<String> playerNames;
		List<Parameter> parameters;
		String expectedOutput;

		private Input(List<String> playerNames, List<Parameter> parameters, String expectedOutput) {
			this.playerNames = playerNames;
			this.parameters = parameters;
			this.expectedOutput = expectedOutput;
		}

		public static Input of(List<String> playerNames, List<Parameter> parameters, String expectedOutput) {
			return new Input(playerNames, parameters, expectedOutput);
		}
	}

	static class TestFile {
		String input;
		String output;

		private TestFile(String input, String output) {
			if (input == null) {
				throw new NullPointerException("input is marked non-null but is null");
			} else if (output == null) {
				throw new NullPointerException("output is marked non-null but is null");
			} else {
				this.input = input;
				this.output = output;
			}
		}

		public static TestFile of(String input, String output) {
			if (input == null) {
				throw new NullPointerException("input is marked non-null but is null");
			} else if (output == null) {
				throw new NullPointerException("output is marked non-null but is null");
			} else {
				return new TestFile(input, output);
			}
		}
	}

	static class Parameter {
		int roll;
		boolean correct;

		private Parameter(int roll, boolean correct) {
			this.roll = roll;
			this.correct = correct;
		}

		public static Parameter of(int roll, boolean correct) {
			return new Parameter(roll, correct);
		}

		public int getRoll() {
			return this.roll;
		}

		public boolean isCorrect() {
			return this.correct;
		}

		public String toString() {
			int var10000 = this.getRoll();
			return "SomeTest.Parameter(roll=" + var10000 + ", correct=" + this.isCorrect() + ")";
		}
	}


}
