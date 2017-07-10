package com.seancheey.gui

import com.seancheey.game.Config
import com.seancheey.game.Game
import com.seancheey.game.GameDirector
import com.seancheey.game.battlefield.Battlefield
import com.seancheey.game.command.MoveCommand
import com.seancheey.game.model.MovableNode
import com.seancheey.game.model.Node
import com.seancheey.game.model.RobotModel
import com.seancheey.game.model.RobotNode

/**
 * Created by Seancheey on 04/06/2017.
 * GitHub: https://github.com/Seancheey
 */
interface GameInspector {
    /**
     * initiate gameDirector to start
     */
    val gameDirector: GameDirector
    /**
     * game the inspector is inspecting
     */
    val game: Game
    /**
     * used for painting battlefield
     */
    val battlefield: Battlefield
        get() = game.field
    /**
     * player's selected nodes
     */
    val focusedNodes: List<Node>
        get() = gameDirector.nodes.filter { it.focusedByPlayer }

    /**
     * pixel width of interface
     */
    val guiWidth: Double
    /**
     * pixel height of interface
     */
    val guiHeight: Double
    /**
     * for graphic scaling and scrolling
     */
    var cameraScale: Double
    /**
     * for camera to move around the battlefield
     */
    var cameraTransX: Double
    /**
     * for camera to move around the battlefield
     */
    var cameraTransY: Double

    /**
     * activated when player clicked a RobotModelSlot
     */
    fun clickRobot(model: RobotModel)


    fun selectRobotBeside(x: Double, y: Double) {
        focusedNodes.forEach { it.focusedByPlayer = false }
        val minDistanceNode = gameDirector.nodes.filterIsInstance<RobotNode>().filter { it.containsPoint(x, y) }.minBy { it.distanceTo(x, y) }
        if (minDistanceNode != null) {
            minDistanceNode.focusedByPlayer = true
        }
    }

    fun moveFocusedRobotsTo(x: Double, y: Double) {
        focusedNodes.filterIsInstance<MovableNode>().forEach { gameDirector.command(MoveCommand(Config.player, it, x, y)) }
    }

    fun selectAllRobotsWithSameType(robotModel: RobotModel) {
        focusedNodes.forEach { it.focusedByPlayer = false }
        battlefield.nodes.filter { it is RobotNode && it.model == robotModel }.forEach { it.focusedByPlayer = true }
    }
}