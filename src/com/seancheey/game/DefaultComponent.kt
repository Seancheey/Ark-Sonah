package com.seancheey.game

import com.seancheey.game.battlefield.Battlefield
import com.seancheey.game.battlefield.EmptyBattlefield
import java.io.Serializable

/**
 * Created by Seancheey on 23/05/2017.
 * GitHub: https://github.com/Seancheey
 */
open class DefaultComponent(open val model: ComponentModel, val gridX: Int, val gridY: Int) : Model by model, Node{
    override final var x: Double = gridX * Config.botGridSize
    override final var y: Double = gridY * Config.botGridSize
    override final var orientation: Double = 0.0
    override final val peers: ArrayList<Node> = arrayListOf()
    override final val children: ArrayList<Node> = arrayListOf()
    override final var field: Battlefield = EmptyBattlefield()
    override final val actionTree: ActionTree
        get() = ActionTree(this)
}