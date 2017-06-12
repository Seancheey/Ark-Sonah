package com.seancheey.game

import com.seancheey.game.battlefield.Battlefield
import com.seancheey.io.PlayerSavesWriter
import java.io.Serializable

/**
 * Created by Seancheey on 26/05/2017.
 * GitHub: https://github.com/Seancheey
 */

class Player(val id: Long, var name: String, val pass_SHA: ByteArray, val robotGroups: ArrayList<RobotModelGroup>) : Serializable {
    var battlefield: Battlefield? = null

    constructor(id: Long, name: String, pass_SHA: ByteArray) : this(id, name, pass_SHA, arrayListOf(RobotModelGroup(arrayListOf())))

    fun saveData(path: String = Config.playerSavePath(name)) {
        PlayerSavesWriter(this).write(path)
    }
}
