package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.adaptionsoft.games.uglytrivia.Category.*;

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
        Player player = Player.of(playerName);
        players.add(player);
        ui.showAddedPlayer(player, players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    private String getQuestion() {
        return switch (currentCategory()) {
            case POP -> popQuestions.removeFirst();
            case SCIENCE -> scienceQuestions.removeFirst();
            case SPORTS -> sportsQuestions.removeFirst();
            case ROCK -> rockQuestions.removeFirst();
        };
    }

    private Category currentCategory() {
        int place = currentPlayer.getPlace();
        if (place > 11 || place < 0) throw new IllegalArgumentException("There can be only 12 valid locations");

        return switch (place % 4) {
            case 0 -> POP;
            case 1 -> SCIENCE;
            case 2 -> SPORTS;
            default -> ROCK;
        };
    }

    public void roll(int roll) {
        if (currentPlayer == null) currentPlayer = players.getFirst();
        currentPlayer.setRoll(roll);
        ui.showDiceRoll(currentPlayer);

        if (currentPlayer.isInPenaltyBox() && currentPlayer.isGettingOutOfPenaltyBox()) {
            currentPlayer.setInPenaltyBox(false);
            ui.showPlayerOutOfPenaltyBox(currentPlayer);
        } else if (currentPlayer.isInPenaltyBox() && !currentPlayer.isGettingOutOfPenaltyBox()) {
            ui.showPlayerNotGettingOutOfPenaltyBox(currentPlayer);
        }

        if (!currentPlayer.isInPenaltyBox() || currentPlayer.isGettingOutOfPenaltyBox()) {
            currentPlayer.moveToNextPlace(roll);
            ui.showNewPlayerLocation(currentPlayer);
            ui.showCurrentCategory(currentCategory());
            ui.showQuestion(getQuestion());
        }
    }

    /**
     * @return Should program continue. If the {@code currentPlayer} wins the game,
     * the game should exit. Otherwise, the game should go on.
     */
    public boolean wasCorrectlyAnswered() {
        if (!currentPlayer.isInPenaltyBox() || currentPlayer.isGettingOutOfPenaltyBox()) {
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

        currentPlayer.setInPenaltyBox(true);
        ui.showPlayerSentToPenaltyBox(currentPlayer);

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
