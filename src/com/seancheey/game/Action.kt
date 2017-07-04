package com.seancheey.game

import com.seancheey.game.model.MovableNode
import com.seancheey.game.model.Node
import java.io.Serializable


/**
 * Created by Seancheey on 05/06/2017.
 * GitHub: https://github.com/Seancheey
 */

abstract class Action : Serializable {
    var discard: Boolean = false
    abstract fun execute(node: Node): Unit


    companion object {
        val CUSTOM_ACTION = 0
        val MOVE_ACTION = 1
        val SHOOT_ACTION = 2
        val ANIMATION_ACTION = 3


        fun moveAction(): Action = object : Action() {
            override fun execute(node: Node) {
                if (node is MovableNode) {
                    node.x += node.vx
                    node.y += node.vy
                    if (node.speed < node.maxSpeed) {
                        node.speed += node.acceleration
                    } else {
                        node.speed = node.maxSpeed
                    }
                }
            }
        }

        fun rotateAction(speed: Double) = object : Action() {
            override fun execute(node: Node) {
                node.orientation += speed
                node.correctOrientation()
            }
        }


        fun gotoTargetAction(x: Double, y: Double): Action = object : Action() {
            override fun execute(node: Node) {
                if (node is MovableNode) {
                    val rotateAction = Math.atan2((y - node.y), (x - node.x)).rotateToAngleAction(node.turnSpeed)
                    rotateAction.execute(node)
                    if (rotateAction.discard) {
                        if (Math.abs(node.x - x) <= node.vx || Math.abs(node.y - y) <= node.vy) {
                            node.stop()
                            discard = true
                        } else {
                            node.orientation = Math.atan2((y - node.y), (x - node.x))
                        }
                    }
                }
            }
        }

        fun Double.rotateToAngleAction(rotateSpeed: Double): Action = object : Action() {
            override fun execute(node: Node) {
                var diff = Node.minAngleDifference(this@rotateToAngleAction, node.orientation)
                if (Math.abs(diff) < rotateSpeed) {
                    discard = true
                } else {
                    if (diff > 0) {
                        node.orientation += rotateSpeed
                        diff -= rotateSpeed
                    } else {
                        node.orientation -= rotateSpeed
                        diff += rotateSpeed
                    }
                }
            }
        }
    }
}
