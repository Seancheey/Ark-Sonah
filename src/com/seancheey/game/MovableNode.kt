package com.seancheey.game

/**
 * Created by Seancheey on 06/06/2017.
 * GitHub: https://github.com/Seancheey
 */
interface MovableNode : Node {
    var maxSpeed: Double
    var turnSpeed: Double
    var acceleration: Double
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
}