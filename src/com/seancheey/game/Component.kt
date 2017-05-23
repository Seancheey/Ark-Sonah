package com.seancheey.game

/**
 * Created by Seancheey on 23/05/2017.
 * GitHub: https://github.com/Seancheey
 */
data class Component<out T : Model>(val model: T, val hostRobot: Robot, var x: Int, var y: Int) {
    val name: String
        get() = model.name
    val width: Int
        get() = model.width
    val height: Int
        get() = model.height
    val health: Int
        get() = model.health
    val weight: Int
        get() = model.weight
}