package com.seancheey.game

/**
 * Created by Seancheey on 06/06/2017.
 * GitHub: https://github.com/Seancheey
 */
interface MovableNode : Node {
    /**
     * maximum speed
     */
    val maxSpeed: Double
    /**
     * maximum turning speed
     */
    val turnSpeed: Double
    /**
     * maximum acceleration
     */
    val maxAcceleration: Double
    /**
     * current acceleration
     */
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

    fun stop() {
        acceleration = 0.0
        speed = 0.0
    }

    fun move() {
        acceleration = maxAcceleration
    }
}