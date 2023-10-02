package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;

public class ConsoleUI implements UI {

    private PrintStream out;

    public ConsoleUI(PrintStream out) {
        this.out = out;
    }

    @Override
    public void showAddedPlayer(String playerName, int size) {
        out.println(playerName + " was added");
        out.println("They are player number " + size);
    }

    @Override
    public void showPlayerNotGettingOutOfPenaltyBox(String currentPlayerName) {
        out.println(currentPlayerName + " is not getting out of the penalty box");
    }

    @Override
    public void showCurrentCategory(String currentCategory) {
        out.println("The category is " + currentCategory);
    }

    @Override
    public void showNewPlayerLocation(String currentPlayerName, int currentPlayerLocation) {
        out.println(currentPlayerName + "'s new location is " + currentPlayerLocation);
    }

    @Override
    public void showPlayerOutOfPenaltyBox(String currentPlayerName) {
        out.println(currentPlayerName + " is getting out of the penalty box");
    }

    @Override
    public void showDiceRoll(String currentPlayerName, int roll) {
        out.println(currentPlayerName + " is the current player");
        out.println("They have rolled a " + roll);
    }

    @Override
    public void showQuestion(String question) {
        out.println(question);
    }

    @Override
    public void showPlayerGoldCount(String currentPlayerName, int currentPlayerGoldCoins) {
        out.println(currentPlayerName + " now has " + currentPlayerGoldCoins + " Gold Coins.");
    }

    @Override
    public void showCorrectAnswer() {
        out.println("Answer was correct!!!!");
    }

    @Override
    public void showPlayerSentToPenaltyBox(String currentPlayerName) {
        out.println(currentPlayerName + " was sent to the penalty box");
    }

    @Override
    public void showIncorrectAnswer() {
        out.println("Question was incorrectly answered");
    }
}