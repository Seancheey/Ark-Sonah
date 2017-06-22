package com.seancheey.game

import java.io.Serializable


/**
 * Created by Seancheey on 05/06/2017.
 * GitHub: https://github.com/Seancheey
 */

class Action(var execute: (Node) -> Unit) : Serializable {


    var discard: Boolean = false

    companion object {
        val CUSTOM_ACTION = 0
        val MOVE_ACTION = 1
        val SHOOT_ACTION = 2
        val ANIMATION_ACTION = 3


        fun moveAction() = Action({
            node ->
            if (node is MovableNode) {
                node.x += node.vx
                node.y += node.vy
                if (node.speed < node.maxSpeed) {
                    node.speed += node.acceleration
                } else {
                    node.speed = node.maxSpeed
                }
            }
        })

        fun rotateAction(speed: Double) = Action({ node ->
            node.orientation += speed
            node.correctOrientation()
        })


        fun gotoTargetAction(x: Double, y: Double): Action {
            val action = Action({})
            action.execute = { node ->
                if (node is MovableNode) {
                    val rotateAction = rotateToAngleAction(node.turnSpeed, Math.atan2((y - node.y), (x - node.x)))
                    rotateAction.execute(node)
                    if (rotateAction.discard) {
                        if (Math.abs(node.x - x) <= node.vx || Math.abs(node.y - y) <= node.vy) {
                            node.stop()
                            action.discard = true
                        } else {
                            node.orientation = Math.atan2((y - node.y), (x - node.x))
                        }
                    }
                }
            }
            return action
        }

        fun rotateToAngleAction(rotateSpeed: Double, targetOrientation: Double): Action {
            val action = Action({})
            action.execute = { node ->
                var diff = Node.minAngleDifference(targetOrientation, node.orientation)
                if (Math.abs(diff) < rotateSpeed) {
                    action.discard = true
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
            return action
        }
    }
}
