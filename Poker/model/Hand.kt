package model

class Hand() {
    private val cardsInHand = mutableListOf<Card>()
    
    val cards: List<Card>  // property로 노출
        get() = cardsInHand
    
    private constructor(cards: List<Card>) : this() {
        cardsInHand.addAll(cards)
    }
    
    companion object {
        fun from(cards: List<Card>): Hand = Hand(cards)
    }
    
    fun receivedHand(cards: List<Card>): Hand {
        cardsInHand.addAll(cards)
        return Hand(cardsInHand)
    }
    
    fun receivedHandOneCard(card: Card): Hand {
        cardsInHand.add(card)
        return Hand(cardsInHand)
    }
    
    fun findMaxCard(): Card {
        return cardsInHand.reduce { max, card ->
            if (card.compare(max) > 0) card else max
        }
    }
}