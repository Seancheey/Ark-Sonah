package com.seancheey.game

import com.seancheey.game.battlefield.Battlefield

/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class RobotNode(model: RobotModel, override val field: Battlefield, override var x: Double, override var y: Double) : RobotModel(model.name, model.components), Node {
    override val actionTree: ActionTree = ActionTree(this)
    override var speed: Double = 0.0
    override var orientation: Double = 0.0
    override val peers: ArrayList<Node> = field.nodes

    init {
        actionTree.putAction(Action.moveAction(this), ActionTree.MOVE_ACTION)
    }
}