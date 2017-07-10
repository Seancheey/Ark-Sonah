package com.seancheey.game.model

import com.seancheey.game.ComponentType
import com.seancheey.game.Config
import com.seancheey.game.battlefield.Battlefield

/**
 * Created by Seancheey on 23/05/2017.
 * GitHub: https://github.com/Seancheey
 */
open class ComponentNode protected constructor(open val model: ComponentModel, val gridX: Int, val gridY: Int, val type: ComponentType, override final var field: Battlefield) : Model by model, Node {
    override var focusedByPlayer: Boolean = false
    override var requestDeletion: Boolean = false
    override final var x: Double = 0.0
        get() = gridX * Config.botGridSize + width / 2 - model.xCorrect
    override final var y: Double = 0.0
        get() = gridY * Config.botGridSize + height / 2 - model.yCorrect
    override final var orientation: Double = 0.0
    override final val peers: ArrayList<Node> = arrayListOf()
    override final val children: ArrayList<Node> = arrayListOf()

    /**
     * create() is used as a factory of ComponentNode
     */
    companion object {
        fun create(componentModel: ComponentModel, gridX: Int, gridY: Int, field: Battlefield): ComponentNode {
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
                return ComponentNode(componentModel, x, y, ComponentType.movement, field)
            if (componentModel is WeaponModel)
                return ComponentNode(componentModel, x, y, ComponentType.weapon, field)
            return ComponentNode(componentModel, x, y, ComponentType.default, field)
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

    fun copy(): ComponentNode {
        return create(model, gridX, gridY, field)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ComponentNode) return false

        if (model != other.model) return false
        if (gridX != other.gridX) return false
        if (gridY != other.gridY) return false
        if (type != other.type) return false
        if (orientation != other.orientation) return false

        return true
    }

    override fun hashCode(): Int {
        var result = model.hashCode()
        result = 31 * result + gridX
        result = 31 * result + gridY
        result = 31 * result + type.hashCode()
        result = 31 * result + orientation.hashCode()
        return result
    }
}

