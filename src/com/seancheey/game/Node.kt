package com.seancheey.game

/**
 * Created by Seancheey on 30/05/2017.
 * GitHub: https://github.com/Seancheey
 */
interface Node {
    val x: Int
    val y: Int
    val peers: ArrayList<Node>
    val field: BattleField

    fun update(elapsed: Int)
}