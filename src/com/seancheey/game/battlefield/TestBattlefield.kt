package com.seancheey.game.battlefield

import com.seancheey.game.model.Node

/**
 * Created by Seancheey on 01/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class TestBattlefield : Battlefield {
    override val nodeAddQueue = arrayListOf<Node>()
    override var name = "Test Battlefield"
    override val width = 2000.0
    override val height = 1200.0
    override val nodes = arrayListOf<Node>()
}