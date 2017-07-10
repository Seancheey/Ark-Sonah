package com.seancheey.game

import com.seancheey.io.PlayerSavesWriter
import java.io.Serializable

/**
 * Created by Seancheey on 26/05/2017.
 * GitHub: https://github.com/Seancheey
 */
/**
 * @param id identity id used to distinguish different gamePlayers
 * @param name the displayed name of player
 * @param pass_SHA the SHA256 of password used to identify player
 * @param robotGroups all robot models the player has
 */
data class Player(val id: Long, var name: String, val pass_SHA: ByteArray, val robotGroups: ArrayList<RobotModelGroup>) : Serializable {
    val setting: Setting = Setting()

    /**
     * used to initiate a new player
     */
    constructor(id: Long, name: String, pass_SHA: ByteArray) : this(id, name, pass_SHA, arrayListOf(RobotModelGroup(arrayListOf())))

    /**
     * save the player's data into file with path
     */
    fun saveData(path: String = Config.playerSavePath(name)) {
        PlayerSavesWriter(this).write(path)
    }
}
