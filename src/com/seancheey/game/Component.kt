package com.seancheey.game

import javafx.scene.image.Image


/**
 * Created by Seancheey on 23/05/2017.
 * GitHub: https://github.com/Seancheey
 */
data class Component<out T : ComponentModel>(val model: T, var x: Int, var y: Int, var hostRobot: RobotModel?) {
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
    val image: Image
        get() = model.image
}