package model

enum class WinnerCardsArray(
    private val winNums: List<Int>,
    private val isSameShape: Boolean
) {
    RoyalStraightFlush(listOf(10, 11, 12, 13, 14), true),
    BackStraightFlush(listOf(14, 2, 3, 4, 5), true),
    None(listOf(0, 0, 0, 0, 0), false);
    
    companion object {
        fun findWinnerCardArray(winNums: List<Int>, isSameShape: Boolean): WinnerCardsArray =
            entries.find { it.winNums == winNums && it.isSameShape == isSameShape }
                ?: None
    }
}