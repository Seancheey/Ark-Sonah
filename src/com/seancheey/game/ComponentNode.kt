package com.seancheey.game

import com.seancheey.game.battlefield.Battlefield
import com.seancheey.game.battlefield.EmptyBattlefield

/**
 * Created by Seancheey on 23/05/2017.
 * GitHub: https://github.com/Seancheey
 */
open class ComponentNode protected constructor(open val model: ComponentModel, val gridX: Int, val gridY: Int, val type: ComponentType) : Model by model, Node {
    override final var x: Double = 0.0
        get() = gridX * Config.botGridSize + width / 2
    override final var y: Double = 0.0
        get() = gridY * Config.botGridSize + height / 2
    override final var orientation: Double = 0.0
    override final val peers: ArrayList<Node> = arrayListOf()
    override final val children: ArrayList<Node> = arrayListOf()
    override final var field: Battlefield = EmptyBattlefield()
    override final val actionTree: ActionTree
        get() = ActionTree(this)

    /**
     * create() is used as a factory of ComponentNode
     */
    companion object {
        fun create(componentModel: ComponentModel, gridX: Int, gridY: Int): ComponentNode {
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
                return ComponentNode(componentModel, x, y, ComponentType.movement)
            if (componentModel is WeaponModel)
                return ComponentNode(componentModel, x, y, ComponentType.weapon)
            return ComponentNode(componentModel, x, y, ComponentType.default)
        }
    }

    fun <T> getModel(): T? {
        try {
            @Suppress("UNCHECKED_CAST")
            val mod = model as T
            return mod
        } catch (e: java.lang.ClassCastException) {
            return null
        }
    }
}

