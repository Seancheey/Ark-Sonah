package com.seancheey.game

import java.io.Serializable


/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class BattleField(val players: ArrayList<Player>) : Serializable {
    var name: String = "Default Map"
    val width: Double = 1000.0
    val height: Double = 600.0
    val nodes: ArrayList<Node> = arrayListOf()
}