package com.seancheey.resources

import com.seancheey.game.ComponentModel
import com.seancheey.game.MovementModel
import com.seancheey.game.WeaponModel
import java.io.StringReader
import javax.json.Json
import javax.json.JsonObject


/**
 * Created by Seancheey on 22/05/2017.
 * GitHub: https://github.com/Seancheey
 */
object Models {
    val BLOCKS: List<ComponentModel> = readBlocks(Resources.components_json)
    val MOVEMENTS: List<MovementModel> = readMovements(Resources.components_json)
    val WEAPONS: List<WeaponModel> = listOf()

    fun readData(data: String): JsonObject = Json.createReader((StringReader(data))).readObject()

    private fun readBlocks(data: String): List<ComponentModel> {
        val block_list = arrayListOf<ComponentModel>()
        val obj = readData(data)
        for (o in obj.getJsonArray("blocks")) {
            block_list.add(ComponentModel.create(o.asJsonObject())!!)
        }
        return block_list.toList()
    }

    private fun readMovements(data: String): List<MovementModel> {
        val move_list = arrayListOf<MovementModel>()
        val obj = readData(data)
        for (o in obj.getJsonArray("movements")) {
            move_list.add(MovementModel.create(o.asJsonObject())!!)
        }
        return move_list.toList()
    }
}