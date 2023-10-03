package com.adaptionsoft.games.uglytrivia;

import lombok.*;

@RequiredArgsConstructor(staticName = "of")
@Getter
@Setter
@ToString
public class Player {
    @NonNull String playerName;
    boolean gettingOutOfPenaltyBox = false;
    boolean inPenaltyBox = false;
    int place = 0;
    int purse = 0;
    int roll = 0;

    public void moveToNextPlace(int roll) {
        this.setPlace((getPlace() + roll) % 12);
    }

    public void incrementPurse() {
        purse++;
    }

    boolean didPlayerWin() {
        return getPurse() == 6;
    }

    public boolean shouldDoSomething() {
        if (inPenaltyBox && gettingOutOfPenaltyBox) {
            return true;
        }
        else if (inPenaltyBox && !gettingOutOfPenaltyBox) {
            return false;
        }
        else if (!inPenaltyBox && gettingOutOfPenaltyBox) {
            return true;
        }
        else if (!inPenaltyBox && !gettingOutOfPenaltyBox) {
            return true;
        }

        return false;
    }
}
