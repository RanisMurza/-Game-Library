package com.ranis.game_library.blackjack

abstract class Person {
    private var hand: Hand = Hand()

    fun hasBlackjack(): Boolean {
        return this.getHand().calculatorValue() == 21
    }

    fun getHand(): Hand {
        return hand
    }

    fun setHand(hand: Hand) {
        this.hand = hand
    }

    @Throws(Exception::class)
    fun hit(deck: Deck, discard: Deck) {
        if (!deck.hasCards()) {
            deck.reloadDeckFromDiscard(discard)
        }
        this.hand.takeCardFromDeck(deck)
    }
}

