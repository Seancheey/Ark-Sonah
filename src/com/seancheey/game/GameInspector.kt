package com.seancheey.game

/**
 * Created by Seancheey on 04/06/2017.
 * GitHub: https://github.com/Seancheey
 */
interface GameInspector {
    val gameDirector: GameDirector
    val guiWidth: Double
    val guiHeight: Double

    var scale: Double
    var transX: Double
    var transY: Double

    var focusedNodes: ArrayList<Node>

    fun selectRobotBeside(x: Double, y: Double)

    fun moveFocusedRobotsTo(x: Double, y: Double)

    fun selectAllRobotsWithSameType(robotModel: RobotModel)
}