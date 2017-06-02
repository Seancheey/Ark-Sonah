package com.seancheey.game

import java.io.Serializable
import java.util.*


/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
open class BattleField(val players: ArrayList<Player>) : Serializable {
    var name: String = "Default Map"
    val width: Double = 1000.0
    val height: Double = 600.0
    val nodes: ArrayList<Node> = arrayListOf()

    fun putRobot(model: RobotModel, x: Double, y: Double, speed: Double = 0.0, orientation: Double = 0.0) {
        val botNode = RobotNode(model, this, x, y)
        botNode.speed = speed
        botNode.orientation = orientation
        nodes.add(botNode)
    }
}