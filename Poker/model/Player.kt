package model

@ConsistentCopyVisibility
data class Player private constructor(
    val playerNum: Int,
    val money: Money,
    val hand: Hand
) {
    companion object {
        fun of(playerNum: Int, money: Money, hand: Hand): Player = 
            Player(playerNum, money, hand)
    }
}