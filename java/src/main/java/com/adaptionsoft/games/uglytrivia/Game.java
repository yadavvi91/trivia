package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private final UI ui;

    List<Player> players = new ArrayList<>();
    Player currentPlayer;

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

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
        players.add(player);
        ui.showAddedPlayer(player, players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        if (currentPlayer == null) currentPlayer = players.getFirst();
        ui.showDiceRoll(currentPlayer, roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 != 0) {
                currentPlayer.setGettingOutOfPenaltyBox(true);
                ui.showPlayerOutOfPenaltyBox(currentPlayer);

                currentPlayer.moveToNextPlace(roll);

                ui.showNewPlayerLocation(currentPlayer);
                ui.showCurrentCategory(currentCategory());
                ui.showQuestion(getQuestion());
            } else {
                currentPlayer.setGettingOutOfPenaltyBox(false);
                ui.showPlayerNotGettingOutOfPenaltyBox(currentPlayer);
            }
        } else {
            currentPlayer.moveToNextPlace(roll);

            ui.showNewPlayerLocation(currentPlayer);
            ui.showCurrentCategory(currentCategory());
            ui.showQuestion(getQuestion());
        }
    }

    private String getQuestion() {
        if (currentCategory().equalsIgnoreCase("Pop")) return popQuestions.removeFirst();
        if (currentCategory().equalsIgnoreCase("Science")) return scienceQuestions.removeFirst();
        if (currentCategory().equalsIgnoreCase("Sports")) return sportsQuestions.removeFirst();
        // if (currentCategory().equalsIgnoreCase("Rock")) rockQuestions.removeFirst();
        return rockQuestions.removeFirst();
    }

    private String currentCategory() {
        int place = currentPlayer.getPlace();
        if (place == 0) return "Pop";
        if (place == 4) return "Pop";
        if (place == 8) return "Pop";
        if (place == 1) return "Science";
        if (place == 5) return "Science";
        if (place == 9) return "Science";
        if (place == 2) return "Sports";
        if (place == 6) return "Sports";
        if (place == 10) return "Sports";
        return "Rock";
    }

    /**
     * @return Should program continue. If the {@code currentPlayer} wins the game,
     * the game should exit. Otherwise, the game should go on.
     */
    public boolean wasCorrectlyAnswered() {
        if (currentPlayer.isInPenaltyBox() && currentPlayer.isGettingOutOfPenaltyBox()) {
            ui.showCorrectAnswer();
            currentPlayer.incrementPurse();
            ui.showPlayerGoldCount(currentPlayer);

            if (currentPlayer.didPlayerWin()) return false;
        }
        else if (currentPlayer.isInPenaltyBox() && !currentPlayer.isGettingOutOfPenaltyBox()) {
        }
        else if (!currentPlayer.isInPenaltyBox() && currentPlayer.isGettingOutOfPenaltyBox()) {
            ui.showCorrectAnswer();
            currentPlayer.incrementPurse();
            ui.showPlayerGoldCount(currentPlayer);

            if (currentPlayer.didPlayerWin()) return false;
        }
        else {
            ui.showCorrectAnswer();
            currentPlayer.incrementPurse();
            ui.showPlayerGoldCount(currentPlayer);

            if (currentPlayer.didPlayerWin()) return false;
        }
        currentPlayer = getNextCurrentPlayer();
        return true;
    }

    /**
     * @return Should program continue. In case of {@code wrongAnswer}, it should always.
     */
    public boolean wrongAnswer() {
        ui.showIncorrectAnswer();
        ui.showPlayerSentToPenaltyBox(currentPlayer);
        currentPlayer.setInPenaltyBox(true);

        currentPlayer = getNextCurrentPlayer();
        return true;
    }

    private Player getNextCurrentPlayer() {
        int foundIndex = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).equals(currentPlayer)) {
                foundIndex = i;
                break;
            }
        }
        return players.get((foundIndex + 1) % players.size());
    }

}
