package com.seancheey.game

import com.seancheey.game.battlefield.Battlefield
import com.seancheey.game.battlefield.EmptyBattlefield

/**
 * Created by Seancheey on 23/05/2017.
 * GitHub: https://github.com/Seancheey
 */
open class DefaultComponent protected constructor(open val model: ComponentModel, val gridX: Int, val gridY: Int) : Model by model, Node {
    override final var x: Double = gridX * Config.botGridSize
    override final var y: Double = gridY * Config.botGridSize
    override final var orientation: Double = 0.0
    override final val peers: ArrayList<Node> = arrayListOf()
    override final val children: ArrayList<Node> = arrayListOf()
    override final var field: Battlefield = EmptyBattlefield()
    override final val actionTree: ActionTree
        get() = ActionTree(this)

    companion object {
        fun create(componentModel: ComponentModel, gridX: Int, gridY: Int): DefaultComponent {
            if (componentModel is MovementModel)
                return MovementComponent(componentModel, gridX, gridY)
            if (componentModel is WeaponModel)
                return WeaponComponent(componentModel, gridX, gridY)
            return DefaultComponent(componentModel, gridX, gridY)
        }
    }
}