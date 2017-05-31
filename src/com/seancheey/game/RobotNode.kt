package com.seancheey.game

import com.seancheey.game.action.Action
import com.seancheey.game.action.MoveAction

/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class RobotNode(model: RobotModel, override val field: BattleField, override var x: Double, override var y: Double) : RobotModel(model.name, model.components), Node {
    override var speed: Double = 0.0
    override var orientation: Double = 0.0
    override val actions: ArrayList<Action> = arrayListOf(MoveAction(this))
    override val peers: ArrayList<Node> = field.nodes

    init {
    }
}