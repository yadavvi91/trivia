package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private final UI ui;

    List<String> players = new ArrayList<>();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game(UI ui) {
        this.ui = ui;
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {
        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        ui.showAddedPlayer(playerName, players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        ui.showDiceRoll(players.get(currentPlayer), roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                ui.showPlayerOutOfPenaltyBox(players.get(currentPlayer));

                moveToNextPlace(roll);

                ui.showNewPlayerLocation(players.get(currentPlayer), places[currentPlayer]);
                ui.showCurrentCategory(currentCategory());
                ui.showQuestion(getQuestion());
            } else {
                isGettingOutOfPenaltyBox = false;
                ui.showPlayerNotGettingOutOfPenaltyBox(players.get(currentPlayer));
            }
        } else {
            moveToNextPlace(roll);

            ui.showNewPlayerLocation(players.get(currentPlayer), places[currentPlayer]);
            ui.showCurrentCategory(currentCategory());
            ui.showQuestion(getQuestion());
        }
    }

    private void moveToNextPlace(int roll) {
        places[currentPlayer] = (places[currentPlayer] + roll) % 12;
    }

    private String getQuestion() {
        if (currentCategory().equalsIgnoreCase("Pop")) return popQuestions.removeFirst();
        if (currentCategory().equalsIgnoreCase("Science")) return scienceQuestions.removeFirst();
        if (currentCategory().equalsIgnoreCase("Sports")) return sportsQuestions.removeFirst();
        // if (currentCategory().equalsIgnoreCase("Rock")) rockQuestions.removeFirst();
        return rockQuestions.removeFirst();
    }

    private String currentCategory() {
        if (places[currentPlayer] == 0) return "Pop";
        if (places[currentPlayer] == 4) return "Pop";
        if (places[currentPlayer] == 8) return "Pop";
        if (places[currentPlayer] == 1) return "Science";
        if (places[currentPlayer] == 5) return "Science";
        if (places[currentPlayer] == 9) return "Science";
        if (places[currentPlayer] == 2) return "Sports";
        if (places[currentPlayer] == 6) return "Sports";
        if (places[currentPlayer] == 10) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                ui.showCorrectAnswer();
                purses[currentPlayer]++;
                ui.showPlayerGoldCount(players.get(currentPlayer), purses[currentPlayer]);

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }
        } else {
            ui.showCorrectAnswer();
            purses[currentPlayer]++;
            ui.showPlayerGoldCount(players.get(currentPlayer), purses[currentPlayer]);

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        ui.showIncorrectAnswer();
        ui.showPlayerSentToPenaltyBox(players.get(currentPlayer));
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }

    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}
