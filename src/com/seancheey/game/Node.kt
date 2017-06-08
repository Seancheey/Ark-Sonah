package com.seancheey.game

import com.seancheey.game.battlefield.Battlefield


/**
 * Created by Seancheey on 30/05/2017.
 * GitHub: https://github.com/Seancheey
 */
interface Node : Model {
    companion object {
        fun correctArcAngle(arc: Double): Double {
            if (arc > Math.PI * 2) {
                return correctArcAngle(arc - 2 * Math.PI)
            } else if (arc < 0) {
                return correctArcAngle(arc + 2 * Math.PI)
            }
            return arc
        }

        fun minAngleDifference(arc1: Double, arc2: Double): Double {
            val diff0 = arc1 - arc2
            val diff1 = diff0 + 2 * Math.PI
            val diff2 = diff0 - 2 * Math.PI
            val min = minOf(Math.abs(diff0), Math.abs(diff1), Math.abs(diff2))
            when (min) {
                Math.abs(diff0) ->
                    return diff0
                Math.abs(diff1) ->
                    return diff1
                Math.abs(diff2) ->
                    return diff2
                else ->
                    return diff0
            }
        }
    }

    /**
     * center position x
     */
    var x: Double
    /**
     * center position y
     */
    var y: Double
    /**
     * orientation in arc, 0 <= orientation <= 2*PI
     */
    var orientation: Double
    /**
     * absolute speed
     */
    var speed: Double
    /**
     * position change per frame
     */
    val vx: Double
        get() = Math.cos(orientation) * speed
    /**
     * position change per frame
     */
    val vy: Double
        get() = Math.sin(orientation) * speed
    /**
     * peer nodes that this node may affect
     */
    val peers: ArrayList<Node>
    /**
     * children node list
     */
    val children: ArrayList<Node>
    /**
     * the battlefield this node is at
     */
    val field: Battlefield
    /**
     * the action tree that the node will perform each frame
     */
    val actionTree: ActionTree

    /**
     * update function is called each frame to make node perform actions
     */
    fun update() {
        actionTree.executeAll()
    }

    fun containsPoint(pointX: Double, pointY: Double): Boolean {
        if (pointX < x - width / 2 || pointY < y - height / 2 || pointX > x + width / 2 || pointY > y + height / 2) return false
        return true
    }

    fun correctOrientation() {
        orientation = Node.correctArcAngle(orientation)
    }
}