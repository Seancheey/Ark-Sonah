package com.seancheey.game.model

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
     * position of the node's four edge points
     */
    val leftX: Double
        get() = x - width / 2
    val upperY: Double
        get() = y - height / 2
    val rightX: Double
        get() = x + width / 2
    val lowerY: Double
        get() = y + height / 2
    /**
     * orientation in arc, 0 <= orientation <= 2*PI
     * node with 0 arc orientation is heading towards right (positive x-axis)
     */
    var orientation: Double
    /**
     * orientation in degree
     */
    val degreeOrientation: Double
        get() = orientation * 180 / Math.PI
    /**
     * peer nodes that this node may affect
     */
    val peers: ArrayList<Node>
    /**
     * children node list
     */
    val children: ArrayList<Node>
    /**
     * children for observing only
     * prevent concurrency issue
     */
    val immutableChildren: List<Node>
        get() = children.map { it }
    /**
     * the battlefield this node is at
     */
    var field: Battlefield
    /**
     * set it to true to let gameDirector to remove this node
     */
    var requestDeletion: Boolean
    /**
     * set by GameInspector, used for handling any possible event when focused
     */
    var focusedByPlayer: Boolean

    /**
     * update function is called each frame to make node perform actions
     */
    fun update() {
        children.removeAll(children.filter { it.requestDeletion })
        actionTree.executeAll(this)
        children.forEach { it.update() }
    }

    fun containsPoint(pointX: Double, pointY: Double): Boolean {
        if (pointX < leftX || pointY < upperY || pointX > rightX || pointY > lowerY) return false
        return true
    }

    fun distanceTo(pointX: Double, pointY: Double): Double {
        val dx = pointX - x
        val dy = pointY - y
        return Math.sqrt(dx * dx + dy * dy)
    }

    fun correctOrientation() {
        orientation = correctArcAngle(orientation)
    }

    fun updateFocusedStatus() {}
}