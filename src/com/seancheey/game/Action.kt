package com.seancheey.game

import java.io.Serializable


/**
 * Created by Seancheey on 05/06/2017.
 * GitHub: https://github.com/Seancheey
 */

class Action(val node: Node, var execute: () -> Unit) : Serializable {
    companion object {
        fun moveAction(node: Node): () -> Unit {
            val action: () -> Unit = {
                node.x += node.vx
                node.y += node.vy
            }
            return action
        }

        fun rotateAction(node: Node, speed: Double): () -> Unit {
            val action: () -> Unit = {
                node.orientation += speed
            }
            return action
        }
    }
}
