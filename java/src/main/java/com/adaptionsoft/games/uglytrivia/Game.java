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
    List<Player> playerList = new ArrayList<>();

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    int currentPlayerNumber = 0;
    boolean isGettingOutOfPenaltyBox;
    Player currentPlayer;

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
        Player player = Player.of(playerName, false, false, 0, 0);
        playerList.add(player);
        ui.showAddedPlayer(player.getPlayerName(), playerList.size());

        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        // ui.showAddedPlayer(playerName, players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        if (currentPlayer == null) currentPlayer = playerList.getFirst();
        ui.showDiceRoll(players.get(currentPlayerNumber), roll);

        if (inPenaltyBox[currentPlayerNumber]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                ui.showPlayerOutOfPenaltyBox(players.get(currentPlayerNumber));

                moveToNextPlace(roll);

                ui.showNewPlayerLocation(players.get(currentPlayerNumber), places[currentPlayerNumber]);
                ui.showCurrentCategory(currentCategory());
                ui.showQuestion(getQuestion());
            } else {
                isGettingOutOfPenaltyBox = false;
                ui.showPlayerNotGettingOutOfPenaltyBox(players.get(currentPlayerNumber));
            }
        } else {
            moveToNextPlace(roll);

            ui.showNewPlayerLocation(players.get(currentPlayerNumber), places[currentPlayerNumber]);
            ui.showCurrentCategory(currentCategory());
            ui.showQuestion(getQuestion());
        }
    }

    private void moveToNextPlace(int roll) {
        places[currentPlayerNumber] = (places[currentPlayerNumber] + roll) % 12;
    }

    private String getQuestion() {
        if (currentCategory().equalsIgnoreCase("Pop")) return popQuestions.removeFirst();
        if (currentCategory().equalsIgnoreCase("Science")) return scienceQuestions.removeFirst();
        if (currentCategory().equalsIgnoreCase("Sports")) return sportsQuestions.removeFirst();
        // if (currentCategory().equalsIgnoreCase("Rock")) rockQuestions.removeFirst();
        return rockQuestions.removeFirst();
    }

    private String currentCategory() {
        if (places[currentPlayerNumber] == 0) return "Pop";
        if (places[currentPlayerNumber] == 4) return "Pop";
        if (places[currentPlayerNumber] == 8) return "Pop";
        if (places[currentPlayerNumber] == 1) return "Science";
        if (places[currentPlayerNumber] == 5) return "Science";
        if (places[currentPlayerNumber] == 9) return "Science";
        if (places[currentPlayerNumber] == 2) return "Sports";
        if (places[currentPlayerNumber] == 6) return "Sports";
        if (places[currentPlayerNumber] == 10) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayerNumber]) {
            if (isGettingOutOfPenaltyBox) {
                ui.showCorrectAnswer();
                purses[currentPlayerNumber]++;
                ui.showPlayerGoldCount(players.get(currentPlayerNumber), purses[currentPlayerNumber]);

                boolean winner = didPlayerWin();
                currentPlayerNumber++;
                if (currentPlayerNumber == players.size()) currentPlayerNumber = 0;

                return winner;
            } else {
                currentPlayerNumber++;
                if (currentPlayerNumber == players.size()) currentPlayerNumber = 0;
                return true;
            }
        } else {
            ui.showCorrectAnswer();
            purses[currentPlayerNumber]++;
            ui.showPlayerGoldCount(players.get(currentPlayerNumber), purses[currentPlayerNumber]);

            boolean winner = didPlayerWin();
            currentPlayerNumber++;
            if (currentPlayerNumber == players.size()) currentPlayerNumber = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        ui.showIncorrectAnswer();
        ui.showPlayerSentToPenaltyBox(players.get(currentPlayerNumber));
        inPenaltyBox[currentPlayerNumber] = true;

        currentPlayerNumber++;
        if (currentPlayerNumber == players.size()) currentPlayerNumber = 0;
        return true;
    }

    private boolean didPlayerWin() {
        return !(purses[currentPlayerNumber] == 6);
    }
}
