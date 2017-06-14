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

    /**
     * create() is used as a factory of DefaultComponent
     */
    companion object {
        fun create(componentModel: ComponentModel, gridX: Int, gridY: Int): DefaultComponent {
            // limit out bound position
            var x = gridX
            var y = gridY
            val limit = Config.botGridNum
            if (x > limit - componentModel.gridWidth)
                x = limit - componentModel.gridWidth
            if (x < 0)
                x = 0
            if (y > limit - componentModel.gridHeight)
                y = limit - componentModel.gridHeight
            if (y < 0)
                y = 0
            // create component according to type of model
            if (componentModel is MovementModel)
                return MovementComponent(componentModel, x, y)
            if (componentModel is WeaponModel)
                return WeaponComponent(componentModel, x, y)
            return DefaultComponent(componentModel, x, y)
        }
    }
}