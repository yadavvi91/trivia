The entire codebase is just this small thing.
Everything else is just noise.

Reads like a tree. (Somewhere I read/watched-a-youtube-video which said that
if your code reads like a tree, you are on the right path)

```
function roll -
- If Player in PenaltyBox
  - If Rolled an Even Number
    - Get Out of PenaltyBox
    - Move to Next Place
  - If Rolled an Odd Number
    - Stay in PenaltyBox
- If Player is NOT in PenaltyBox
  - Move to Next Place

function correctAnswer -
- If Player is in PenaltyBox
  - If Player is getting out of PenaltyBox
    - Increase gold in purse
    - If Player Wins, exit game
    - Get next player
  - If Player is NOT getting out of PenaltyBox
      - Get next player
- If Player is NOT in PenaltyBox
    - Increase gold in purse
    - If Player Wins, exit game
    - Get next player

function wrongAnswer -
- Move player to PenaltyBox
- Get next player
```

```java
public void roll(int roll) {
    if (currentPlayer == null) currentPlayer = players.getFirst();

    if (currentPlayer.isInPenaltyBox()) {
        if (roll % 2 != 0) {
            currentPlayer.setGettingOutOfPenaltyBox(true);
            currentPlayer.moveToNextPlace(roll);
        } else {
            currentPlayer.setGettingOutOfPenaltyBox(false);
        }
    } else {
        currentPlayer.moveToNextPlace(roll);
    }
}

/**
 * @return Should program continue. If the {@code currentPlayer} wins the game,
 * the game should exit. Otherwise, the game should go on.
 */
public boolean wasCorrectlyAnswered() {
    if (currentPlayer.isInPenaltyBox()) {
        if (currentPlayer.isGettingOutOfPenaltyBox()) {
            currentPlayer.incrementPurse();
            if (currentPlayer.didPlayerWin()) return false;
            currentPlayer = getNextCurrentPlayer();
            return true;
        } else {
            currentPlayer = getNextCurrentPlayer();
            return true;
        }
    } else {
        currentPlayer.incrementPurse();
        if (currentPlayer.didPlayerWin()) return false;
        currentPlayer = getNextCurrentPlayer();
        return true;
    }
}

/**
 * @return Should program continue. In case of {@code wrongAnswer}, it should always.
 */
public boolean wrongAnswer() {
    currentPlayer.setInPenaltyBox(true);
    currentPlayer = getNextCurrentPlayer();
    return true;
}
```
---

This can be further refactored like so -

```java
public void roll(int roll) {
    if (currentPlayer == null) currentPlayer = players.getFirst();
    currentPlayer.setRoll(roll);

    if (currentPlayer.isInPenaltyBox() && currentPlayer.isGettingOutOfPenaltyBox()) {
        currentPlayer.setInPenaltyBox(false);
    }

    if (!currentPlayer.isInPenaltyBox() || currentPlayer.isGettingOutOfPenaltyBox()) {
        currentPlayer.moveToNextPlace(roll);
    }
}

/**
 * @return Should program continue. If the {@code currentPlayer} wins the game,
 * the game should exit. Otherwise, the game should go on.
 */
public boolean wasCorrectlyAnswered() {
    if (!currentPlayer.isInPenaltyBox() || currentPlayer.isGettingOutOfPenaltyBox()) {
        currentPlayer.incrementPurse();
        if (currentPlayer.didPlayerWin()) return false;
    }

    currentPlayer = getNextCurrentPlayer();
    return true;
}

/**
 * @return Should program continue. In case of {@code wrongAnswer}, it should always.
 */
public boolean wrongAnswer() {
    currentPlayer.setInPenaltyBox(true);
    currentPlayer = getNextCurrentPlayer();
    return true;
}
```