package com.seancheey.game

import com.seancheey.game.battlefield.Battlefield

/**
 * Created by Seancheey on 14/06/2017.
 * GitHub: https://github.com/Seancheey
 */
data class PlayerInGame(val player: Player, val robotGroupUsed: RobotModelGroup, val side: Side) {
    enum class Side {
        side1, side2, side3, side4
    }
}