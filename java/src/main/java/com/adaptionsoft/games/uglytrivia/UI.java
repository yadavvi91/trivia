package com.adaptionsoft.games.uglytrivia;

public interface UI {
    void showAddedPlayer(Player player, int size);

    void showPlayerNotGettingOutOfPenaltyBox(Player player);

    void showCurrentCategory(String currentCategory);

    void showNewPlayerLocation(Player player);

    void showPlayerOutOfPenaltyBox(Player player);

    void showDiceRoll(Player player);

    void showQuestion(String question);

    void showPlayerGoldCount(Player player);

    void showCorrectAnswer();

    void showPlayerSentToPenaltyBox(Player player);

    void showIncorrectAnswer();
}
