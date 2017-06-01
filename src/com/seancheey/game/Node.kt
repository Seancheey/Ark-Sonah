package com.seancheey.game


/**
 * Created by Seancheey on 30/05/2017.
 * GitHub: https://github.com/Seancheey
 */
interface Node : Model {
    var x: Double
    var y: Double
    var orientation: Double
    var speed: Double
    val vx: Double
        get() = Math.cos(orientation) * speed
    val vy: Double
        get() = Math.sin(orientation) * speed
    val peers: ArrayList<Node>
    val field: BattleField

    fun update() {
    }
}