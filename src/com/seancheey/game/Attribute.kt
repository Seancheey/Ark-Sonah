package com.seancheey.game

/**
 * Created by Seancheey on 12/06/2017.
 * GitHub: https://github.com/Seancheey
 */
enum class Attribute(val id: String) {
    weapon_mount("mount"),
    weapon("weapon");

    companion object {
        fun hasAttribute(id: String): Boolean {
            return Attribute.values().any { it.match(id) }
        }

        fun getAttribute(id: String): Attribute? {
            return Attribute.values().firstOrNull { it.match(id) }
        }
    }

    override fun toString(): String {
        return id
    }

    fun match(id: String): Boolean {
        return this.id == id
    }

}