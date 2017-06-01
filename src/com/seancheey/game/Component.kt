package com.seancheey.game

import java.io.Serializable

/**
 * Created by Seancheey on 23/05/2017.
 * GitHub: https://github.com/Seancheey
 */
data class Component<out T : ComponentModel>(val model: T, var x: Int, var y: Int) : ComponentModel(model.name, model.imageURL, model.health, model.weight, model.gridWidth, model.gridHeight), Serializable
