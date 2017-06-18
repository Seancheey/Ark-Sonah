package com.seancheey.game

/**
 * Created by Seancheey on 14/06/2017.
 * GitHub: https://github.com/Seancheey
 */
data class PlayerInGame(val player: Player, val robotGroupUsed: RobotModelGroup, val side: Side) {
    var money: Int = 1000

    enum class Side {
        side1, side2, side3, side4
    }
}