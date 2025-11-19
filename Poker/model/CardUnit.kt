package model

import java.util.Comparator

abstract class CardUnit(
    val unitNumber: Int
) : Comparator<CardUnit> {
    
    fun compareTo(other: CardUnit): Int = 
        unitNumber.compareTo(other.unitNumber)
}