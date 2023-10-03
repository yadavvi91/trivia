package com.adaptionsoft.games.uglytrivia;

enum Category {
    POP("Pop"),
    SPORTS("Sports"),
    SCIENCE("Science"),
    ROCK("Rock");

    final String type;

    Category(String type) {
        this.type = type;
    }
}
