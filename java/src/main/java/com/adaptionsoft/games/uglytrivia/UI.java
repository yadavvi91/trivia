package com.adaptionsoft.games.uglytrivia;

public interface UI {
    void showAddedPlayer(String playerName, int size);

    void showPlayerNotGettingOutOfPenaltyBox(String currentPlayerName);

    void showCurrentCategory(String currentCategory);

    void showNewPlayerLocation(String currentPlayerName, int currentPlayerLocation);

    void showPlayerOutOfPenaltyBox(String currentPlayerName);

    void showDiceRoll(String currentPlayerName, int roll);

    void showQuestion(String question);

    void showPlayerGoldCount(String currentPlayerName, int currentPlayerGoldCoins);

    void showCorrectAnswer();

    void showPlayerSentToPenaltyBox(String currentPlayerName);

    void showIncorrectAnswer();
}
