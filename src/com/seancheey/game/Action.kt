package com.seancheey.game

import java.io.Serializable


/**
 * Created by Seancheey on 05/06/2017.
 * GitHub: https://github.com/Seancheey
 */

class Action(val node: Node, var execute: () -> Unit) : Serializable {
    var discard: Boolean = false

    companion object {
        val CUSTOM_ACTION = 0
        val MOVE_ACTION = 1
        val SHOOT_ACTION = 2
        val ANIMATION_ACTION = 3
        fun moveAction(node: Node): () -> Unit {
            return {
                node.x += node.vx
                node.y += node.vy
            }
        }

        fun rotateAction(node: Node, speed: Double): () -> Unit {
            return {
                node.orientation += speed
            }
        }

        fun gotoTargetAction(node: Node, x: Double, y: Double): Action {
            val action = Action(node, {})
            action.execute = {
                if (Math.abs(node.x - x) < 1 && Math.abs(node.y - y) < 1) {
                    node.speed = 0.0
                    action.discard = true
                } else {
                    node.orientation = Math.atan2((y - node.y), (x - node.x))
                }
            }
            return action
        }
    }
}
