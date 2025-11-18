package model

@ConsistentCopyVisibility
data class Money private constructor(
    val seedMoney: Int,
    val bettingMoney: Int
) {
    companion object {
        fun from(seedMoney: Int): Money = Money(seedMoney, 0)
        
        fun of(seedMoney: Int, bettingMoney: Int): Money = 
            Money(seedMoney, bettingMoney)
    }
}