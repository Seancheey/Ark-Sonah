package com.seancheey.game

/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class RobotNode(model: RobotModel, override val field: BattleField, override var x: Double, override var y: Double) : RobotModel(model.name, model.components), Node {
    override var speed: Double = 0.0
    override var orientation: Double = 0.0
    override val peers: ArrayList<Node> = field.nodes

    init {
    }

    override fun update() {
        x += vx
        y += vy
    }
}