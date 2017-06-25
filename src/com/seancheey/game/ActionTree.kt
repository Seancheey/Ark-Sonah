package com.seancheey.game

import java.io.Serializable

/**
 * Created by Seancheey on 05/06/2017.
 * GitHub: https://github.com/Seancheey
 */
class ActionTree(private val actions: MutableMap<Int, Action>) : Serializable {

    constructor() : this(mutableMapOf())

    fun putAction(action: Action, type: Int): Unit {
        actions[type] = action
    }

    fun executeAll(node: Node) {
        val toDelete = arrayListOf<Int>()
        for ((key, action) in actions.entries) {
            if (!action.discard) {
                action.execute(node)
            } else {
                toDelete.add(key)
            }
        }
        for (del in toDelete) {
            actions.remove(del)
        }
    }
}