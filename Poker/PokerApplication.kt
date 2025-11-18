import common.util.CardComparator
import model.*
import view.InputView
import java.util.Collections

object PokerApplication {
    private const val INIT_CARD_COUNT = 3
    private val cardComparator = CardComparator()

    @JvmStatic
    fun main(args: Array<String>) {
        val playersCount = InputView.inputPlayersCount()
        val playersSeedMoney = InputView.inputSeedMoney()

        val players = Players.from(playersCount, playersSeedMoney)

        // 초기 카드 분배
        for (i in 0 until playersCount) {
            val initCards = mutableListOf<Card>()
            
            repeat(INIT_CARD_COUNT) {
                initCards.add(Cards.getNextCard())
            }

            val hand = Hand()
            players.setPlayer(
                i,
                Player.of(i, Money.from(playersSeedMoney), hand.receivedHand(initCards))
            )
        }

        // 최대 카드를 가진 플레이어 찾기
        var maxCard = players.player(0).hand.cards[0]
        var maxPlayerNum = 0
        
        for (i in 1 until playersCount) {
            val currentCard = players.player(i).hand.findMaxCard()
            if (currentCard.compare(players.player(i - 1).hand.findMaxCard()) > 0) {
                maxCard = currentCard
                maxPlayerNum = i
            }
        }

        println("$maxPlayerNum 부터 시작합니다.")

        // 첫 번째 베팅 라운드 (maxPlayerNum부터 끝까지)
        for (i in maxPlayerNum until playersCount) {
            val currentPlayer = players.player(i)
            val updatedHand = currentPlayer.hand.receivedHandOneCard(Cards.getNextCard())
            Collections.sort(updatedHand.cards.toMutableList(), cardComparator)
            
            val bettingMoney = InputView.inputBettingMoney()
            players.setPlayer(
                i,
                Player.of(
                    i,
                    Money.of(currentPlayer.money.seedMoney - bettingMoney, bettingMoney),
                    currentPlayer.hand
                )
            )
        }

        // 첫 번째 베팅 라운드 (0부터 maxPlayerNum-1까지)
        for (i in 0 until maxPlayerNum) {
            val currentPlayer = players.player(i)
            val updatedHand = currentPlayer.hand.receivedHandOneCard(Cards.getNextCard())
            Collections.sort(updatedHand.cards.toMutableList(), cardComparator)
            
            val bettingMoney = InputView.inputBettingMoney()
            players.setPlayer(
                i,
                Player.of(
                    i,
                    Money.of(currentPlayer.money.seedMoney - bettingMoney, bettingMoney),
                    currentPlayer.hand
                )
            )
        }

        // 두 번째 베팅 라운드 (maxPlayerNum부터 끝까지)
        for (i in maxPlayerNum until playersCount) {
            val currentPlayer = players.player(i)
            val updatedHand = currentPlayer.hand.receivedHandOneCard(Cards.getNextCard())
            Collections.sort(updatedHand.cards.toMutableList(), cardComparator)
            
            val bettingMoney = InputView.inputBettingMoney()
            players.setPlayer(
                i,
                Player.of(
                    i,
                    Money.of(currentPlayer.money.seedMoney - bettingMoney, bettingMoney),
                    currentPlayer.hand
                )
            )
        }

        // 두 번째 베팅 라운드 (0부터 maxPlayerNum-1까지)
        for (i in 0 until maxPlayerNum) {
            val currentPlayer = players.player(i)
            val updatedHand = currentPlayer.hand.receivedHandOneCard(Cards.getNextCard())
            Collections.sort(updatedHand.cards.toMutableList(), cardComparator)
            
            val bettingMoney = InputView.inputBettingMoney()
            players.setPlayer(
                i,
                Player.of(
                    i,
                    Money.of(currentPlayer.money.seedMoney - bettingMoney, bettingMoney),
                    currentPlayer.hand
                )
            )
        }

        // 게임 결과 출력
        println("게임 결과")

        for (i in 0 until players.playersCount()) {
            val playerScore = mutableListOf<Int>()
            
            players.player(i).hand.cards.forEach { card ->
                playerScore.add(card.card[0].unitNumber)
            }

            val card = players.player(i).hand.cards[0].card
            val isSameShape = players.player(i).hand.cards
                .all { it.card[1] == card[1] }

            println(playerScore)
            println("$i : ${WinnerCardsArray.findWinnerCardArray(playerScore, isSameShape)}")
        }
    }
}