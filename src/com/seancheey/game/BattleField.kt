package com.seancheey.game

import java.io.Serializable


/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class BattleField(val players: ArrayList<Player>) : Serializable {
    var name: String = "Default Map"
    val width: Int = 1000
    val height: Int = 600
    val nodes: ArrayList<Node> = arrayListOf()
    val commands: ArrayList<Command> = arrayListOf()

    fun executeCommands() {
        for (command in commands) {
            command.execute()
        }
        commands.clear()
    }
}