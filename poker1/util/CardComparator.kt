package common.util

import model.Card
import model.CardUnit

class CardComparator : Comparator<Card> {
    override fun compare(c1: Card, c2: Card): Int {
        val firstComparison = compareTo(c1.card[0], c2.card[0])
        
        if (firstComparison != 0) {
            return firstComparison
        }
        
        return compareTo(c1.card[1], c2.card[1])
    }
    
    private fun compareTo(x1: CardUnit, x2: CardUnit): Int =
        x1.unitNumber.compareTo(x2.unitNumber)
}