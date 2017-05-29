package com.seancheey.game

import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable

/**
 * Created by Seancheey on 26/05/2017.
 * GitHub: https://github.com/Seancheey
 */

data class Player(val id: Long, var name: String, val robots: ArrayList<RobotModelGroup>) : Serializable {
    constructor(id: Long, name: String) : this(id, name, arrayListOf(RobotModelGroup(arrayListOf())))

    fun saveData(path: String = "dat/player.object") {
        val fileo = FileOutputStream(path)
        val objecto = ObjectOutputStream(fileo)
        objecto.writeObject(this)
    }
}