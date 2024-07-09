package com.ranis.game_library.blackjack

import android.widget.ImageView
import com.ranis.game_library.R

class Deck// создание колоды карт
    (makeDeck: Boolean) {
    // колода будет состоять из списка карт
    private var deck: ArrayList<Card>

    init {
        deck = ArrayList()
        if(makeDeck){
            for(suit in Suit.values()){
                for(rank in Rank.values()){
                    var imageInt = identifyTheMapImage(rank, suit)
                    deck.add(Card(suit, rank, imageInt))
                }
            }
        }
    }
    fun identifyTheMapImage(rank: Rank, suit: Suit): Int{
        if(suit.suitName.equals("clubs")){
            if(rank.rankName.equals("2")){
                return R.drawable.clubs2
            }else if(rank.rankName.equals("3")){
                return R.drawable.clubs3
            }else if(rank.rankName.equals("4")){
                return R.drawable.clubs4
            }else if(rank.rankName.equals("5")){
                return R.drawable.clubs5
            }else if(rank.rankName.equals("6")){
                return R.drawable.clubs6
            }else if(rank.rankName.equals("7")){
                return R.drawable.clubs7
            }else if(rank.rankName.equals("8")){
                return R.drawable.clubs8
            }else if(rank.rankName.equals("9")){
                return R.drawable.clubs9
            }else if(rank.rankName.equals("10")){
                return R.drawable.clubs10
            }else if(rank.rankName.equals("jack")){
                return R.drawable.clubs_jack
            }else if(rank.rankName.equals("queen")){
                return R.drawable.clubs_queen
            }else if(rank.rankName.equals("king")){
                return R.drawable.clubs_king
            }else{
                return R.drawable.clubs_ace
            }
        }else if(suit.suitName.equals("diamonds")){
            if(rank.rankName.equals("2")){
                return R.drawable.diamonds2
            }else if(rank.rankName.equals("3")){
                return R.drawable.diamonds3
            }else if(rank.rankName.equals("4")){
                return R.drawable.diamonds4
            }else if(rank.rankName.equals("5")){
                return R.drawable.diamonds5
            }else if(rank.rankName.equals("6")){
                return R.drawable.diamonds6
            }else if(rank.rankName.equals("7")){
                return R.drawable.diamonds7
            }else if(rank.rankName.equals("8")){
                return R.drawable.diamonds8
            }else if(rank.rankName.equals("9")){
                return R.drawable.diamonds9
            }else if(rank.rankName.equals("10")){
                return R.drawable.diamonds10
            }else if(rank.rankName.equals("jack")){
                return R.drawable.diamonds_jack
            }else if(rank.rankName.equals("queen")){
                return R.drawable.diamonds_queen
            }else if(rank.rankName.equals("king")){
                return R.drawable.diamonds_king
            }else{
                return R.drawable.diamonds_ace
            }
        }else if(suit.suitName.equals("hearts")){
            if(rank.rankName.equals("2")){
                return R.drawable.hearts2
            }else if(rank.rankName.equals("3")){
                return R.drawable.hearts3
            }else if(rank.rankName.equals("4")){
                return R.drawable.hearts4
            }else if(rank.rankName.equals("5")){
                return R.drawable.hearts5
            }else if(rank.rankName.equals("6")){
                return R.drawable.hearts6
            }else if(rank.rankName.equals("7")){
                return R.drawable.hearts7
            }else if(rank.rankName.equals("8")){
                return R.drawable.hearts8
            }else if(rank.rankName.equals("9")){
                return R.drawable.hearts9
            }else if(rank.rankName.equals("10")){
                return R.drawable.hearts10
            }else if(rank.rankName.equals("jack")){
                return R.drawable.hearts_jack
            }else if(rank.rankName.equals("queen")){
                return R.drawable.hearts_queen
            }else if(rank.rankName.equals("king")){
                return R.drawable.hearts_king
            }else{
                return R.drawable.hearts_ace
            }
        }else{
            if(rank.rankName.equals("2")){
                return R.drawable.spades2
            }else if(rank.rankName.equals("3")){
                return R.drawable.spades3
            }else if(rank.rankName.equals("4")){
                return R.drawable.spades4
            }else if(rank.rankName.equals("5")){
                return R.drawable.spades5
            }else if(rank.rankName.equals("6")){
                return R.drawable.spades6
            }else if(rank.rankName.equals("7")){
                return R.drawable.spades7
            }else if(rank.rankName.equals("8")){
                return R.drawable.spades8
            }else if(rank.rankName.equals("9")){
                return R.drawable.spades9
            }else if(rank.rankName.equals("10")){
                return R.drawable.spades10
            }else if(rank.rankName.equals("jack")){
                return R.drawable.spades_jack
            }else if(rank.rankName.equals("queen")){
                return R.drawable.spades_queen
            }else if(rank.rankName.equals("king")){
                return R.drawable.spades_king
            }else{
                return R.drawable.spades_ace
            }
        }
    }

    // метод для добавления карты в список
    fun addCard(card: Card){
        deck.add(card)
    }

    // метод для перетасовки карт
    fun shuffle(){
        val shuffled = ArrayList<Card>()
        while(deck.size > 0){
            val cardToPull = (0 until deck.size).random()
            shuffled.add(deck[cardToPull])
            deck.removeAt(cardToPull)
        }
        deck = shuffled
    }

    // извлечение карты из колоды
    @Throws(Exception::class)
    fun takeCard(): Card{
        return if(deck.size > 0){
            val cardToTake = deck[0]
            deck.removeAt(0)
            cardToTake
        } else {
            throw Exception("The deck is empty.")
        }
    }

    override fun toString(): String{
        var output = ""
        for(card in deck){
            output += "$card\n"
        }
        return output
    }

    // проверка того, есть ли в колоде карты
    fun hasCards(): Boolean {
        return deck.isNotEmpty()
    }

    // очистка колоды
    fun emptyDeck(){
        deck.clear()
    }

    // добавление нескольких карт в колоду
    fun addCards(cards: ArrayList<Card>){
        deck.addAll(cards)
    }

    fun reloadDeckFromDiscard(discard: Deck){
        addCards(discard.getDeck())
        shuffle()
        discard.emptyDeck()
        println("Закончились карты, создаем новую колоду из стопки сброса и перетасовываем колоду.")
    }

    fun cardsLeft(): Int {
        return deck.size
    }

    fun getDeck(): ArrayList<Card> {
        return deck
    }
}