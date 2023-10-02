package com.adaptionsoft.games.uglytrivia;

import lombok.*;

@RequiredArgsConstructor(staticName = "of")
@Getter
@Setter
@ToString
public class Player {
    @NonNull String playerName;
    @NonNull boolean gettingOutOfPenaltyBox;
    @NonNull boolean inPenaltyBox;
    @NonNull int place;
    @NonNull int purse;

    public void moveToNextPlace(int roll) {
        this.setPlace((getPlace() + roll) % 12);
    }

    public void incrementPurse() {
        purse++;
    }
}
