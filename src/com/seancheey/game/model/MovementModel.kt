package com.seancheey.game.model

import javax.json.JsonObject
import javax.json.JsonObjectBuilder

/**
 * Created by Seancheey on 23/05/2017.
 * GitHub: https://github.com/Seancheey
 */
class MovementModel(name: String, imageURL: String, health: Int, weight: Int, width: Int, height: Int, price: Int, val force: Double, val turn: Double) : ComponentModel(name, imageURL, health, weight, width, height, price) {
    override fun createJsonBuilder(): JsonObjectBuilder {
        return super.createJsonBuilder()
                .add("force", force)
                .add("turn", turn)
    }

    constructor(defaultModel: ComponentModel, force: Double, turn: Double) : this(defaultModel.name, defaultModel.imageURL, defaultModel.health, defaultModel.weight, defaultModel.gridWidth, defaultModel.gridHeight, defaultModel.price, force, turn)

    companion object {
        private val varList = listOf("force", "turn")
        fun create(j: JsonObject): MovementModel? {
            if (!(varList + ComponentModel.requiredKeys).any { j.isNull(it) }) {
                val defaultModel = ComponentModel.create(j)!!
                return MovementModel(defaultModel, j.getJsonNumber(varList[0]).doubleValue(), j.getJsonNumber(varList[1]).doubleValue())
            }
            return null
        }
    }
}