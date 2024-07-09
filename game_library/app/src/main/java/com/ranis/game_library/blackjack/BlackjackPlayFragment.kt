package com.ranis.game_library.blackjack

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ranis.game_library.R
import com.ranis.game_library.databinding.FragmentPlayBlackjackBinding

class BlackjackPlayFragment : Fragment(R.layout.fragment_play_blackjack) {

    private var binding: FragmentPlayBlackjackBinding? = null

    private var money = 5000

    private var bet = 0

    private var gameIsInProcess: Boolean = false

    private var cardsPlayerInDeck: ArrayList<ImageView> = ArrayList<ImageView>()

    private var cardsDealerInDeck: ArrayList<ImageView> = ArrayList<ImageView>()

    private var freeIndexInDealerHand: Int = 0

    private var freeIndexInPlayerHand: Int = 0

    //создаем основную колоду
    private var deck: Deck = Deck(true)
    //создаем пустую колоду
    private var discarded = Deck(true)

    private var player: Player = Player()

    private var dealer: Dealer = Dealer()

    private var hit = false

    private var countCardsPlayer = 0

    private var stand = false

    private var countCardsDealer = 0

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlayBlackjackBinding.bind(view)
        addCardInTheList()

        binding?.run {
            chip10.setOnClickListener{
                if(gameIsInProcess){
                    Toast.makeText(context, "The game is in process", Toast.LENGTH_SHORT).show()
                }else if(bet + 10 <= money){
                    bet += 10
                    tvBet.text = "Bet: $bet"
                }else{
                    Toast.makeText(context, "Not enough money", Toast.LENGTH_SHORT).show()
                }
            }

            chip25.setOnClickListener{
                if(gameIsInProcess){
                    Toast.makeText(context, "The game is in process", Toast.LENGTH_SHORT).show()
                }else if(bet + 25 <= money){
                    bet += 25
                    tvBet.text = "Bet: $bet"
                }else{
                    Toast.makeText(context, "Not enough money", Toast.LENGTH_SHORT).show()
                }
            }

            chip100.setOnClickListener{
                if(gameIsInProcess){
                    Toast.makeText(context, "The game is in process", Toast.LENGTH_SHORT).show()
                }else if(bet + 100 <= money){
                    bet += 100
                    tvBet.text = "Bet: $bet"
                }else{
                    Toast.makeText(context, "Not enough money", Toast.LENGTH_SHORT).show()
                }
            }

            chip500.setOnClickListener{
                if(gameIsInProcess){
                    Toast.makeText(context, "The game is in process", Toast.LENGTH_SHORT).show()
                }else if(bet + 500 <= money){
                    bet += 500
                    tvBet.text = "Bet: $bet"
                }else{
                    Toast.makeText(context, "Not enough money", Toast.LENGTH_SHORT).show()
                }
            }

            buttonClear.setOnClickListener{
                if(!gameIsInProcess) {
                    bet = 0
                    tvBet.text = "Bet: $bet"
                }
            }

            ivIcon.setOnClickListener{
                findNavController().navigate(R.id.action_blackjackPlayFragment_to_menuFragment)
            }

            buttonDeal.setOnClickListener{
                if(gameIsInProcess){
                    Toast.makeText(context, "The game is in process", Toast.LENGTH_SHORT).show()
                }else if(bet == 0){
                    Toast.makeText(context, "Please place a bet", Toast.LENGTH_SHORT).show()
                }else{
                    gameIsInProcess = true
                    tvInfoText.text = "The game has started"
                    startRound()
                }
            }
            playAgain.setOnClickListener{
                cleaningBeforeRound()
                playAgain.visibility = View.INVISIBLE
                gameIsInProcess = false
            }

            buttonHit.setOnClickListener{
                stand = true
                if(hit && player.getHand().calculatorValue() <= 19){
                    player.hit(deck, discarded)
                    countCardsPlayer++
                    cardsPlayerInDeck.get(freeIndexInPlayerHand).setImageResource(player.getHand().getCard(1 + countCardsPlayer).getInt())
                    freeIndexInPlayerHand++
                    tvPlayerScore.text = "${player.getHand().calculatorValue()}"
                    if(player.getHand().calculatorValue() > 21){
                        tvInfoText.text = "You've lost,more 21"
                        playAgain.visibility = View.VISIBLE
                    }else if(player.getHand().calculatorValue() == 20){
                        binding?.run {
                            tvInfoText.text = "Enter in STAND"
                            hit = false
                            stand = true
                        }
                    }else if(player.getHand().calculatorValue() == 21){
                        binding?.run {
                            tvInfoText.text = "Enter in STAND"
                            hit = false
                            stand = true
                        }
                    }
                }else if(!hit){
                    Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                }else{
                    binding?.run {
                        tvInfoText.text = "Enter on STAND"
                    }
                    Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
            buttonStand.setOnClickListener{
                binding?.run {
                    tvInfoText.text = "Dealer's move"
                }
                dealerOpenSecondCard()

            }
        }
    }

    fun addCardInTheList(){
        binding?.run {
            cardsPlayerInDeck.add(playerCard1)
            cardsPlayerInDeck.add(playerCard2)
            cardsPlayerInDeck.add(playerCard3)
            cardsPlayerInDeck.add(playerCard4)
            cardsPlayerInDeck.add(playerCard5)

            cardsDealerInDeck.add(dealerCard1)
            cardsDealerInDeck.add(dealerCard2)
            cardsDealerInDeck.add(dealerCard3)
            cardsDealerInDeck.add(dealerCard4)
            cardsDealerInDeck.add(dealerCard5)
        }
    }


    fun startRound(){
        money -= bet
        binding?.run {
            tvMoney.text = "Money: $money"
        }
        deck.shuffle()
        dealer.getHand().discardHandToDeck(discarded);
        player.getHand().discardHandToDeck(discarded);

        if(deck.cardsLeft() < 4){
            deck.reloadDeckFromDiscard(discarded)
        }
        //Берем две карты для Дилера
        dealer.getHand().takeCardFromDeck(deck);
        dealer.getHand().takeCardFromDeck(deck);
        //Берем две карты для Игрока
        player.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);

        binding?.run {
            tvDealerScore.text = "${dealer.getHand().calculatorValue()- dealer.getHand().getCard(1).getValue()}"
            tvPlayerScore.text = "${player.getHand().calculatorValue()}"
        }
        cardsDealerInDeck.get(freeIndexInDealerHand).setImageResource(dealer.getHand().getCard(0).getInt())
        freeIndexInDealerHand++
        cardsDealerInDeck.get(freeIndexInDealerHand).setImageResource(R.drawable.back)
        freeIndexInDealerHand++

        cardsPlayerInDeck.get(freeIndexInPlayerHand).setImageResource(player.getHand().getCard(0).getInt())
        freeIndexInPlayerHand++
        cardsPlayerInDeck.get(freeIndexInPlayerHand).setImageResource(player.getHand().getCard(1).getInt())
        freeIndexInPlayerHand++

        if(dealer.hasBlackjack()){
            if(player.hasBlackjack()){
                freeIndexInDealerHand--
                cardsDealerInDeck.get(freeIndexInDealerHand).setImageResource(dealer.getHand().getCard(1).getInt())
                binding?.run {
                    tvDealerScore.text = "${dealer.getHand().calculatorValue()}"
                }
                money += bet
                binding?.run {
                    tvMoney.text = "Money: $money"
                    tvInfoText.text = "There are no winner"
                    playAgain.visibility = View.VISIBLE
                    //cleaningBeforeRound()
                }

            }else{
                freeIndexInDealerHand--
                cardsDealerInDeck.get(freeIndexInDealerHand).setImageResource(dealer.getHand().getCard(1).getInt())
                binding?.run {
                    tvDealerScore.text = "${dealer.getHand().calculatorValue()}"
                }
                binding?.run {
                    tvInfoText.text = "You've lost, Blackjack!"
                    playAgain.visibility = View.VISIBLE
                    //cleaningBeforeRound()
                }
            }
        }
        if(player.hasBlackjack()){
            freeIndexInDealerHand--
            cardsDealerInDeck.get(freeIndexInDealerHand).setImageResource(dealer.getHand().getCard(1).getInt())
            binding?.run {
                tvDealerScore.text = "${dealer.getHand().calculatorValue()}"
            }
            binding?.run {
                money += bet * 2
                tvMoney.text = "Money $money"
                tvInfoText.text = "You've won, Blackjack!"
                playAgain.visibility = View.VISIBLE
                //cleaningBeforeRound()
            }
        }

        if(!player.hasBlackjack() && !dealer.hasBlackjack()){
            binding?.run {
                tvInfoText.text = "Hit or stand?"
            }
        }
        if(player.getHand().calculatorValue() == 20){
            binding?.run {
                tvInfoText.text = "Enter in STAND"
                hit = false
                stand = true
            }
        }else{
            hit = true
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun cleaningBeforeRound() {
        freeIndexInDealerHand = 0
        freeIndexInPlayerHand = 0
        for (imageView in cardsPlayerInDeck) {
            imageView.setImageDrawable(null)
        }
        for (imageView in cardsDealerInDeck) {
            imageView.setImageDrawable(null)
        }
        gameIsInProcess = false
        bet = 0
        binding?.run {
            tvPlayerScore.text = "0"
            tvDealerScore.text = "0"
            tvBet.text = "Bet: $bet"
            tvInfoText.text = "Place a bet"
        }
        hit = false
        stand = false
        countCardsPlayer = 0
        countCardsDealer = 0
    }

    fun dealerOpenSecondCard(){
        freeIndexInDealerHand--
        cardsDealerInDeck.get(freeIndexInDealerHand).setImageResource(dealer.getHand().getCard(1).getInt())
        binding?.run {
            tvDealerScore.text = "${dealer.getHand().calculatorValue()}"
        }
        freeIndexInDealerHand++
        if(dealer.getHand().calculatorValue()<17){
            dealerHit()
        }else{
            if(dealer.getHand().calculatorValue()>21){
                money += bet * 2
                binding?.run {
                    tvMoney.text = "Money $money"
                    tvInfoText.text = "You've won"
                    playAgain.visibility = View.VISIBLE
                }
            }
            else if(dealer.getHand().calculatorValue() > player.getHand().calculatorValue()){
                binding?.run {
                    tvInfoText.text = "You've lost"
                    playAgain.visibility = View.VISIBLE
                }
            }
            else if(player.getHand().calculatorValue() > dealer.getHand().calculatorValue()){
                money += bet * 2
                binding?.run {
                    tvMoney.text = "Money $money"
                    tvInfoText.text = "You've won"
                    playAgain.visibility = View.VISIBLE
                }
            }
            else{
                money += bet
                binding?.run {
                    tvMoney.text = "Money: $money"
                    tvInfoText.text = "There are no winner"
                    playAgain.visibility = View.VISIBLE
                }
            }
        }
    }
    fun dealerHit(){
        countCardsDealer++
        dealer.hit(deck, discarded);
        cardsDealerInDeck.get(freeIndexInDealerHand).setImageResource(dealer.getHand().getCard(1 + countCardsDealer).getInt())
        freeIndexInDealerHand++
        binding?.run {
            tvDealerScore.text = "${dealer.getHand().calculatorValue()}"
        }
        if(dealer.getHand().calculatorValue()<17){
            dealerHit()
        }else{
            if(dealer.getHand().calculatorValue()>21){
                money += bet * 2
                binding?.run {
                    tvMoney.text = "Money $money"
                    tvInfoText.text = "You've won"
                    playAgain.visibility = View.VISIBLE
                }
            }
            else if(dealer.getHand().calculatorValue() > player.getHand().calculatorValue()){
                binding?.run {
                    tvInfoText.text = "You've lost"
                    playAgain.visibility = View.VISIBLE
                }
            }
            else if(player.getHand().calculatorValue() > dealer.getHand().calculatorValue()){
                money += bet * 2
                binding?.run {
                    tvMoney.text = "Money $money"
                    tvInfoText.text = "You've won"
                    playAgain.visibility = View.VISIBLE
                }
            }
            else{
                money += bet
                binding?.run {
                    tvMoney.text = "Money: $money"
                    tvInfoText.text = "There are no winner"
                    playAgain.visibility = View.VISIBLE
                }
            }
        }
    }

}