package com.seancheey.game

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
}