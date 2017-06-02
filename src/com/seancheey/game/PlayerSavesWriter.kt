package com.seancheey.game

import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream

/**
 * Created by Seancheey on 02/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class PlayerSavesWriter(val player: Player) {
    companion object {
        fun writeNewPlayer(name: String, password: String) {
            val player = newPlayer(name, password)
            PlayerSavesWriter(player).write()
        }

        private fun newPlayer(name: String, password: String): Player {
            return Player(name.hashCode().toLong(), name, PlayerSavesReader.encryptPass(password))
        }
    }


    fun write(path: String = Config.playerSavePath(player.name)) {
        val file = File(path)
        file.parentFile.mkdirs()
        val fileo = FileOutputStream(file)
        val objecto = ObjectOutputStream(fileo)
        objecto.writeObject(player)
    }
}