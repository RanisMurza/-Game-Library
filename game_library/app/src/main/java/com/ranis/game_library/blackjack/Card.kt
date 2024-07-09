package com.ranis.game_library.blackjack

import android.widget.ImageView

class Card(private val suit: Suit, private val rank: Rank, private val int: Int) {

    fun getValue(): Int {
        return rank.rankValue
    }

    fun getSuit(): Suit {
        return suit
    }

    fun getRank(): Rank {
        return rank
    }
    fun getInt(): Int {
        return int
    }
}