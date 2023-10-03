package com.adaptionsoft.games.uglytrivia;

import lombok.*;

@RequiredArgsConstructor(staticName = "of")
@Getter
@Setter
@ToString
public class Player {
    @NonNull String playerName;
    boolean inPenaltyBox = false;
    int place = 0;
    int purse = 0;
    int roll = 0;

    public void moveToNextPlace(int roll) {
        setPlace((place + roll) % 12);
    }

    public void incrementPurse() {
        purse++;
    }

    boolean didPlayerWin() {
        return getPurse() == 6;
    }

    public boolean isGettingOutOfPenaltyBox() {
        // If the user was in a penalty box, then he rolls an odd number,
        // he is getting out of the penalty box
        return roll % 2 != 0;
    }

    public boolean shouldDoSomething() {
        // This could be simplified as
        //         return !isInPenaltyBox() || isGettingOutOfPenaltyBox();
        // and then be inlined in Game class
        if (isInPenaltyBox() && isGettingOutOfPenaltyBox()) {
            return true;
        }
        else if (isInPenaltyBox() && !isGettingOutOfPenaltyBox()) {
            return false;
        }
        else if (!isInPenaltyBox() && isGettingOutOfPenaltyBox()) {
            return true;
        }
        else if (!isInPenaltyBox() && !isGettingOutOfPenaltyBox()) {
            return true;
        }

        return false;
    }
}
