package com.seancheey.game

import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable

/**
 * Created by Seancheey on 26/05/2017.
 * GitHub: https://github.com/Seancheey
 */

class Player(val id: Long, var name: String, val pass_SHA: ByteArray, val robots: ArrayList<RobotModelGroup>) : Serializable {
    var battleField: BattleField? = null

    constructor(id: Long, name: String, pass_SHA: ByteArray) : this(id, name, pass_SHA, arrayListOf(RobotModelGroup(arrayListOf())))

    fun saveData(path: String = Config.playerSavePath(name)) {
        val fileo = FileOutputStream(path)
        val objecto = ObjectOutputStream(fileo)
        objecto.writeObject(this)
    }
}