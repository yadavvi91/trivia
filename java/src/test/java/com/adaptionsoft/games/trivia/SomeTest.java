package com.adaptionsoft.games.trivia;


import com.adaptionsoft.games.uglytrivia.ConsoleUI;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.UI;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SomeTest {

    @Test
    public void testCasePasses() throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outStream, true, StandardCharsets.UTF_8);
        UI ui = new ConsoleUI(stream);
        Game aGame = new Game(ui);


        File file = new File("src/test/resources/input.txt");
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

        String expected = Files.readString(Path.of("src/test/resources/output.txt"), StandardCharsets.UTF_8);
        String actual = outStream.toString();
        assertEquals(expected, actual);
    }
}
