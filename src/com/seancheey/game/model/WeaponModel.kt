package com.seancheey.game.model

import com.seancheey.game.Action
import com.seancheey.game.ActionTree
import javax.json.JsonObject

/**
 * Created by Seancheey on 23/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class WeaponModel(name: String, imageURL: String, health: Int, weight: Int, gridWidth: Int, gridHeight: Int, price: Int, visualWidth: Int, visualHeight: Int, val damage: Int, val range: Int, val frequency: Int, val turnSpeed: Double) : ComponentModel(name, imageURL, health, weight, gridWidth, gridHeight, price, visualWidth, visualHeight) {
    override val actionTree: ActionTree = ActionTree(Action.SHOOT_ACTION to Action.rotateAction(turnSpeed))

    constructor(defaultModel: ComponentModel, damage: Int, range: Int, frequency: Int, turnSpeed: Double) : this(defaultModel.name, defaultModel.imageURL, defaultModel.health, defaultModel.weight, defaultModel.gridWidth, defaultModel.gridHeight, defaultModel.price, defaultModel.visualWidth, defaultModel.visualHeight, damage, range, frequency, turnSpeed)

    companion object {
        private val keys = listOf("damage", "range", "frequency", "turnSpeed")
        fun create(j: JsonObject): WeaponModel? {
            if (!(keys + ComponentModel.requiredKeys).any { j.isNull(it) }) {
                val defaultModel = ComponentModel.create(j)!!
                return WeaponModel(defaultModel, j.getInt(keys[0]), j.getInt(keys[1]), j.getInt(keys[2]), j.getJsonNumber(keys[3]).doubleValue())
            }
            return null
        }
    }
}