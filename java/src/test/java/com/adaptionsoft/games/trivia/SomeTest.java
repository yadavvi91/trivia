package com.adaptionsoft.games.trivia;


import com.adaptionsoft.games.uglytrivia.ConsoleUI;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.UI;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SomeTest {

    public static Stream<Arguments> provideParameters() throws IOException {
        File file = new File("src/test/resources/input.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> playerNames = List.of(reader.readLine().split(" "));

        List<Parameter> parameters = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] params = line.trim().split(" ");
            int roll = Integer.parseInt(params[0]);
            boolean correct = params[1].equals("correct") ? true : params[1].equals("incorrect") ? false : false;
            parameters.add(new Parameter(roll, correct));
        }

        String expectedOutput = Files.readString(Path.of("src/test/resources/output.txt"), StandardCharsets.UTF_8);
        return Stream.of(Arguments.of(playerNames, parameters, expectedOutput));
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    public void testCasePasses(List<String> playerNames, List<Parameter> parameters, String expectedOutput) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outStream, true, StandardCharsets.UTF_8);
        UI ui = new ConsoleUI(stream);
        Game game = new Game(ui);

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

        String actual = outStream.toString();
        assertEquals(expectedOutput, actual);
    }
}
