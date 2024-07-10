package com.ranis.game_library.blackjack

enum class Rank(val rankName: String, val rankValue: Int) {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4",4),
    FIVE("5",5),
    SIX("6",6),
    SEVEN("7",7),
    EIGHT("8",8),
    NINE("9",9),
    TEN("10",10),
    JACK("jack",10),
    QUEEN("queen",10),
    KING("king",10),
    ACE("ace", 11);
}