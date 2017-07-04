package com.seancheey.game.model

import com.seancheey.game.ComponentType
import com.seancheey.game.battlefield.Battlefield

/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class RobotNode(val model: RobotModel, override var field: Battlefield, override var x: Double, override var y: Double) : RobotModel(model.name, model.components.map { it.copy() }), MovableNode {
    override var focusedByPlayer: Boolean = false
        set(value) {
            if (field != value) {
                field = value
                updateFocusedStatus()
            }
        }
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
        if (components.any { it.model.name == "builder block" }) {
            if (focusedByPlayer) {
                children.add(BotSelectNode(field.players[0], field, { selectedModel ->
                    field.putRobot(selectedModel, x, y, 0.0, orientation)
                }))
            } else {
                children.filterIsInstance<BotSelectNode>().forEach { it.requestDeletion = true }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RobotNode) return false
        if (!super.equals(other)) return false

        if (model != other.model) return false
        if (x != other.x) return false
        if (y != other.y) return false
        if (focusedByPlayer != other.focusedByPlayer) return false
        if (requestDeletion != other.requestDeletion) return false
        if (acceleration != other.acceleration) return false
        if (speed != other.speed) return false
        if (orientation != other.orientation) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + model.hashCode()
        result = 31 * result + x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + focusedByPlayer.hashCode()
        result = 31 * result + requestDeletion.hashCode()
        result = 31 * result + acceleration.hashCode()
        result = 31 * result + speed.hashCode()
        result = 31 * result + orientation.hashCode()
        return result
    }

    override fun toString(): String {
        return "RobotNode(x=$x, y=$y, focusedByPlayer=$focusedByPlayer, acceleration=$acceleration, speed=$speed)"
    }
}