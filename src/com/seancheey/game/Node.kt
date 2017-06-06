package com.seancheey.game

import com.seancheey.game.battlefield.Battlefield


/**
 * Created by Seancheey on 30/05/2017.
 * GitHub: https://github.com/Seancheey
 */
interface Node : Model {
    /**
     * position x
     */
    var x: Double
    /**
     * position y
     */
    var y: Double
    /**
     * orientation in arc
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
        if (pointX < x || pointY < y || pointX > x + width || pointY > y + height) return false
        return true
    }
}