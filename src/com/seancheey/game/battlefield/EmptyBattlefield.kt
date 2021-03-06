package com.seancheey.game.battlefield

import com.seancheey.game.model.Node

/**
 * Created by Seancheey on 10/07/2017.
 * GitHub: https://github.com/Seancheey
 */
class EmptyBattlefield : Battlefield {
    override val nodeAddQueue: ArrayList<Node> = arrayListOf()
    override var name: String = ""
    override val width: Double = 0.0
    override val height: Double = 0.0
    override val mutableNodes: ArrayList<Node> = arrayListOf()
}