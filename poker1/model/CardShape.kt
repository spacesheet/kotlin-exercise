package model

class CardShape private constructor(unitNumber: Int) : CardUnit(unitNumber) {
    
    companion object {
        private const val MIN_CARD_NUMBER = 0
        private const val MAX_CARD_NUMBER = 3
        private val CARD_SHAPE_CACHE: List<CardShape> = 
            (MIN_CARD_NUMBER..MAX_CARD_NUMBER).map { CardShape(it) }
        
        fun randomCardShape(): CardShape = CARD_SHAPE_CACHE.random()
        
        fun from(unitNumber: Int): CardShape = CARD_SHAPE_CACHE[unitNumber]
    }
    
    override fun compare(c1: CardUnit, c2: CardUnit): Int =
        c1.unitNumber.compareTo(c2.unitNumber)
}