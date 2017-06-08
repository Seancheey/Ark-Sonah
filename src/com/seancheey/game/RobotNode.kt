package com.seancheey.game

import com.seancheey.game.battlefield.Battlefield

/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class RobotNode(model: RobotModel, override val field: Battlefield, override var x: Double, override var y: Double) : RobotModel(model.name, model.components), MovableNode {
    override val children: ArrayList<Node> = arrayListOf()
    override var maxSpeed: Double = 0.0
    override var turnSpeed: Double = 0.0
    override val actionTree: ActionTree = ActionTree(this)
    override var speed: Double = 0.0
    override var orientation: Double = 0.0
        set(value) {
            field = value - 2 * Math.PI * (value / (2 * Math.PI)).toInt()
        }
    override val peers: ArrayList<Node> = field.nodes

    init {
        actionTree.putAction(Action.moveAction(this), Action.MOVE_ACTION)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RobotNode) return false
        if (!super.equals(other)) return false

        if (field != other.field) return false
        if (x != other.x) return false
        if (y != other.y) return false
        if (actionTree != other.actionTree) return false
        if (speed != other.speed) return false
        if (orientation != other.orientation) return false
        if (peers != other.peers) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + field.hashCode()
        result = 31 * result + x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + actionTree.hashCode()
        result = 31 * result + speed.hashCode()
        result = 31 * result + orientation.hashCode()
        result = 31 * result + peers.hashCode()
        return result
    }
}