package com.adaptionsoft.games.trivia;

public class Parameter {
    int roll;
    boolean correct;

    Parameter(int roll, boolean correct) {
        this.roll = roll;
        this.correct = correct;
    }

    public int getRoll() {
        return roll;
    }

    public boolean isCorrect() {
        return correct;
    }

    @Override
    public String toString() {
        return "[" + "roll: " + roll + ", correct: " + correct + "]";
    }
}
