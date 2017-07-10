package com.seancheey.game

/**
 * Created by Seancheey on 14/06/2017.
 * GitHub: https://github.com/Seancheey
 */
data class PlayerInGame(val player: Player, val side: Int, val initialMoney: Int, var robotGroup: RobotModelGroup = player.robotGroups[0]) {
    var money: Int = initialMoney
}