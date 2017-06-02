package com.seancheey.game

/**
 * Created by Seancheey on 01/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class ClassicAIBattleField : BattleField(arrayListOf(Config.player)) {
    init {
        name = "Classic AI Battle"
        val firstRobot = players[0].robotGroups[0][0]
        putRobot(firstRobot, 150.0, 200.0)
    }
}