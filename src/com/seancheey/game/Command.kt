package com.seancheey.game

import java.io.Serializable

/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
abstract class Command(val from: Player, val to: RobotNode, val battleField: BattleField) : Serializable {
    var finished: Boolean = false

    abstract fun execute(interval: Int)
}