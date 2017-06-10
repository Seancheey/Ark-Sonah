package com.seancheey.game

import javax.json.JsonObject
import javax.json.JsonObjectBuilder

/**
 * Created by Seancheey on 23/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class MovementModel(name: String, imageURL: String, health: Int, weight: Int, width: Int, height: Int, val force: Double, val turn: Double) : ComponentModel(name, imageURL, health, weight, width, height) {
    override fun createJsonBuilder(): JsonObjectBuilder {
        return super.createJsonBuilder()
                .add("force", force)
                .add("turn", turn)
    }

    companion object {
        private val varList = listOf("name", "imageURL", "health", "weight", "gridWidth", "gridHeight", "force", "turn")
        fun create(j: JsonObject): MovementModel? {
            if (!varList.any { j.isNull(it) })
                return MovementModel(j.getString(varList[0]), j.getString(varList[1]), j.getInt(varList[2]), j.getInt(varList[3]), j.getInt(varList[4]), j.getInt(varList[5]), j.getJsonNumber("force").doubleValue(), j.getJsonNumber("turn").doubleValue())
            return null
        }
    }
}