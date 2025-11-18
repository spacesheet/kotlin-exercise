package model

object Cards {
    private const val CARDS_COUNT = 52
    private val cards: List<Card> = createCards()
    private var cardsIndex = 0
    
    fun getInstance(): List<Card> = cards
    
    private fun createCards(): List<Card> = 
        List(CARDS_COUNT) { Card.createCard() }
            .shuffled()
    
    fun getNextCard(): Card = cards[cardsIndex++]
}