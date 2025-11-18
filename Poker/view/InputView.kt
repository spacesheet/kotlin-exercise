package view

object InputView {
    fun inputPlayersCount(): Int {
        print("플레이어의 수를 입력하세요. : ")
        return readln().toInt()
    }
    
    fun inputSeedMoney(): Int {
        println("시드 머니를 입력하세요. : ")
        return readln().toInt()
    }
    
    fun inputBettingMoney(): Int {
        println("배팅 머니를 입력하세요. : ")
        return readln().toInt()
    }
}