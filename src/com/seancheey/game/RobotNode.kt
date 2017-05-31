package com.seancheey.game

/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class RobotNode(val model: RobotModel, override val field: BattleField, override var x: Int, override var y: Int) : RobotModel(model.name, model.components), Node {
    override val peers: ArrayList<Node> = field.nodes

    override fun update(elapsed: Int) {

    }

}