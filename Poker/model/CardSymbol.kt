package model

class CardSymbol private constructor(unitNumber: Int) : CardUnit(unitNumber) {
    
    companion object {
        private const val MIN_CARD_NUMBER = 2
        private const val MAX_CARD_NUMBER = 14
        private val CARD_SYMBOL_CACHE: List<CardSymbol> = 
            (MIN_CARD_NUMBER..MAX_CARD_NUMBER).map { CardSymbol(it) }
        
        fun randomCardSymbol(): CardSymbol = CARD_SYMBOL_CACHE.random()
        
        fun from(unitNumber: Int): CardSymbol = CARD_SYMBOL_CACHE[unitNumber]
    }
    
    override fun compare(c1: CardUnit, c2: CardUnit): Int =
        c1.unitNumber.compareTo(c2.unitNumber)
}