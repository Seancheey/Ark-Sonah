package com.seancheey.game

import java.io.Serializable

/**
 * Created by Seancheey on 05/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class ActionTree(val node: Node) : Serializable {
    private val actions: MutableMap<Int, Action> = mutableMapOf()

    companion object {
        val CUSTOM_ACTION = 0
        val MOVE_ACTION = 1
        val SHOOT_ACTION = 2
        val ANIMATION_ACTION = 3
    }

    fun putAction(action: Action, type: Int): Unit {
        actions[type] = action
    }

    fun putAction(action: () -> Unit, type: Int): Unit {
        this.putAction(Action(node, action), type)
    }

    fun executeAll() {
        for (action in actions.values) {
            action.execute()
        }
    }
}