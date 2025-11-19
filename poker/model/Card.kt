package model

class Card private constructor(
    val card: List<CardUnit>  // property로 노출
) : Comparator<Card> {
    
    companion object {
        private const val CARD_DIVISION = 2
        
        fun createCard(): Card = Card(
            listOf(
                CardSymbol.randomCardSymbol(),
                CardShape.randomCardShape()
            )
        )
        
        fun of(cardSymbol: CardSymbol, cardShape: CardShape): Card = Card(
            listOf(cardSymbol, cardShape)
        )
    }
    
    fun compare(maxCard: Card): Int {
        val firstComparison = card[0].compareTo(maxCard.card[0])
        
        if (firstComparison != 0) {
            return firstComparison
        }
        
        return card[1].compareTo(maxCard.card[1])
    }
    
    override fun compare(c1: Card, c2: Card): Int = c1.compare(c2)
}