package com.seancheey.game

import javax.json.JsonObject

/**
 * Created by Seancheey on 23/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class WeaponModel(name: String, imageURL: String, health: Int, weight: Int, gridWidth: Int, gridHeight: Int, price: Int, val damage: Int, val range: Int, val frequency: Int) : ComponentModel(name, imageURL, health, weight, gridWidth, gridHeight, price) {

    constructor(defaultModel: ComponentModel, damage: Int, range: Int, frequency: Int) : this(defaultModel.name, defaultModel.imageURL, defaultModel.health, defaultModel.weight, defaultModel.gridWidth, defaultModel.gridHeight, defaultModel.price, damage, range, frequency)

    companion object {
        private val keys = listOf("damage", "range", "frequency")
        fun create(j: JsonObject): WeaponModel? {
            if (!(keys + ComponentModel.requiredKeys).any { j.isNull(it) }) {
                val defaultModel = ComponentModel.create(j)!!
                return WeaponModel(defaultModel, j.getInt(keys[0]), j.getInt(keys[1]), j.getInt(keys[2]))
            }
            return null
        }
    }
}