package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;

public class ConsoleUI implements UI {
    private final PrintStream out;

    public ConsoleUI(PrintStream out) {
        this.out = out;
    }

    @Override
    public void showAddedPlayer(Player player, int size) {
        out.println(player.getPlayerName() + " was added");
        out.println("They are player number " + size);
    }

    @Override
    public void showDiceRoll(Player player) {
        out.println();
        out.println(player.getPlayerName() + " is the current player");
        out.println("They have rolled a " + player.getRoll());
    }

    @Override
    public void showNewPlayerLocation(Player player) {
        out.println(player.getPlayerName() + "'s new location is " + player.getPlace());
    }

    @Override
    public void showPlayerGoldCount(Player player) {
        out.println(player.getPlayerName() + " now has " + player.getPurse() + " Gold Coins.");
    }

    @Override
    public void showPlayerSentToPenaltyBox(Player player) {
        out.println(player.getPlayerName() + " was sent to the penalty box");
    }

    @Override
    public void showPlayerOutOfPenaltyBox(Player player) {
        out.println(player.getPlayerName() + " is getting out of the penalty box");
    }

    @Override
    public void showPlayerNotGettingOutOfPenaltyBox(Player player) {
        out.println(player.getPlayerName() + " is not getting out of the penalty box");
    }

    @Override
    public void showCorrectAnswer() {
        out.println("Answer was correct!!!!");
    }

    @Override
    public void showIncorrectAnswer() {
        out.println("Question was incorrectly answered");
    }

    @Override
    public void showCurrentCategory(String currentCategory) {
        out.println("The category is " + currentCategory);
    }

    @Override
    public void showQuestion(String question) {
        out.println(question);
    }
}
