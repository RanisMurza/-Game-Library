package com.ranis.game_library.blackjack

class Hand {
    private val hand: ArrayList<Card> = ArrayList()

    fun takeCardFromDeck(deck: Deck) {
        hand.add(deck.takeCard())
    }

    fun discardHandToDeck(discardDeck: Deck) {
        //copy cards from hand to discardDeck
        discardDeck.addCards(hand)

        //clear the hand
        hand.clear()
    }

    fun calculatorValue(): Int {
        var value = 0
        var aceCount = 0

        for (card in hand) {
            value += card.getRank().rankValue
            if (card.getRank().rankValue == 11) {
                aceCount++
            }
        }
        if (aceCount > 0 && value > 21) {
            while (aceCount > 0 && value > 21) {
                value -= 10
                aceCount--
            }
        }
        return value
    }

    fun getCard(idx: Int): Card {
        return hand[idx]
    }
}