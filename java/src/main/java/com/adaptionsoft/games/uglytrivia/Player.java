package com.adaptionsoft.games.uglytrivia;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor(staticName = "of")
@Getter
@ToString
public class Player {
    @NonNull String playerName;
    @NonNull boolean gettingOutOfPenaltyBox;
    @NonNull boolean inPenaltyBox;
    @NonNull int place;
    @NonNull int purse;
}
