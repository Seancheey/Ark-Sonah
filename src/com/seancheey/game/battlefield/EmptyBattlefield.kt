package com.seancheey.game.battlefield

import com.seancheey.game.model.Node

/**
 * Created by Seancheey on 10/07/2017.
 * GitHub: https://github.com/Seancheey
 */
class EmptyBattlefield : Battlefield {
    override var name: String = ""
    override val width: Double = 0.0
    override val height: Double = 0.0
    override val nodes: ArrayList<Node> = arrayListOf()
}