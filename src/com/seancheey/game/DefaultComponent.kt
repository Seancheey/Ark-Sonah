package com.seancheey.game

import java.io.Serializable

/**
 * Created by Seancheey on 23/05/2017.
 * GitHub: https://github.com/Seancheey
 */
open class DefaultComponent(val model: ComponentModel, var x: Int, var y: Int) : Model by model, Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DefaultComponent) return false

        if (model != other.model) return false
        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = model.hashCode()
        result = 31 * result + x
        result = 31 * result + y
        return result
    }
}
