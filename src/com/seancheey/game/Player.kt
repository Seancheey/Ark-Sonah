package com.seancheey.game

import java.io.Serializable

/**
 * Created by Seancheey on 26/05/2017.
 * GitHub: https://github.com/Seancheey
 */

data class Player(val id: Long, var name: String, val robots: ArrayList<RobotModelGroup>) : Serializable {
    constructor(id: Long, name: String) : this(id, name, arrayListOf())
}