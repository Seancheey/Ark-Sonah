package com.seancheey.game

import com.seancheey.game.battlefield.Battlefield

/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class RobotNode(val model: RobotModel, override var field: Battlefield, override var x: Double, override var y: Double) : RobotModel(model.name, model.components.map { it.copy() }), MovableNode {
    override var focusedByPlayer: Boolean = false
    override var requestDeletion: Boolean = false
    override var acceleration: Double = 0.0
    override var speed: Double = 0.0
    override var orientation: Double = 0.0
        set(value) {
            field = value - 2 * Math.PI * (value / (2 * Math.PI)).toInt()
        }
    override val children: ArrayList<Node> = arrayListOf()
    override val peers: ArrayList<Node> = field.nodes

    init {
        components.filter { it.type == ComponentType.weapon }.forEach { children.add(it) }
    }

    override fun updateFocusedStatus() {
        super.updateFocusedStatus()
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