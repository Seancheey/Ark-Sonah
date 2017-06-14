package com.seancheey.gui

import com.seancheey.game.*
import com.seancheey.game.battlefield.Battlefield
import com.seancheey.game.command.MoveCommand

/**
 * Created by Seancheey on 04/06/2017.
 * GitHub: https://github.com/Seancheey
 */
interface GameInspector {
    val gameDirector: GameDirector
    val battlefield: Battlefield
    val guiWidth: Double
    val guiHeight: Double

    var scale: Double
    var transX: Double
    var transY: Double

    var focusedNodes: ArrayList<Node>

    fun selectRobotBeside(x: Double, y: Double) {
        focusedNodes.clear()
        val minDistanceNode = gameDirector.nodes.filter { it.containsPoint(x, y) }.minBy { it.distanceTo(x, y) }
        if (minDistanceNode != null) {
            focusedNodes.add(minDistanceNode)
        } else {
        }
    }

    fun moveFocusedRobotsTo(x: Double, y: Double) {
        focusedNodes.forEach { node -> if (node is MovableNode) gameDirector.command(MoveCommand(Config.player, node, x, y)) }
    }

    fun selectAllRobotsWithSameType(robotModel: RobotModel) {
        focusedNodes.clear()
        for (node in battlefield.nodes) {
            if (node is RobotNode) {
                if (node.model == robotModel) {
                    focusedNodes.add(node)
                }
            }
        }
    }
}