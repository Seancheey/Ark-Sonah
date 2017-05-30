package com.seancheey.game

import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.ObjectInputStream

/**
 * Created by Seancheey on 29/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class PlayerSavesReader(path: String) {
    var objin: ObjectInputStream? = null

    init {
        try {
            objin = ObjectInputStream(FileInputStream(path))
        } catch (e: FileNotFoundException) {
        }
    }

    fun readPlayer(): Player? {
        return objin?.readObject() as Player?
    }

}