package model

class Players private constructor(
    private val players: MutableList<Player>
) {
    companion object {
        fun from(playerCount: Int, seedMoney: Int): Players = 
            Players(
                List(playerCount) { i ->
                    Player.of(i, Money.from(seedMoney), Hand())
                }.toMutableList()
            )
    }
    
    fun player(playerNum: Int): Player = players[playerNum]
    
    fun players(): List<Player> = players
    
    fun playersCount(): Int = players.size
    
    fun setPlayer(index: Int, player: Player): Players {
        players[index] = player
        return Players(players)
    }
}