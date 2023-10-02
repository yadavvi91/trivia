package com.adaptionsoft.games.trivia;


import com.adaptionsoft.games.uglytrivia.ConsoleUI;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.UI;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SomeTest {
    private static final List<TestFile> testFiles = Arrays.asList(
            TestFile.of("src/test/resources/input.txt", "src/test/resources/output.txt"),
            TestFile.of("src/test/resources/input2.txt", "src/test/resources/output2.txt")
    );

    @RequiredArgsConstructor(staticName = "of")
    static class TestFile {
        @NonNull String input;
        @NonNull String output;
    }

    @RequiredArgsConstructor(staticName = "of")
    @Getter
    @ToString
    static class Parameter {
        @NonNull int roll;
        @NonNull boolean correct;
    }

    public static Stream<Arguments> provideParameters() throws IOException {
        List<Arguments> arguments = new ArrayList<>();
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
            arguments.add(Arguments.of(playerNames, parameters, expectedOutput));
        }

        return Stream.of(arguments.toArray(Arguments[]::new));
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
