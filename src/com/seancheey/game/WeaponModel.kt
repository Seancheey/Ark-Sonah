package com.seancheey.game

import javax.json.JsonObject

/**
 * Created by Seancheey on 23/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class WeaponModel(name: String, imageURL: String, health: Int, weight: Int, width: Int, height: Int, val damage: Int, val range: Int, val frequency: Int) : ComponentModel(name, imageURL, health, weight, width, height) {
    companion object {
        private val varList = listOf("name", "imageURL", "health", "weight", "gridWidth", "gridHeight", "damage", "range", "frequency")
        fun create(j: JsonObject): WeaponModel? {
            if (!varList.any { j.isNull(it) })
                return WeaponModel(j.getString(varList[0]), j.getString(varList[1]), j.getInt(varList[2]), j.getInt(varList[3]), j.getInt(varList[4]), j.getInt(varList[5]), j.getInt(varList[6]), j.getInt(varList[7]), j.getInt(varList[8]))
            return null
        }
    }
}