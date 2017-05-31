package com.seancheey.game

import com.seancheey.game.action.Action

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
    val actions: ArrayList<Action>

    fun update() {
        for (action in actions) {
            action.perform()
        }
    }
}