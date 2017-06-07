package com.seancheey.game

import java.io.Serializable

/**
 * Created by Seancheey on 23/05/2017.
 * GitHub: https://github.com/Seancheey
 */
data class Component(val model: ComponentModel, var x: Int, var y: Int) : Model by model, Serializable
