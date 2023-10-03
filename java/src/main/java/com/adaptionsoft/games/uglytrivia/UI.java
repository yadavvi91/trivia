package com.adaptionsoft.games.uglytrivia;

public interface UI {
    void showAddedPlayer(Player player, int size);

    void showDiceRoll(Player player);

    void showNewPlayerLocation(Player player);

    void showPlayerGoldCount(Player player);

    void showPlayerSentToPenaltyBox(Player player);

    void showPlayerOutOfPenaltyBox(Player player);

    void showPlayerNotGettingOutOfPenaltyBox(Player player);

    void showCorrectAnswer();

    void showIncorrectAnswer();

    void showCurrentCategory(Category category);

    void showQuestion(String question);
}
