package com.seancheey.game

import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.ObjectInputStream
import java.security.MessageDigest
import java.util.*


/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class PlayerSavesReader(val name: String, val pass: String) {
    var objin: ObjectInputStream? = null
    var hasSaves: Boolean = true
    private val player: Player?

    init {
        try {
            objin = ObjectInputStream(FileInputStream("${Config.playerSaveDir}$name.object"))
        } catch (e: FileNotFoundException) {
            hasSaves = false
        }
        player = objin?.readObject() as Player?
    }

    fun passwordCorrect(): Boolean {
        return Arrays.equals(player?.pass_SHA, toSHA256(pass))
    }

    fun readPlayer(): Player? {
        if (passwordCorrect())
            return player
        else
            return null
    }

    fun newPlayer(): Player {
        return Player(name.hashCode().toLong(), name, toSHA256(pass))
    }


    private fun toSHA256(str: String): ByteArray {
        val md = MessageDigest.getInstance("SHA-256")
        md.update(str.toByteArray(charset("UTF-8")))
        return md.digest()
    }
}